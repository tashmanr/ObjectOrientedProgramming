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

    public BinaryExpression(Expression e1, Expression e2) {
        this.expression1 = e1;
        this.expression2 = e2;
    }

    //public abstract Expression getExpression();

    @Override
    public List<String> getVariables() {
        HashSet s = new HashSet(this.expression1.getVariables());
        s.addAll(this.expression2.getVariables());
        return new ArrayList<>(s);
    }
}
