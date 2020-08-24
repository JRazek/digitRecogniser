package jrazek.neuralNetwork;

import jrazek.neuralNetwork.backpropagation.BackpropagationModule;
import jrazek.neuralNetwork.netStructure.Net;

public class Main {
    public static void main(String[] args) {
        Net net = new Net();
        BackpropagationModule backpropagationModule = new BackpropagationModule(net);
        net.forwardPass(new double[]{1d,2d});
        backpropagationModule.backPropagate(new double[]{0.99, 0.01});
        net.showStructure();
        backpropagationModule.backPropagate(new double[]{0.99,0.01});
        net.showOutput();
    }
}
