package sunshine.seasonexo.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class YamlManager {

    public static HashMap getHashMapFile(String path) {

        Yaml yaml = new Yaml();

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("plugins" + path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashMap yamlMap = yaml.load(inputStream);

        return yamlMap;

    }


    public static Object getSpecificValue(String path, String value) {

        HashMap hashMap = getHashMapFile(path);
        HashMap returnvalue = hashMap;

        String[] parts = value.split("\\.");

        for (int i=0 ; i < parts.length-1 ; i++) {
            returnvalue = (HashMap) returnvalue.get(parts[i]);
        }

        return returnvalue.get(parts[parts.length-1]);

    }

}
