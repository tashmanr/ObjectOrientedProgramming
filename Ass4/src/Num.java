/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Num class which implements Expression.
 */
public class Num implements Expression {
    private double number;

    /**
     * Constructor which receives a double.
      * @param num double to save in the expression
     */
    public Num(double num) {
        this.number = num;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.number;
    }

    @Override
    public double evaluate() throws Exception {
        return this.number;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return Double.toString(this.number);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0); // numbers will never be the variable we are deriving in relation to
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
