package jrazek.neuralNetwork.backpropagation;

import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputNeuron;

import javax.management.RuntimeErrorException;

public class BackpropagationModule {
    final private Net net;
    public BackpropagationModule(Net net) {
        this.net = net;
    }
    public void backPropagate(double [] expected) throws RuntimeErrorException {
        if(expected.length != net.getOutputLayer().getNeurons().size())
            throw new RuntimeErrorException(new Error("3123 ERROR"));
        double errorT = getErrorT(expected);
        //todo
    }
    private double getErrorT(double [] expected){
        double sum = 0;
        int neuronNum = 0;
        for(OutputNeuron neuron : net.getOutputLayer().getNeurons()){
            sum += Math.pow(neuron.getActivationValue() - expected[neuronNum], 2);
            neuronNum++;
        }
        return sum;
    }
}
