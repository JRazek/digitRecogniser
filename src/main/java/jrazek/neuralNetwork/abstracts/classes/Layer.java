package jrazek.neuralNetwork.abstracts.classes;


import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public abstract class Layer <T extends Neuron>{
    private List<T> neurons;
    private int layerIndex;//number of layer starting from 0
    public Layer(int index){
        this.layerIndex = index;
        neurons = new ArrayList<>();
    }
    public abstract void initNeurons();
    protected void addNeuron(T n){
        this.neurons.add(n);
    }
    public int getLayerIndex() {
        return layerIndex;
    }
    public List<T> getNeurons(){
        return neurons;
    }
    public void feed(double [] inputArr) throws RuntimeErrorException {
        if(inputArr.length != getNeurons().size())
            throw new RuntimeErrorException(new Error("Invalid size!"));
        int neuronNum = 0;
        for(T n : neurons){
            if(n == null)
                throw new RuntimeErrorException(new Error("WRONG NEURON TYPE!"));
            n.gainInput(inputArr[neuronNum]);
            neuronNum ++;
        }
    }
}
