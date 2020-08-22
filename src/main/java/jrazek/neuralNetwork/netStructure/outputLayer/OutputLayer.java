package jrazek.neuralNetwork.netStructure.outputLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.inputLayer.InputNeuron;

import javax.management.RuntimeErrorException;

import static jrazek.neuralNetwork.utils.Rules.inputNeurons;
import static jrazek.neuralNetwork.utils.Rules.outputNeurons;

public class OutputLayer extends Layer<OutputNeuron> {
    private Layer<Neuron> previousLayer;
    public OutputLayer(Layer<Neuron> prev, int index){
        super(index);
        this.previousLayer = prev;
    }

    @Override
    public void initNeurons() {
        for(int i = 0; i < inputNeurons; i ++){
            super.addNeuron(new OutputNeuron(this));
        }
    }
}
