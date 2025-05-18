// File: src/main/java/main/DelphiToLLVM.java
package main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import generated.delphiLexer;
import generated.delphiParser;

/**
 * Entry point: takes one or more .pas files on the command line,
 * parses each with ANTLR4, runs the LLVMGenerator visitor,
 * and emits a same‐named .ll file under ./output/.
 */
public class DelphiToLLVM {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java main.DelphiToLLVM <file1.pas> [file2.pas ...]");
            System.exit(1);
        }

        // 1) Prepare output directory
        java.io.File outDir = new java.io.File("output");
        if (!outDir.exists() && !outDir.mkdirs()) {
            System.err.println("Error: could not create output directory");
            System.exit(2);
        }

        // 2) Loop over each .pas file
        for (String path : args) {
            if (!path.toLowerCase().endsWith(".pas")) {
                System.err.println("Skipping non-.pas file: " + path);
                continue;
            }
            try {
                // 2a) Lex & parse
                CharStream cs    = CharStreams.fromFileName(path);
                delphiLexer   lexer  = new delphiLexer(cs);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                delphiParser  parser = new delphiParser(tokens);
                ParseTree     tree   = parser.program();

                // 2b) Generate LLVM IR
                LLVMGenerator gen = new LLVMGenerator();
                String        ll  = gen.compile(tree);

                // 2c) Compute output filename
                String base = new java.io.File(path)
                                  .getName()
                                  .replaceFirst("(?i)\\.pas$", "");
                java.io.File outFile = new java.io.File(outDir, base + ".ll");

                // 2d) Write to disk
                try (java.io.FileWriter fw = new java.io.FileWriter(outFile)) {
                    fw.write(ll);
                }
                System.out.println("Wrote LLVM IR to " + outFile.getPath());

            } catch (java.io.IOException io) {
                System.err.println("I/O error on " + path + ": " + io.getMessage());
            } catch (Exception ex) {
                System.err.println("Error processing " + path + ": " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
