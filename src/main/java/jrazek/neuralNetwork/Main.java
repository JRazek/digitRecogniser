package jrazek.neuralNetwork;

import jrazek.neuralNetwork.backpropagation.BackpropagationModule;
import jrazek.neuralNetwork.netStructure.Net;

public class Main {
    public static void main(String[] args) {
        Net net = new Net();
        BackpropagationModule backpropagationModule = new BackpropagationModule(net);
        net.forwardPass(new double[]{1d,2d,4,9});
        backpropagationModule.backPropagate(new double[]{0.99, 0.01});
        //todo should make the sum of shuffled n training examples
        net.showStructure();
        net.showOutput();
    }
}
