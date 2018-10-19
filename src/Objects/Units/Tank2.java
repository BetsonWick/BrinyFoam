package Objects.Units;

import Fields.GameField;
import Objects.Unit;
import Resources.ResLoader;
import org.newdawn.slick.GameContainer;

public class Tank2 extends Unit {

    public Tank2(float xPos, float yPos, boolean friendly) {
        super(xPos, yPos, friendly);
        reward = 15;
        speed = 1;
        loadSpeed = 2000;
        radius = 550;
        type = "Soldier";
        if (friendly) {
            img1 = ResLoader.getTank2();
            aimX = xPos + 150;
            flipped = 1;
        } else {
            img1 = ResLoader.getTank2().getFlippedCopy(true, false);
            aimX = xPos - 150;
            flipped = -1;
        }
        xShot = xPos + flipped * 60;
        yShot = yPos - img1.getHeight() / 2 - 3;
        hp = 4;
    }

    @Override
    public void draw(GameContainer gmc) {
        super.draw(gmc);
        if (flipped == 1) img1 = ResLoader.getTank2();
        else img1 = ResLoader.getTank2().getFlippedCopy(true, false);
        img1.draw(super.xPos - img1.getWidth() / 2, super.yPos - img1.getHeight());
    }

    @Override
    public void behave() {
        super.behave();
        xShot = xPos + flipped * 60;
        yShot = yPos - img1.getHeight() / 2 - 3;
        if (super.condition.equals("Shooting")) {
            if (timer == -1)
                timer = System.currentTimeMillis();
            if (System.currentTimeMillis() - timer > loadSpeed) {
                GameField.rockets.add(new Rocket("Armorer", xShot, yShot, friendly , flipped));
                timer = System.currentTimeMillis();
            }
        }
    }
}
