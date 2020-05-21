/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * This is Sin class which extends the UnaryExpression class.
 */
public class Sin extends UnaryExpression {
    private Expression ex;

    /**
     * Constructor, calls to constructor for UnaryExpression class.
     * @param e new expression
     */
    public Sin(Expression e) {
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
        return sin(toRadians(this.ex.evaluate(assignment)));
    }

    @Override
    public String toString() {
        return "sin(" + this.ex.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(this.ex.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Cos(this.ex), this.ex.differentiate(var));
    }

    @Override
    public Expression simplify() {
        UnaryExpression u = new Sin(this.ex.simplify());
        if (u.getVariables().isEmpty()) {
            return u.noVariablesSimplify();
        } else {
            return u;
        }
    }
}
