package main;

import generated.delphiBaseVisitor;
import generated.delphiParser;

public class ASTBuilder extends delphiBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(delphiParser.ProgramContext ctx) {
        // PROGRAM IDENTIFIER SEMI block DOT
        return visit(ctx.block());
    }

    @Override
    public ASTNode visitBlock(delphiParser.BlockContext ctx) {
        // block: declarations compoundStatement
        return visit(ctx.compoundStatement());
    }

    @Override
    public ASTNode visitCompoundStatement(delphiParser.CompoundStatementContext ctx) {
        // BEGIN statementList END
        CompoundStatementNode compound = new CompoundStatementNode();
        if (ctx.statementList() != null) {
            // visitStatementList returns a CompoundStatementNode whose .statements holds each ASTNode
            CompoundStatementNode listNode =
                (CompoundStatementNode) visit(ctx.statementList());
            for (ASTNode stmt : listNode.statements) {
                compound.addStatement(stmt);
            }
        }
        return compound;
    }

    @Override
    public ASTNode visitStatementList(delphiParser.StatementListContext ctx) {
        // statementList: (statement (SEMI statement)*)?
        CompoundStatementNode temp = new CompoundStatementNode();
        if (ctx.statement() != null) {
            for (delphiParser.StatementContext sctx : ctx.statement()) {
                ASTNode node = visit(sctx);
                if (node != null) {
                    temp.addStatement(node);
                }
            }
        }
        return temp;
    }

    @Override
    public ASTNode visitStatement(delphiParser.StatementContext ctx) {
        if (ctx.assignmentStatement() != null) {
            return visit(ctx.assignmentStatement());
        }
        // skip other statements (loops, proc calls, etc.) for constant propagation
        return null;
    }

    @Override
    public ASTNode visitAssignmentStatement(delphiParser.AssignmentStatementContext ctx) {
        VariableNode varNode = new VariableNode(ctx.variableReference().getText());
        ASTNode exprNode = visit(ctx.expression());
        return new AssignmentNode(varNode, exprNode);
    }

    @Override
    public ASTNode visitExpression(delphiParser.ExpressionContext ctx) {
        return visit(ctx.relationalExpr());
    }

    @Override
    public ASTNode visitRelationalExpr(delphiParser.RelationalExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.getChild(0));
        }
        ASTNode left = visit(ctx.getChild(0));
        String op = ctx.getChild(1).getText();
        ASTNode right = visit(ctx.getChild(2));
        return new BinaryOpNode(op, left, right);
    }

    @Override
    public ASTNode visitAdditiveExpr(delphiParser.AdditiveExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.getChild(0));
        }
        ASTNode left = visit(ctx.getChild(0));
        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String op = ctx.getChild(i).getText();
            ASTNode right = visit(ctx.getChild(i + 1));
            left = new BinaryOpNode(op, left, right);
        }
        return left;
    }

    @Override
    public ASTNode visitMultiplicativeExpr(delphiParser.MultiplicativeExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.getChild(0));
        }
        ASTNode left = visit(ctx.getChild(0));
        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String op = ctx.getChild(i).getText();
            ASTNode right = visit(ctx.getChild(i + 1));
            left = new BinaryOpNode(op, left, right);
        }
        return left;
    }

    @Override
    public ASTNode visitUnaryExpr(delphiParser.UnaryExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.getChild(0));
        }
        String op = ctx.getChild(0).getText();
        ASTNode operand = visit(ctx.getChild(1));
        // Represent unary minus as 0 - operand
        return new BinaryOpNode(op, new LiteralNode(0), operand);
    }

    @Override
    public ASTNode visitPrimaryExpr(delphiParser.PrimaryExprContext ctx) {
        if (ctx.INT_LITERAL() != null) {
            int val = Integer.parseInt(ctx.INT_LITERAL().getText());
            return new LiteralNode(val);
        }
        if (ctx.variableReference() != null) {
            return new VariableNode(ctx.variableReference().getText());
        }
        if (ctx.expression() != null) {
            return visit(ctx.expression());
        }
        return null;
    }
}
