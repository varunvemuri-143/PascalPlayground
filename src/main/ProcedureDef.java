package main;

import java.util.List;
import generated.delphiParser;

public class ProcedureDef implements Callable {
    private List<String> parameters;
    private delphiParser.CompoundStatementContext body;
    private DelphiInterpreterVisitor visitor;
    
    // Capture the environment where this procedure is declared (for static scoping).
    private Environment definingEnv;

    public ProcedureDef(List<String> parameters,
                        delphiParser.CompoundStatementContext body,
                        DelphiInterpreterVisitor visitor) {
        this.parameters = parameters;
        this.body = body;
        this.visitor = visitor;
        // The environment at creation time is the "defining environment."
        this.definingEnv = visitor.getSymbolTable().currentEnvironment();
    }

    @Override
    public Object call(List<Object> args) {
        // 1) Push a new scope that chains to definingEnv, preserving static scoping.
        visitor.getSymbolTable().pushScope(definingEnv);

        // 2) Bind parameters to arguments in this new scope.
        for (int i = 0; i < parameters.size(); i++) {
            // If not enough arguments, default to 0 or null as you prefer.
            Object arg = (i < args.size()) ? args.get(i) : 0;
            visitor.getSymbolTable().setVariable(parameters.get(i), arg);
        }

        // 3) Execute the procedure body.
        visitor.visit(body);

        // 4) Pop the scope, discarding local variables.
        visitor.getSymbolTable().popScope();
        
        // Procedures return no value, so return null.
        return null;
    }
}
