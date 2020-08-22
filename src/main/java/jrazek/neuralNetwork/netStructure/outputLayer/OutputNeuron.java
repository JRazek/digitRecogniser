package jrazek.neuralNetwork.netStructure.outputLayer;

import jrazek.neuralNetwork.abstracts.classes.DerivedNeuron;
import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;

public class OutputNeuron extends DerivedNeuron {
    public OutputNeuron(Layer<? extends Neuron> layer, int number) {
        super(layer, number);
    }

    @Override
    public void addConnection(Connection conn) throws RuntimeErrorException {
        if(conn.getOutputNeuron().equals(this) && !conn.getInputNeuron().equals(this))
            super.connections.add(conn);
        else{
            throw new RuntimeErrorException(new Error("Wrong assignment of connection in output neuron"));
        }
    }

}
