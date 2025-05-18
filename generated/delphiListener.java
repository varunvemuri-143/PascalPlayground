// Generated from delphi.g4 by ANTLR 4.13.2
package generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link delphiParser}.
 */
public interface delphiListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link delphiParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(delphiParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(delphiParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(delphiParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(delphiParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#declarations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarations(delphiParser.DeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#declarations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarations(delphiParser.DeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#varDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclarationPart(delphiParser.VarDeclarationPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#varDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclarationPart(delphiParser.VarDeclarationPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(delphiParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(delphiParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#typeDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationPart(delphiParser.TypeDeclarationPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#typeDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationPart(delphiParser.TypeDeclarationPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(delphiParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(delphiParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#type_}.
	 * @param ctx the parse tree
	 */
	void enterType_(delphiParser.Type_Context ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#type_}.
	 * @param ctx the parse tree
	 */
	void exitType_(delphiParser.Type_Context ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(delphiParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(delphiParser.BaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(delphiParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(delphiParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#inheritanceList}.
	 * @param ctx the parse tree
	 */
	void enterInheritanceList(delphiParser.InheritanceListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#inheritanceList}.
	 * @param ctx the parse tree
	 */
	void exitInheritanceList(delphiParser.InheritanceListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(delphiParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(delphiParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#visibilitySection}.
	 * @param ctx the parse tree
	 */
	void enterVisibilitySection(delphiParser.VisibilitySectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#visibilitySection}.
	 * @param ctx the parse tree
	 */
	void exitVisibilitySection(delphiParser.VisibilitySectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#classMember}.
	 * @param ctx the parse tree
	 */
	void enterClassMember(delphiParser.ClassMemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#classMember}.
	 * @param ctx the parse tree
	 */
	void exitClassMember(delphiParser.ClassMemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(delphiParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(delphiParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#destructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterDestructorDeclaration(delphiParser.DestructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#destructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitDestructorDeclaration(delphiParser.DestructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDeclaration(delphiParser.InterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDeclaration(delphiParser.InterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBody(delphiParser.InterfaceBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBody(delphiParser.InterfaceBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#interfaceMember}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMember(delphiParser.InterfaceMemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#interfaceMember}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMember(delphiParser.InterfaceMemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#procFuncDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcFuncDeclaration(delphiParser.ProcFuncDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#procFuncDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcFuncDeclaration(delphiParser.ProcFuncDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDeclaration(delphiParser.ProcedureDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDeclaration(delphiParser.ProcedureDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(delphiParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(delphiParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(delphiParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(delphiParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(delphiParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(delphiParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(delphiParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(delphiParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#statementList}.
	 * @param ctx the parse tree
	 */
	void enterStatementList(delphiParser.StatementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#statementList}.
	 * @param ctx the parse tree
	 */
	void exitStatementList(delphiParser.StatementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(delphiParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(delphiParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(delphiParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(delphiParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#procFuncCallStatement}.
	 * @param ctx the parse tree
	 */
	void enterProcFuncCallStatement(delphiParser.ProcFuncCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#procFuncCallStatement}.
	 * @param ctx the parse tree
	 */
	void exitProcFuncCallStatement(delphiParser.ProcFuncCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(delphiParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(delphiParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(delphiParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(delphiParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(delphiParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(delphiParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(delphiParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(delphiParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(delphiParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(delphiParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(delphiParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(delphiParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(delphiParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(delphiParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(delphiParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(delphiParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(delphiParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(delphiParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(delphiParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(delphiParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(delphiParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(delphiParser.MultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(delphiParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(delphiParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(delphiParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(delphiParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(delphiParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(delphiParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(delphiParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(delphiParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#variableReference}.
	 * @param ctx the parse tree
	 */
	void enterVariableReference(delphiParser.VariableReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#variableReference}.
	 * @param ctx the parse tree
	 */
	void exitVariableReference(delphiParser.VariableReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(delphiParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(delphiParser.IdentifierListContext ctx);
}