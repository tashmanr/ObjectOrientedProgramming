/**
 * 336423124
 * Rebecca Tashman
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {
    private String variable;

    //constructor
    public Var(String var) {
        this.variable = var;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double evaluate() throws Exception {
        throw new IllegalArgumentException();
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        vars.add(this.variable);
        return vars;
    }

    @Override
    public String toString() {
     return variable;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression differentiate(String var) {
        if (this.variable.equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
