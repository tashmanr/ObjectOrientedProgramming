package gamesetup;

public class Counter {
    private int number;
    public Counter(){
        number = 0;
    }
    // add number to current count.
    public void increase(int number){
        this.number+=number;
    }
    // subtract number from current count.
    public void decrease(int number){
        this.number-=number;
    }
    // get current count.
    public int getValue(){
        return number;
    }
}