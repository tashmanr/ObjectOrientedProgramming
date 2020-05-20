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
        expression = e;
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
    public String toString() {
        return "cos(" + this.expression.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(this.expression.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Neg(new Sin(this.expression)), this.expression.differentiate(var));
    }

    @Override
    public Expression simplify() {
        if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception IllegalArgumentException) {
                throw new IllegalArgumentException();
            }
        }
        return new Cos(this.expression.simplify());
    }
}
