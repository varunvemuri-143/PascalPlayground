#!/bin/bash
set -e

if [ -z "$1" ]; then
  echo "Usage: $0 <test_name>"
  echo "Example: $0 test_encapsulation"
  exit 1
fi

TEST_NAME="$1"
WASM_FILE="output/${TEST_NAME}.wasm"

if [ ! -f "$WASM_FILE" ]; then
  echo "Error: WebAssembly file not found: $WASM_FILE"
  echo "Try running ./build.sh and ./compile_to_wasm.sh first."
  exit 1
fi

# Check if Node.js is installed
if ! command -v node >/dev/null 2>&1; then
  echo "Error: Node.js is required to run WebAssembly files with the runtime."
  echo "Please install Node.js and try again."
  exit 1
fi

echo "Running $WASM_FILE with the JavaScript runtime..."
node wasm_runtime.js "$WASM_FILE" 