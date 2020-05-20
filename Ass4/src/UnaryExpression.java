import java.util.List;

/**
 * Rebecca Tashman.
 * 336423124
 */

public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;

    public UnaryExpression(Expression e) {
        this.expression = e;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public List<String> getVariables() {
        return this.expression.getVariables();
    }
}
