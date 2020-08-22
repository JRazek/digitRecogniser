package jrazek.neuralNetwork.abstracts.classes;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;

public abstract class FeedableLayer<T extends Neuron> extends Layer<T> {
    public FeedableLayer(int index) {
        super(index);
    }

    public abstract void feed(double[] argsArr);
}
