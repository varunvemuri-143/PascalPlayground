// Pascal to WebAssembly Player
document.addEventListener('DOMContentLoaded', function() {
    // DOM elements
    const wasmSelect = document.getElementById('wasmSelect');
    const runButton = document.getElementById('runButton');
    const clearButton = document.getElementById('clearButton');
    const outputPanel = document.getElementById('output');
    const loader = document.getElementById('loader');
    const programDescription = document.getElementById('programDescription');
    
    // Available WASM programs with descriptions
    const programs = [
        { 
            id: 'test_break_continue',
            name: 'Break & Continue',
            description: 'Demonstrates the use of break and continue statements in loops.'
        },
        { 
            id: 'test_classes',
            name: 'Basic Classes',
            description: 'Shows basic class definitions and instantiation.'
        },
        { 
            id: 'test_const_prop',
            name: 'Constant Propagation',
            description: 'Demonstrates compile-time constant propagation optimization.'
        },
        { 
            id: 'test_constructors_destructors',
            name: 'Constructors & Destructors',
            description: 'Demonstrates class constructors and destructors usage.'
        },
        { 
            id: 'test_encapsulation',
            name: 'Encapsulation',
            description: 'Shows public and private fields with class encapsulation.'
        },
        { 
            id: 'test_for',
            name: 'For Loops',
            description: 'Demonstrates for loop control structures.'
        },
        { 
            id: 'test_inheritance',
            name: 'Inheritance',
            description: 'Shows class inheritance and method overriding.'
        },
        { 
            id: 'test_interfaces',
            name: 'Interfaces',
            description: 'Demonstrates interfaces and polymorphism.'
        },
        { 
            id: 'test_parameters',
            name: 'Parameters',
            description: 'Shows parameter passing to procedures and functions.'
        },
        { 
            id: 'test_procs_funcs',
            name: 'Procedures & Functions',
            description: 'Demonstrates the use of procedures and functions.'
        },
        { 
            id: 'test_scope',
            name: 'Variable Scope',
            description: 'Shows variable scope rules and lifetime.'
        },
        { 
            id: 'test_while',
            name: 'While Loops',
            description: 'Demonstrates while loop control structures.'
        },
        { 
            id: 'test_widget',
            name: 'Widget Class',
            description: 'Advanced class example with a Widget implementation.'
        }
    ];
    
    // Global state
    let currentWasmModule = null;
    let wasmInstance = null;
    let wasmMemory = null;
    let heapOffset = 0;
    
    // Populate the dropdown
    function populateDropdown() {
        programs.forEach(program => {
            const option = document.createElement('option');
            option.value = program.id;
            option.textContent = program.name;
            wasmSelect.appendChild(option);
        });
    }
    
    // Helpers
    function readCString(ptr) {
        if (!wasmMemory) return "";
        
        const memory = new Uint8Array(wasmMemory.buffer);
        let str = "";
        let i = ptr;
        
        while (memory[i] !== 0) {
            str += String.fromCharCode(memory[i]);
            i++;
        }
        
        return str;
    }
    
    // Clear console output
    function clearOutput() {
        outputPanel.textContent = '';
        clearButton.disabled = true;
    }
    
    // Create imports for WebAssembly
    function createImports() {
        return {
            env: {
                printInt: function(value) {
                    outputPanel.textContent += value + '\n';
                    clearButton.disabled = false;
                },
                
                printString: function(ptr) {
                    const str = readCString(ptr);
                    outputPanel.textContent += str + '\n';
                    clearButton.disabled = false;
                },
                
                readInt: function() {
                    // In a real application, this would prompt the user
                    const input = prompt('Enter an integer:', '42');
                    return parseInt(input || '42', 10);
                },
                
                malloc: function(size) {
                    // Simple bump allocator
                    const result = heapOffset;
                    heapOffset += size;
                    // 8-byte align
                    heapOffset = (heapOffset + 7) & ~7;
                    return result;
                }
            }
        };
    }
    
    // Load and compile WebAssembly module
    async function loadWasmModule(programId) {
        try {
            showLoading(true);
            
            // Reset state
            wasmInstance = null;
            wasmMemory = null;
            heapOffset = 0;
            
            // Fetch the wasm file
            const response = await fetch(`output/${programId}.wasm`);
            if (!response.ok) {
                throw new Error(`Failed to load WebAssembly module: ${response.status} ${response.statusText}`);
            }
            
            const wasmBytes = await response.arrayBuffer();
            
            // Compile and instantiate the module
            const result = await WebAssembly.instantiate(wasmBytes, createImports());
            
            // Save references
            wasmInstance = result.instance;
            wasmMemory = wasmInstance.exports.memory;
            
            // Update UI
            runButton.disabled = false;
            
            // Find and display program description
            const program = programs.find(p => p.id === programId);
            if (program) {
                programDescription.textContent = program.description;
                programDescription.classList.remove('hidden');
            }
            
            return true;
        } catch (error) {
            console.error('Error loading WebAssembly module:', error);
            outputPanel.textContent = `Error loading module: ${error.message}`;
            clearButton.disabled = false;
            
            // Update UI
            runButton.disabled = true;
            programDescription.classList.add('hidden');
            
            return false;
        } finally {
            showLoading(false);
        }
    }
    
    // Run the WebAssembly module
    async function runWasmModule() {
        if (!wasmInstance) return;
        
        try {
            showLoading(true);
            
            // Reset the heap offset before each run
            heapOffset = 0;
            
            // Clear previous output
            clearOutput();
            
            // Call the main function
            const result = wasmInstance.exports.main();
            
            // Display return code
            outputPanel.textContent += `\nProgram exited with code: ${result}\n`;
            clearButton.disabled = false;
        } catch (error) {
            console.error('Error running WebAssembly module:', error);
            outputPanel.textContent += `\nRuntime error: ${error.message}\n`;
            clearButton.disabled = false;
        } finally {
            showLoading(false);
        }
    }
    
    // Show/hide loading indicator
    function showLoading(isLoading) {
        if (isLoading) {
            loader.classList.remove('hidden');
            runButton.disabled = true;
        } else {
            loader.classList.add('hidden');
            runButton.disabled = !wasmInstance;
        }
    }
    
    // Event listeners
    wasmSelect.addEventListener('change', async () => {
        const programId = wasmSelect.value;
        if (programId) {
            await loadWasmModule(programId);
        }
    });
    
    runButton.addEventListener('click', runWasmModule);
    
    clearButton.addEventListener('click', clearOutput);
    
    // Initial setup
    populateDropdown();
    
    // Console message
    console.log('Pascal to WebAssembly player initialized.');
}); 