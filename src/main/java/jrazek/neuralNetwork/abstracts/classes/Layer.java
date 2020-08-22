package jrazek.neuralNetwork.abstracts.classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Layer {
    private List<Neuron> neurons;
    private int layerIndex;//number of layer starting from 0
    public Layer(int index){
        this.layerIndex = index;
        neurons = new ArrayList<>();
    }
    protected abstract void initNeurons();
    public void addNeuron(Neuron n){
        this.neurons.add(n);
    }
    public int getLayerIndex() {
        return layerIndex;
    }
    public List<Neuron> getNeurons(){
        return neurons;
    }
}
