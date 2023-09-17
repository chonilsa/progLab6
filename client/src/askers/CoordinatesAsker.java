package askers;

import collections.Coordinates;
import exceptions.IncorrectScriptInputException;
import exceptions.ValidValuesException;
import managers.CommandParser;
import managers.Console;
import java.io.InvalidObjectException;

public class CoordinatesAsker extends AbstractAsker {

    private final Console console;

    public CoordinatesAsker(Console console) {
        this.console = console;
    }

    @Override
    public Coordinates build() throws IncorrectScriptInputException, ValidValuesException {
        Coordinates coordinates = new Coordinates(askX(), askY());
        if (!coordinates.validate()) throw new ValidValuesException();

        return coordinates;
    }


    public Integer askX() throws IncorrectScriptInputException {
        Integer x;
        var fileMode = CommandParser.fileMode();
        while (true) {
            try {
                console.println("Enter x (should be Integer and non null):");
                String xString = CommandParser.getScanner().nextLine().trim();

                x = Integer.parseInt(xString);
                break;
            } catch (NumberFormatException e) {
                console.println("X must be non null");
                if (fileMode) throw new IncorrectScriptInputException();
            }
        }
        return x;
    }

    public float askY() throws IncorrectScriptInputException {
        float y;
        var fileMode = CommandParser.fileMode();
        while (true) {
            try {
                console.println("Enter y (should be float):");
                String yString = CommandParser.getScanner().nextLine().trim();
                y = Float.parseFloat(yString);
                break;
            } catch (NumberFormatException e) {
                console.println("Y must be a float");
                if (fileMode) throw new IncorrectScriptInputException();
            }
        }
        return y;
    }

}
