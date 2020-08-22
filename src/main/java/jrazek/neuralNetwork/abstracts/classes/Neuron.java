package jrazek.neuralNetwork.abstracts.classes;

import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;

public abstract class Neuron{
    private Layer layer;
    public Neuron(Layer layer){
        this.layer = layer;
    }
    public Layer getLayer() {
        return layer;
    }
    public abstract void addConnection(Connection conn) throws RuntimeErrorException;
    protected abstract void gainInput(double val);
}

