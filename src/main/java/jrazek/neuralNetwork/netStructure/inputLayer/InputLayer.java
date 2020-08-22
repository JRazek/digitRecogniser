package jrazek.neuralNetwork.netStructure.inputLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.abstracts.classes.FeedableLayer;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

import static jrazek.neuralNetwork.utils.Rules.inputNeurons;

public class InputLayer extends FeedableLayer<InputNeuron> {
    private List<Double> inputs;
    private Layer<? extends Neuron> nextLayer;
    public InputLayer(int index){
        super(index);
        inputs = new ArrayList<>();
    }
    public void setNextLayer(Layer<? extends Neuron> nextLayer) {
        this.nextLayer = nextLayer;
    }

    @Override
    public void initNeurons() {
        for(int i = 0; i < inputNeurons; i ++){
            InputNeuron n = new InputNeuron(this);
            super.addNeuron(n);
        }
    }
    public void feed(double [] inputArr) throws RuntimeErrorException {
        if(inputArr.length != getNeurons().size())
            throw new RuntimeErrorException(new Error("Invalid size!"));
        int neuronNum = 0;
        for(InputNeuron n : super.getNeurons()){
            if(n == null)
                throw new RuntimeErrorException(new Error("WRONG NEURON TYPE!"));
            n.gainInput(inputArr[neuronNum]);
            neuronNum ++;
        }
    }
}
