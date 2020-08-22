package jrazek.neuralNetwork.netStructure.outputLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;

import static jrazek.neuralNetwork.utils.Rules.inputNeurons;

public class OutputLayer extends Layer {
    private Layer previousLayer;
    public OutputLayer(Layer prev, int index){
        super(index);
        this.previousLayer = prev;
    }

    @Override
    protected void initNeurons() {
        for(int i = 0; i < inputNeurons; i ++){
            super.addNeuron(new OutputNeuron(this));
        }
    }
}
