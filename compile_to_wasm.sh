#!/usr/bin/env bash
set -euo pipefail

# 1) Make sure you have LLVM + wasm toolchain installed:
#    e.g. brew install llvm wasm-ld wasmtime
#    and add LLVM to your PATH:
#    export PATH="/usr/local/opt/llvm/bin:$PATH"

# 2) For every .ll in ./output, compile to a .wasm:
for ll in output/*.ll; do
  base=$(basename "$ll" .ll)
  echo "--- $base.ll → $base.wasm ---"

  # (a) emit a Wasm object file:
  llc -march=wasm32 -filetype=obj "$ll" -o "output/$base.o"

  # (b) link into a .wasm (no start, export all symbols):
  wasm-ld \
    --no-entry \
    --export-all \
    --allow-undefined \
    -o "output/$base.wasm" \
    "output/$base.o"
done

echo "All .wasm modules are in ./output/*.wasm"

# 3) Instructions for running the WebAssembly files:
echo
echo "To run a WebAssembly module with the JavaScript runtime, use:"
echo "./run_wasm.sh <test_name>"
echo "Example: ./run_wasm.sh test_encapsulation"
echo
echo "This uses the JavaScript runtime in wasm_runtime.js to provide"
echo "implementations for printInt, printString, and readInt functions."
