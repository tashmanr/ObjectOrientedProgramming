/**
 * Rebecca Tashman.
 * 336423124
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class BinaryExpression extends BaseExpression {
    private Expression expression1;
    private Expression expression2;

    /**
     * Basic constructor.
     * @param e1 first expression
     * @param e2 second expression
     */
    public BinaryExpression(Expression e1, Expression e2) {
        this.expression1 = e1;
        this.expression2 = e2;
    }

    /**
     * In order to ensure that we don't receive multiples of the same variable, we create a set of the variables.
     * Once we've added all the variables from both expressions, we convert it to a list.
     * @return list of variables in both expressions
     */
    @Override
    public List<String> getVariables() {
        HashSet<String> s = new HashSet<>(this.expression1.getVariables());
        s.addAll(this.expression2.getVariables());
        return new ArrayList<>(s);
    }
}
