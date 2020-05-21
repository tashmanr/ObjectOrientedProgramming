/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;
import static java.lang.Math.log;

/**
 * Log class which extends BinaryExpression.
 */
public class Log extends BinaryExpression {
    private Expression ex1;
    private Expression ex2;
    private static Var e = new Var("e");

    /**
     * Constructor, calls to constructor for BinaryExpression class.
     * @param e1 first expression
     * @param e2 second expression
     */
    public Log(Expression e1, Expression e2) {
        super(e1, e2);
        ex1 = e1;
        ex2 = e2;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String s : this.ex1.getVariables()) {
            if (!assignment.containsKey(s)) {
                throw new IllegalArgumentException();
            }
        }
        for (String s : this.ex2.getVariables()) {
            if (!assignment.containsKey(s)) {
                throw new IllegalArgumentException();
            }
        }
        if (this.ex1.simplify().toString().equals(new Num(0).toString())
                || this.ex1.simplify().toString().equals(new Num(1).toString())
                || this.ex1.simplify().evaluate() < 0
                || this.ex2.simplify().toString().equals(new Num(0).toString())) {
            throw new IllegalArgumentException();
        }
        return log(this.ex2.evaluate(assignment)) / log(this.ex1.evaluate(assignment));
    }

    @Override
    public String toString() {
        return "log(" + this.ex1.toString() + ", " + this.ex2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(ex1.assign(var, expression), ex2.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // the derivative for logf(x) g(x) is (((lnf(x)*g'(x)) / g(x)) - (f'(x)*lng(x) / f(x))) / (lnf(x)^2)
        return new Div(new Minus(new Div(new Mult(new Log(e, this.ex1), this.ex2.differentiate(var)),
                this.ex2), new Div(new Mult(this.ex1.differentiate(var), new Log(e, this.ex2)), this.ex1)),
                new Pow(new Log(e, this.ex1), new Num(2)));
    }

    @Override
    public Expression simplify() {
        if (this.ex2.simplify().toString().equals(this.ex1.simplify().toString())) {
            return new Num(1);
        } else {
            BinaryExpression b = new Log(this.ex1.simplify(), this.ex2.simplify());
            if (b.getVariables().isEmpty()) {
                return b.noVariablesSimplify();
            } else {
                return b;
            }
        }
    }
}
