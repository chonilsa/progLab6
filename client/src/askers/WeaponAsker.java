package askers;

import collections.Weapon;
import exceptions.EmptyFieldException;
import exceptions.IncorrectScriptInputException;
import managers.CommandParser;
import managers.Console;

public class WeaponAsker extends AbstractAsker {
    private final Console console;

    public WeaponAsker(Console console) {
        this.console = console;
    }

    @Override
    public Weapon build() throws IncorrectScriptInputException, EmptyFieldException {
        Weapon weapon = askWeapon();
        return weapon;
    }

    public Weapon askWeapon() throws IncorrectScriptInputException {
        Weapon weapon;
        String weaponString;
        var fileMode = CommandParser.fileMode();
        while (true) {
            try {
                console.println("Enter weapon type (" + Weapon.names() + "):");
                weaponString = CommandParser.getScanner().nextLine().trim();
                if (weaponString.equals("")) throw new EmptyFieldException();
                weapon = Weapon.valueOf(weaponString.toUpperCase());
                break;

            } catch (IllegalArgumentException e) {
                console.println("Invalid weapon type");
                if (fileMode) throw new IncorrectScriptInputException();
            } catch (EmptyFieldException e) {
                console.println("Field can't be empty");
                if (fileMode) throw new IncorrectScriptInputException();
            }
        }
        return weapon;
    }
}
