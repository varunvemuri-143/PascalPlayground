// Generated from delphi.g4 by ANTLR 4.13.2
package generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link delphiParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface delphiVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link delphiParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(delphiParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(delphiParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarations(delphiParser.DeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#varDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclarationPart(delphiParser.VarDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(delphiParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#typeDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationPart(delphiParser.TypeDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(delphiParser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#type_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_(delphiParser.Type_Context ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(delphiParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(delphiParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#inheritanceList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInheritanceList(delphiParser.InheritanceListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(delphiParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#visibilitySection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVisibilitySection(delphiParser.VisibilitySectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#classMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassMember(delphiParser.ClassMemberContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclaration(delphiParser.ConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#destructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestructorDeclaration(delphiParser.DestructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceDeclaration(delphiParser.InterfaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#interfaceBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceBody(delphiParser.InterfaceBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#interfaceMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMember(delphiParser.InterfaceMemberContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#procFuncDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcFuncDeclaration(delphiParser.ProcFuncDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureDeclaration(delphiParser.ProcedureDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(delphiParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(delphiParser.FormalParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameter(delphiParser.FormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(delphiParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#statementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementList(delphiParser.StatementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(delphiParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(delphiParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#procFuncCallStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcFuncCallStatement(delphiParser.ProcFuncCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(delphiParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(delphiParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(delphiParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(delphiParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(delphiParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(delphiParser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(delphiParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(delphiParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#relationalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(delphiParser.RelationalExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#additiveExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(delphiParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(delphiParser.MultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#unaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(delphiParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#primaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(delphiParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(delphiParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(delphiParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#variableReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableReference(delphiParser.VariableReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(delphiParser.IdentifierListContext ctx);
}