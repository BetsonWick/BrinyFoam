package Objects;

import Fields.GameField;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Unit {
    protected float xPos, yPos, xShot, yShot, aimX;
    protected Image img1;
    protected float speed, radius;
    protected boolean friendly;
    protected long timer = -1;
    protected float loadSpeed;
    protected int flipped;
    public int reward, level, spawnTime;
    public boolean chosen = false;
    protected String condition = "Moving", amount = "", type;
    private int count = 0, counter = 1, step = 2;
    protected float hp;

    public Unit(float xPos, float yPos, boolean friendly) {
        this.xPos = xPos;
        this.yPos = yPos;
        aimX = xPos;
        this.friendly = friendly;
        level = 1;
        spawnTime = 2000;
    }

    public void draw(GameContainer gmc) {
        gmc.getGraphics().drawString(amount, xPos, yPos - img1.getHeight() * 2);
        gmc.getGraphics().drawString(condition, xPos, yPos - img1.getHeight() * 4);
        if (chosen)
            gmc.getGraphics().drawOval(xPos, yPos - img1.getHeight() * 2, 2, 2);
    }

    public void behave() {
        for (Unit unit : GameField.units) {
            if (!unit.equals(this) && unit.isFriendly() != friendly && Math.abs(unit.getxPos() - getxPos()) < radius) {
                count++;
            }
            if (!unit.equals(this) && unit.isFriendly() == friendly && Math.abs(unit.getxPos() - getxPos()) < step) {
                counter++;
            }
        }
        if (counter > 1)
            amount = counter + "";
        else amount = "";
        counter = 1;
        if (count > 0 && !type.equals("Worker"))
            condition = "Shooting";
        else if (!type.equals("Worker")) condition = "Moving";
        count = 0;
        if (condition.equals("Moving") || condition.equals("Working") || condition.equals("Extracting"))
            toAim();
        if (aimX >= xPos) flipped = 1;
        else flipped = -1;
    }

    private void toAim() {
        if (Math.abs(aimX - xPos) > img1.getWidth() / 4) {
            xPos += (aimX - xPos) * speed / Math.abs(aimX - xPos);
        }
    }

    public boolean isFriendly() {
        return friendly;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public float getSize() {
        return img1.getWidth();
    }

    public float getHeight() {
        return img1.getHeight();
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getHp() {
        return hp;
    }

    public void setChar(float[] chars) {
        speed = chars[0];
        loadSpeed = chars[1];
        radius = chars[2];
    }

    public int getReward() {
        return reward;
    }

    public void setAimX(float aimX) {
        this.aimX = aimX;
    }

    public String getType() {
        return type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isChosen() {
        return chosen;
    }
}
