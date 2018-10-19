package Fields;

import Interface.Button;
import Landscape.Map1;
import Objects.Bubble;
import Objects.Buildings.Base;
import Objects.Fish;
import Objects.Hero;
import Objects.Unit;
import Objects.Units.Rocket;
import Objects.Units.Worker1;
import Resources.Iron;
import Resources.ResLoader;
import Resources.Resource;
import Resources.Stone;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class GameField {
    private Hero hero;
    private Base building;
    private GameContainer gmc;
    private static ArrayList<Bubble> bubbles;
    public static ArrayList<Unit> units;
    public static ArrayList<Rocket> rockets;
    public static ArrayList<Base> bases;
    private static ArrayList<Resource> resources;
    public static float cameraX, cameraY, step, width, height;
    private float xMouse, yMouse;
    private static int sizeX, money, stone, iron, coal, oil;
    private String typeMen = "", regime = "Survival", menLine = "";
    private static ArrayList<Button> buttons;
    private float chX, chY, chX1, chY1;
    private static ArrayList<Fish> fish;
    boolean haveSent;
    private int countButtons;

    private void setRegime() {
        switch (regime) {
            case "Waves":
                break;
            case "Survival":
                units.add(new Worker1(sizeX / 2, Map1.getLevel(), true));
                units.add(new Worker1(sizeX / 2 + step, Map1.getLevel(), true));
                units.add(new Worker1(sizeX / 2 - step, Map1.getLevel(), true));
                units.add(new Worker1(sizeX / 2 - 2 * step, Map1.getLevel(), true));
                units.add(new Worker1(sizeX / 2 + 2 * step, Map1.getLevel(), true));
                money = 10000;
                stone = 55;
                iron = 55;
                cameraX = -sizeX / 2 + width / 2;
                cameraY = 0;
                hero = new Hero(sizeX / 2, 200);
                break;
        }
    }

    public static Resource getRes(float aimX) {
        for (Resource resource : resources) {
            if (resource.xPos == aimX)
                return resource;
        }
        return null;
    }

    private void initParams(GameContainer gmc) {
        sizeX = 4096;
        countButtons = 4;
        width = gmc.getWidth();
        height = gmc.getHeight();
        step = ResLoader.getScale() * 12;
        Map1.init(gmc);
        bubbles = new ArrayList<>();
        units = new ArrayList<>();
        rockets = new ArrayList<>();
        buttons = new ArrayList<>();
        bases = new ArrayList<>();
        resources = new ArrayList<>();
        fish = new ArrayList<>();
        setRegime();
        makeZero();
    }

    private void fillLists(GameContainer gmc) {
        for (int i = 0; i < 2; i++) {
            fish.add(new Fish((float) Math.random() * gmc.getWidth(),
                    (float) Math.random() * gmc.getHeight(), "Fish1"));
        }
        resources.add(new Stone((float) (sizeX / 2 + Math.random() * 400 + 400),
                Map1.getLevel() - ResLoader.getImage("Stone").getHeight()));
        resources.add(new Stone((float) (sizeX / 2 - Math.random() * 400 - 400),
                Map1.getLevel() - ResLoader.getImage("Stone").getHeight()));
        resources.add(new Iron((float) (sizeX / 2 + Math.random() * 400 + 800),
                Map1.getLevel() - ResLoader.getImage("Iron").getHeight()));
        resources.add(new Iron((float) (sizeX / 2 - Math.random() * 400 - 800),
                Map1.getLevel() - ResLoader.getImage("Iron").getHeight()));
    }

    void init(GameContainer gmc) throws SlickException {
        ResLoader.setScale(2);
        ResLoader.load();
        initParams(gmc);
        fillLists(gmc);
        this.gmc = gmc;
    }

    private void makeZero() {
        chX = -1;
        chY = -1;
        chX1 = -1;
        chY1 = -1;
    }

    private void menLine(Graphics graphics, Image img) {
        graphics.setColor(Color.black);
        graphics.drawString(menLine, -cameraX + step / 3, height - img.getHeight() + step / 4);
        graphics.setColor(Color.white);
    }

    private void drawMen(String type, Graphics graphics) {
        Image img;
        switch (type) {
            case "Build":
                img = ResLoader.getImage("Panel");
                img.draw(-cameraX, height - img.getHeight());
                menLine(graphics, img);
                img = ResLoader.getBuild1();
                img.draw(-cameraX, height - img.getHeight());
                img = ResLoader.getBuild2();
                img.draw(-cameraX + img.getWidth(), height - img.getHeight());
                img = ResLoader.getBuild3();
                img.draw(-cameraX + img.getWidth() * 2, height - img.getHeight());
                img = ResLoader.getImage("Build4");
                img.draw(-cameraX + img.getWidth() * 3, height - img.getHeight());
                break;
            case "Centre":
                img = ResLoader.getImage("PanelSmall");
                img.draw(-cameraX, height - img.getHeight());
                menLine(graphics, img);
                img = ResLoader.getImage("DroneI");
                img.draw(-cameraX, height - img.getHeight());
                img = ResLoader.getImage("ChooseIdle");
                img.draw(-cameraX + img.getWidth(), height - img.getHeight());
                int count = 0, timer = 0, spawnTime = 0;
                for (Base base : bases) {
                    if (base.isCurrent && base.getType().equals("Centre")) {
                        count += base.units.size();
                        if (base.timer > timer)
                            timer = base.timer;
                        if (base.units.size() > 0)
                            spawnTime = base.units.get(0).spawnTime;
                    }
                }
                graphics.drawString(count + "", -cameraX, height - img.getHeight());
                if (spawnTime != 0)
                    graphics.drawString(Math.round(timer * 100 / spawnTime) + "%",
                            -cameraX + step, height - img.getHeight());
                break;
            case "Barracks":
                img = ResLoader.getImage("Panel");
                img.draw(-cameraX, height - img.getHeight());
                menLine(graphics, img);
                break;
            default:
                if (!typeMen.equals("") && !typeMen.equals("Placement")) {
                    img = ResLoader.getImage("PanelSmall");
                    img.draw(-cameraX, height - img.getHeight());
                    menLine(graphics, img);
                }
        }
    }

    private boolean touchedInterface() {
        boolean result = false;
        float size = ResLoader.getBuild1().getWidth();
        if (xMouse + cameraX < size * countButtons && yMouse > height - size)
            result = true;
        if (typeMen.equals("Placement"))
            result = false;
        return result;
    }

    private void selectIdlers() {
        for (Unit unit : units) {
            unit.chosen = false;
            if (unit.getType().equals("Worker") && unit.getCondition().equals("Moving")) {
                unit.chosen = true;
                typeMen = "Build";
            }
        }
    }

    private int numButton() {
        int size = ResLoader.getBuild1().getWidth();
        if (xMouse + cameraX < size && yMouse > height - size)
            return 1;
        if (xMouse + cameraX < size * 2 && yMouse > height - size &&
                xMouse + cameraX > size)
            return 2;
        if (xMouse + cameraX < size * 3 && yMouse > height - size &&
                xMouse + cameraX > size * 2)
            return 3;
        if (xMouse + cameraX < size * 4 && yMouse > height - size &&
                xMouse + cameraX > size * 3)
            return 4;
        return 0;
    }

    private void updateMen(String type) {
        Input in = gmc.getInput();
        switch (type) {
            default:
                countButtons = 2;
                break;
            case "Barracks":
                if (!in.isMousePressed(Input.MOUSE_LEFT_BUTTON))
                    break;
                countButtons = 4;
                switch (numButton()) {
                    default:
                        for (Base base : bases) {
                            if (base.isCurrent && base.getType().equals("Barracks")
                                    && money >= Base.getPrice("Tank" + numButton())) {
                                base.addToQueue("Tank" + numButton());
                                money -= Base.getPrice("Tank" + numButton());
                            }
                        }
                }
                break;
            case "Centre":
                if (!in.isMousePressed(Input.MOUSE_LEFT_BUTTON))
                    break;
                countButtons = 2;
                switch (numButton()) {
                    case 1:
                        for (Base base : bases) {
                            if (base.isCurrent && base.getType().equals("Centre")
                                    && money >= Base.getPrice("Worker")) {
                                base.addToQueue("Worker");
                                money -= Base.getPrice("Worker");
                            }
                        }
                        return;
                    case 2:
                        selectIdlers();
                        return;
                }
                break;
            case "Build":
                if (!in.isMousePressed(Input.MOUSE_LEFT_BUTTON))
                    break;
                countButtons = 4;
                switch (numButton()) {
                    case 1:
                        building = new Base(xMouse, Map1.getLevel(), true, "Barracks");
                        building.setHp(building.getMaxHp());
                        typeMen = "Placement";
                        return;
                    case 2:
                        building = new Base(xMouse, Map1.getLevel(), true, "Energy");
                        building.setHp(building.getMaxHp());
                        typeMen = "Placement";
                        return;
                    case 3:
                        building = new Base(xMouse, Map1.getLevel(), true, "Centre");
                        building.setHp(building.getMaxHp());
                        typeMen = "Placement";
                        return;
                    case 4:
                        building = new Base(xMouse, Map1.getLevel(), true, "Storage");
                        building.setHp(building.getMaxHp());
                        typeMen = "Placement";
                }
                break;
        }
    }

    private void setChosen(float x, float y) {
        if (chX == -1 && chY == -1) {
            chX = x;
            chY = y;
        }
        chX1 = x;
        chY1 = y;
    }

    private void updateUnits(GameContainer gmc) {
        int count = 0;
        for (int i = 0; i < units.size(); i++) {
            units.get(i).behave();
            if (units.get(i).isChosen())
                count++;
            if (units.get(i).getHp() <= 0) {
                if (!units.get(i).isFriendly()) {
                    money += units.get(i).getReward();
                }
                units.remove(i);
            }
        }
        if (gmc.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
            for (Unit unit : units) {
                if (unit.chosen) {
                    unit.setAimX(gmc.getInput().getMouseX() - cameraX);
                    unit.setCondition("Moving");
                }
            }
        }
        if (count == 0 && typeMen.equals("Build"))
            typeMen = "";
    }


    private void chooseBase(GameContainer gmc) {
        Input input = gmc.getInput();
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            for (Base base : bases) {
                if (base.isPressed(xMouse, yMouse))
                    base.isCurrent = true;
                else base.isCurrent = false;
            }
        }
    }

    private boolean checkFrame(float x, float y) {
        if (x > chX && x < chX1 && y > chY && y < chY1 && chX <= chX1 && chY <= chY1)
            return true;
        if (chX > chX1 && chY > chY1 && x > chX1 && x < chX && y > chY1 && y < chY)
            return true;
        else return false;
    }

    private void setPicked() {
        if (!typeMen.equals("Placement") && !touchedInterface())
            typeMen = "";
        boolean unitPicked = false;
        for (Unit unit : units) {
            if (!touchedInterface())
                unit.chosen = false;
            if (checkFrame(unit.getxPos(), unit.getyPos())) {
                unit.chosen = true;
                unitPicked = true;
                if (unit.getType().equals("Worker")) {
                    countButtons = 4;
                    typeMen = "Build";
                    menLine = "Builder:\n" + Math.round(unit.getHp()) + " hp";
                }
            }
        }
        for (Base base : bases) {
            if (!touchedInterface())
                base.isCurrent = false;
            if (checkFrame(base.getxPos(), base.getyPos()) && !unitPicked) {
                base.isCurrent = true;
                if (base.getHp() == base.getMaxHp()) {
                    typeMen = base.getType();
                    menLine = base.getType() + ":\n" + Math.round(base.getHp()) + " hp";
                }
            }
        }
    }

    private boolean checkAvailable(Base building) {
        boolean result = true;
        for (Base base : bases) {
            if (building.getxPos() + building.getSizeX() / 2 >
                    base.getxPos() - base.getSizeX() / 2
                    && building.getxPos() - building.getSizeX() / 2 <
                    base.getxPos() + base.getSizeX() / 2)
                result = false;
        }
        for (Resource res : resources) {
            if (building.getxPos() + building.getSizeX() / 2 >
                    res.getxPos() - res.getSizeX() / 2
                    && building.getxPos() - building.getSizeX() / 2 <
                    res.getxPos() + res.getSizeX() / 2)
                result = false;

        }
        return result;
    }

    private void checkBuilding(GameContainer gmc) {
        if (building != null && typeMen.equals("Placement")) {
            building.setxPos(xMouse);
            if (gmc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)
                    && building.isAvailable(stone, iron, money)
                    && checkAvailable(building)
                    && !touchedInterface()) {
                building.setHp(1);
                bases.add(building);
                money -= building.getThisPrice();
                stone -= building.getStone();
                iron -= building.getIron();
                building = null;
                typeMen = "";
            }
            if (gmc.getInput().isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
                building = null;
                typeMen = "";
            }
        } else if (!typeMen.equals("Placement")) {
            building = null;
        }
    }

    void update(GameContainer gmc) {
        Input input = gmc.getInput();
        xMouse = input.getMouseX() - cameraX;
        yMouse = input.getMouseY();
        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !typeMen.equals("Placement"))
            setChosen(xMouse, yMouse);
        else {
            if (chX != -1)
                setPicked();
            makeZero();
        }
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gmc.exit();
        }
        hero.behave(gmc);
        for (int i = 0; i < rockets.size(); i++) {
            rockets.get(i).behave();
            if (rockets.get(i).isShot()) {
                rockets.remove(i);
            }
        }
        for (Bubble bubble : bubbles) {
            bubble.behave();
        }
        for (Fish fish : fish) {
            fish.behave(gmc);
        }
        updateBases(gmc);
        updateRes(gmc);
        updateUnits(gmc);
        for (Button button : buttons) {
            button.check(gmc);
        }
        checkBuilding(gmc);
        updateMen(typeMen);
    }

    public static void addStone(int stone1) {
        stone += stone1;
    }

    public static void addIron(int iron1) {
        iron += iron1;
    }

    private void drawAll() {
        for (Rocket rocket : rockets) {
            rocket.draw();
        }
        for (Bubble bubble : bubbles) {
            bubble.draw();
        }
        for (Fish fish : fish) {
            fish.draw();
        }
        for (Base base : bases) {
            base.draw(gmc);
        }
        for (Resource resource : resources) {
            resource.draw();
        }
        for (Unit unit : units) {
            unit.draw(gmc);
        }
        for (Button button : buttons) {
            button.draw();
        }
    }

    void render(GameContainer gmc) {
        Graphics graphics = gmc.getGraphics();
        graphics.translate(cameraX, cameraY);
        Map1.drawMap(gmc);
        drawAll();
        drawBuilds(gmc);
        Map1.drawBorder(gmc);
        hero.draw(gmc);
        Image img1 = ResLoader.getCoin();
        img1.draw(step - cameraX - img1.getWidth() / 2, step + cameraY);
        img1 = ResLoader.getImage("StoneIcon");
        img1.draw(step - cameraX - img1.getWidth() / 2, step * 2.05f + cameraY);
        img1 = ResLoader.getImage("IronIcon");
        img1.draw(step - cameraX - img1.getWidth() / 2, step * 3.1f + cameraY);
        drawMen(typeMen, graphics);
        graphics.drawString("  " + money, step - cameraX, step + cameraY);
        graphics.drawString("  " + stone, step - cameraX, step * 2 + cameraY);
        graphics.drawString("  " + iron, step - cameraX, step * 3 + cameraY);
        graphics.drawRect(chX, chY, chX1 - chX, chY1 - chY);
    }

    private void updateBases(GameContainer gmc) {
        for (Base base : bases) {
            if (base.getHp() == base.getMaxHp())
                base.behave(gmc);
            else if (base.isPressed(xMouse, yMouse) &&
                    gmc.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)
                    && !touchedInterface()) {
                if (pickNearest(base.getxPos(), true) != null) {
                    int index = units.indexOf(pickNearest(base.getxPos(), true));
                    units.get(index).setAimX(base.getxPos());
                    units.get(index).setCondition("Working");
                    units.get(index).chosen = false;
                }
            }
        }
    }

    private void updateRes(GameContainer gmc) {
        for (Resource res : resources) {
            if (res.isPressed(xMouse, yMouse) &&
                    gmc.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)
                    && !touchedInterface()) {
                if (pickNearest(res.getxPos(), true) != null) {
                    int index = units.indexOf(pickNearest(res.getxPos(), true));
                    units.get(index).setAimX(res.getxPos());
                    units.get(index).setCondition("Extracting");
                    units.get(index).chosen = false;
                }
            }
        }
    }

    private void stopChosen() {
        for (Unit unit : units) {
            if (unit.isChosen()) {
                unit.setAimX(unit.getxPos());
            }
        }
    }

    private Unit pickNearest(float xPos, boolean friend) {
        Unit un1 = null;
        float distance = sizeX;
        for (Unit unit : units) {
            if (Math.abs(unit.getxPos() - xPos) < distance
                    && unit.getType().equals("Worker") && friend == unit.isFriendly()
                    && unit.isChosen()) {
                distance = Math.abs(unit.getxPos() - xPos);
                un1 = unit;
            }
        }
        return un1;
    }

    public static Base pickNearestBase(float xPos, String type) {
        Base base = null;
        float distance = sizeX;
        String curType = "";
        for (Base bas : bases) {
            curType = bas.getType();
            if (Math.abs(bas.getxPos() - xPos) < distance
                    && bas.getHp() == bas.getMaxHp()) {
                if ((curType.equals("Storage") || curType.equals("Centre") && type.equals("Storage"))) {
                    distance = Math.abs(bas.getxPos() - xPos);
                    base = bas;
                } else if (curType.equals(type)) {
                    distance = Math.abs(bas.getxPos() - xPos);
                    base = bas;
                }
            }
        }
        return base;
    }

    private void drawBuilds(GameContainer gmc) {
        if (building != null) {
            building.draw(gmc);
        }
    }

    public static int getSizeX() {
        return sizeX;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        GameField.money = money;
    }

    public static float getCameraX() {
        return cameraX;
    }

    public static float getCameraY() {
        return cameraY;
    }
}
