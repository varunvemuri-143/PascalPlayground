#!/usr/bin/env bash
set -euo pipefail

# Usage: ./compile_to_wasm.sh [<file.ll>]
#   with no args → compile every output/*.ll
#   with one arg → compile only that .ll (basename or full path)

if [[ $# -gt 1 ]]; then
  echo "Usage: $0 [<file.ll>]" >&2
  exit 1
fi

# determine list of .ll files to process
if [[ $# -eq 1 ]]; then
  # allow passing either "foo.ll" or "foo"
  arg="$1"
  [[ "$arg" != *.ll ]] && arg="$arg.ll"
  files=( "output/$(basename "$arg")" )
else
  files=( output/*.ll )
fi

echo
echo "→ Compiling to WebAssembly..."
for ll in "${files[@]}"; do
  if [[ ! -f "$ll" ]]; then
    echo "⚠️  Skipping missing file: $ll"
    continue
  fi
  base=$(basename "$ll" .ll)
  echo "--- $base.ll → $base.wasm ---"
  # (a) .ll → Wasm object
  llc -march=wasm32 -filetype=obj "$ll" -o "output/$base.o"
  # (b) link object → final .wasm
  wasm-ld \
    --no-entry \
    --export-all \
    --allow-undefined \
    -o "output/$base.wasm" \
    "output/$base.o"
done

echo
echo "✅ All done. WebAssembly modules in output/*.wasm"

# if user asked for just one, run it under wasmtime
if [[ $# -eq 1 ]]; then
  base=$(basename "${files[0]}" .ll)
  echo
  echo "▶ Running output/$base.wasm with wasmtime:"
  wasmtime "output/$base.wasm"
fi
