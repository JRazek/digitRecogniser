package jrazek.neuralNetwork;

import jrazek.neuralNetwork.backpropagation.BackpropagationModule;
import jrazek.neuralNetwork.filesManagers.FileDecoder;
import jrazek.neuralNetwork.filesManagers.HandWrittenNumber;
import jrazek.neuralNetwork.filesManagers.StructureManager;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.utils.Accuracy;
import jrazek.neuralNetwork.utils.Rules;

import static jrazek.neuralNetwork.utils.Rules.accuracyResetRate;
import static jrazek.neuralNetwork.utils.Rules.maxIterations;

public class Main {
    public static void main(String[] args) {
        FileDecoder fileDecoder = new FileDecoder();
        Net net = new Net();
        BackpropagationModule backpropagationModule = new BackpropagationModule(net);
        Accuracy accuracy = new Accuracy(net);
        int iteration = 0;
        while(fileDecoder.hasNext() && (maxIterations == -1 || iteration < maxIterations)){
            HandWrittenNumber num = fileDecoder.getNextImage();
            net.forwardPass(num.getPixels());
            accuracy.check(num.getTarget());

            double [] target = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            target[num.getTarget()] = 0.99;
            backpropagationModule.backPropagate(target);
            iteration ++;
            System.out.println("Iteration: " + iteration + ", accuracy = " + accuracy.getAccuracy()*100 + "%");

            if((iteration+1) % accuracyResetRate == 0){
                accuracy.reset();
            }
        }
        StructureManager.save(net);
        //todo should make the sum of shuffled n training examples
    }
}
