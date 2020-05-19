/**
 * Rebecca Tashman.
 * 336423124
 */

public abstract class BinaryExpression extends BaseExpression {
    private Expression expression1;
    private Expression expression2;

    public BinaryExpression(Expression e1, Expression e2) {
        this.expression1 = e1;
        this.expression2 = e2;
    }

    public abstract Expression getExpression();
}
