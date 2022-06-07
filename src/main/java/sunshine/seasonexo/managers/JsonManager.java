package sunshine.seasonexo.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class JsonManager {


    public static HashMap readFile(String path) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream("plugins" + path);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(isr);

        Gson gson = new GsonBuilder().create();
        HashMap<String, String> json = gson.fromJson(bufferedReader, HashMap.class);
        return (HashMap) json;

    }


    public static Object readSimpleValue(String path, String value) throws FileNotFoundException {
        HashMap hashMap = readFile(path);
        return hashMap.get(value);
    }


    public static void writeFile(String path, Map map) throws IOException {
        FileWriter fileWriter = new FileWriter("plugins" + path);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(map, fileWriter);
        fileWriter.close();
    }



}
