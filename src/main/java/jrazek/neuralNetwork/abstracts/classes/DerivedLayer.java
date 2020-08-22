package jrazek.neuralNetwork.abstracts.classes;


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
}
