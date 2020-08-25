package jrazek.neuralNetwork.fileDecoder;

import jrazek.neuralNetwork.utils.Utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class HandWrittenNumber {
    float [][] pixels;
    int number;
    HandWrittenNumber(BufferedImage img, int number){
        this.number = number;
        int x = img.getWidth();
        int y = img.getHeight();
        pixels = new float[x][y];
        for(int i = 0; i < y; i ++){
            for(int j = 0; j < x; j++){
                pixels[j][i] = (Utils.toGrayscale(img.getRGB(j, i))/255f);
            }
        }
        /// TODO: 25.08.2020 imageNormalization
    }
}
