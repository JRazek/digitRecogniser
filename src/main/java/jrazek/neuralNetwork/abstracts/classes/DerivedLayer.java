package jrazek.neuralNetwork.abstracts.classes;


public abstract class DerivedLayer<T extends Neuron> extends Layer<T> {
    public DerivedLayer(int index) {
        super(index);
    }

    public void takeFromPreviousLayer(){
        System.out.println("TEST");
    }
}
