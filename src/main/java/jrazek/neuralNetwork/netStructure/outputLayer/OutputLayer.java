package jrazek.neuralNetwork.netStructure.outputLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.abstracts.classes.DerivedLayer;

import static jrazek.neuralNetwork.utils.Rules.inputNeurons;

public class OutputLayer extends DerivedLayer<OutputNeuron> {
    private Layer<? extends Neuron> previousLayer;
    public OutputLayer(Layer<? extends Neuron> prev, int index){
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
