package Objects.Units;

import Fields.GameField;
import Objects.Unit;
import Resources.ResLoader;
import org.newdawn.slick.GameContainer;

public class Tank3 extends Unit {

    public Tank3(float xPos, float yPos, boolean friendly) {
        super(xPos, yPos, friendly);
        reward = 10;
        speed = 1.4f;
        loadSpeed = 1000;
        radius = 450;
        type = "Soldier";
        if (friendly) {
            img1 = ResLoader.getTank3();
            aimX = xPos + 150;
            flipped = 1;
        } else {
            img1 = ResLoader.getTank3().getFlippedCopy(true, false);
            aimX = xPos - 150;
            flipped = -1;
        }
        xShot = xPos;
        yShot = yPos - img1.getHeight() / 2 - 2;
        hp = 2;
    }

    @Override
    public void draw(GameContainer gmc) {
        super.draw(gmc);
        if (flipped == 1) img1 = ResLoader.getTank3();
        else img1 = ResLoader.getTank3().getFlippedCopy(true, false);
        img1.draw(super.xPos - img1.getWidth() / 2, super.yPos - img1.getHeight());
    }

    @Override
    public void behave() {
        super.behave();
        xShot = xPos;
        yShot = yPos - img1.getHeight() / 2 - 2;
        if (super.condition.equals("Shooting")) {
            if (timer == -1)
                timer = System.currentTimeMillis();
            if (System.currentTimeMillis() - timer > loadSpeed) {
                GameField.rockets.add(new Rocket("Usual", xShot, yShot, friendly , flipped));
                timer = System.currentTimeMillis();
            }
        }
    }
}
