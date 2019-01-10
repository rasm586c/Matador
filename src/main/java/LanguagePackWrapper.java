import gui_main.GUI;

import java.io.IOException;

public class LanguagePackWrapper {
    public enum LanguageType {
        Danish,
        English
    }

    private LanguageType type;
    private GameBoard board;
    private LanguagePack languagePack;

    public LanguagePackWrapper(LanguageType type) throws IOException {
        this.type = type;
        languagePack = createLanguagePack();
        board = createBoard();
    }

    /**
     * Opdates the text for every field in the GUI
     * @param gui
     */
    public void updateGUI(GUI gui) {
        for (int j = 0; j < gui.getFields().length; j++) {
            gui.getFields()[j].setDescription(board.getGuiFields()[j].getDescription());
            gui.getFields()[j].setSubText(board.getGuiFields()[j].getSubText());
            gui.getFields()[j].setTitle(board.getGuiFields()[j].getTitle());
        }
    }

    public LanguagePack getLanguagePack() {
        return languagePack;
    }

    private LanguagePack createLanguagePack() throws IOException {
        return new LanguagePack(String.format("resources/%s_game_strings.txt", getLanguage()));
    }

    private GameBoard createBoard() throws IOException {
        return new GameBoard(languagePack);
    }

    private String getLanguage() {
        switch (type) {
            case Danish: return "DA";
            case English: return "EN";
        }
        return null;
    }

    public GameBoard getBoard() { return board; }
}
