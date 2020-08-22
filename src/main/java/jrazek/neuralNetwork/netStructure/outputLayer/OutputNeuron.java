package jrazek.neuralNetwork.netStructure.outputLayer;

import jrazek.neuralNetwork.abstracts.Layer;
import jrazek.neuralNetwork.abstracts.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public class OutputNeuron extends Neuron {
    private List<Connection> inputConnections;
    public OutputNeuron(Layer layer) {
        super(layer);
        inputConnections = new ArrayList<>();
    }

    @Override
    public void addConnection(Connection conn) throws RuntimeErrorException {
        if(conn.getOutputNeuron().equals(this))
            inputConnections.add(conn);
        else{
            throw new RuntimeErrorException(new Error("Wrong assignment of connection in output neuron"));
        }
    }

    public void addInputConnection(Connection c) {
        this.inputConnections.add(c);
    }
}
