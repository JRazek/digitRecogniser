package jrazek.neuralNetwork.netStructure.inputLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;

import javax.management.RuntimeErrorException;

public class InputNeuron extends Neuron {
    private double input;//same as output
    public InputNeuron(Layer<? extends Neuron> layer, int number) {
        super(layer, number);
    }

    @Override
    public void addConnection(Connection conn) throws RuntimeErrorException {
        if(conn.getInputNeuron().equals(this) && !conn.getOutputNeuron().equals(this))
            super.connections.add(conn);
        else{
            throw new RuntimeErrorException(new Error("Wrong assignment in input neuron!"));
        }
    }

    public double getOutput() {
        return input;
    }//as the input is not changed - it just goes unchanged

    public void addInput(double i){
        this.input = i;
    }
}