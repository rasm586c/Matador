package Model;

import java.io.IOException;

public class LanguagePackFactory {
    public enum LanguageType {
        Danish,
        English
    }

    private LanguageType type;
    private GameBoard board;
    private LanguagePack languagePack;

    public LanguagePackFactory(LanguageType type) throws IOException {
        this.type = type;
        languagePack = createLanguagePack();
        board = createBoard();
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
