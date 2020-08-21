package jrazek.neuralNetwork.netStructure.inputLayer;

import jrazek.neuralNetwork.abstracts.Layer;

import java.util.ArrayList;
import java.util.List;

public class InputLayer extends Layer {
    private List<Double> inputs;
    private Layer nextLayer;
    InputLayer(int index){
        super(index);
        inputs = new ArrayList<>();
    }
}
