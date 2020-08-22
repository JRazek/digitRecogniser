package jrazek.neuralNetwork.abstracts.classes;

import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public abstract class Neuron{
    private Layer<? extends Neuron> layer;
    private int number; //number from top and 0 in layer
    protected List<Connection> connections;
    public Neuron(Layer<? extends Neuron> layer, int num){
        this.layer = layer;
        connections = new ArrayList<>();
        number = num;
    }
    public Layer<? extends Neuron> getLayer() {
        return layer;
    }
    public abstract void addConnection(Connection conn) throws RuntimeErrorException;
    public List<Connection> getConnections() {
        return connections;
    }
}

