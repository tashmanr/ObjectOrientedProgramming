/**
 * Rebecca Tashman.
 * 336423124
 */

import java.util.Map;
import java.util.List;

public interface Expression {

    /**
     * This function evaluates the expression using the variable values provided in the assignment and returns
     * the result. If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment a map of variables and their double values
     * @return double - result of the calculation
     * @throws Exception if variable is missing from assignment
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * This function calls to the evaluate method from above using an empty assignment.
     * @return double - result of the calculation
     * @throws Exception if variable is missing from assignment
     */
    double evaluate() throws Exception;

    /**
     * This method returns a list of the variables in the expression.
     * @return list of variables
     */
    List<String> getVariables();

    /**
     * This function returns a string representation of the expression.
     * @return string
     */
    String toString();

    /**
     * This function returns a new expression in which all occurrences of the variable var are replaced.
     * with the provided expression (does not modify the current expression).
     * @param var variable to be replaced
     * @param expression expression to replace the variable
     * @return new expression with the replacement if it happened
     */
    Expression assign(String var, Expression expression);

    /**
     * This function returns the expression tree resulting from differentiating the current expression relative to var.
     * @param var variable to differentiate in relation to
     * @return new expression (the derivative)
     */
    Expression differentiate(String var);

    /**
     * This function returns a simplified version of the current expression.
     * @return new expression simplified as much as possible
     */
    Expression simplify();
}
