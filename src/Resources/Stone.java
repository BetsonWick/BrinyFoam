package Resources;

import Fields.GameField;

public class Stone extends Resource {

    public Stone(float xPos, float yPos) {
        super(xPos, yPos);
        img = ResLoader.getImage("Stone");
        type = Type.Stone;
    }

    @Override
    public void addResource(int amount) {
        GameField.addStone(amount);
    }
}
