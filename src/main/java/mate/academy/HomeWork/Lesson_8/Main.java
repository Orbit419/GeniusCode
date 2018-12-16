package mate.academy.HomeWork.Lesson_8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>();

        fruits.add(new Fruit());
        fruits.add(new Fruit());

        fruits.get(0).setType(FruitType.APPLE);
        fruits.get(0).setShelfLife(30);
        fruits.get(0).setDate(new Date(118, 11, 15));
        fruits.get(0).setPrice(100);
        fruits.get(1).setType(FruitType.BANANA);
        fruits.get(1).setShelfLife(10);
        fruits.get(1).setDate(new Date(118, 11, 15));
        fruits.get(1).setPrice(120);

        TradeShop shop = new TradeShop();

        shop.addFruits(fruits, "Supply_15-12-2018.json");
        System.out.println(shop.getFruitList());

        shop.save("Supply_All.json");

        shop.load("Supply_All.json");

        System.out.println(shop.getFruitList());

        fruits.clear();

        fruits.add(new Fruit());
        fruits.add(new Fruit());
        fruits.get(0).setType(FruitType.CHERRY);
        fruits.get(0).setShelfLife(20);
        fruits.get(0).setDate(new Date(118, 11, 16));
        fruits.get(0).setPrice(90);
        fruits.get(1).setType(FruitType.COCONUT);
        fruits.get(1).setShelfLife(60);
        fruits.get(1).setDate(new Date(118, 11, 16));
        fruits.get(1).setPrice(240);

        shop.addFruits(fruits, "Supply_16-12-2018.json");
        System.out.println(shop.getFruitList());

        shop.save("Supply_All.json");

        shop.load("Supply_All.json");

        System.out.println(shop.getFruitList());

        System.out.println(shop.getSpoiledFruits(new Date(119, 0, 30)));
        System.out.println(shop.getSpoiledFruits(new Date(119, 0, 30), FruitType.APPLE));

        System.out.println(shop.getAvailableFruits(new Date(119, 0, 10)));
        System.out.println(shop.getAvailableFruits(new Date(119, 0, 10), FruitType.APPLE));

        System.out.println(shop.getAddedFruits(new Date(118, 11, 16)));
        System.out.println(shop.getAddedFruits(new Date(118, 11, 16), FruitType.CHERRY));
    }
}
