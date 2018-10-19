package Objects.Buildings;

import Fields.GameField;
import Objects.Unit;
import Objects.Units.Tank1;
import Objects.Units.Tank2;
import Objects.Units.Tank3;
import Objects.Units.Worker1;
import Resources.ResLoader;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public class Base {
    private float xPos, yPos;
    private boolean friendly;
    private Image img1;
    public int timer = 0;
    private String type;
    private float hp, maxHp;
    public boolean isCurrent = false;
    private int await, price1, price2, price3, price4, thisPrice;
    private int stone, iron;
    private float[] chars1 = {1, 2000, 450};
    private float[] chars2 = {0.8f, 2000, 550};
    private float[] chars3 = {1.4f, 1000, 450};
    public int level = 1;
    public ArrayList<Unit> units;

    public static int getPrice(String type) {
        int price = 0;
        switch (type) {
            case "Tank1":
                break;
            case "Worker":
                price = 20;
                break;
        }
        return price;
    }

    public void addToQueue(String type) {
        switch (type) {
            case "Worker":
                units.add(new Worker1(xPos, yPos, friendly));
                break;
            case "Tank1":
                units.add(new Tank1(xPos, yPos, friendly));
                break;
            case "Tank2":
                units.add(new Tank2(xPos, yPos, friendly));
                break;
            case "Tank3":
                units.add(new Tank3(xPos, yPos, friendly));
                break;
        }
    }

    public Base(float xPos, float yPos, boolean friendly, String type) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.friendly = friendly;
        this.type = type;
        units = new ArrayList<>();
        switch (type) {
            case "Centre":
                img1 = ResLoader.getBase1();
                maxHp = 25;
                await = 500;
                thisPrice = 60;
                stone = 10;
                iron = 10;
                break;
            case "Barracks":
                img1 = ResLoader.getBase4();
                maxHp = 15;
                if (friendly)
                    await = 500;
                else await = 2000;
                thisPrice = 50;
                stone = 5;
                iron = 7;
                break;
            case "Energy":
                img1 = ResLoader.getBase3();
                maxHp = 10;
                await = 1000;
                thisPrice = 50;
                stone = 2;
                iron = 8;
                break;
            case "Storage":
                img1 = ResLoader.getBase5();
                maxHp = 5;
                thisPrice = 40;
                stone = 5;
                iron = 2;
                break;
        }
        price1 = 10;
        price2 = 25;
        price3 = 15;
        price4 = 20;
    }

    public void draw(GameContainer gmc) {
        Graphics graphics = gmc.getGraphics();
        if (hp < maxHp)
            img1.setAlpha(0.7f);
        else img1.setAlpha(1);
        img1.draw(xPos - img1.getWidth() / 2, yPos - img1.getHeight());
        graphics.drawString(price1 + " " + price2 + " " + price3 + " " + price4, 10, 300);
        if (isCurrent) {
            graphics.setColor(Color.red);
            graphics.drawOval(xPos - 5, yPos - img1.getHeight() * 1.5f, 10, 10);
            graphics.setColor(Color.white);
        }
    }

    public boolean isPressed(float x, float y) {
        boolean result = false;
        float distX, distY;
        distX = xPos - x;
        distY = yPos - y;
        float radius = (img1.getWidth() + img1.getHeight()) / 4;
        if (distY < Math.sqrt(radius * radius - distX * distX)) {
            result = true;
        }
        return result;
    }

    void createUnit() {

    }

    public void behave(GameContainer gmc) {
        switch (type) {
            case "Energy":
                timer += 10;
                if (timer > await) {
                    if (friendly) {
                        GameField.setMoney(GameField.getMoney() + 1);
                        timer = 0;
                    }
                }
                break;
            default:
                if (units.size() > 0) {
                    timer += 10;
                    int lastIndex = units.size() - 1;
                    if (timer > units.get(lastIndex).spawnTime) {
                        GameField.units.add(units.get(lastIndex));
                        units.remove(units.get(lastIndex));
                        timer = 0;
                    }
                }
                break;
        }
    }


    public void upgradeChars1() {

    }

    public void upgradeChars2() {

    }

    public void upgradeChars3() {
        chars3[0] *= 1.2f;
        chars3[1] /= 1.2f;
        chars3[2] *= 1.2f;
    }

    public float getHp() {
        return hp;
    }

    public float getMaxHp() {
        return maxHp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public int getThisPrice() {
        return thisPrice;
    }

    public boolean isAvailable(int stone1, int iron1, int money1) {
        return stone1 >= stone && iron1 >= iron && money1 >= thisPrice;
    }

    public float getSizeX() {
        return img1.getWidth();
    }

    public float getSizeY() {
        return img1.getHeight();
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public String getType() {
        return type;
    }

    public int getStone() {
        return stone;
    }

    public int getIron() {
        return iron;
    }
}
