package askers;

import collections.*;
import exceptions.EmptyFieldException;
import exceptions.IncorrectScriptInputException;
import exceptions.InvalidObjectException;
import exceptions.ValidValuesException;
import managers.CommandParser;
import managers.Console;

import java.util.Date;

public class SpaceMarineAsker extends AbstractAsker {

    private final Console console;

    public SpaceMarineAsker(Console console) {
        this.console = console;
    }

    public SpaceMarine build() throws IncorrectScriptInputException, EmptyFieldException, InvalidObjectException {
        String name = askName(); //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates = askCoordinates(); //Поле не может быть null
        float health = askHealth(); //Значение поля должно быть больше 0
        AstartesCategory category = askAstartesCategory(); //Поле не может быть null
        Weapon weaponType = askWeapon(); //Поле не может быть null
        MeleeWeapon meleeWeapon = askMeleeWeapon(); //Поле может быть null
        Chapter chapter = askChapter(); //Поле может быть null

        SpaceMarine spaceMarine = new SpaceMarine(name, coordinates, health, category, weaponType, meleeWeapon, chapter);
        if (!spaceMarine.validate()) throw new InvalidObjectException();

        return spaceMarine;
    }

    private Coordinates askCoordinates() throws InvalidObjectException, IncorrectScriptInputException, EmptyFieldException {
        return new CoordinatesAsker(console).build();
    }

    private Weapon askWeapon() throws InvalidObjectException, IncorrectScriptInputException, EmptyFieldException {
        return new WeaponAsker(console).build();
    }

    private MeleeWeapon askMeleeWeapon() throws InvalidObjectException, IncorrectScriptInputException, EmptyFieldException {
        return new MeleeWeaponAsker(console).build();
    }

    private AstartesCategory askAstartesCategory() throws InvalidObjectException, IncorrectScriptInputException, EmptyFieldException {
        return new AstartesCategoryAsker(console).build();
    }

    private Chapter askChapter() throws InvalidObjectException, IncorrectScriptInputException, EmptyFieldException {
        return new ChapterAsker(console).build();
    }

    private String askName() throws IncorrectScriptInputException {
        String name;
        var fileMode = CommandParser.fileMode();
        while (true) {
            try {
                console.println("Enter name:");
                name = CommandParser.getScanner().nextLine().trim();
                if (name.equals("")) throw new EmptyFieldException();
                break;
            } catch (EmptyFieldException e) {
                console.println("Name cannot be empty or null");
                if (fileMode) throw new IncorrectScriptInputException();
            }
        }
        return name;
    }

    private float askHealth() throws IncorrectScriptInputException {
        float health;
        var fileMode = CommandParser.fileMode();
        while (true) {
            try {
                console.println("Enter health (should be float and > 0):");
                String healthString = CommandParser.getScanner().nextLine().trim();
                if (healthString.equals("")) throw new NumberFormatException();
                float healthFloat = Float.parseFloat(healthString);
                if (!(healthFloat > 0)) throw new NumberFormatException();
//                try {
//                    health = Float.parseFloat(healthString);
//                    if (!(health>0)){
//                        throw new ValidValuesException();
//                    }
//                } catch (ValidValuesException e) {
//                    console.println("health must be float and > 0");
//                }
                health = healthFloat;
                break;
            } catch (NumberFormatException e) {
                console.println("health must be float and > 0");
                if (fileMode) throw new IncorrectScriptInputException();
            }
        }
        return health;
    }

}