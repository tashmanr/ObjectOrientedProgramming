/**
 * Rebecca Tashman.
 * 336423124
 */

import java.util.List;

public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;

    /**
     * Basic constructor for unary expressions.
     * @param e expression
     */
    public UnaryExpression(Expression e) {
        this.expression = e;
    }

    @Override
    public List<String> getVariables() {
        return this.expression.getVariables();
    }
}
