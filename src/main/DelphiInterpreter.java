package main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import generated.delphiLexer;
import generated.delphiParser;
import java.util.List;

public class DelphiInterpreter {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: java main.DelphiInterpreter <filename>");
            return;
        }
        
        // --- Original Interpretation Pipeline ---
        delphiLexer lexer = new delphiLexer(CharStreams.fromFileName(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        delphiParser parser = new delphiParser(tokens);
        delphiParser.ProgramContext tree = parser.program();
        
        DelphiInterpreterVisitor visitor = new DelphiInterpreterVisitor();
        visitor.getSymbolTable().setVariable("writeln", new Callable() {
            @Override
            public Object call(List<Object> args) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < args.size(); i++) {
                    sb.append(args.get(i));
                    if (i < args.size() - 1) sb.append(" ");
                }
                System.out.println(sb);
                return null;
            }
        });
        visitor.visit(tree);
        
        // --- AST + Constant Folding Pipeline (guarded) ---
        try {
            ASTBuilder astBuilder = new ASTBuilder();
            ASTNode ast = astBuilder.visit(tree);
            ConstantFolder folder = new ConstantFolder();
            ASTNode folded = ast.accept(folder);
            // Only print if there is at least one assignment in the AST
            if (folded instanceof CompoundStatementNode &&
                !((CompoundStatementNode)folded).statements.isEmpty()) {
                ASTPrinter printer = new ASTPrinter();
                String out = folded.accept(printer);
                System.out.println("Folded AST:\n" + out);
            }
        } catch (NullPointerException e) {
            // No assignments/statements to fold — skip AST printing
        }
    }
}
