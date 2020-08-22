package jrazek.neuralNetwork.abstracts.classes;

import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public abstract class Neuron{
    private Layer<? extends Neuron> layer;
    private double inputValue = 0;
    private int num; //number from top and 0 in layer
    protected List<Connection> connections;
    public Neuron(Layer<? extends Neuron> layer, int number){
        this.layer = layer;
        connections = new ArrayList<>();
    }
    public Layer<? extends Neuron> getLayer() {
        return layer;
    }
    public abstract void addConnection(Connection conn) throws RuntimeErrorException;
    protected void addInput(double val){
        this.inputValue += val;
    }
}

