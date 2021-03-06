package Landscape;

import Fields.GameField;
import Resources.ResLoader;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Map1 {
    static Image img1 = ResLoader.getTile1();
    static Image img2 = ResLoader.getTile2();
    static Image alga1 = ResLoader.getAlga1();
    static Image background1 = ResLoader.getBackground1();
    static Image background2 = ResLoader.getBackground2();
    static Image background3 = ResLoader.getImage("Background3");
    static int width, height;
    static double amount;
    public static float level;
    static int upValue;

    public static void init(GameContainer gmc) {
        width = GameField.getSizeX();
        height = gmc.getHeight();
        gmc.getGraphics().setBackground(Color.decode("#121341"));
        level = height - img2.getHeight() + 5;
    }

    void drawCorals() {
        amount = Math.ceil(width / background3.getWidth()) + 1;
        for (int i = 0; i < amount; i++) {
            background3.draw(i * background3.getWidth() - GameField.cameraX / 3,
                    height - background3.getHeight() - upValue);
        }
    }

    public static void drawMap(GameContainer gmc) {
        upValue = 0;
        amount = Math.ceil(width / background2.getWidth()) + 1;
        for (int i = 0; i < amount; i++) {
            background2.draw(i * background2.getWidth() - GameField.cameraX / 2,
                    height - background2.getHeight() - upValue);
        }
        amount = Math.ceil(width / img1.getWidth()) + 1;
        for (int i = 0; i < amount; i++) {
            img1.draw(i * img1.getWidth(), height - img1.getHeight());
        }
        amount = Math.ceil(width / alga1.getWidth()) + 1;
        for (int i = 0; i < amount; i++) {
            alga1.draw(i * alga1.getWidth(), height - alga1.getHeight());
        }

    }

    public static void drawBorder(GameContainer gmc) {
        amount = Math.ceil(width / img2.getWidth()) + 1;
        for (int i = 0; i < amount; i++) {
            img2.draw(i * img2.getWidth(), height - img2.getHeight());
        }
    }

    public static float getLevel() {
        return level;
    }
}
