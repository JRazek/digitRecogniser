package jrazek.neuralNetwork.filesManagers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static jrazek.neuralNetwork.utils.Rules.*;
import static jrazek.neuralNetwork.utils.Utils.randomDouble;
import static jrazek.neuralNetwork.utils.Utils.randomInt;

public class FileDecoder {
    private Map<Integer, List<String>> digitsPaths;
    private Map<Integer, Integer> currentDigitInList;
    private int iteration;
    private int currentDigit;

    private int togo;
    public FileDecoder(){
        reset();
    }
    public void reset(){
        digitsPaths = new HashMap<>();
        currentDigitInList = new HashMap<>();
        iteration = 0;
        currentDigit = 0;
        togo = 0;
        int smallest = Integer.MAX_VALUE;
        for(int i = 0; i < 10; i ++){
            List<String> l = Arrays.asList(new File(datasetFolder + i).list());
            digitsPaths.put(i, l);
            currentDigitInList.put(i,0);
            if(smallest > l.size())
                smallest = l.size();
        }
        togo = smallest*10;
       // throw new RuntimeException(new Error("t "+togo));
    }
    public boolean hasNext(){
        return iteration+1 < togo;
    }
    public HandWrittenNumber getNextImage(){
        try {
            if (currentDigit == 10) {
                currentDigit = 0;
            }
           // System.out.println(currentDigit);

            String imgFile = digitsPaths.get(currentDigit).get(currentDigitInList.get(currentDigit));
            BufferedImage img = FileReader.readImageFile(datasetFolder + currentDigit + "/" + imgFile);
            HandWrittenNumber number = new HandWrittenNumber(img, currentDigit);
            currentDigitInList.put(currentDigit, currentDigitInList.get(currentDigit) + 1);



            currentDigit++;
            iteration++;



            return number;
        }catch (IOException | NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }
}
