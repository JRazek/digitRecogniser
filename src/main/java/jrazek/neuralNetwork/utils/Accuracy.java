package jrazek.neuralNetwork.utils;

import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputNeuron;

import javax.management.RuntimeErrorException;

public class Accuracy {
    float timesMeasured = 0;
    float ok = 0;
    Net net;
    int predicted;
    int iteration = 0;
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

        predicted = bestIndex;

        if(bestIndex == target){
            this.ok ++;
        }
        //System.out.println("OK IS " + target + " PREDICTION IS " + bestIndex);
        this.timesMeasured ++;
        this.iteration ++;
    }
    public int getPrediction(){
        return predicted;
    }
    public void reset(){
        this.timesMeasured = 0;
        this.ok = 0;
    }
    public float getAccuracy(){
        return ok/timesMeasured;
    }
}
