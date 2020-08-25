package jrazek.neuralNetwork;

import jrazek.neuralNetwork.backpropagation.BackpropagationModule;
import jrazek.neuralNetwork.netStructure.Net;

public class Main {
    public static void main(String[] args) {
        Net net = new Net();
        BackpropagationModule backpropagationModule = new BackpropagationModule(net);
        double e1 = 0;
        for(int i = 0; i < 3; i++){
            net.forwardPass(new double[]{1d,2d,4,9});
            backpropagationModule.backPropagate(new double[]{0.99, 0.01});
            if(i == 0)
                e1 = backpropagationModule.showError();
        }
        net.showStructure();
        net.showOutput();
        System.out.println("Error 1:" + e1);
        System.out.println("Error 2:" + backpropagationModule.showError());
        //todo should make the sum of shuffled n training examples
    }
}
