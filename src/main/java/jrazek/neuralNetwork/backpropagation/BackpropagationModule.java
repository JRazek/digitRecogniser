package jrazek.neuralNetwork.backpropagation;

import jrazek.neuralNetwork.netStructure.Net;

public class BackpropagationModule {
    private Net net;
    double [] expected;
    public BackpropagationModule(Net net, double [] expected) {
        this.net = net;
        this.expected = expected;
    }
    public void backPropagate(){
        
    }
}
