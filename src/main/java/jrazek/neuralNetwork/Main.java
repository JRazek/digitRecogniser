package jrazek.neuralNetwork;

import jrazek.neuralNetwork.netStructure.Net;

public class Main {
    public static void main(String[] args) {
        Net net = new Net();
        net.forwardPass(new double[]{1d,2d});
        net.showStructure();
        net.showOutput();
    }
}
