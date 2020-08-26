package jrazek.neuralNetwork.utils;

import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputNeuron;

public class Accuracy {
    float timesMeasured = 0;
    float ok = 0;
    Net net;
    public Accuracy(Net net){
        this.net = net;
    }
    public void check(int target){
        int bestIndex = 0;
        double bestValue = 0;
        int i = 0;
        for(OutputNeuron neuron : net.getOutputLayer().getNeurons()){
            if(neuron.getActivationValue() > bestValue){
                bestValue = neuron.getActivationValue();
                bestIndex = i;
            }
            i++;
        }
        if(bestIndex == target){
            this.ok ++;
        }
        this.timesMeasured ++;
    }
    public void reset(){
        this.timesMeasured = 0;
        this.ok = 0;
    }
    public float getAccuracy(){
        return ok/timesMeasured;
    }
}
