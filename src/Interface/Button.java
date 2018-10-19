package Interface;

import Fields.GameField;
import Objects.Buildings.Base;
import Resources.ResLoader;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Button {
    public float xPos, yPos;
    public String type;
    private Image img1;
    private float sizeX, sizeY;

    public Button(float xPos, float yPos, String type) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = type;
        switch (type) {
            case "Upgrade":
                img1 = ResLoader.getButton1();
                break;
        }
        sizeX = img1.getWidth();
        sizeY = img1.getHeight();
    }

    public void draw() {
        img1.draw(xPos - GameField.getCameraX(), yPos);
    }

    public void check(GameContainer gmc) {
        float mouseX = gmc.getInput().getMouseX();
        float mouseY = gmc.getInput().getMouseY();
        if (gmc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) &&
                mouseX > xPos && mouseX < xPos + sizeX && mouseY > yPos && mouseY < yPos + sizeY) {
            /*switch (type) {
                case "Upgrade":
                    if (GameField.getMoney() >= Base.getPrice()) {
                        GameField.setMoney(GameField.getMoney() - Base.getPrice());
                        GameField.bases.get(0).upgradeChars3();
                    }
            }*/
        }
    }
}
