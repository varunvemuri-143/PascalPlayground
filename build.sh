#!/usr/bin/env bash
set -euo pipefail

echo "Cleaning…"
rm -rf generated bin output

echo "1) Regenerate parser"
pushd grammar >/dev/null
java -jar ../libs/antlr-4.13.2-complete.jar \
  -Dlanguage=Java -visitor -package generated -o ../generated delphi.g4
popd >/dev/null

echo "2) Compile Java"
mkdir -p bin
javac -cp ".:libs/antlr-4.13.2-complete.jar" -d bin \
    generated/*.java \
    src/main/*.java

echo "3) Emit LLVM IR for all tests"
mkdir -p output
java -cp ".:libs/antlr-4.13.2-complete.jar:bin" \
    main.DelphiToLLVM Tests_final/*.pas

echo "Done!  .ll files in ./output"
