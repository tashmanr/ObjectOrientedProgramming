/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;
import static java.lang.Math.pow;

/**
 * Pow class which extends BinaryExpression.
 */
public class Pow extends BinaryExpression {
    private Expression ex1;
    private Expression ex2;
    private static Var e = new Var("e");

    /**
     * Constructor, calls to constructor for BinaryExpression class.
     * @param e1 first expression
     * @param e2 second expression
     */
    public Pow(Expression e1, Expression e2) {
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
        return pow(this.ex1.evaluate(assignment), this.ex2.evaluate(assignment));
    }

    @Override
    public String toString() {
        return "(" + this.ex1.toString() + "^" + this.ex2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(ex1.assign(var, expression), ex2.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(this, new Plus(new Mult(this.ex1.differentiate(var),
                new Div(this.ex2, this.ex1)), new Mult(this.ex2.differentiate(var),
                new Log(e, this.ex1))));
    }

    @Override
    public Expression simplify() {
        if (this.ex2.simplify().toString().equals(new Num(1).toString())) {
            return this.ex1;
        } else if (this.ex2.simplify().toString().equals(new Num(0).toString())) {
            return new Num(1);
        } else {
            BinaryExpression b = new Pow(this.ex1.simplify(), this.ex2.simplify());
            if (b.getVariables().isEmpty()) {
                return b.noVariablesSimplify();
            } else {
                return b;
            }
        }
    }
}
