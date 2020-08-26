package jrazek.neuralNetwork;

import jrazek.neuralNetwork.backpropagation.BackpropagationModule;
import jrazek.neuralNetwork.fileDecoder.FileDecoder;
import jrazek.neuralNetwork.fileDecoder.HandWrittenNumber;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.utils.Rules;

import static jrazek.neuralNetwork.utils.Rules.inputNeurons;
import static jrazek.neuralNetwork.utils.Rules.showErrorEveryIteration;
import static jrazek.neuralNetwork.utils.Utils.randomDouble;

public class Main {
    public static void main(String[] args) {
        Net net = new Net();
        BackpropagationModule backpropagationModule = new BackpropagationModule(net);
        FileDecoder fileDecoder = new FileDecoder();
        double e1 = 0;
        double e2 = 0;
        //Double [] weights = {0.1, 0.1, 0.1, 0.1, 0.3, 0.1, 0.1, 0.1};
        //net.setWeights(weights);
        for(int i = 0; i < Rules.iterations; i++){
            HandWrittenNumber num = fileDecoder.getNextImage();
            net.forwardPass(num.getPixels());
            double [] target = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            target[num.getTarget()] = 0.99;
            backpropagationModule.backPropagate(target);
            if(showErrorEveryIteration)
                System.out.println("Error " + i + " = " + backpropagationModule.showError());
            if(i == 0)
                e1 = backpropagationModule.showError();
            else if(i + 1 == Rules.iterations)
                e2 = backpropagationModule.showError();

        }
        if(!showErrorEveryIteration) {
            System.out.println("Error 1:" + e1);
            System.out.println("Error 2:" + e2);
        }
        //todo should make the sum of shuffled n training examples
    }
}
