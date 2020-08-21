package jrazek.neuralNetwork.netStructure.hiddenLayer;

import jrazek.neuralNetwork.abstracts.Layer;

public class HiddenLayer extends Layer {
    private Layer previousLayer;
    private Layer nextLayer;
    HiddenLayer(Layer prev, Layer next, int index){
        super(index);
    }
}
