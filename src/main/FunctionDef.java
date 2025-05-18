package main;

import java.util.List;
import generated.delphiParser;

public class FunctionDef implements Callable {
    private String funcName;  // the function's own identifier
    private List<String> parameters;
    private delphiParser.CompoundStatementContext body;
    private DelphiInterpreterVisitor visitor;
    
    // The environment in which the function was declared.
    private Environment definingEnv;

    public FunctionDef(String funcName,
                       List<String> parameters,
                       delphiParser.CompoundStatementContext body,
                       DelphiInterpreterVisitor visitor) {
        this.funcName = funcName;
        this.parameters = parameters;
        this.body = body;
        this.visitor = visitor;
        this.definingEnv = visitor.getSymbolTable().currentEnvironment();
    }

    @Override
    public Object call(List<Object> args) {
        // 1) Push a new scope that chains to the defining environment for static scoping.
        visitor.getSymbolTable().pushScope(definingEnv);

        // 2) Bind parameters in this new scope.
        for (int i = 0; i < parameters.size(); i++) {
            Object arg = (i < args.size()) ? args.get(i) : 0;
            visitor.getSymbolTable().setVariable(parameters.get(i), arg);
        }

        // 3) Initialize the function's own name as 0 (or any default) for the return value.
        //    E.g. in Delphi/Turbo Pascal: MyFunction := 0
        visitor.getSymbolTable().setVariable(funcName, 0);

        // 4) Execute the function body. The body can do "funcName := <some value>" to set the result.
        visitor.visit(body);

        // 5) Retrieve the final value of "funcName", which is the returned result.
        Object resultVal = visitor.getSymbolTable().getVariable(funcName);

        // 6) Pop the scope.
        visitor.getSymbolTable().popScope();

        // 7) Return the function's value to the caller.
        return resultVal;
    }
}
