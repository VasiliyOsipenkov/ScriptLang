package ru.avalon.javapp.devj110.scriptlang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                buf = s.split(" ");
                if (buf[0].equals("set"))
                    set(buf);
                if (buf[0].equals("print"))
                    print(buf);
            }
        }
    }

    private void set(String[] s) {
        if (s[1].contains("$") && s[2].equals("="))
            try {
                values.put(s[1],Integer.parseInt(s[3]));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("dsf");
            }
        System.out.println(values.get(s[1]));
        /*
        set $n1 = 21
        set $n2 = 121
        set $sum = $n1 + $n2 - 42
        */
    }

    private void print(String[] s) {
        System.out.println("printing");
    }
    /*Напишите программу, реализующую интерпретатор скриптового языка, который позволяет:
• складывать и вычитать числа друг с другом;
• сохранять результат вычисления в переменных;
• использовать переменные в вычислениях;
• печатать строки и переменные на экран.
Вычисления производятся в целых числах.   т.е int
• оператор set вычисляет значение заданного математического выражения и присваивает
результат указанной переменной; выражение может содержать числа, другие переменные, операции сложения и вычитания;
• оператор print печатает на экран заданный список строк и переменных; строки и переменные в списке разделяются запятыми; строки заключаются в двойные кавычки;
Имя переменной предваряется знаком доллара («$») и состоит из английских букв, цифр и знака
подчёркивания. Например: $n, $var_name, $longVarName, $42.
Если в выражении оператора set или в операторе print используются неизвестные переменные, то выполнение скрипта должно быть прервано, а пользователю должно быть показано соответствующее сообщение об ошибке.
Если при чтении файла возникают ошибки (файл не существует, ошибка чтения и т.п.), то пользователю должно быть выведено текстовое сообщение с описанием проблемы, после чего работа программы должна быть завершена.
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
