import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * This class is used for defining which language to play the game in.
 *
 * <p>
 *     The application can be used in 2 languages:
 *      - English
 *      - Danish
 * </p>
 *
 */
public class LanguagePack {
    /**
     *
     */
    Hashtable<String, String> gameStrings;

    /**
     * This constructor is used for calling the method parseFile
     *
     * <p>
     *
     * </p>
     *
     * @param filePath Is the path of where the file is located.
     * @throws FileNotFoundException Is for if the program can't find the file.
     */
    public LanguagePack(String filePath) throws FileNotFoundException {
        parseFile(filePath);
    }

    // Create empty LanguagePack where everything returned is empty
    public LanguagePack() {}

    /**
     *
     * @param key Is used for...
     * @return Returning ...
     */
    public String getString(String key) {
        if (gameStrings == null) return "";
        return gameStrings.get(key);
    }

    public String getString(String key, Object... args) {
        if (gameStrings == null) return "";
        return String.format(gameStrings.get(key), args);
    }
    /**
     *
     * @param path Is the path of where the file is located.
     * @throws FileNotFoundException Is for if the program can't find the file.
     */
    private void parseFile(String path) throws FileNotFoundException {
        gameStrings = new Hashtable<String, String>();

        FileInputStream fileInputStream = new FileInputStream(path);
        Scanner s = new Scanner(fileInputStream);

        int lineCount = 0;
        String key = "";
        while (s.hasNext()) {
            String line = s.nextLine();

            if (line.equals("")) continue;

            if (lineCount % 2 == 0) { key = line; }
            else {
                gameStrings.put(key, line);
            }
            lineCount++;
        }

        s.close();
    }
}
