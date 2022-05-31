package sunshine.seasonexo.datas;

import sunshine.seasonexo.utils.YamlManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemDictManager {

    static Map dictItemYAML;

    private static Map createDictItem() {

        //items dict from YAML File : items.yml
        //--> dict<MaterialName,PurcentageOfDrop>

        Object yamlMap = new YamlManager().getSpecificValue("/SeasonExo/items.yml", "items");

        ArrayList<ArrayList<String>> listOfLists = (ArrayList<ArrayList<String>>) yamlMap;
        Map<String, Integer> dict = new HashMap<String, Integer>();

        for (int i=0; listOfLists.size() > i; i++) {
            dict.put(listOfLists.get(i).get(0), Integer.valueOf(listOfLists.get(i).get(1)));
        }

        return dict;
    }

    public static Map getDictItem() {
        return dictItemYAML;
    }

    public static void reloadDict() {
        dictItemYAML = createDictItem();
    }

}
