/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;

public class Plus extends BinaryExpression {
    private Expression expression1;
    private Expression expression2;

    public Plus(Expression e1, Expression e2) {
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
        return this.expression1.evaluate(assignment) + this.expression2.evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(" + this.expression1.toString() + " + " + this.expression2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(expression1.assign(var, expression), expression2.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(this.expression1.differentiate(var), this.expression2.differentiate(var));
    }

    @Override
    public Expression simplify() {
        if (this.expression2.simplify().toString().equals(new Num(0).toString())) {
            return this.expression1.simplify();
        } else if (this.expression1.simplify().toString().equals(new Num(0).toString())) {
            return this.expression2.simplify();
        } else {
            BinaryExpression b = new Plus(this.expression1.simplify(), this.expression2.simplify());
            if (b.getVariables().isEmpty()) {
                return b.noVariablesSimplify();
            } else {
                return b;
            }
        }
    }
}
