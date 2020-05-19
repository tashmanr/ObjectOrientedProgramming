/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.List;
import java.util.Map;

public abstract class BaseExpression implements Expression {

    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    public abstract double evaluate() throws Exception;

    public abstract List<String> getVariables();

    public abstract String toString();

    public abstract Expression assign(String var, Expression expression);
}
