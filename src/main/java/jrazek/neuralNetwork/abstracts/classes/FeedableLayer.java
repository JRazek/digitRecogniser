package jrazek.neuralNetwork.abstracts.classes;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.inputLayer.InputNeuron;

import javax.management.RuntimeErrorException;

public abstract class FeedableLayer extends Layer<InputNeuron> {
    public FeedableLayer(int index) {
        super(index);
    }

    public void feed(double [] inputArr) throws RuntimeErrorException {
        if(inputArr.length != getNeurons().size())
            throw new RuntimeErrorException(new Error("Invalid size!"));
        int neuronNum = 0;
        for(InputNeuron n : super.getNeurons()){
            if(n == null)
                throw new RuntimeErrorException(new Error("WRONG NEURON TYPE!"));
            n.addInput(inputArr[neuronNum]);
            neuronNum ++;
        }
    }
}
