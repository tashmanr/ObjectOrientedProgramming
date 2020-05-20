/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;

public class Neg extends UnaryExpression {
    private Expression expression;

    public Neg(Expression e) {
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
        return (-1) * this.expression.evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(-" + this.expression.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(this.expression.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(this.expression.differentiate(var));
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
        return new Neg(this.expression.simplify());
    }
}