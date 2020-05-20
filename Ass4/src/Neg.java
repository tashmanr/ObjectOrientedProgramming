/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;

public class Neg extends UnaryExpression {
    private Expression expression;

    public Neg(Expression e) {
        super(e);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String s : this.expression.getVariables()) {
            if (!assignment.containsKey(s)) {
                throw new IllegalArgumentException();
            }
        }
        return (-1) * this.expression.evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        return (-1) * this.expression.evaluate();
    }

    @Override
    public String toString() {
        return "(-" + this.expression.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(expression.assign(var, expression));
    }
}