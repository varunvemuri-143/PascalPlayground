package main;

public class VariableNode extends ASTNode {
    public String name;
    
    public VariableNode(String name) {
        this.name = name;
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
