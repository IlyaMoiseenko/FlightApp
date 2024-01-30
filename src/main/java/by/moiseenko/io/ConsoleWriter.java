package by.moiseenko.io;

/*
    @author Ilya Moiseenko on 30.01.24
*/

import java.util.Map;

public class ConsoleWriter implements Writer {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
