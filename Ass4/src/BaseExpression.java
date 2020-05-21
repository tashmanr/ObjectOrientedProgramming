/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseExpression implements Expression {
    protected static Var e = new Var("e");

    /**
     * This function is called on an expression that contains no variables.
     * It calculates the functions, and returns the result. If the calculation returns an error we throw the exxception
     * @return new expression that is a calculation (contains no variables)
     */
    public Expression noVariablesSimplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception IllegalArgumentException) {
            throw new IllegalArgumentException();
        }
    }

    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    public double evaluate() throws Exception {
        return evaluate(new HashMap<String, Double>());
    }

    public abstract List<String> getVariables();

    public abstract String toString();

    public abstract Expression assign(String var, Expression expression);
}
