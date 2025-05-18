package main;

public class BinaryOpNode extends ASTNode {
    public String op;
    public ASTNode left;
    public ASTNode right;
    
    public BinaryOpNode(String op, ASTNode left, ASTNode right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
