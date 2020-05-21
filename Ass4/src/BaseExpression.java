/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseExpression implements Expression {
    protected static Var e = new Var("e");

    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    public double evaluate() throws Exception {
        return evaluate(new HashMap<String, Double>());
    }

    public abstract List<String> getVariables();

    public abstract String toString();

    public abstract Expression assign(String var, Expression expression);
}
