package jrazek.neuralNetwork.netStructure.hiddenLayer;

import jrazek.neuralNetwork.abstracts.Layer;
import jrazek.neuralNetwork.abstracts.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;
import java.util.List;

public class HiddenNeuron extends Neuron {
    private List<Connection> inputConnections;
    private List<Connection> outputConnections;
    HiddenNeuron(Layer layer){
        super(layer);
    }
    private void addConnection(Connection c) throws RuntimeErrorException {
        if(c.getInputNeuron() == null || c.getOutputNeuron() == null){
            throw new RuntimeErrorException(new Error("CONNECTION IS INITIATED THE WRONG WAY"));
        }
        if(c.getInputNeuron().equals(this)){
            this.outputConnections.add(c);
        }
        else if(c.getOutputNeuron().equals(this)){
            this.inputConnections.add(c);
        }
        else{
            throw new RuntimeErrorException(new Error("NEITHER OF CONNECTION'S NEURONS IS THE NEURON ITSELF!"));
        }
    }
}
