package jrazek.neuralNetwork.backpropagation;

import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputNeuron;

import javax.management.RuntimeErrorException;

public class BackpropagationModule {
    private Net net;
    double errorT;
    public BackpropagationModule(Net net) {
        this.net = net;
        this.errorT = 0;
    }
    public void backPropagate(double [] expected) throws RuntimeErrorException{
        if(net.getOutputLayer().getNeurons().size() != expected.length)
            throw new RuntimeErrorException(new Error("ERROR 2124"));
        int neuronNum = 0;
        for(OutputNeuron neuron : net.getOutputLayer().getNeurons()){
            errorT += Math.pow(expected[neuronNum] - neuron.getActivationValue(), 2);
            neuronNum ++;
        }
        System.out.println(errorT);
    }
}
