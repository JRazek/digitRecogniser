package jrazek.neuralNetwork.netStructure.inputLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public class InputNeuron extends Neuron {
    InputNeuron(Layer<? extends Neuron> layer){
        super(layer);
    }

    @Override
    public void addConnection(Connection conn) throws RuntimeErrorException {
        if(conn.getInputNeuron().equals(this) && !conn.getOutputNeuron().equals(this))
            super.connections.add(conn);
        else{
            throw new RuntimeErrorException(new Error("Wrong assignment in input neuron!"));
        }
    }
}