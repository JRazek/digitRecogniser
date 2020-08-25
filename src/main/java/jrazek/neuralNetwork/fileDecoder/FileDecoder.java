package jrazek.neuralNetwork.fileDecoder;

import java.awt.image.BufferedImage;
import java.util.List;

public class FileDecoder {
    private String navigationFile = "dataSet/training-a.csv";
    private String datasetFolder = "dataSet/training-a/";
    List<String> digits;
    BufferedImage currentImg;
    HandWrittenNumber currImgObj;
    int lineNum = 0;
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
    public HandWrittenNumber getNextImage(){
       readNext().split(",");
       //filename,original filename,scanid,digit,database name original,contributing team,database name
        //a00000.png,Scan_58_digit_5_num_8.png,58,5,BHDDB,Buet_Broncos,training-a
        return null;
    }
}
