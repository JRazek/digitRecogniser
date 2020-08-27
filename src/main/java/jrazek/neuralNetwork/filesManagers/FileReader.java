package jrazek.neuralNetwork.filesManagers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public static List<String> readTextFile(String path){
        List<String> lines = new LinkedList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                lines.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lines;
    }
    public static BufferedImage readImageFile(String path) throws IOException {
       // System.out.println(path);
        return ImageIO.read(new File(path));
    }
    public static JSONObject readJSONFile(String path) throws IOException{
        JSONParser jsonParser = new JSONParser();

        try{
            java.io.FileReader reader = new java.io.FileReader(path);
            return (JSONObject) jsonParser.parse(reader);
        } catch (ParseException | IOException e) {
            //e.printStackTrace();
            throw new IOException();
        }
    }
    public static String formatJSON(String unformatted){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(JsonParser.parseString(unformatted));
    }
}

