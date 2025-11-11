package alfarius.goida;

import java.io.Serializable;

public class Calculator implements Serializable {
    private static final long serialVersionUID = 1L;

    private int firstNumber = 0;
    private int secondNumber = 0;
    private int result = 0;

    public int getFirstNumber() { return firstNumber; }
    public void setFirstNumber(int firstNumber) { this.firstNumber = firstNumber; }

    public int getSecondNumber() { return secondNumber; }
    public void setSecondNumber(int secondNumber) { this.secondNumber = secondNumber; }

    public int getResult() { return result; }
    public void setResult(int result) { this.result = result; }

    public void add() { result = firstNumber + secondNumber; }
    public void multiply() { result = firstNumber * secondNumber; }
    public void clear() { firstNumber = 0; secondNumber = 0; result = 0; }
}
