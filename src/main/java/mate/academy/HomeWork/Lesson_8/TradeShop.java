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

    public List<Fruit> getSpoiledFruits(Date date) {
        List<Fruit> spoiledFruits = new ArrayList<>();
        for (Fruit elem : fruitList) {
            if (((date.getTime() - elem.getDate().getTime()) / (24 * 60 * 60 * 1000)) > elem.getShelfLife())
                spoiledFruits.add(elem);
        }

        return spoiledFruits;
    }

    public List<Fruit> getSpoiledFruits(Date date, FruitType type) {
        List<Fruit> spoiledFruits = new ArrayList<>();
        for (Fruit elem : fruitList) {
            if (elem.getType() == type)
                if (((date.getTime() - elem.getDate().getTime()) / (24 * 60 * 60 * 1000)) > elem.getShelfLife())
                    spoiledFruits.add(elem);
        }

        return spoiledFruits;
    }

    public List<Fruit> getAvailableFruits(Date date) {
        List<Fruit> availableFruits = new ArrayList<>();
        for (Fruit elem : fruitList) {
            if (((date.getTime() - elem.getDate().getTime()) / (24 * 60 * 60 * 1000)) < elem.getShelfLife())
                availableFruits.add(elem);
        }
        return availableFruits;
    }

    public List<Fruit> getAvailableFruits(Date date, FruitType type) {
        List<Fruit> availableFruits = new ArrayList<>();
        for (Fruit elem : fruitList) {
            if (elem.getType() == type)
                if (((date.getTime() - elem.getDate().getTime()) / (24 * 60 * 60 * 1000)) < elem.getShelfLife())
                    availableFruits.add(elem);
        }
        return availableFruits;
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

    public List<Fruit> getFruitList() {
        return fruitList;
    }
}
