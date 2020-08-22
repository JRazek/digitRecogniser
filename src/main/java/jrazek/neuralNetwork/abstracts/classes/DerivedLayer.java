package jrazek.neuralNetwork.abstracts.classes;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;

public abstract class DerivedLayer<T extends Neuron> extends Layer<T> {
    public DerivedLayer(int index) {
        super(index);
    }
    public void TEST(){
        System.out.println("WORKS");
    }
}
