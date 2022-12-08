package ru.avalon.javapp.devj110.scriptlang;

public enum Operation {
    ADD((a, b) -> a + b),
    SUB((a, b) -> a - b),
    MUL((a1, a2) -> a1 * a2),
    DIV((a1, a2) -> a1 / a2);

    Operator operator;

    private Operation(Operator operator){
        this.operator = operator;
    }
}
