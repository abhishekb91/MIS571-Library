package library.abhishek.util;

/**
 * Created by abhishek on 11/5/2016.
 */

public class Pair {
    private int month;

    private double number;

    /**
     *
     * @param month     : This is value of the month
     * @param number    : This is value of the number
     */
    public Pair(int month, double number) {
        super();
        this.month = month;
        this.number = number;
    }

    /**
     * This function is used to set the value of the month variable
     * @param month     : Month value
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * This function is used to get value of the set month
     * @return          : Current value of the month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * This function is used to set the number value
     * @param number    : The value of number
     */
    public void setNumber(double number) {
        this.number = number;
    }

    /**
     * This function is used to get the value of the number
     * @return      : The current value of the number
     */
    public double getNumber() {
        return this.number;
    }
}
