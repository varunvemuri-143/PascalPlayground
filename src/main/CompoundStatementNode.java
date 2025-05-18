package main;

import java.util.ArrayList;
import java.util.List;

public class CompoundStatementNode extends ASTNode {
    public List<ASTNode> statements = new ArrayList<>();
    
    public void addStatement(ASTNode stmt) {
        statements.add(stmt);
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
