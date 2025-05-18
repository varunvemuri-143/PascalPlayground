package main;

public class ASTPrinter implements ASTVisitor<String> {

    private int indent = 0;

    private String getIndent() {
        return " ".repeat(indent);
    }

    @Override
    public String visit(BinaryOpNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append(getIndent()).append("BinaryOp(").append(node.op).append(")\n");
        indent += 2;
        sb.append(node.left.accept(this));
        sb.append(node.right.accept(this));
        indent -= 2;
        return sb.toString();
    }

    @Override
    public String visit(LiteralNode node) {
        return getIndent() + "Literal(" + node.value + ")\n";
    }

    @Override
    public String visit(VariableNode node) {
        return getIndent() + "Variable(" + node.name + ")\n";
    }

    @Override
    public String visit(AssignmentNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append(getIndent()).append("Assignment\n");
        indent += 2;
        sb.append(getIndent()).append("Variable:\n");
        indent += 2;
        sb.append(node.variable.accept(this));
        indent -= 2;
        sb.append(getIndent()).append("Expression:\n");
        indent += 2;
        sb.append(node.expression.accept(this));
        indent -= 4;
        return sb.toString();
    }

    @Override
    public String visit(CompoundStatementNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append(getIndent()).append("CompoundStatement\n");
        indent += 2;
        for (ASTNode stmt : node.statements) {
            sb.append(stmt.accept(this));
        }
        indent -= 2;
        return sb.toString();
    }
}
