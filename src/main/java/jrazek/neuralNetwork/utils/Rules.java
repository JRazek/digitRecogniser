package jrazek.neuralNetwork.utils;

public class Rules {
    public static final int layersNum = 3;
    public static final int inputNeurons = 784;
    public static final int hiddenNeurons = 16;
    public static final int outputNeurons = 10;
    public static final double gradientDescentRate = 0.01;
    public static final int maxIterations = -1;
    public static int batchSize = 1; //every x images the gradient is averaged and then changed
    public static boolean repeat = true;

    public static boolean save = true;
    public static int saveRate = 3000;
    public static String savePath = "nets/cp.json";

    /**
     * determines if the file should be read and the path of of it
     * */
    public static boolean loadFromFile = false;
    public static String loadFile = "nets/cp.json";
    public static String navigationFile = "dataSet/training-a.csv";
    public static String datasetFolder = "dataSet/training-a/";

    public static int accuracyResetRate = 100;
    public static double sigmoidDivider = 100;
}