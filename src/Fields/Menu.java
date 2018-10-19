package Fields;

import Resources.ResLoader;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Menu {
    float initialLine = -1;

    public void drawWait(GameContainer gmc, int x, int y) {
        int width = 200;
        int height = 20;
        Graphics graphics = gmc.getGraphics();
        graphics.drawRect(x, y, width, height);
        if (initialLine == -1)
            initialLine = x;
        if (initialLine < x + width + 100) {
            initialLine += 2;
        } else
            initialLine = -100;
        if (initialLine > x && initialLine < x + width) {
            graphics.setColor(new Color(100, 100, 100, 50));
        }
        for (int i = x; i < x + width; i++) {
            graphics.setColor(new Color(100, 100, 100, 50 / (Math.abs(i - initialLine) + 150)));
            graphics.setLineWidth(1);
            graphics.drawLine(i, y + 1, i, y + 19);
        }
        graphics.setColor(Color.white);
    }

    void init(GameContainer gmc) {

    }

    void update(GameContainer gmc) {

    }

    void render(GameContainer gmc) {
        int width = gmc.getWidth();
        int height = gmc.getHeight();
        drawWait(gmc, width / 2 - 100, height - 100);
        Image label1 = ResLoader.getMenuLabel();
        label1.draw(width - label1.getWidth(), 0);
        label1.getFlippedCopy(true, false).draw(0, 0);
    }
}
