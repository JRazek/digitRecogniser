package jrazek.neuralNetwork.netStructure.hiddenLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.abstracts.classes.DerivedLayer;

import static jrazek.neuralNetwork.utils.Rules.hiddenNeurons;

public class HiddenLayer extends DerivedLayer<HiddenNeuron> {
    private Layer<? extends Neuron> previousLayer;
    private Layer<? extends Neuron> nextLayer;
    public HiddenLayer(Layer<? extends Neuron> prev, int index){
        super(index);
        this.previousLayer = prev;
    }

    public void setNextLayer(Layer<? extends Neuron> nextLayer) {
        this.nextLayer = nextLayer;
    }

    @Override
    public void initNeurons() {
        for(int i = 0; i < hiddenNeurons; i ++){
            super.addNeuron(new HiddenNeuron(this));
        }
    }

}
