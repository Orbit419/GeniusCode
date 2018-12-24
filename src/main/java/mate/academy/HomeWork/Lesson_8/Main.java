package mate.academy.HomeWork.Lesson_8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>();

        Fruit fruit1 = new Fruit();
        Fruit fruit2 = new Fruit();
        fruit1.setType(FruitType.APPLE);
        fruit1.setShelfLife(30);
        fruit1.setDate(new Date(118, 11, 15));
        fruit1.setPrice(100);
        fruit2.setType(FruitType.BANANA);
        fruit2.setShelfLife(10);
        fruit2.setDate(new Date(118, 11, 15));
        fruit2.setPrice(120);
        fruits.add(fruit1);
        fruits.add(fruit2);

        TradeShop shop = new TradeShop();

        shop.addFruits(fruits, "Supply_15-12-2018.json");
        System.out.println(shop.getFruitList());

        shop.save("Supply_All.json");

        shop.load("Supply_All.json");

        System.out.println(shop.getFruitList());

        fruits.clear();

        Fruit fruit3 = new Fruit();
        Fruit fruit4 = new Fruit();
        fruit3.setType(FruitType.CHERRY);
        fruit3.setShelfLife(20);
        fruit3.setDate(new Date(118, 11, 16));
        fruit3.setPrice(90);
        fruit4.setType(FruitType.COCONUT);
        fruit4.setShelfLife(60);
        fruit4.setDate(new Date(118, 11, 16));
        fruit4.setPrice(240);
        fruits.add(fruit3);
        fruits.add(fruit4);

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
