# Delphi Pascal Interpreter 

This project implements an interpreter for a subset of Delphi (Object Pascal) using ANTLR4 and Java.

---

## 🚀 How to Run

### 1. Generate the Parser
```
antlr4 -Dlanguage=Java grammar/delphi.g4 -visitor -o generated
```

### 2. Compile Java Code
```
javac -cp ".:libs/antlr-4.13.2-complete.jar" src/main/*.java
```

### 3. Run a Test
```
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/<test_file>.pas
```

---

## 📁 Project 1

### ✅ Implemented Features
- Classes & Objects
- Constructors and Destructors
- Encapsulation (public/private/protected)

### ⭐ Bonus Features
- Inheritance
- Interfaces

### ▶️ Test Cases
```bash
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_classes.pas
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_constructors_destructors.pas
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_encapsulation.pas
# BONUS
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_inheritance.pas   # (BONUS)
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_interfaces.pas    # (BONUS)
```

---

## 📁 Project 2

### ✅ Implemented Features
- Loops: `while-do`, `for-do`
- Control Flow: `break`, `continue`
- User-defined procedures and functions
- Static scoping implementation
- AST generation and printing

### ⭐ Bonus Features

#### 1. Constant Propagation (AST Optimization)
Constant expressions like `v = 2 * (10 + 11)` are precomputed during AST construction and simplified to `v = 42`. This optimization improves efficiency by evaluating constant expressions during interpretation.

The output includes a **Folded AST**, showing simplified expressions:
```
Folded AST:
  Assignment
    Variable: v
    Expression: Literal(42)
```

#### 2. Formal Parameter Passing
Procedures and functions now accept parameters. These are passed and evaluated correctly with scoping:
- Each procedure/function creates a local scope.
- Only global variables + local parameters are visible inside.
- Helps avoid variable shadowing issues.

---

### ▶️ Test Cases
```bash
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_while.pas
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_for.pas
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_break_continue.pas
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_procs_funcs.pas
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_scope.pas
# BONUS
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_const_prop.pas    # (BONUS)
java -cp ".:libs/antlr-4.13.2-complete.jar" main.DelphiInterpreter Tests_final/test_parameters.pas    # (BONUS)
```

---

## 📁 Project 3

### ✅ Implemented Features
- LLVM IR Generation from Pascal AST
- WebAssembly compilation from LLVM IR
- Class implementation with proper inheritance and encapsulation
- Function and procedure parameter handling
- Web interface for interactive WebAssembly execution

### 🔧 How to Generate LLVM IR
```bash
# Use the build script to generate LLVM IR for all test files at once
./build.sh
```

This will generate `.ll` files for all test cases in the Tests_final directory.

### 🔨 How to Compile to WebAssembly
```bash
# Use the provided script to compile the LLVM IR to WebAssembly
./compile_to_wasm.sh test_inheritance
```

This will generate both a `.wasm` file and JavaScript runtime components in the `output` directory.

### 🌐 How to Run in Browser
To view and run the compiled WebAssembly programs:
1. Open the `index.html` file using a live server
2. Select a test program from the dropdown menu
3. Click "Run Program" to execute and see output in real-time

You can use any live server extension in your code editor (like Live Server in VS Code) 
or simply open the index.html file directly in your browser.

### 📝 Key Improvements
- Fixed function parameter handling in LLVM IR generation
- Implemented proper scope handling for variables
- Added support for class field access in methods
- Created a JavaScript runtime for WebAssembly execution
- Developed a modern web interface for program execution

All test cases now compile correctly to LLVM IR and WebAssembly, preserving the semantics of the original Pascal programs.

---

