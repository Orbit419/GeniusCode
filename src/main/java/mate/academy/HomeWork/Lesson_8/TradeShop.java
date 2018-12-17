package mate.academy.HomeWork.Lesson_8;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TradeShop {
    private List<Fruit> fruitList = new ArrayList<>();

    ObjectMapper mapper = new ObjectMapper();

    public void addFruits(List<Fruit> fruits, String pathToJsonFile) {
        try (PrintWriter printWriter = new PrintWriter(new File(pathToJsonFile))) {
            printWriter.write(mapper.writeValueAsString(fruits));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fruitList.addAll(fruits);
    }

    public void save(String pathToJsonFile) {
        try (PrintWriter printWriter = new PrintWriter(new File(pathToJsonFile))) {
            printWriter.write(mapper.writeValueAsString(fruitList));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String pathToJsonFile) {
        if (new File(pathToJsonFile).length() != 0) {
            fruitList.clear();

            try {
                fruitList = mapper.readValue(new File(pathToJsonFile), new TypeReference<List<Fruit>>() {});
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("File is empty!");
    }

    public List<Fruit> getSpoiledFruits(Date date) {
        return fruitList.stream()
                .filter(fruit -> dateDifference(date, fruit) > fruit.getShelfLife())
                .collect(Collectors.toList());
    }

    public List<Fruit> getSpoiledFruits(Date date, FruitType type) {
        return fruitList.stream()
                .filter(fruit -> fruit.getType() == type)
                .filter(fruit -> dateDifference(date, fruit) > fruit.getShelfLife())
                .collect(Collectors.toList());
    }

    public List<Fruit> getAvailableFruits(Date date) {
        return fruitList.stream()
                .filter(fruit -> dateDifference(date, fruit) < fruit.getShelfLife())
                .collect(Collectors.toList());
    }

    public List<Fruit> getAvailableFruits(Date date, FruitType type) {
        return fruitList.stream()
                .filter(fruit -> fruit.getType() == type)
                .filter(fruit -> dateDifference(date, fruit) < fruit.getShelfLife())
                .collect(Collectors.toList());
    }

    public List<Fruit> getAddedFruits(Date date) {
        List<Fruit> addedFruits = new ArrayList<>();
        for (Fruit elem : fruitList) {
            if (elem.getDate().equals(date))
                addedFruits.add(elem);
        }

        return addedFruits;
    }

    public List<Fruit> getAddedFruits(Date date, FruitType type) {
        List<Fruit> addedFruits = new ArrayList<>();
        for (Fruit elem : fruitList)
            if (elem.getType() == type)
                if (elem.getDate().equals(date))
                    addedFruits.add(elem);

        return addedFruits;
    }

    private int dateDifference(Date date, Fruit fruit) {
        return (int)((date.getTime() - fruit.getDate().getTime()) / (24 * 60 * 60 * 1000));
    }

    public List<Fruit> getFruitList() {
        return fruitList;
    }
}
