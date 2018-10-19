package Objects.Units;

import Fields.GameField;
import Landscape.Map1;
import Resources.ResLoader;
import org.newdawn.slick.Image;

public class Rocket {
    public String type;
    float xPos, yPos, originX;
    float speed, distance = 0, maxDistance;
    float damage;
    Image img1;
    Image[] anim;
    boolean friendly;
    long timer = -1;
    int index = 0;
    public boolean shot = false;
    int flipped;

    public Rocket(String type, float xPos, float yPos, boolean friendly, int flipped) {
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.originX = xPos;
        this.friendly = friendly;
        this.flipped = flipped;
        switch (type) {
            case "Usual":
                speed = 3;
                damage = 1;
                maxDistance = 550;
                img1 = ResLoader.getbA1();
                anim = ResLoader.getAnim1();
                break;
            case "Armorer":
                speed = 4;
                damage = 1.5f;
                maxDistance = 700;
                img1 = ResLoader.getbB1();
                anim = ResLoader.getAnim1();
                break;
            case "Bomb":
                speed = 2;
                damage = 2;
                maxDistance = 400;
                img1 = ResLoader.getbC1();
                anim = ResLoader.getAnim1();
                break;
            case "Bah":
                speed = 0;
                damage = 0;
                maxDistance = 1;
                img1 = ResLoader.getbC1();
                anim = ResLoader.getAnim2();
                break;
            default:
                speed = 0;
                damage = 0;
                img1 = ResLoader.getbA1();
        }
    }

    public void draw() {
        if (timer == -1)
            timer = System.currentTimeMillis();
        if (!type.equals("Bah"))
            img1.draw(xPos - img1.getWidth() / 2, yPos - img1.getHeight() / 2);
        if (index < anim.length - 1 && !type.equals("Bomb")) {
            if (friendly)
                anim[index].draw(originX, yPos - anim[index].getHeight() / 2);
            else
                anim[index].getFlippedCopy(true, false).draw(originX - anim[index].getWidth(),
                        yPos - anim[index].getHeight() / 2);
            if (System.currentTimeMillis() - timer > 100) {
                timer = System.currentTimeMillis();
                index++;
            }
        } else if (type.equals("Bah")) {
            shot = true;
        }
    }

    public void behave() {
        if (!type.equals("Bomb"))
            xPos += speed * flipped;
        else yPos += speed;
        distance += speed;
        for (int i = 0; i < GameField.units.size(); i++) {
            if (GameField.units.get(i).isFriendly() != friendly
                    && Math.abs(GameField.units.get(i).getxPos() - xPos) < GameField.units.get(i).getSize() / 2 &&
                    Math.abs(GameField.units.get(i).getyPos() - yPos) < GameField.units.get(i).getHeight()) {
                shot = true;
                GameField.units.get(i).setHp(GameField.units.get(i).getHp() - damage);
                break;
            }
        }
        if (distance >= maxDistance)
            shot = true;
        if (type.equals("Bomb") && yPos >= Map1.getLevel()) {
            shot = true;
        }
        if (shot && type.equals("Bomb")) {
            GameField.rockets.add(new Rocket("Bah", xPos, yPos, true, 0));
        }
    }

    public boolean isShot() {
        return shot;
    }
}
