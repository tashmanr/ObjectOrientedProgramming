/**
 * 336423124.
 * Rebecca Tashman
 */

/**
 * Class which contains a main to call on all the other classes.
 */
public class ExpressionsTest {
    /**
     * Main function, doesn't use the arguments provided.
     * @param args not used
     */
    public static void main(String[] args) {
        Expression ex1 = new Plus(new Plus(new Mult(new Num(2), new Var("x")),
                new Sin(new Mult(new Num(4), new Var("y")))), new Pow(new Var("e"), new Var("x")));
        System.out.println(ex1);
        System.out.println(ex1.assign("x", new Num(2)).assign("y",
                new Num(0.25)).assign("e", new Num(2.71)).simplify());
        System.out.println(ex1.differentiate("x"));
        Expression ex2 = ex1.differentiate("x").assign("x", new Num(2)).assign("e", new Num(2.71));
        System.out.println(ex2.simplify());
        System.out.println(ex1.differentiate("x").simplify());
    }
}
