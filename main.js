// List all your test‐names here (without extension)
const TESTS = [
    "test_break_continue",
    "test_classes",
    "test_const_prop",
    "test_constructors_destructors",
    "test_encapsulation",
    "test_for",
    "test_inheritance",
    "test_interfaces",
    "test_parameters",
    "test_procs_funcs",
    "test_scope",
    "test_while",
    "test_widget"
  ];
  
  const selectEl = document.getElementById("testSelect");
  const runBtn   = document.getElementById("run");
  const outDiv   = document.getElementById("output");
  
  // Populate dropdown
  for (const name of TESTS) {
    const opt = document.createElement("option");
    opt.value = name;
    opt.textContent = name;
    selectEl.appendChild(opt);
  }
  
  // A little helper to read a zero‐terminated C‐string out of a WebAssembly.Memory
  function readStringFromMemory(mem, ptr) {
    const bytes = new Uint8Array(mem.buffer, ptr);
    let len = 0;
    while (bytes[len] !== 0) len++;
    return new TextDecoder("utf8").decode(bytes.subarray(0, len));
  }
  
  runBtn.addEventListener("click", async () => {
    outDiv.textContent = "";               // clear old output
    const testName = selectEl.value;
    const wasmUrl  = `output/${testName}.wasm`;
  
    // create a fresh Memory for each run:
    const memory = new WebAssembly.Memory({ initial: 10 });
  
    // set up the imports your generated module expects:
    const imports = {
      env: {
        memory,
        malloc: (size) => {                     // stubbed allocator
          console.warn("malloc(",size,") called but not implemented");
          return 0;
        },
        readInt: () => 0,                       // no stdin
        printInt: (i32) => {
          outDiv.textContent += i32 + "\n";
        },
        printString: (ptr) => {
          const s = readStringFromMemory(memory, ptr);
          outDiv.textContent += s;
        }
      }
    };
  
    // fetch + instantiate the .wasm
    const resp = await fetch(wasmUrl);
    const { instance } = await WebAssembly.instantiateStreaming(resp, imports);
  
    // run its `main()` and show the return code
    const ret = instance.exports.main();
    outDiv.textContent += `\n(main returned ${ret})\n`;
  });
  