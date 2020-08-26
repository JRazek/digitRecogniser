package jrazek.neuralNetwork.utils;

public class Rules {
    public static final int layersNum = 4;
    public static int inputNeurons;
    public static final int hiddenNeurons = 16;
    public static final int outputNeurons = 10;
    public static final double gradientDescentRate = 0.1;
    public static final int maxIterations = -1;
    public static int updateRateAverage = 10;//every 10 images the gradient is averaged and then changed
    public static double sigmoidDivider = 100;
}