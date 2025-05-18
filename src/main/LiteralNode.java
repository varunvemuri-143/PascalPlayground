package main;

public class LiteralNode extends ASTNode {
    public int value;
    
    public LiteralNode(int value) {
        this.value = value;
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
