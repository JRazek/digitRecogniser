package jrazek.neuralNetwork.netStructure.hiddenLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public class HiddenNeuron extends Neuron {
    HiddenNeuron(Layer<? extends Neuron> layer){
        super(layer);
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
