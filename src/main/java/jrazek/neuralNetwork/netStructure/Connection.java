package jrazek.neuralNetwork.netStructure;

import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;

import javax.management.RuntimeErrorException;

import static jrazek.neuralNetwork.utils.Utils.randomDouble;

public class Connection {
    private final double weight;
    private final Neuron inputNeuron;
    private final Neuron outputNeuron;
    public Connection(Neuron input, Neuron output) throws RuntimeErrorException {
        this.weight = randomDouble(-1,1);
        this.inputNeuron = input;
        this.outputNeuron = output;

        if(input == null || output == null){
            throw new RuntimeErrorException(new Error("CONNECTION IS INITIATED THE WRONG WAY!"));
        }
        else if(input.equals(output)){
            throw new RuntimeErrorException(new Error("NEURONS IN CONNECTIONS CANNOT BE THE SAME!"));
        }
        if(inputNeuron.getLayer().equals(outputNeuron.getLayer())){
            throw new RuntimeErrorException(new Error("CANT CONNECT IN THE SAME LAYER!"));
        }
        if(Math.abs(inputNeuron.getLayer().getLayerIndex() - outputNeuron.getLayer().getLayerIndex()) != 1){
            throw new RuntimeErrorException(new Error("YOU CAN ONLY CONNECT NEURONS FROM NEIGHBOUR LAYERS!"));
        }

    }

    public double getWeight() {
        return weight;
    }

    public Neuron getInputNeuron() {
        return inputNeuron;
    }

    public Neuron getOutputNeuron() {
        return outputNeuron;
    }
}
