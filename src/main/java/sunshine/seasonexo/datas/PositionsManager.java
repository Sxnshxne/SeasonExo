package sunshine.seasonexo.datas;

import org.bukkit.Bukkit;
import sunshine.seasonexo.utils.JsonManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class PositionsManager {

    static ArrayList<ArrayList<Integer>> listOfPositions;

    public static void setListOfPositions() throws FileNotFoundException {

        HashMap hashMap = (HashMap) new JsonManager().readFile("/SeasonExo/positions.json");
        listOfPositions = (ArrayList<ArrayList<Integer>>) hashMap.get("positions");

    }

    public static List getRandomPositions() {

        Random random = new Random();

        int randomValue = random.nextInt(listOfPositions.size());

        List coords = new ArrayList<>();

        coords.add(listOfPositions.get(randomValue).get(0));
        coords.add(listOfPositions.get(randomValue).get(1));
        coords.add(listOfPositions.get(randomValue).get(2));

        return coords;
    }


    public static void addPositionToFile(Double x, Double y, Double z) throws IOException {

        ArrayList arrayList = new ArrayList<>();
        arrayList.add(x);
        arrayList.add(y);
        arrayList.add(z);

        setListOfPositions();
        listOfPositions.add(arrayList);

        HashMap hashMap = new HashMap<>();
        hashMap.put("positions", listOfPositions);

        JsonManager.writeFile("/SeasonExo/positions.json", hashMap);

    }

    public static void reloadListOfPositions() throws FileNotFoundException { setListOfPositions(); }



}
