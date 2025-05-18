package main;

public abstract class ASTNode {
    public abstract <T> T accept(ASTVisitor<T> visitor);
}
