package jrazek.neuralNetwork.netStructure.inputLayer;

import jrazek.neuralNetwork.abstracts.classes.layers.FeedableLayer;

import static jrazek.neuralNetwork.utils.Rules.inputNeurons;

public class InputLayer extends FeedableLayer {
    public InputLayer(int index){
        super(index);
    }

    @Override
    public void initNeurons() {
        for(int i = 0; i < inputNeurons; i ++){
            InputNeuron n = new InputNeuron(this, i);
            super.addNeuron(n);
        }
    }
}
