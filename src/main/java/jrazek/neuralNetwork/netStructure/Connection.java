package jrazek.neuralNetwork.netStructure;

import jrazek.neuralNetwork.abstracts.Neuron;

import javax.management.RuntimeErrorException;

import static jrazek.neuralNetwork.utils.Utils.randomDouble;

public class Connection {
    private double weight;
    private Neuron inputNeuron;
    private Neuron outputNeuron;
    public Connection(Neuron input, Neuron output) throws RuntimeErrorException {
        this.weight = randomDouble(-1,1);
        if(inputNeuron.getLayer().equals(outputNeuron.getLayer())){
            throw new RuntimeErrorException(new Error("CANT CONNECT IN THE SAME LAYER!"));
        }
        if(Math.abs(inputNeuron.getLayer().getLayerIndex() - outputNeuron.getLayer().getLayerIndex()) != 1){
            throw new RuntimeErrorException(new Error("YOU CAN ONLY CONNECT NEURONS FROM NEIGHBOUR LAYERS!"));
        }
        this.inputNeuron = input;
        this.outputNeuron = output;
    }

    public Neuron getInputNeuron() {
        return inputNeuron;
    }

    public Neuron getOutputNeuron() {
        return outputNeuron;
    }
}
