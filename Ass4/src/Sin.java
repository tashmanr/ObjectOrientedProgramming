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
        expression = e;
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
    public String toString() {
        return "sin(" + this.expression.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(this.expression.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Cos(this.expression), this.expression.differentiate(var));
    }

    @Override
    public Expression simplify() {
        UnaryExpression u = new Sin(this.expression.simplify());
        if (u.getVariables().isEmpty()) {
            return u.noVariablesSimplify();
        } else {
            return u;
        }
    }
}
