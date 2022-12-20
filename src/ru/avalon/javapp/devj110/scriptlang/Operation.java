package ru.avalon.javapp.devj110.scriptlang;

public class Operation {
    private String a,
    operation,
    b;
    private int result;

    public Operation(String a, String operation, String b){
        setA(a);
        setOperation(operation);
        setB(b);
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void startOp() {
        switch (operation) {
            case ("/"):
                result = div();
                break;
            case ("*"):
                result = mul();
                break;
            case ("-"):
                result = sub();
                break;
            case ("+"):
                result = add();
                break;
            default:
                throw new IllegalArgumentException("operation " + operation + " not supported");
        }
    }

    public void continueOp(String operation, String b) {
        setA(Integer.toString(result));
        setOperation(operation);
        setB(b);
        startOp();
    }

    private int add() {
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    private int sub() {
        return Integer.parseInt(a) - Integer.parseInt(b);
    }

    private int mul() {
        return Integer.parseInt(a) * Integer.parseInt(b);
    }

    private int div() {
        return Integer.parseInt(a) / Integer.parseInt(b);
    }
}
