package jrazek.neuralNetwork.filesManagers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static jrazek.neuralNetwork.utils.Rules.inputNeurons;

public class FileDecoder {
    private String navigationFile = "dataSet/training-a.csv";
    private String datasetFolder = "dataSet/training-a/";
    private List<String> digits;
    private int lineNum = 0;
    public FileDecoder(){
        digits = FileReader.readTextFile(navigationFile);
    }
    private boolean hasNextImg(){
        return (digits.size() - 1 != lineNum);
    }
    private String readNext(){
        if(digits.get(lineNum) != null){
            lineNum ++;
            return digits.get(lineNum);
        }

        return null;
    }
    public boolean hasNext(){
        return (lineNum + 1 != digits.size());
    }
    public HandWrittenNumber getNextImage() {
        try {
            String [] data = Objects.requireNonNull(readNext()).split(",");
            BufferedImage img = FileReader.readImageFile(datasetFolder + data[0]);
            int digit = Integer.parseInt(data[3]);
            return new HandWrittenNumber(img, digit);
        }catch (NumberFormatException | IOException e){
            e.printStackTrace();
        }

       //filename,original filename,scanid,digit,database name original,contributing team,database name
        //a00000.png,Scan_58_digit_5_num_8.png,58,5,BHDDB,Buet_Broncos,training-a
        return null;
    }
}
