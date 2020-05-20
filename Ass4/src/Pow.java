/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;

import static java.lang.Math.pow;

public class Pow extends BinaryExpression {
    private Expression expression1;
    private Expression expression2;

    public Pow(Expression e1, Expression e2) {
        super(e1, e2);
        expression1 = e1;
        expression2 = e2;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String s : this.expression1.getVariables()) {
            if (!assignment.containsKey(s)) {
                throw new IllegalArgumentException();
            }
        }
        for (String s : this.expression2.getVariables()) {
            if (!assignment.containsKey(s)) {
                throw new IllegalArgumentException();
            }
        }
        return pow(this.expression1.evaluate(assignment), this.expression2.evaluate(assignment));
    }

    @Override
    public String toString() {
        return "(" + this.expression1.toString() + "^" + this.expression2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(expression1.assign(var, expression), expression2.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(this.expression2, new Pow(this.expression1, new Minus(this.expression2, new Num(1))));
    }

    @Override
    public Expression simplify() {
        if (this.expression2.simplify().toString().equals(new Num(1).simplify().toString())) {
            return this.expression1;
        } else if (this.expression2.simplify().toString().equals(new Num(0).simplify().toString())) {
            return new Num(1);
        } else {
            if (this.getVariables().isEmpty()) {
                try {
                    return new Num(this.evaluate());
                } catch (Exception IllegalArgumentException) {
                    throw new IllegalArgumentException();
                }
            }
            return new Pow(this.expression1.simplify(), this.expression2.simplify());
        }
    }
}
