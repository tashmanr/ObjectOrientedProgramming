/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is BaseExpression, implements Expression and all of the function classes extend this.
 */
public abstract class BaseExpression implements Expression {
    /**
     * This function is called on an expression that contains no variables.
     * It calculates the functions, and returns the result. If the calculation returns an error we throw the exxception
     * @return new expression that is a calculation (contains no variables)
     */
    public Expression noVariablesSimplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception illegalArgumentException) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    @Override
    public double evaluate() throws Exception {
        return evaluate(new HashMap<String, Double>());
    }

    @Override
    public abstract List<String> getVariables();

    @Override
    public abstract String toString();

    @Override
    public abstract Expression assign(String var, Expression expression);
}
