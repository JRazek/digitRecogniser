package jrazek.neuralNetwork.abstracts.classes;

import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;

public abstract class Neuron{
    private Layer<? extends Neuron> layer;
    public Neuron(Layer<? extends Neuron> layer){
        this.layer = layer;
    }
    public Layer<? extends Neuron> getLayer() {
        return layer;
    }
    public abstract void addConnection(Connection conn) throws RuntimeErrorException;
    protected abstract void gainInput(double val);
}

