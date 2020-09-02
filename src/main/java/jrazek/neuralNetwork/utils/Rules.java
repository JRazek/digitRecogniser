package jrazek.neuralNetwork.utils;

public class Rules {
    public static final int layersNum = 4;
    public static final int inputNeurons = 784;
    public static final int hiddenNeurons = 16;
    public static final int outputNeurons = 10;
    public static final double gradientDescentRate = 0.0001;
    public static final int maxIterations = -1;
    public static int batchSize = 100; //every x images the gradient is averaged and then changed
    public static boolean repeatDataset = true; //if dataset end - repeat

    public static boolean save = true;
    public static int saveRate = 500;
    public static String savePath = "nets/cp.json";

    /**
     * determines if the file should be read and the path of of it
     * */
    public static boolean loadFromFile = true;
    public static String loadFile = "nets/cp.json";

    public static String datasetFolder = "dataSet/";
    public static boolean learnMode = true;

    public static int accuracyResetRate = 1000;
}