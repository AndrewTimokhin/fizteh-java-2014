package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator.InteractiveMode;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.FactoryImplements;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.KeyNullAndNotFound;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableImplement;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProviderImplements;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.Writer;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator.Commands;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator.UnknownCommandException;

public class Main {

    protected static TableImplement table = null;
    protected static TableProviderImplements provider;

    static {
        try {
            provider = new TableProviderImplements(System.getProperty("fizteh.db.dir"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException,
            IllegalArgumentException, KeyNullAndNotFound, UnknownCommandException {

        Map<String, Commands> totalCommands;

        totalCommands = new HashMap<>();
        totalCommands.put("create", new CreateCommand());
        totalCommands.put("put", new PutCommand());
        totalCommands.put("get", new GetCommand());
        totalCommands.put("remove", new RemoveCommand());
        totalCommands.put("drop", new DropCommand());
        totalCommands.put("commit", new CommitCommand());
        totalCommands.put("rollback", new RollbackCommand());
        totalCommands.put("use", new UseCommand());
        totalCommands.put("size", new SizeCommand());
        totalCommands.put("list", new ListCommand());
        totalCommands.put("exit", new ExitCommand());

        String path = "C:\\DataBase";
        FactoryImplements factory = new FactoryImplements();
        provider = (TableProviderImplements) factory.create(path);
        InteractiveMode user = new InteractiveMode(totalCommands);
        Scanner sc = new Scanner(System.in);
        System.out.print("$ ");
        try {
            while (true) {
                String commands = sc.nextLine();

                Main.table = user.runCommand(commands).execute(commands.trim().split(" "), Main.provider, Main.table);
                System.out.print("$ ");
            }

        } catch (ExitException ex) {
            System.out.println("Save all changes to the disc? [YES/NO]");
            String userAnswer = sc.nextLine();
            while (!userAnswer.equals("YES") && !userAnswer.equals("NO")) {
                System.out.println("try again");
                userAnswer = sc.nextLine();
            }
            if (userAnswer.equals("YES")) {
                Writer writeToFileSystem = new Writer();
                writeToFileSystem.write(provider);
            }
            System.out.println("Exited");
        }

    }
}
