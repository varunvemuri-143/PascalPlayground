#!/usr/bin/env bash
set -euo pipefail

if [ $# -ne 1 ] || [[ "$1" != *.pas ]]; then
  echo "Usage: $0 <source>.pas"
  exit 1
fi

SRC="$1"
BASE="$(basename "${SRC%.*}")"

echo "Cleaning…"
rm -rf generated bin output

echo "1) Regenerate parser"
pushd grammar >/dev/null
java -jar ../libs/antlr-4.13.2-complete.jar \
  -Dlanguage=Java \
  -visitor \
  -package generated \
  -o ../generated \
  delphi.g4
popd >/dev/null

echo "2) Compile Java"
mkdir -p bin
javac -cp ".:libs/antlr-4.13.2-complete.jar" -d bin \
    generated/*.java \
    src/main/*.java

echo "3) Emit LLVM IR for ${SRC}"
mkdir -p output
java -cp ".:libs/antlr-4.13.2-complete.jar:bin" \
    main.DelphiToLLVM "${SRC}"

echo "Done!  LLVM IR for ${SRC} → output/${BASE}.ll"
