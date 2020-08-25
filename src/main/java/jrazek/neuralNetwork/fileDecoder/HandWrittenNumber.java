package jrazek.neuralNetwork.fileDecoder;

import jrazek.neuralNetwork.utils.Utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class HandWrittenNumber {
    double [] pixels;
    int number;
    HandWrittenNumber(BufferedImage img, int number){
        this.number = number;
        int x = img.getWidth();
        int y = img.getHeight();
        pixels = new double[x*y];
        for(int i = 0; i < y; i ++){
            for(int j = 0; j < x; j++){
                pixels[j+i*x] = (Utils.toGrayscale(img.getRGB(j, i))/255f);
            }
        }
        /// TODO: 25.08.2020 imageNormalization
    }

    public double[] getPixels() {
        return pixels;
    }
}
