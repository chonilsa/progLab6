package askers;

import collections.Chapter;
import collections.Coordinates;
import exceptions.EmptyFieldException;
import exceptions.IncorrectScriptInputException;
import exceptions.ValidValuesException;
import managers.CommandParser;
import managers.Console;

public class ChapterAsker extends AbstractAsker {

    private final Console console;

    public ChapterAsker(Console console) {
        this.console = console;
    }

    @Override
    public Chapter build() throws IncorrectScriptInputException, ValidValuesException {
        Chapter coordinates = new Chapter(askChapterName(), askMarinesCount());
        if (!coordinates.validate()) throw new ValidValuesException();

        return coordinates;
    }


    public String askChapterName() throws IncorrectScriptInputException {
        String name;
        var fileMode = CommandParser.fileMode();
        while (true) {
            try {
                console.println("Enter chapterName (should be String, non null and non empty):");
                String nameString = CommandParser.getScanner().nextLine().trim();
                if (nameString.equals("")) throw new NumberFormatException();
                name = nameString;
                break;
            } catch (NumberFormatException e) {
                console.println("chapterName must be String, non null and and non empty");
                if (fileMode) throw new IncorrectScriptInputException();
            }
        }
        return name;
    }

    public long askMarinesCount() throws IncorrectScriptInputException {
        long marinesCount;
        var fileMode = CommandParser.fileMode();
        while (true) {
            try {
                console.println("Enter marinesCount (should be > 0 and <= 1000):");
                String marinesCountString = CommandParser.getScanner().nextLine().trim();
                long marinesCountLong = Long.parseLong(marinesCountString);
                if (((marinesCountLong <= 0) || (marinesCountLong > 1000))) {
                    throw new NumberFormatException();
                }
                marinesCount = marinesCountLong;
                break;
            } catch (NumberFormatException e) {
                console.println("marinesCount must be > 0 and <= 1000");
                if (fileMode) throw new IncorrectScriptInputException();
            }
        }
        return marinesCount;
    }

}
