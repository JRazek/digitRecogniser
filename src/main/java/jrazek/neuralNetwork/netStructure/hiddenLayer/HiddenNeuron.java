package jrazek.neuralNetwork.netStructure.hiddenLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
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

    @Override
    public void addConnection(Connection conn) {
        if(conn.getInputNeuron().equals(this))
            outputConnections.add(conn);
        else if(conn.getOutputNeuron().equals(this))
            inputConnections.add(conn);
        else{
            throw new RuntimeErrorException(new Error("Wrong assignment of connection in hidden neuron!"));
        }
    }
}
