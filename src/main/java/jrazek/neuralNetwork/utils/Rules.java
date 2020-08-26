package jrazek.neuralNetwork.utils;

public class Rules {
    public static final int layersNum = 3;
    public static final int inputNeurons = 1024;
    public static final int hiddenNeurons = 16;
    public static final int outputNeurons = 10;
    public static final double gradientDescentRate = 0.1;
    public static final int maxIterations = -1;
    public static int batchSize = 10; //every x images the gradient is averaged and then changed

    /**
     * determines if the file should be read and the path of of it
     * */
    public static boolean loadFromFile = true;
    public static String loadFile = "nets/cp.json";

    public static int accuracyResetRate = 10;
    public static double sigmoidDivider = 100;
}