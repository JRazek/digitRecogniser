package jrazek.neuralNetwork.netStructure.inputLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public class InputNeuron extends Neuron {
    private List<Connection> outputConnections;
    private double input;
    InputNeuron(Layer<? extends Neuron> layer){
        super(layer);
        this.input = 0;
        this.outputConnections = new ArrayList<>();
    }

    @Override
    public void addConnection(Connection conn) throws RuntimeErrorException {
        if(conn.getInputNeuron().equals(this))
            this.outputConnections.add(conn);
        else{
            throw new RuntimeErrorException(new Error("Wrong assignment in input neuron!"));
        }
    }
    protected void gainInput(double val){
        this.input = val;
    }
}