package ru.avalon.javapp.devj110.scriptlang;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ScriptLang lang = new ScriptLang();
        try {
            lang.readDataFile(args[0]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Specify the file in the program launch argument");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
