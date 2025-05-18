package main;

public interface ASTVisitor<T> {
    T visit(BinaryOpNode node);
    T visit(LiteralNode node);
    T visit(VariableNode node);
    T visit(AssignmentNode node);
    T visit(CompoundStatementNode node);
}
