package ru.avalon.javapp.devj110.scriptlang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScriptLang {
    private final Map<String, Integer> values = new HashMap<String, Integer>(); //хранилище переменных читаемых из файла

    public void readDataFile(String fileName) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s;
            String[] buf;
            while(( s = br.readLine() ) != null) {// пока строчки не кончились
                if (s.contains("#")){
                    s = s.substring(0, s.indexOf("#"));// отсекаем коммент
                }
                buf = s.split(" ");//очень через опу
                if (buf[0].equals("set"))
                    set(buf);
                if (buf[0].equals("print"))
                    print(s);
            }
        }
    }

    private String getValue(String var) {
        if (!values.containsKey(var) && var.contains("$"))
            throw new IllegalArgumentException("Variable " + var + " is not initialized");

        return var.contains("$") ? Integer.toString(values.get(var)) : var;
    }

    private void set(String[] s) {
        if (s.length < 4 || !s[2].equals("=") || !s[1].contains("$") || !(s.length%2 == 0))
            throw new IllegalArgumentException("Incorrect input");
        if (s.length == 4) {
            if (s[3].contains("$")) {// добавить проверку на отсутствие переменной
                values.put(s[1], values.get(s[3]));
            } else {
                values.put(s[1], Integer.parseInt(s[3]));
            }
        }
        if (s.length > 4) {//добавить добавление переменных
            Operation operation = new Operation(getValue(s[3]), s[4], getValue(s[5]));
            operation.startOp();
            int step = 6;
            while (step < s.length - 1) {
                operation.continueOp(s[step], getValue(s[step + 1]));
                step +=2;
            }
            values.put(s[1], operation.getResult());
        }
    }

    private void print(String s) {
        s = s.substring(7);
        String[] buf = s.split("[\".*\"]?,\\s[\".*\"]?");
        for (int i = 0; i < buf.length; i++) {
            if (values.containsKey(buf[i]))
                buf[i] = Integer.toString(values.get(buf[i]));
        }
        /*
        print "Hello, world!!!"
        print "$sum = ", $n1, "+", $n2, "-42 = ", $sum

        должно печататься

        Hello, world!!!
        $sum = 21+121-42 = 100
        */

        for (String v : buf) {
            System.out.print(v);
        }
        System.out.println();
    }
    /*
• оператор print печатает на экран заданный список строк и переменных; строки и переменные в списке разделяются запятыми; строки заключаются в двойные кавычки;
Если в выражении оператора set или в операторе print используются неизвестные переменные, то выполнение скрипта должно быть прервано,
а пользователю должно быть показано соответствующее сообщение об ошибке.
Пример использования
Например, при запуске программы, которой передано имя файла со следующим содержимым
# Printing "Hello, world!!!" phrase
print "Hello, world!!!"
# Printing "21+121-42 = 100"
set $n1 = 21
set $n2 = 121
set $sum = $n1 + $n2 - 42
print "$sum = ", $n1, "+", $n2, "-42 = ", $sum
должно печататься

Hello, world!!!
$sum = 21+121-42 = 100
*/
}
