package jrazek.neuralNetwork.netStructure.hiddenLayer;

import jrazek.neuralNetwork.abstracts.classes.neurons.DerivedNeuron;
import jrazek.neuralNetwork.abstracts.classes.layers.Layer;
import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;

public class HiddenNeuron extends DerivedNeuron {

    public HiddenNeuron(Layer<? extends Neuron> layer, int number) {
        super(layer, number);
    }

    @Override
    public void addConnection(Connection conn) {
        if(conn.getInputNeuron().equals(this) && !conn.getOutputNeuron().equals(this))
            super.connections.add(conn);
        else if(!conn.getInputNeuron().equals(this) && conn.getOutputNeuron().equals(this)){
            super.connections.add(conn);
        }
        else{
            throw new RuntimeErrorException(new Error("Wrong assignment of connection in hidden neuron!"));
        }
    }

}
