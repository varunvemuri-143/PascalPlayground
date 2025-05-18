package main;

public class ConstantFolder implements ASTVisitor<ASTNode> {

    @Override
    public ASTNode visit(BinaryOpNode node) {
        ASTNode leftFolded = node.left.accept(this);
        ASTNode rightFolded = node.right.accept(this);
        if (leftFolded instanceof LiteralNode && rightFolded instanceof LiteralNode) {
            int leftVal = ((LiteralNode) leftFolded).value;
            int rightVal = ((LiteralNode) rightFolded).value;
            int result;
            switch (node.op) {
                case "+":
                    result = leftVal + rightVal;
                    break;
                case "-":
                    result = leftVal - rightVal;
                    break;
                case "*":
                    result = leftVal * rightVal;
                    break;
                case "div":
                case "/":
                    result = leftVal / rightVal;
                    break;
                case "mod":
                    result = leftVal % rightVal;
                    break;
                default:
                    return new BinaryOpNode(node.op, leftFolded, rightFolded);
            }
            return new LiteralNode(result);
        }
        return new BinaryOpNode(node.op, leftFolded, rightFolded);
    }

    @Override
    public ASTNode visit(LiteralNode node) {
        return node;
    }

    @Override
    public ASTNode visit(VariableNode node) {
        return node;
    }

    @Override
    public ASTNode visit(AssignmentNode node) {
        ASTNode foldedExpr = node.expression.accept(this);
        return new AssignmentNode(node.variable, foldedExpr);
    }

    @Override
    public ASTNode visit(CompoundStatementNode node) {
        CompoundStatementNode newCompound = new CompoundStatementNode();
        for (ASTNode stmt : node.statements) {
            newCompound.addStatement(stmt.accept(this));
        }
        return newCompound;
    }
}
