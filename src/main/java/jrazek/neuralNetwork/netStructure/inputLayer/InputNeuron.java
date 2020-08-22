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

    @Override
    public void addConnection(Connection conn) throws RuntimeErrorException {
        if(conn.getInputNeuron().equals(this))
            outputConnections.add(conn);
        else{
            throw new RuntimeErrorException(new Error("Wrong assignment in input neuron!"));
        }
    }

    public void addOutputConnection(Connection c){
        this.outputConnections.add(c);
    }
}