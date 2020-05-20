/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class Sin extends UnaryExpression {
    private Expression expression;

    public Sin(Expression e) {
        super(e);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String s : this.expression.getVariables()) {
            if (!assignment.containsKey(s)) {
                throw new IllegalArgumentException();
            }
        }
        return sin(toRadians(this.expression.evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        return sin(toRadians(this.expression.evaluate()));
    }

    @Override
    public String toString() {
        return "sin(" + this.expression.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(expression.assign(var, expression));
    }
}
