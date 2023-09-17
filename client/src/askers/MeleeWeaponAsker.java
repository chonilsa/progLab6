package askers;

import collections.MeleeWeapon;
import exceptions.EmptyFieldException;
import exceptions.IncorrectScriptInputException;
import managers.CommandParser;
import managers.Console;

public class MeleeWeaponAsker extends AbstractAsker {
    private final Console console;

    public MeleeWeaponAsker(Console console) {
        this.console = console;
    }

    @Override
    public MeleeWeapon build() throws IncorrectScriptInputException, EmptyFieldException {
        MeleeWeapon meleeWeapon = askMeleeWeapon();
        return meleeWeapon;
    }

    public MeleeWeapon askMeleeWeapon() throws IncorrectScriptInputException {
        MeleeWeapon meleeWeapon;
        String meleeWeaponString;
        var fileMode = CommandParser.fileMode();
        while (true) {
            try {
                console.println("Enter meleeWeapon type (" + MeleeWeapon.names() + "):");
                meleeWeaponString = CommandParser.getScanner().nextLine().trim();
                if (meleeWeaponString.equals("")) throw new EmptyFieldException();
                meleeWeapon = MeleeWeapon.valueOf(meleeWeaponString.toUpperCase());
                break;

            } catch (IllegalArgumentException e) {
                console.println("Invalid meleeWeapon type");
                if (fileMode) throw new IncorrectScriptInputException();
            } catch (EmptyFieldException e) {
                console.println("Field can't be empty");
                if (fileMode) throw new IncorrectScriptInputException();
            }
        }
        return meleeWeapon;
    }
}
