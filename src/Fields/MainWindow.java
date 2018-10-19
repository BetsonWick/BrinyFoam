package Fields;

import org.newdawn.slick.*;

public class MainWindow extends BasicGame {
    public String state = "Game";
    GameField gameField;
    Menu menu;
    public static int Width, Height;

    public MainWindow(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameField = new GameField();
        menu = new Menu();
        Width = gameContainer.getWidth();
        Height = gameContainer.getHeight();
        gameField.init(gameContainer);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        switch (state) {
            case "Game":
                gameField.update(gameContainer);
                break;
            case "Menu":
                menu.update(gameContainer);
                break;
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        switch (state) {
            case "Game":
                gameField.render(gameContainer);
                break;
            case "Menu":
                menu.render(gameContainer);
                break;
        }
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer agc = new AppGameContainer(new MainWindow("Game"));
        agc.setDisplayMode(1280, 720, true);
        agc.setSmoothDeltas(true);
        agc.setShowFPS(false);
        agc.setTargetFrameRate(100);
        agc.start();
    }

    public static int getWidth() {
        return Width;
    }

    public static int getHeight() {
        return Height;
    }
}
