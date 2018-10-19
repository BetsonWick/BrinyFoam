package Resources;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;
import java.util.Map;

public class ResLoader {
    private static Image temple1, temple2, tank1, tank2, help1, ship1, hero, fish1, background1, background2;
    private static Image base1, base2, base3, base4, base5, basement1, tile1, tile2, alga1, bubble1, bA1, bB1, bC1;
    private static Image a1, a2, a3, a4, coin, button1, b1, b2, b3, b4, b5, b6;
    private static Image hero2, tank3, worker1, menuLabel, build1, build2, build3, stone1;
    private static float scale = 1;
    private static Map<String, Image> map;

    public static Image getImage(String value) {
        return map.get(value);
    }

    public static void load() throws SlickException {
        map = new HashMap<>();
        map.put("Panel", new Image("res\\Interface\\Panel.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        map.put("ChooseIdle", new Image("res\\Interface\\ChooseIdle.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        map.put("PanelSmall", new Image("res\\Interface\\PanelSmall.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        map.put("DroneI", new Image("res\\Interface\\DroneI.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        map.put("Build4", new Image("res\\Interface\\Build4.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        map.put("Stone", new Image("res\\Objects\\Map1\\Stalactits1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        map.put("Iron", new Image("res\\Objects\\Map1\\IronMine.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        map.put("Background3", new Image("res\\Objects\\Map1\\Background3.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        map.put("Drone", new Image("res\\Heroes\\Drone.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        map.put("StoneIcon", new Image("res\\Objects\\Stone1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        map.put("IronIcon", new Image("res\\Objects\\IronBar.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale));
        //Bullets
        bA1 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\anim1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        bB1 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\anim2.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        bC1 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\anim3.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        a1 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\a1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        a2 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\a2.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        a3 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\a3.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        a4 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\a4.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        b1 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\b1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        b2 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\b2.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        b3 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\b3.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        b4 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\b4.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        b5 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\b5.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        b6 = new Image("D:\\TDS\\res\\Bullets\\Rockets\\b6.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        //Objects
        ship1 = new Image("res\\Objects\\Ship1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        temple1 = new Image("res\\Objects\\Temple1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        temple2 = new Image("res\\Objects\\Temple2.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        base1 = new Image("res\\Objects\\Base1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        basement1 = new Image("res\\Objects\\Basement1.png",
                false, Image.FILTER_LINEAR).getScaledCopy(scale);
        base2 = new Image("res\\Objects\\Base2.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        base3 = new Image("res\\Objects\\Base3.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        base4 = new Image("res\\Objects\\Base4.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        base5 = new Image("res\\Objects\\Base5.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        //Interface
        coin = new Image("res\\Objects\\Coin.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        build1 = new Image("res\\Interface\\Build1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        build2 = new Image("res\\Interface\\Build2.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        build3 = new Image("res\\Interface\\Build3.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        button1 = new Image("res\\Interface\\Button1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        menuLabel = new Image("res\\Interface\\MenuLabel.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        //Landscape
        tile1 = new Image("res\\Objects\\Map1\\Tile1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        tile2 = new Image("res\\Objects\\Map1\\Tile2.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        alga1 = new Image("res\\Objects\\Map1\\Alga2.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        fish1 = new Image("res\\Objects\\Map1\\Fish1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        background1 = new Image("res\\Objects\\Map1\\Background1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        background2 = new Image("res\\Objects\\Map1\\Background2.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        bubble1 = new Image("res\\Objects\\Bubble1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        //Heroes
        tank1 = new Image("res\\Heroes\\Tank1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        tank2 = new Image("res\\Heroes\\Tank2.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        tank3 = new Image("res\\Heroes\\Tank3.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        help1 = new Image("res\\Heroes\\Help1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        hero = new Image("res\\Heroes\\Hero1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        hero2 = new Image("res\\Heroes\\Hero3.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);
        worker1 = new Image("res\\Heroes\\Worker1.png", false,
                Image.FILTER_NEAREST).getScaledCopy(scale);

    }

    public static Image getWorker1() {
        return worker1;
    }

    public static Image getButton1() {
        return button1;
    }

    public static Image getHero2() {
        return hero2;
    }

    public static Image getCoin() {
        return coin;
    }

    public static Image getTank3() {
        return tank3;
    }

    public static Image getbA1() {
        return bA1;
    }

    public static Image getbB1() {
        return bB1;
    }

    public static Image getbC1() {
        return bC1;
    }

    public static Image getTemple1() {
        return temple1;
    }

    public static Image getTemple2() {
        return temple2;
    }

    public static Image getBase1() {
        return base1;
    }

    public static Image getBase2() {
        return base2;
    }

    public static Image getBase3() {
        return base3;
    }

    public static Image getBase4() {
        return base4;
    }

    public static Image getBase5() {
        return base5;
    }

    public static Image getHelp1() {
        return help1;
    }

    public static Image getTank1() {
        return tank1;
    }

    public static Image getTank2() {
        return tank2;
    }

    public static Image getShip1() {
        return ship1;
    }

    public static Image getBasement1() {
        return basement1;
    }

    public static Image getBubble1() {
        return bubble1;
    }

    public static Image getBackground1() {
        return background1;
    }

    public static Image getBackground2() {
        return background2;
    }

    public static Image getTile1() {
        return tile1;
    }

    public static Image getTile2() {
        return tile2;
    }

    public static Image getAlga1() {
        return alga1;
    }

    public static Image getFish1() {
        return fish1;
    }

    public static void setScale(float scale) {
        ResLoader.scale = scale;
    }

    public static float getScale() {
        return scale;
    }

    public static Image getHero() {
        return hero;
    }

    public static Image[] getAnim1() {
        return new Image[]{a1, a2, a3, a4};
    }

    public static Image[] getAnim2() {
        return new Image[]{b1, b2, b3, b4, b5, b6};
    }

    public static Image getMenuLabel() {
        return menuLabel;
    }

    public static Image getBuild1() {
        return build1;
    }

    public static Image getBuild2() {
        return build2;
    }

    public static Image getBuild3() {
        return build3;
    }

    public static Image getStone1() {
        return stone1;
    }
}
