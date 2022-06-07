package sunshine.seasonexo.datas;

import org.bukkit.Bukkit;
import sunshine.seasonexo.utils.JsonManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemsManager {

    static Map dictItem;


    public static void createDictItem() throws FileNotFoundException {

        Object jsonMap = new JsonManager().readSimpleValue("/SeasonExo/items.json", "items");

        ArrayList<ArrayList<Object>> listOfLists = (ArrayList<ArrayList<Object>>) jsonMap;

        Map<String, Double> dict = new HashMap<>();

        for (int i=0; listOfLists.size() > i; i++) {
            dict.put((String) listOfLists.get(i).get(0), (Double) listOfLists.get(i).get(1));
        }

        dictItem = dict;
    }


    public static void addItemToFile(String material, double purcentage) throws IOException {

        // new coords
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(material);
        arrayList.add(purcentage);

        //list of coords
        ArrayList<ArrayList<Integer>> arrayListArrayList = new ArrayList<>();

        //add old coords to list of coords
        for (int i=0; dictItem.size() > i; i++) {
            ArrayList arrayListFor = new ArrayList<>();
            Object iKey = dictItem.keySet().toArray()[i];

            arrayListFor.add(iKey);
            arrayListFor.add(dictItem.get(iKey));

            arrayListArrayList.add(arrayListFor);
        }

        arrayListArrayList.add(arrayList);

        HashMap hashMap = new HashMap<>();
        hashMap.put("items", arrayListArrayList);

        JsonManager.writeFile("/SeasonExo/items.json", hashMap);

    }


    public static Map getDictItem() {
        return dictItem;
    }

    public static void reloadDict() throws FileNotFoundException {
        createDictItem();
    }

}
