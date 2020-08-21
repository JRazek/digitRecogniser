package jrazek.neuralNetwork.netStructure.hiddenLayer;

import jrazek.neuralNetwork.abstracts.Layer;
import jrazek.neuralNetwork.abstracts.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public class HiddenNeuron extends Neuron {
    private List<Connection> inputConnections;
    private List<Connection> outputConnections;
    HiddenNeuron(Layer layer){
        super(layer);
        this.inputConnections = new ArrayList<>();
        this.outputConnections = new ArrayList<>();
    }
    private void addInputConnection(Connection c) {
        this.inputConnections.add(c);
    }
    private void addOutputConnection(Connection c) {
        this.outputConnections.add(c);
    }
}
