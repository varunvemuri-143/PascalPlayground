// WebAssembly runtime environment for Pascal-to-LLVM IR compiler

// Helper to convert WebAssembly memory pointer to JavaScript string
function wasmPtrToString(memory, ptr) {
  let str = "";
  let i = ptr;
  let currentChar = memory[i];
  
  while (currentChar !== 0) {
    str += String.fromCharCode(currentChar);
    i++;
    currentChar = memory[i];
  }
  
  return str;
}

// Create the environment with functions imported by our WASM modules
const env = {
  printInt: function(value) {
    console.log(value);
  },
  
  printString: function(ptr) {
    const memory = new Uint8Array(this.memory.buffer);
    const str = wasmPtrToString(memory, ptr);
    console.log(str);
  },
  
  readInt: function() {
    // In a browser environment, this would use prompt()
    // For now, just return 42 as a default value
    return 42;
  },
  
  // We'll add the memory instance at runtime
  memory: null
};

// Function to load and run a WebAssembly module
async function runWasm(wasmPath) {
  try {
    // Read the wasm file
    const response = await fetch(wasmPath);
    const buffer = await response.arrayBuffer();
    
    // Create a memory instance for the module
    const memory = new WebAssembly.Memory({ initial: 256, maximum: 256 });
    env.memory = memory;
    
    // Instantiate the WebAssembly module
    const { instance } = await WebAssembly.instantiate(buffer, { env });
    
    // Call the main function
    const result = instance.exports.main();
    console.log("Program exited with code:", result);
    
    return result;
  } catch (error) {
    console.error("Error running WebAssembly module:", error);
    return -1;
  }
}

// If this script is run directly (e.g., with Node.js)
if (typeof require !== 'undefined') {
  if (process.argv.length < 3) {
    console.error("Usage: node wasm_runtime.js <path_to_wasm_file>");
    process.exit(1);
  }
  
  const fs = require('fs');
  const wasmPath = process.argv[2];
  
  // In Node.js we need to polyfill fetch
  global.fetch = async function(path) {
    return {
      arrayBuffer: async function() {
        return fs.readFileSync(path);
      }
    };
  };
  
  runWasm(wasmPath).then(code => {
    process.exit(code);
  });
} 