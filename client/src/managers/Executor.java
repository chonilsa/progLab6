package managers;

import askers.*;
import collections.*;
import commands.CommandDescription;
import commands.CommandRequest;
import commands.CommandResponse;
import exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Executor {
    private final List<String> recursionStack = new ArrayList<>();
    private final CommandManager commandManager;
    private Client client;

    public String rnfile;

    public String rncommand;

    public boolean rnflag = true; // true - first time, false - second time

    int cnter = 0;

    public Executor(CommandManager commandManager, Console console, Client client) {

        AskerManager.setAsker(SpaceMarine.class, new SpaceMarineAsker(console));
        AskerManager.setAsker(AstartesCategory.class, new AstartesCategoryAsker(console));
        AskerManager.setAsker(ChapterAsker.class, new ChapterAsker(console));
        AskerManager.setAsker(Coordinates.class, new CoordinatesAsker(console));
        AskerManager.setAsker(MeleeWeapon.class, new MeleeWeaponAsker(console));
        AskerManager.setAsker(Weapon.class, new WeaponAsker(console));

        this.commandManager = commandManager;
        this.client = client;
    }

    public void consoleMode() {
        Scanner userScanner = CommandParser.getScanner();
        try {
            Status status = Status.OK;
            String[] command;
            do {
                System.out.println("Enter command: ");
                try {
                    command = userScanner.nextLine().trim().split(" ");

                    if (command[0].equals("exit")) {
                        status = Status.EXIT;
                        break;
                    } else if (command[0].equals("execute_script")) {

                        scriptMode(command[1]);
//                        rnflag = true;
//                        do {
//                            if (rnflag) {
//                                status = scriptMode(command[1]);
//                            } else {
//                                status = scriptMode(rnfile);
//                            }
//                        }while (true);
////                        while (!rnflag) status = scriptMode(rnfile);
                    }
                    if (validateCommand(command)) {
//                        for (String i: recursionStack){
//                            if(i.equals(command[1])){
//                                return;
//                            }
//                        }
                        CommandDescription description = commandManager.getCommand(command[0]);
                        Object object = null;
                        if (description.getRequiredObjectType() != Void.class) {
                            AbstractAsker asker = AskerManager.getAsker(description.getRequiredObjectType());
                            object = asker.build();
                        }
                        String[] args = new String[command.length - 1];
                        System.arraycopy(command, 1, args, 0, command.length - 1);

                        CommandRequest request = new CommandRequest(command[0], args, object);
                        manageResponse(request);
//                        client.run(request);
                    } else {
                        System.out.println("Wrong command, use help to get list of commands");
                    }

                } catch (NoSuchElementException e) {
                    System.out.println("Ctrl+D, finishing program...");
                    status = Status.EXIT;
                }

            } while (status != Status.EXIT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    private boolean validateCommand(String[] command) {
        try {
            CommandDescription description = commandManager.getCommand(command[0]);
            if (command[0].equals("execute_script")){
                return true;
            }
            if (description == null) {
                System.out.println("Command not found");
                return false;
            }
            if (description.getArgsCount() != command.length - 1) {
                System.out.println("Wrong number of arguments");
                return false;
            }
            for (int i = 1; i < command.length; i++) {
                try {
//                    if (command[0].equals("execute_script")) return false;
                    parseLong(command[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Wrong type of argument");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error while validating command");
            return false;
        }
    }

    public Status scriptMode(String arg) {
        String[] command = new String[2];
        Status status = Status.OK;
        recursionStack.add(arg);
//        if (!new File(arg).exists()) {
//            arg = "../" + arg;
//        }
//        rnfile = arg;
        Scanner defaultScanner = CommandParser.getScanner();
        try (Scanner scanner = new Scanner(new File(arg))) {
            if (!scanner.hasNextLine()) {
                System.out.println("File is empty");
                return Status.ERROR;
            }

            CommandParser.setScanner(scanner);
            CommandParser.setFileMode();
            do {

                command = scanner.nextLine().trim().split(" ");
                while (scanner.hasNextLine() && command[0].isEmpty()) {
                    command = scanner.nextLine().trim().split(" ");
                }
                System.out.println("Executing command: " + command[0]);
                if (command[0].equals("execute_script")) {
//                    for (String s : recursionStack) {
//                        if (s.equals(command[1])) throw new RecoursiveCallException();
//                    }
                    scriptMode(command[1]);
                }

                if (command[0].equals("exit")) {
                    status = Status.EXIT;
                    return status;
                }
                if (validateCommand(command)) {
                    CommandDescription description = commandManager.getCommand(command[0]);
                    //call needed asker
                    Object object = null;
                    if (description.getRequiredObjectType() != Void.class) {
                        AbstractAsker asker = AskerManager.getAsker(description.getRequiredObjectType());
                        object = asker.build();
                    }
                    String[] args = new String[command.length - 1];
                    System.arraycopy(command, 1, args, 0, command.length - 1);

                    CommandRequest request = new CommandRequest(command[0], args, object);
                    status = Status.OK;
                    manageResponse(request);
//                    rnflag = true;
                    //somehow send request to server
                } else {
                    CommandParser.setScanner(defaultScanner);
                    CommandParser.setConsoleMode();
                    return Status.OK;
                }

            } while (status == Status.OK && scanner.hasNextLine());
//            while (scanner.nextLine().equals("execute_script") || scanner.nextLine().equals("")) {
//                if (scanner.nextLine().equals("execute_script")) {
//                    rnflag = true;
//                    return status;
//                }
//            }
//            recursionStack.remove(recursionStack.get(recursionStack.size() - 1));
            CommandParser.setScanner(defaultScanner);
            CommandParser.setConsoleMode();
            return status;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (RecoursiveCallException e) {
            System.out.println("Recursion in script");
        } catch (InvalidObjectException | EmptyFieldException | IncorrectScriptInputException e) {
            throw new RuntimeException(e);
        } finally {
            CommandParser.setScanner(defaultScanner);
            CommandParser.setConsoleMode();

        }

        return Status.ERROR;
    }

    private void manageResponse(CommandRequest request) {
        CommandResponse response = client.run(request);
        System.out.println(response.getMessage());
        if (response.getObject() != null) {
            Object object1 = response.getObject();
            if (object1 instanceof Iterable<?>)
                for (Object o : (Iterable<?>) object1) System.out.println(o.toString());
            else System.out.println("file: " + object1.toString());
        }

    }


//    public void scriptMode(String arg) {
//        Scanner defaultScanner = CommandParser.getScanner();
//        try (Scanner scanner = new Scanner(new File(arg))) {
//            if (!scanner.hasNextLine()) {
//                System.out.println("File is empty");
//                System.out.println("");
//            }
//            do {
//                String[] command = new String[2];
//                command = scanner.nextLine().trim().split(" ");
//                while (scanner.hasNextLine() && command[0].isEmpty()) {
//                    command = scanner.nextLine().trim().split(" ");
//                }
//                if(!scanner.hasNextLine()) break;
//
//                System.out.println("Executing command: " + command[0]);
//                if (command[0].equals("execute_script")) {
//                    if(cnter > 500){
//                        System.out.println("Stack ov");
//                    }
//                    else {
//                        scriptMode(command[1]);
//                    }
//                }
//                else if (command[0].equals("exit")) {
//                    System.out.println("exiting");
//                }
//                else if (validateCommand(command)) {
//                    CommandDescription description = commandManager.getCommand(command[0]);
//                    //call needed asker
//                    Object object = null;
//                    if (description.getRequiredObjectType() != Void.class) {
//                        AbstractAsker asker = AskerManager.getAsker(description.getRequiredObjectType());
//                        object = asker.build();
//                    }
//                    String[] args = new String[command.length - 1];
//                    System.arraycopy(command, 1, args, 0, command.length - 1);
//
//                    CommandRequest request = new CommandRequest(command[0], args, object);
//                    manageResponse(request);
//                }
//
//
//            } while (scanner.hasNextLine());
//
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        } catch (RecoursiveCallException e) {
//            System.out.println("Recursion in script");
//        } catch (InvalidObjectException | EmptyFieldException | IncorrectScriptInputException e) {
//            throw new RuntimeException(e);
//        } finally {
//            System.out.println("qwertyuio");
//        }
//        cnter--;
//
//    }

    public enum Status {
        OK,
        ERROR,
        EXIT,
    }
}
