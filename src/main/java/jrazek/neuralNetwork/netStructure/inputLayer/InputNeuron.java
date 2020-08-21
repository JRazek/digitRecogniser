package jrazek.neuralNetwork.netStructure.inputLayer;

import jrazek.neuralNetwork.abstracts.Layer;
import jrazek.neuralNetwork.abstracts.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public class InputNeuron extends Neuron {
    private List<Connection> outputConnections;
    InputNeuron(Layer layer){
        super(layer);
        outputConnections = new ArrayList<>();
    }
    public void addOutputConnection(Connection c){
        this.outputConnections.add(c);
    }
}