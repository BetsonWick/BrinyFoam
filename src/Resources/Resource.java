package Resources;

import org.newdawn.slick.Image;

public class Resource {
    public float xPos, yPos;
    public Image img;
    Type type;

    public enum Type {
        Stone, Gold, Coal, Iron
    }

    public Resource(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void draw() {
        img.draw(xPos - img.getWidth() / 2, yPos);
    }

    public void addResource(int amount) {

    }

    public boolean isPressed(float x, float y) {
        boolean result = false;
        float distX, distY;
        distX = xPos - x;
        distY = yPos - y;
        float radius = (img.getWidth() + img.getHeight()) / 4;
        if (distY < Math.sqrt(radius * radius - distX * distX)) {
            result = true;
        }
        return result;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public Type getType() {
        return type;
    }

    public float getSizeX() {
        return img.getWidth();
    }
}
