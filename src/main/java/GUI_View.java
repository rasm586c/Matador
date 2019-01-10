import gui_main.GUI;

public class GUI_View {
    public GUI gui;

    /**
     * Creates a new GUI_View class using an empty LanguagePack
     * */
    public GUI_View()
    {
        // Language pack will be defined by controller from user input
        GameBoard board = new GameBoard(new LanguagePack());
        gui = new GUI(board.getGuiFields());
    }

    /**
     * Retrives the GUI representation of the GUI_view (i.e. what we are manipulating)
     * */
    public GUI getGUI() {
        return gui;
    }
}
