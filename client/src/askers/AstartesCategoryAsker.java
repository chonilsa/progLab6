package askers;

import collections.AstartesCategory;
import exceptions.EmptyFieldException;
import exceptions.IncorrectScriptInputException;
import managers.CommandParser;
import managers.Console;

public class AstartesCategoryAsker extends AbstractAsker {
    private final Console console;

    public AstartesCategoryAsker(Console console) {
        this.console = console;
    }

    @Override
    public AstartesCategory build() throws IncorrectScriptInputException, EmptyFieldException {
        AstartesCategory astartesCategory = askAstartesCategory();
        return astartesCategory;
    }
    public AstartesCategory askAstartesCategory() throws IncorrectScriptInputException {
        AstartesCategory astartesCategory;
        String astartesCategoryString;
        var fileMode = CommandParser.fileMode();
        while (true) {
            try {
                console.println("Enter astartesCategory type (" + AstartesCategory.names() + "):");
                astartesCategoryString = CommandParser.getScanner().nextLine().trim();
                if (astartesCategoryString.equals("")) throw new EmptyFieldException();
                astartesCategory = AstartesCategory.valueOf(astartesCategoryString.toUpperCase());
                break;

            } catch (IllegalArgumentException e) {
                console.println("Invalid astartesCategory type");
                if (fileMode) throw new IncorrectScriptInputException();
            } catch (EmptyFieldException e) {
                console.println("Field can't be empty");
                if (fileMode) throw new IncorrectScriptInputException();
            }
        }
        return astartesCategory;
    }
}
