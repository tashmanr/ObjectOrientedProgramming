/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;

/**
 * This is Neg class which extends the UnaryExpression class.
 */
public class Neg extends UnaryExpression {
    private Expression ex;

    /**
     * Constructor, calls to constructor for UnaryExpression class.
     * @param e new expression
     */
    public Neg(Expression e) {
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
        return (-1) * this.ex.evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(-" + this.ex.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(this.ex.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(this.ex.differentiate(var));
    }

    @Override
    public Expression simplify() {
        UnaryExpression u = new Neg(this.ex.simplify());
        if (u.getVariables().isEmpty()) {
            return u.noVariablesSimplify();
        } else {
            return u;
        }
    }
}