package jrazek.neuralNetwork.abstracts;

import java.util.List;

public abstract class Neuron {
    private Layer layer;
    public Neuron(Layer layer){
        this.layer = layer;
    }
    public Layer getLayer() {
        return layer;
    }
}

