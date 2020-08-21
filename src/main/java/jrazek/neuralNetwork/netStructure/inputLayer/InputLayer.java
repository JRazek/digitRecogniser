package jrazek.neuralNetwork.netStructure.inputLayer;

import jrazek.neuralNetwork.abstracts.Layer;
import jrazek.neuralNetwork.abstracts.Neuron;

import java.util.ArrayList;
import java.util.List;

import static jrazek.neuralNetwork.utils.Rules.inputNeurons;

public class InputLayer extends Layer {
    private List<Double> inputs;
    private Layer nextLayer;
    InputLayer(int index){
        super(index);
        inputs = new ArrayList<>();
    }

    @Override
    protected void initNeurons() {
        for(int i = 0; i < inputNeurons; i ++){
            Neuron n = new InputNeuron(this);
            super.addNeuron(n);
        }
    }
}
