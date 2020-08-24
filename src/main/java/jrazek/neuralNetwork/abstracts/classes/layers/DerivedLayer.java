package jrazek.neuralNetwork.abstracts.classes.layers;


import jrazek.neuralNetwork.abstracts.classes.neurons.DerivedNeuron;

public abstract class DerivedLayer<T extends DerivedNeuron> extends Layer<T> {
    public DerivedLayer(int index) {
        super(index);
    }

    public void takeFromPreviousLayer(){
        for(T neuron : super.getNeurons()){
            neuron.sumInputs();
            neuron.countActivation();
        }
    }
    public void reset(){
        for(T neuron : super.getNeurons()){
            neuron.reset();
        }
    }
}
