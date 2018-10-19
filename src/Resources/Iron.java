package Resources;

import Fields.GameField;

public class Iron extends Resource {

    public Iron(float xPos, float yPos) {
        super(xPos, yPos);
        img = ResLoader.getImage("Iron");
        type = Type.Iron;
    }

    @Override
    public void addResource(int amount) {
        GameField.addIron(amount);
    }
}
