package jrazek.neuralNetwork.netStructure.hiddenLayer;

import jrazek.neuralNetwork.abstracts.Layer;

import static jrazek.neuralNetwork.utils.Rules.hiddenNeurons;

public class HiddenLayer extends Layer {
    private Layer previousLayer;
    private Layer nextLayer;
    public HiddenLayer(Layer prev, int index){
        super(index);
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    @Override
    protected void initNeurons() {
        for(int i = 0; i < hiddenNeurons; i ++){
            super.addNeuron(new HiddenNeuron(this));
        }
    }
}
