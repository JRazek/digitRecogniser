package jrazek.neuralNetwork.abstracts;

import jrazek.neuralNetwork.netStructure.Connection;

import java.util.ArrayList;
import java.util.List;

public abstract class Layer {
    private List<Neuron> neurons;
    private int layerIndex;//number of layer starting from 0
    public Layer(int index){
        this.layerIndex = index;
        neurons = new ArrayList<>();
    }
    void addNeuron(Neuron n){
        neurons.add(n);
    }

    public int getLayerIndex() {
        return layerIndex;
    }
}
