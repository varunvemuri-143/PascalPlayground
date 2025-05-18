package main;

import generated.delphiBaseVisitor;
import generated.delphiParser;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.ArrayList;
import java.util.List;

public class DelphiInterpreterVisitor extends delphiBaseVisitor<Object> {

    private SymbolTable symbolTable = new SymbolTable();
    private String currentClassName = null;

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public Object visitProgram(delphiParser.ProgramContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Object visitAssignmentStatement(delphiParser.AssignmentStatementContext ctx) {
        List<TerminalNode> ids = ctx.variableReference().IDENTIFIER();
        if (ids.size() == 2) {
            String objName = ids.get(0).getText();
            String fieldName = ids.get(1).getText();
            Object leftVal = symbolTable.getVariable(objName);
            if (leftVal instanceof DelphiObject) {
                DelphiObject obj = (DelphiObject) leftVal;
                DelphiClass cls = obj.getClassDefinition();
                DelphiClass.Visibility vis = cls.getFieldVisibility(fieldName);
                boolean allowed = (vis == DelphiClass.Visibility.PUBLIC)
                    || (currentClassName != null && currentClassName.equals(cls.getName()));
                if (!allowed) {
                    System.err.println("Access violation: cannot assign to " + fieldName);
                    return null;
                }
            }
        }
        String varName = ctx.variableReference().getText();
        Object value = evaluateExpression(ctx.expression());
        symbolTable.updateVariable(varName, value);
        return null;
    }

    @Override
    public Object visitClassDeclaration(delphiParser.ClassDeclarationContext ctx) {
        String className = ctx.IDENTIFIER().getText();
        currentClassName = className;
        DelphiClass newClass = new DelphiClass(className);

        if (ctx.inheritanceList() != null) {
            String parentName = ctx.inheritanceList().IDENTIFIER(0).getText();
            DelphiClass parentClass = symbolTable.getClass(parentName);
            if (parentClass != null) {
                newClass.setParent(parentClass);
            }
        }
        symbolTable.setClass(className, newClass);

        for (delphiParser.VisibilitySectionContext vsCtx : ctx.classBody().visibilitySection()) {
            DelphiClass.Visibility vis = switch (vsCtx.getChild(0).getText()) {
                case "public" -> DelphiClass.Visibility.PUBLIC;
                case "protected" -> DelphiClass.Visibility.PROTECTED;
                default -> DelphiClass.Visibility.PRIVATE;
            };
            for (delphiParser.ClassMemberContext mCtx : vsCtx.classMember()) {
                if (mCtx.varDeclaration() != null) {
                    for (TerminalNode id : mCtx.varDeclaration().identifierList().IDENTIFIER()) {
                        newClass.addField(id.getText(), vis, 0);
                    }
                }
            }
        }
        visit(ctx.classBody());
        currentClassName = null;
        return null;
    }

    @Override
    public Object visitConstructorDeclaration(delphiParser.ConstructorDeclarationContext ctx) {
        String methodName = (ctx.IDENTIFIER() != null) ? ctx.IDENTIFIER().getText() : "constructor";
        Method cons = new Method() {
            @Override
            public void invoke(DelphiObject instance) {
                System.out.println("Constructor called for object of class " +
                                   instance.getClassDefinition().getName());
            }
        };
        if (currentClassName != null) {
            DelphiClass currentClass = symbolTable.getClass(currentClassName);
            if (currentClass != null) {
                currentClass.setConstructor(cons);
            }
        }
        return null;
    }

    @Override
    public Object visitDestructorDeclaration(delphiParser.DestructorDeclarationContext ctx) {
        String methodName = (ctx.IDENTIFIER() != null) ? ctx.IDENTIFIER().getText() : "destructor";
        Method dest = new Method() {
            @Override
            public void invoke(DelphiObject instance) {
                System.out.println("Destructor called for object of class " +
                                   instance.getClassDefinition().getName());
            }
        };
        if (currentClassName != null) {
            DelphiClass currentClass = symbolTable.getClass(currentClassName);
            if (currentClass != null) {
                currentClass.setDestructor(dest);
            }
        }
        return null;
    }

    @Override
    public Object visitProcedureDeclaration(delphiParser.ProcedureDeclarationContext ctx) {
        String procName = ctx.IDENTIFIER().getText();
        List<String> params = new ArrayList<>();
        if (ctx.formalParameters() != null) {
            for (delphiParser.FormalParameterContext fpCtx : ctx.formalParameters().formalParameter()) {
                for (TerminalNode idNode : fpCtx.identifierList().IDENTIFIER()) {
                    params.add(idNode.getText());
                }
            }
        }
        delphiParser.CompoundStatementContext body = ctx.compoundStatement();

        if (currentClassName != null) {
            DelphiClass currentClass = symbolTable.getClass(currentClassName);
            Method methodImpl = new Method() {
                @Override
                public void invoke(DelphiObject instance) {
                    symbolTable.pushScope();
                    try {
                        visit(body);
                    } finally {
                        symbolTable.popScope();
                    }
                }
            };
            currentClass.addMethod(procName, methodImpl);
            return null;
        }

        ProcedureDef procDef = new ProcedureDef(params, body, this);
        symbolTable.setVariable(procName, procDef);
        return null;
    }

    @Override
    public Object visitFunctionDeclaration(delphiParser.FunctionDeclarationContext ctx) {
        String funcName = ctx.IDENTIFIER().getText();
        List<String> params = new ArrayList<>();
        if (ctx.formalParameters() != null) {
            for (delphiParser.FormalParameterContext fpCtx : ctx.formalParameters().formalParameter()) {
                for (TerminalNode idNode : fpCtx.identifierList().IDENTIFIER()) {
                    params.add(idNode.getText());
                }
            }
        }
        delphiParser.CompoundStatementContext body = ctx.compoundStatement();
        FunctionDef funcDef = new FunctionDef(funcName, params, body, this);
        symbolTable.setVariable(funcName, funcDef);
        return null;
    }

    @Override
    public Object visitProcFuncCallStatement(delphiParser.ProcFuncCallStatementContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Object callable = symbolTable.getVariable(name);
        if (callable instanceof Callable) {
            List<Object> args = new ArrayList<>();
            if (ctx.argumentList() != null) {
                for (int i = 0; i < ctx.argumentList().getChildCount(); i++) {
                    if (ctx.argumentList().getChild(i) instanceof delphiParser.ExpressionContext) {
                        Object argVal = evaluateExpression((delphiParser.ExpressionContext) ctx.argumentList().getChild(i));
                        args.add(argVal);
                    }
                }
            }
            ((Callable) callable).call(args);
        } else {
            System.err.println("Procedure/Function " + name + " not defined.");
        }
        return null;
    }

    @Override
    public Object visitIfStatement(delphiParser.IfStatementContext ctx) {
        Object cond = evaluateExpression(ctx.expression());
        if (cond instanceof Integer && ((Integer) cond) != 0) {
            return visit(ctx.statement(0));
        } else if (ctx.ELSE() != null) {
            return visit(ctx.statement(1));
        }
        return null;
    }

    @Override
    public Object visitWhileStatement(delphiParser.WhileStatementContext ctx) {
        while ((Integer) evaluateExpression(ctx.expression()) != 0) {
            symbolTable.pushScope();
            try {
                visit(ctx.statement());
            } catch (BreakException be) {
                symbolTable.popScope();
                break;
            } catch (ContinueException ce) {
                symbolTable.popScope();
                continue;
            }
            symbolTable.popScope();
        }
        return null;
    }

    @Override
    public Object visitForStatement(delphiParser.ForStatementContext ctx) {
        String loopVar = ctx.IDENTIFIER().getText();
        int start = (Integer) evaluateExpression(ctx.expression(0));
        int end = (Integer) evaluateExpression(ctx.expression(1));
        boolean ascending = ctx.getChild(4).getText().equalsIgnoreCase("to");
        for (int iVal = start; ascending ? iVal <= end : iVal >= end; iVal += (ascending ? 1 : -1)) {
            symbolTable.pushScope();
            symbolTable.setVariable(loopVar, iVal);
            try {
                visit(ctx.statement());
            } catch (BreakException be) {
                symbolTable.popScope();
                break;
            } catch (ContinueException ce) {
                symbolTable.popScope();
                continue;
            }
            symbolTable.popScope();
        }
        symbolTable.setVariable(loopVar, ascending ? end + 1 : end - 1);
        return null;
    }

    @Override
    public Object visitBreakStatement(delphiParser.BreakStatementContext ctx) {
        throw new BreakException();
    }

    @Override
    public Object visitContinueStatement(delphiParser.ContinueStatementContext ctx) {
        throw new ContinueException();
    }

    @Override
    public Object visitMethodCall(delphiParser.MethodCallContext ctx) {
        visitMethodCallExpr(ctx);
        return null;
    }

    public Object visitMethodCallExpr(delphiParser.MethodCallContext ctx) {
        String objName = ctx.IDENTIFIER(0).getText();
        String methodName = ctx.IDENTIFIER(1).getText();
        Object leftVal = symbolTable.getVariable(objName);
        if (!(leftVal instanceof DelphiObject)) {
            System.err.println("Not an object: " + objName);
            return null;
        }
        DelphiObject obj = (DelphiObject) leftVal;
        if (obj.isDestroyed()) {
            System.err.println("Error: Object " + objName + " has been destroyed. Cannot invoke method " + methodName);
            return null;
        }
        DelphiClass clazz = obj.getClassDefinition();
        if ("Init".equals(methodName)) {
            Method constructor = clazz.getConstructor();
            if (constructor != null) {
                constructor.invoke(obj);
            } else {
                System.err.println("No constructor found for class " + clazz.getName());
            }
        } else if ("Destroy".equals(methodName)) {
            Method destructor = clazz.getDestructor();
            if (destructor != null) {
                destructor.invoke(obj);
                obj.setDestroyed(true);
            } else {
                System.err.println("No destructor found for class " + clazz.getName());
            }
        } else {
            Method m = clazz.getMethod(methodName);
            if (m != null) {
                m.invoke(obj);
            } else {
                System.err.println("No method " + methodName + " found in class " + clazz.getName());
            }
        }
        return null;
    }

    private Object evaluateExpression(delphiParser.ExpressionContext ctx) {
        return new ExpressionEvaluator().visit(ctx);
    }

    private class ExpressionEvaluator extends delphiBaseVisitor<Object> {

        @Override
        public Object visitExpression(delphiParser.ExpressionContext ctx) {
            return visit(ctx.relationalExpr());
        }

        @Override
        public Object visitRelationalExpr(delphiParser.RelationalExprContext ctx) {
            if (ctx.getChildCount() == 1) {
                return visit(ctx.getChild(0));
            }
            Object leftVal = visit(ctx.getChild(0));
            String op = ctx.getChild(1).getText();
            Object rightVal = visit(ctx.getChild(2));
            if (leftVal instanceof Integer && rightVal instanceof Integer) {
                int l = (Integer) leftVal;
                int r = (Integer) rightVal;
                return switch (op) {
                    case "=" -> (l == r) ? 1 : 0;
                    case "<>" -> (l != r) ? 1 : 0;
                    case "<" -> (l < r) ? 1 : 0;
                    case "<=" -> (l <= r) ? 1 : 0;
                    case ">" -> (l > r) ? 1 : 0;
                    case ">=" -> (l >= r) ? 1 : 0;
                    default -> 0;
                };
            }
            return 0;
        }

        @Override
        public Object visitAdditiveExpr(delphiParser.AdditiveExprContext ctx) {
            Object result = visit(ctx.getChild(0));
            for (int i = 1; i < ctx.getChildCount(); i += 2) {
                String op = ctx.getChild(i).getText();
                Object right = visit(ctx.getChild(i + 1));
                if (result instanceof Integer && right instanceof Integer) {
                    int leftInt = (Integer) result;
                    int rightInt = (Integer) right;
                    if (op.equals("+")) {
                        result = leftInt + rightInt;
                    } else if (op.equals("-")) {
                        result = leftInt - rightInt;
                    }
                } else {
                    System.err.println("Arithmetic on non-integer values not supported.");
                    result = 0;
                }
            }
            return result;
        }

        @Override
        public Object visitMultiplicativeExpr(delphiParser.MultiplicativeExprContext ctx) {
            Object result = visit(ctx.getChild(0));
            for (int i = 1; i < ctx.getChildCount(); i += 2) {
                String op = ctx.getChild(i).getText();
                Object right = visit(ctx.getChild(i + 1));
                if (result instanceof Integer && right instanceof Integer) {
                    int leftInt = (Integer) result;
                    int rightInt = (Integer) right;
                    if (op.equals("*")) {
                        result = leftInt * rightInt;
                    } else if (op.equalsIgnoreCase("div")) {
                        result = leftInt / rightInt;
                    } else if (op.equalsIgnoreCase("mod")) {
                        result = leftInt % rightInt;
                    }
                } else {
                    System.err.println("Arithmetic on non-integer values not supported.");
                    result = 0;
                }
            }
            return result;
        }

        @Override
        public Object visitUnaryExpr(delphiParser.UnaryExprContext ctx) {
            if (ctx.getChildCount() == 1) {
                return visit(ctx.getChild(0));
            }
            String sign = ctx.getChild(0).getText();
            Object value = visit(ctx.getChild(1));
            if (value instanceof Integer) {
                int val = (Integer) value;
                return sign.equals("-") ? -val : val;
            }
            return value;
        }

        @Override
        public Object visitPrimaryExpr(delphiParser.PrimaryExprContext ctx) {
            if (ctx.INT_LITERAL() != null) {
                return Integer.parseInt(ctx.INT_LITERAL().getText());
            }
            if (ctx.STRING_LITERAL() != null) {
                String text = ctx.STRING_LITERAL().getText();
                return text.substring(1, text.length() - 1);
            }
            if (ctx.functionCall() != null) {
                String name = ctx.functionCall().IDENTIFIER().getText();
                if (symbolTable.getClass(name) != null) {
                    DelphiClass clazz = symbolTable.getClass(name);
                    DelphiObject newObj = new DelphiObject(clazz);
                    return newObj;
                }
                return visitFunctionCallExpr(ctx.functionCall());
            }
            if (ctx.methodCall() != null) {
                return visitMethodCallExpr(ctx.methodCall());
            }
            if (ctx.variableReference() != null) {
                return symbolTable.getVariable(ctx.variableReference().getText());
            }
            if (ctx.expression() != null) {
                return visit(ctx.expression());
            }
            return 0;
        }

        private Object visitFunctionCallExpr(delphiParser.FunctionCallContext ctx) {
            String name = ctx.IDENTIFIER().getText();
            Object callable = symbolTable.getVariable(name);
            if (callable instanceof Callable) {
                List<Object> args = new ArrayList<>();
                if (ctx.argumentList() != null) {
                    for (int i = 0; i < ctx.argumentList().getChildCount(); i++) {
                        if (ctx.argumentList().getChild(i) instanceof delphiParser.ExpressionContext) {
                            Object argVal = visit((delphiParser.ExpressionContext) ctx.argumentList().getChild(i));
                            args.add(argVal);
                        }
                    }
                }
                return ((Callable) callable).call(args);
            } else {
                System.err.println("Function " + name + " not defined.");
                return 0;
            }
        }
    }
}
