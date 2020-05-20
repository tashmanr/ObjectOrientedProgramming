/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;
import static java.lang.Math.cos;
import static java.lang.Math.toRadians;

public class Cos extends UnaryExpression {
    private Expression expression;

    public Cos(Expression e) {
        super(e);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String s : this.expression.getVariables()) {
            if (!assignment.containsKey(s)) {
                throw new IllegalArgumentException();
            }
        }
        return cos(toRadians(this.expression.evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        return cos(toRadians(this.expression.evaluate()));
    }

    @Override
    public String toString() {
        return "cos(" + this.expression.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(expression.assign(var, expression));
    }
}
