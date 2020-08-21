package jrazek.neuralNetwork.netStructure;

import jrazek.neuralNetwork.abstracts.Neuron;

public class Bias {
    Neuron neuron;
    double value;
    Bias(Neuron n, double v){
        this.neuron = n;
        this.value = v;
    }
}
