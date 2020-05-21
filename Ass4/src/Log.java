/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;

import static java.lang.Math.log;

public class Log extends BinaryExpression {
    private Expression expression1;
    private Expression expression2;

    public Log(Expression e1, Expression e2) {
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
        if (this.expression1.simplify().toString().equals(new Num(0).toString())
                || this.expression1.simplify().toString().equals(new Num(1).toString())
                || this.expression1.simplify().evaluate() < 0
                || this.expression2.simplify().toString().equals(new Num(0).toString())) {
            throw new IllegalArgumentException();
        }
        return log(this.expression2.evaluate(assignment)) / log(this.expression1.evaluate(assignment));
    }

    @Override
    public String toString() {
        return "log(" + this.expression1.toString() + ", " + this.expression2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(expression1.assign(var, expression), expression2.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Div(new Minus(new Div(new Mult(new Log(e, this.expression1), this.expression2.differentiate(var)),
                this.expression2), new Div(new Mult(this.expression1.differentiate(var), new Log(e,
                this.expression2)), this.expression1)), new Pow(new Log(e, this.expression1), new Num(2)));
    }

    @Override
    public Expression simplify() {
        if (this.expression2.simplify().toString().equals(this.expression1.simplify().toString())) {
            return new Num(1);
        } else {
            BinaryExpression b = new Log(this.expression1.simplify(), this.expression2.simplify());
            if (b.getVariables().isEmpty()) {
                return b.noVariablesSimplify();
            } else {
                return b;
            }
        }
    }
}
