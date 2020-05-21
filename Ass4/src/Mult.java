/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;

/**
 * Mult class which extends BinaryExpression.
 */
public class Mult extends BinaryExpression {
    private Expression ex1;
    private Expression ex2;

    /**
     * Constructor, calls to constructor for BinaryExpression class.
     * @param e1 first expression
     * @param e2 second expression
     */
    public Mult(Expression e1, Expression e2) {
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
        return this.ex1.evaluate(assignment) * this.ex2.evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(" + this.ex1.toString() + " * " + this.ex2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(ex1.assign(var, expression), ex2.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // the derivative for f(x)*g(x) is f'(x)*g(x) + f(x)*g'(x)
        return new Plus(new Mult(this.ex1.differentiate(var), this.ex2), new Mult(this.ex1,
                this.ex2.differentiate(var)));
    }

    @Override
    public Expression simplify() {
        if (this.ex2.simplify().toString().equals(new Num(1).toString())) {
            return this.ex1.simplify(); // x*1=x
        } else if (this.ex1.simplify().toString().equals(new Num(1).toString())) {
            return this.ex2.simplify(); // 1*x=x
        } else if (this.ex1.simplify().toString().equals(new Num(0).toString())
                || this.ex2.simplify().toString().equals(new Num(0).toString())) {
            return new Num(0); // x*0=0
        } else {
            BinaryExpression b = new Mult(this.ex1.simplify(), this.ex2.simplify());
            if (b.getVariables().isEmpty()) {
                return b.noVariablesSimplify();
            } else {
                return b;
            }
        }
    }
}
