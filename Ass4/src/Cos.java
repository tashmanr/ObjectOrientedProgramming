/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;

import static java.lang.Math.cos;
import static java.lang.Math.toRadians;

/**
 * This is Cos class which extends the UnaryExpression class.
 */
public class Cos extends UnaryExpression {
    private Expression ex;

    /**
     * Constructor, calls to constructor for UnaryExpression class.
     * @param e new expression
     */
    public Cos(Expression e) {
        super(e);
        ex = e;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String s : this.ex.getVariables()) {
            if (!assignment.containsKey(s)) {
                throw new IllegalArgumentException();
            }
        }
        return cos(toRadians(this.ex.evaluate(assignment)));
    }

    @Override
    public String toString() {
        return "cos(" + this.ex.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(this.ex.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // the derivative for cos(x) is -sin(f(x))*f'(x)
        return new Neg(new Mult(new Sin(this.ex), this.ex.differentiate(var)));
    }

    @Override
    public Expression simplify() {
        UnaryExpression u = new Cos(this.ex.simplify());
        if (u.getVariables().isEmpty()) {
            return u.noVariablesSimplify();
        } else {
            return u;
        }
    }
}
