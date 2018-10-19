package Objects.Units;

import Fields.GameField;
import Landscape.Map1;
import Objects.Buildings.Base;
import Objects.Unit;
import Resources.ResLoader;
import Resources.Resource;
import org.newdawn.slick.GameContainer;

public class Worker1 extends Unit {
    long timer = -1;
    int wait = 1000, inventory = 0;
    float resX, resY;
    Resource res = null;

    public Worker1(float xPos, float yPos, boolean friendly) {
        super(xPos, yPos, friendly);
        speed = 1;
        hp = 3;
        type = "Worker";
        aimX = xPos;
        img1 = ResLoader.getImage("Drone");
        spawnTime = 5000;
    }

    @Override
    public void draw(GameContainer gmc) {
        if (flipped == 1) img1 = ResLoader.getImage("Drone");
        else img1 = ResLoader.getImage("Drone").getFlippedCopy(true, false);
        img1.draw(xPos - img1.getWidth() / 2, yPos - img1.getHeight());
        super.draw(gmc);
    }

    @Override
    public void behave() {
        super.behave();
        if (condition.equals("Moving"))
            timer = -1;
        if (condition.equals("Working")) {
            for (int i = 0; i < GameField.bases.size(); i++) {
                if (GameField.bases.get(i).getxPos() == aimX && Math.abs(aimX - xPos) <= img1.getWidth() / 4 &&
                        GameField.bases.get(i).getHp() < GameField.bases.get(i).getMaxHp()) {
                    if (timer == -1) timer = System.currentTimeMillis();
                    if (System.currentTimeMillis() - timer > wait) {
                        GameField.bases.get(i).setHp(GameField.bases.get(i).getHp() + 1);
                        timer = System.currentTimeMillis();
                    }
                }
                if (GameField.bases.get(i).getxPos() == aimX &&
                        GameField.bases.get(i).getHp() >= GameField.bases.get(i).getMaxHp()) {
                    condition = "Moving";
                }
            }
        }
        if (!condition.equals("Extracting")) {
            yPos = Map1.getLevel();
        }
        if (condition.equals("Extracting")) {
            if (inventory < 1) {
                res = GameField.getRes(aimX);
                if (timer == -1 && Math.abs(aimX - xPos) <= img1.getWidth() / 4) {
                    timer = System.currentTimeMillis();
                    if (res.getType() == Resource.Type.Stone
                            || res.getType() == Resource.Type.Iron) {
                        yPos = -50;
                    }
                }
                if (System.currentTimeMillis() - timer > wait * 3
                        && res != null
                        && Math.abs(aimX - xPos) <= img1.getWidth() / 4
                        && timer != -1) {
                    resX = aimX;
                    timer = System.currentTimeMillis();
                    inventory++;
                }
            }
            if (inventory == 1 && res != null) {
                yPos = Map1.getLevel();
                Base base = GameField.pickNearestBase(xPos, "Storage");
                if (condition.equals("Extracting")) {
                    if (base == null) {
                        condition = "Moving";
                        aimX = xPos;
                    } else {
                        aimX = base.getxPos();
                    }
                }
                if (condition.equals("Extracting") && Math.abs(aimX - xPos) <= img1.getWidth() / 4) {
                    inventory = 0;
                    res.addResource(level);
                    aimX = resX;
                    timer = -1;
                }
            }
        }
    }

    public void setAimX(float aimX) {
        this.aimX = aimX;
    }
}
