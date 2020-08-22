package jrazek.neuralNetwork.netStructure;

import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;

public class Bias {
    private final Neuron neuron;
    private final double value;
    public Bias(Neuron n, double val){
        this.neuron = n;
        this.value = val;
    }

    public double getValue() {
        return value;
    }
}
