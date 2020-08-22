package jrazek.neuralNetwork.netStructure.outputLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public class OutputNeuron extends Neuron {
    private List<Connection> inputConnections;
    private double netVal;
    public OutputNeuron(Layer layer) {
        super(layer);
        inputConnections = new ArrayList<>();
        this.netVal = 0;
    }

    @Override
    public void addConnection(Connection conn) throws RuntimeErrorException {
        if(conn.getOutputNeuron().equals(this))
            inputConnections.add(conn);
        else{
            throw new RuntimeErrorException(new Error("Wrong assignment of connection in output neuron"));
        }
    }

    @Override
    protected void gainInput(double val) {
        this.netVal += val;
    }
}
