grammar delphi;

/*
 * PARSER RULES
 */

program
    : PROGRAM IDENTIFIER SEMI block DOT
    ;

block
    : declarations compoundStatement
    ;

declarations
    : ( varDeclarationPart
      | typeDeclarationPart
      | procFuncDeclaration
      | typeDeclaration
      )*
    ;

// ----------------- Variable Declarations -----------------
varDeclarationPart
    : VAR (varDeclaration SEMI)+
    ;

varDeclaration
    : identifierList COLON type_
    ;

// ----------------- Type Declarations -----------------
typeDeclarationPart
    : TYPE (typeDeclaration SEMI)+
    ;

typeDeclaration
    : classDeclaration
    | interfaceDeclaration
    | IDENTIFIER EQUAL type_
    ;

type_
    : baseType
    | IDENTIFIER
    ;

baseType
    : INTEGER
    | REAL
    | STRING
    | BOOLEAN
    ;

// ----------------- Class Declaration -----------------
classDeclaration
    : IDENTIFIER EQUAL CLASS (LPAREN inheritanceList RPAREN)? classBody END
    ;

inheritanceList
    : IDENTIFIER (COMMA IDENTIFIER)*
    ;

classBody
    : visibilitySection (SEMI visibilitySection)* SEMI?
    ;

visibilitySection
    : (PUBLIC | PRIVATE | PROTECTED) COLON classMember+
    ;

classMember
    : varDeclaration SEMI
    | constructorDeclaration
    | destructorDeclaration
    | procFuncDeclaration
    ;

constructorDeclaration
    : CONSTRUCTOR IDENTIFIER? (LPAREN formalParameters? RPAREN)? SEMI
    ;

destructorDeclaration
    : DESTRUCTOR IDENTIFIER? (LPAREN formalParameters? RPAREN)? SEMI
    ;

// ----------------- Interface Declaration -----------------
interfaceDeclaration
    : IDENTIFIER EQUAL INTERFACE (LPAREN inheritanceList RPAREN)? interfaceBody END
    ;

interfaceBody
    : interfaceMember+
    ;

interfaceMember
    : functionDeclaration
    | procedureDeclaration
    ;

// ----------------- Procedures and Functions -----------------
procFuncDeclaration
    : procedureDeclaration
    | functionDeclaration
    ;

procedureDeclaration
    : PROCEDURE IDENTIFIER (LPAREN formalParameters? RPAREN)? SEMI compoundStatement SEMI?
    ;

functionDeclaration
    : FUNCTION IDENTIFIER (LPAREN formalParameters? RPAREN)? COLON type_ SEMI compoundStatement SEMI?
    ;

formalParameters
    : formalParameter (SEMI formalParameter)*
    ;

formalParameter
    : identifierList COLON type_
    ;

// ----------------- Compound Statements -----------------
compoundStatement
    : BEGIN statementList END
    ;

statementList
    : (statement (SEMI statement)*)?
    ;

statement
    : compoundStatement
    | assignmentStatement
    | procFuncCallStatement
    | methodCall
    | ifStatement
    | whileStatement
    | forStatement
    | breakStatement
    | continueStatement
    | emptyStatement
    ;

assignmentStatement
    : variableReference ASSIGN expression
    ;

procFuncCallStatement
    : IDENTIFIER (LPAREN argumentList? RPAREN)?
    ;

whileStatement
    : WHILE expression DO statement
    ;

forStatement
    : FOR IDENTIFIER ASSIGN expression (TO | DOWNTO) expression DO statement
    ;

breakStatement
    : BREAK
    ;

continueStatement
    : CONTINUE
    ;

ifStatement
    : IF expression THEN statement (ELSE statement)?
    ;

emptyStatement
    : // empty
    ;

// ----------------- Expressions -----------------
argumentList
    : expression (COMMA expression)*
    ;

expression
    : relationalExpr
    ;

relationalExpr
    : additiveExpr ( (EQUAL | NOTEQUAL | LT | LE | GT | GE) additiveExpr )*
    ;

additiveExpr
    : multiplicativeExpr ( (PLUS | MINUS) multiplicativeExpr )*
    ;

multiplicativeExpr
    : unaryExpr ( (STAR | DIV | MOD) unaryExpr )*
    ;

unaryExpr
    : (PLUS | MINUS)? primaryExpr
    ;

// --- Primary Expression ---
primaryExpr
    : INT_LITERAL
    | STRING_LITERAL
    | TRUE
    | FALSE
    | methodCall
    | functionCall
    | variableReference
    | LPAREN expression RPAREN
    ;

// A plain function call: IDENTIFIER LPAREN argumentList? RPAREN
functionCall
    : IDENTIFIER LPAREN argumentList? RPAREN
    ;

// Unified method call: IDENTIFIER.DOT.IDENTIFIER(...) e.g. MyObj.DoSomething(...)
methodCall
    : IDENTIFIER DOT IDENTIFIER LPAREN argumentList? RPAREN
    ;

// ----------------- Variable References -----------------
variableReference
    : IDENTIFIER (DOT IDENTIFIER)*
    ;

// ----------------- Helpers -----------------
identifierList
    : IDENTIFIER (COMMA IDENTIFIER)*
    ;

/*
 * LEXER RULES
 */

PROGRAM       : 'program';
VAR           : 'var';
TYPE          : 'type';
CLASS         : 'class';
INTERFACE     : 'interface';
PUBLIC        : 'public';
PRIVATE       : 'private';
PROTECTED     : 'protected';
CONSTRUCTOR   : 'constructor';
DESTRUCTOR    : 'destructor';
PROCEDURE     : 'procedure';
FUNCTION      : 'function';
BEGIN         : 'begin';
END           : 'end';
IF            : 'if';
THEN          : 'then';
ELSE          : 'else';
WHILE         : 'while';
DO            : 'do';
FOR           : 'for';
TO            : 'to';
DOWNTO        : 'downto';
BREAK         : 'break';
CONTINUE      : 'continue';
TRUE          : 'true';
FALSE         : 'false';
MOD           : 'mod';
DIV           : 'div';
INTEGER       : 'integer';
REAL          : 'real';
STRING        : 'string';
BOOLEAN       : 'boolean';

SEMI          : ';';
COLON         : ':';
COMMA         : ',';
DOT           : '.';
LPAREN        : '(';
RPAREN        : ')';
ASSIGN        : ':=';

EQUAL         : '=';
NOTEQUAL      : '<>';
LT            : '<';
LE            : '<=';
GT            : '>';
GE            : '>=';

PLUS          : '+';
MINUS         : '-';
STAR          : '*';

WS
    : [ \t\r\n]+ -> skip
    ;

INT_LITERAL
    : DIGIT+
    ;

STRING_LITERAL
    : '\'' ( ~'\'' )* '\''
    ;

IDENTIFIER
    : LETTER (LETTER | DIGIT | '_')*
    ;

fragment DIGIT : [0-9];
fragment LETTER : [a-zA-Z];
