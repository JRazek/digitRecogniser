package jrazek.neuralNetwork;

import jrazek.neuralNetwork.backpropagation.BackpropagationModule;
import jrazek.neuralNetwork.filesManagers.FileDecoder;
import jrazek.neuralNetwork.filesManagers.HandWrittenNumber;
import jrazek.neuralNetwork.filesManagers.StructureManager;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.utils.Accuracy;

import java.io.IOException;

import static jrazek.neuralNetwork.utils.Rules.*;
import static jrazek.neuralNetwork.utils.Utils.randomInt;

public class Main {
    public static void main(String[] args) {
            FileDecoder fileDecoder = new FileDecoder();
            Net net;
            if (loadFromFile && !loadFile.equals("")) {
                try {
                    net = new Net(StructureManager.load(loadFile));
                }catch (IOException e){
                    System.out.println("FILE NOT FOUND CREATING NEW NETWORK");
                    net = new Net();
                }
            } else {
                net = new Net();
            }
            BackpropagationModule backpropagationModule = new BackpropagationModule(net);
            Accuracy accuracy = new Accuracy(net);
            int iteration = 0;
            do {
                while (fileDecoder.hasNext() && (maxIterations == -1 || iteration < maxIterations)) {
                    HandWrittenNumber num = fileDecoder.getNextImage();
                   // net.forwardPass(new double[]{0.99, 0, 0, 0.99, 0, 0, 0, 0.13, 0, 0,0.1,0.543, .21, .765, .123, .765});
                    //accuracy.check(3);
                    net.forwardPass(num.getPixels());
                    accuracy.check(num.getTarget());

                    //double [] target = new double[]{0, 0, 0, 0.99, 0, 0, 0, 0, 0, 0};
                    if (learnMode) {
                        double[] target = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        target[num.getTarget()] = 0.99;
                        backpropagationModule.backPropagate(target);
                    }
                    iteration++;

                    if ((iteration + 1) % accuracyResetRate == 0) {
                        System.out.println("Iteration: " + (iteration + 1) + ", accuracy = " + accuracy.getAccuracy() * 100 + "%");
                        //System.out.println("Iteration: " + (iteration + 1) + ", given = " + num.getTarget() + ", predicted = " + accuracy.getPrediction());
                        if(learnMode)System.out.println("Error " + (iteration + 1) + ": " + backpropagationModule.showError());
                        accuracy.reset();
                    }
                    if((iteration + 1) % saveRate == 0 && save && learnMode){
                        StructureManager.save(net);
                    }
                }
                if(save && learnMode)
                    StructureManager.save(net);
                System.out.println("Repeating dataset");
                fileDecoder.reset();
                //todo should make the sum of shuffled n training examples
            }while (repeatDataset && learnMode);
    }
}
