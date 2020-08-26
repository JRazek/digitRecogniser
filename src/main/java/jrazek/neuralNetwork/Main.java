package jrazek.neuralNetwork;

import jrazek.neuralNetwork.backpropagation.BackpropagationModule;
import jrazek.neuralNetwork.filesManagers.FileDecoder;
import jrazek.neuralNetwork.filesManagers.HandWrittenNumber;
import jrazek.neuralNetwork.filesManagers.StructureManager;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.utils.Rules;

import static jrazek.neuralNetwork.utils.Rules.maxIterations;

public class Main {
    public static void main(String[] args) {
        FileDecoder fileDecoder = new FileDecoder();
        Net net = new Net();
        BackpropagationModule backpropagationModule = new BackpropagationModule(net);
        int iteration = 0;
        while(fileDecoder.hasNext() && (maxIterations == -1 || iteration < maxIterations)){
            HandWrittenNumber num = fileDecoder.getNextImage();
            net.forwardPass(num.getPixels());
            double [] target = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            target[num.getTarget()] = 0.99;
            backpropagationModule.backPropagate(target);
            iteration ++;
            System.out.println("Error " + iteration + " = " + backpropagationModule.showError());
        }
        StructureManager.save(net);
        //todo should make the sum of shuffled n training examples
    }
}
