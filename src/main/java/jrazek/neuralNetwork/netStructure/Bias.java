package jrazek.neuralNetwork.netStructure;

import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;

public class Bias {
    private final Neuron neuron;
    private double value;
    private final int biasID;
    public Bias(Neuron n, int bID, double val){
        this.neuron = n;
        this.biasID = bID;
        this.value = val;
    }

    public Neuron getNeuron() {
        return neuron;
    }

    public int getBiasID() {
        return biasID;
    }
    public void updateBias(double delta){
        this.value += delta;
    }
    public double getValue() {
        return value;
    }
}
