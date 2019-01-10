import java.io.IOException;

/**
 * Used for running the application.
 *
 * <p>
 *     Creates a new instance of the class. Starts the game by calling the startGame-method.
 * </p>
 *
 * @author Hans, Nicklas, Rasmus, Mathias, Jeppe and Anton.
 * @version 1.0.1
 */
public class Main {

    /**
     * This method is for running the application.
     * @param args Something you need in order to run the application.
     * @throws IOException Is used for...
     */
    public static void main(String[] args) {
        try {
            GUI_View view = new GUI_View();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}