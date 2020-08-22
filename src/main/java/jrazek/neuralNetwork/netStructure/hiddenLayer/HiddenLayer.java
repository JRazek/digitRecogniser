package jrazek.neuralNetwork.netStructure.hiddenLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;

import static jrazek.neuralNetwork.utils.Rules.hiddenNeurons;

public class HiddenLayer extends Layer<HiddenNeuron> {
    private Layer<HiddenNeuron> previousLayer;
    private Layer<HiddenNeuron> nextLayer;
    public HiddenLayer(Layer<HiddenNeuron> prev, int index){
        super(index);
        this.previousLayer = prev;
    }

    public void setNextLayer(Layer<HiddenNeuron> nextLayer) {
        this.nextLayer = nextLayer;
    }

    @Override
    public void initNeurons() {
        for(int i = 0; i < hiddenNeurons; i ++){
            super.addNeuron(new HiddenNeuron(this));
        }
    }
}
