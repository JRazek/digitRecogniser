package jrazek.neuralNetwork.netStructure.inputLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

import static jrazek.neuralNetwork.utils.Rules.inputNeurons;

public class InputLayer extends Layer<InputNeuron> {
    private List<Double> inputs;
    private Layer<? extends Neuron> nextLayer;
    public InputLayer(int index){
        super(index);
        inputs = new ArrayList<>();
    }
    public void setNextLayer(Layer<? extends Neuron> nextLayer) {
        this.nextLayer = nextLayer;
    }

    @Override
    public void initNeurons() {
        for(int i = 0; i < inputNeurons; i ++){
            InputNeuron n = new InputNeuron(this);
            super.addNeuron(n);
        }
    }

}
