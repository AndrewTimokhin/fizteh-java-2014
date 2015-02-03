package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator.InteractiveMode;
import java.io.IOException;
import java.util.Scanner;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.KeyNullAndNotFound;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator.Commands;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator.UnknownCommand;

public class Main {

    public static void main(String[] args) throws IOException,
            IllegalArgumentException, KeyNullAndNotFound, UnknownCommand {
        InteractiveMode user = new InteractiveMode("C:\\DataBase", new Commands());
        Scanner sc = new Scanner(System.in);
        System.out.print("$ ");
        try {
            while (user.command(sc.nextLine())) {
                System.out.print("$ ");
            } 
        } catch (UnknownCommand ex) {
            System.out.println("Unknow operation.  Fail");
        }
    }
}
