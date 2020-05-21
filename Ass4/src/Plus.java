/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.Map;

/**
 * Plus class which extends BinaryExpression.
 */
public class Plus extends BinaryExpression {
    private Expression ex1;
    private Expression ex2;

    /**
     * Constructor, calls to constructor for BinaryExpression class.
     * @param e1 first expression
     * @param e2 second expression
     */
    public Plus(Expression e1, Expression e2) {
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
        return this.ex1.evaluate(assignment) + this.ex2.evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(" + this.ex1.toString() + " + " + this.ex2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(ex1.assign(var, expression), ex2.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(this.ex1.differentiate(var), this.ex2.differentiate(var));
    }

    @Override
    public Expression simplify() {
        if (this.ex2.simplify().toString().equals(new Num(0).toString())) {
            return this.ex1.simplify();
        } else if (this.ex1.simplify().toString().equals(new Num(0).toString())) {
            return this.ex2.simplify();
        } else {
            BinaryExpression b = new Plus(this.ex1.simplify(), this.ex2.simplify());
            if (b.getVariables().isEmpty()) {
                return b.noVariablesSimplify();
            } else {
                return b;
            }
        }
    }
}
