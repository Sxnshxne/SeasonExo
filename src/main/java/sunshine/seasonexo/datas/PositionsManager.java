package sunshine.seasonexo.datas;

import sunshine.seasonexo.utils.YamlManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PositionsManager {

    static ArrayList<ArrayList<String>> listOfPositions;

    public static void setListOfPositions() {

        Object yamlMap = new YamlManager().getSpecificValue("/SeasonExo/positions.yml", "coords");
        listOfPositions = (ArrayList<ArrayList<String>>) yamlMap;

    }

    public static List getRandomPositions() {

        Random random = new Random();

        int randomValue = random.nextInt(listOfPositions.size());

        List coords = new ArrayList();
        coords.add(Integer.parseInt(listOfPositions.get(randomValue).get(0)));
        coords.add(Integer.parseInt(listOfPositions.get(randomValue).get(1)));
        coords.add(Integer.parseInt(listOfPositions.get(randomValue).get(2)));

        return coords;
    }

    public static void reloadListOfPositions() { setListOfPositions(); }



}
