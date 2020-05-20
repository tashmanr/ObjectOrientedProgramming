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
    public double evaluate() throws Exception {
        return pow(this.expression1.evaluate(), this.expression2.evaluate());
    }

    @Override
    public String toString() {
        return "(" + this.expression1.toString() + "^" + this.expression2.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(expression1.assign(var, expression), expression2.assign(var,expression));
    }

}
