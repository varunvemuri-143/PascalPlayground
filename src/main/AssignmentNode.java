package main;

public class AssignmentNode extends ASTNode {
    public VariableNode variable;
    public ASTNode expression;
    
    public AssignmentNode(VariableNode variable, ASTNode expression) {
        this.variable = variable;
        this.expression = expression;
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
