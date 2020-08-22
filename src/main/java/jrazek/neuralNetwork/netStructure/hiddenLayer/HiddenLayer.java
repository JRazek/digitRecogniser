package jrazek.neuralNetwork.netStructure.hiddenLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.inputLayer.InputNeuron;

import javax.management.RuntimeErrorException;

import static jrazek.neuralNetwork.utils.Rules.hiddenNeurons;

public class HiddenLayer extends Layer {
    private Layer previousLayer;
    private Layer nextLayer;
    public HiddenLayer(Layer prev, int index){
        super(index);
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    @Override
    protected void initNeurons() {
        for(int i = 0; i < hiddenNeurons; i ++){
            super.addNeuron(new HiddenNeuron(this));
        }
    }
    public void feed(double [] inputArr) throws RuntimeErrorException {
        if(inputArr.length != super.getNeurons().size())
            throw new RuntimeErrorException(new Error("Invalid size!"));
        int neuronNum = 0;
        for(Neuron n : super.getNeurons()){
            if(!(n instanceof HiddenNeuron))
                throw new RuntimeErrorException(new Error("WRONG NEURON TYPE!"));
            HiddenNeuron hiddenNeuron = (HiddenNeuron) n;
            hiddenNeuron.gainInput(inputArr[neuronNum]);
            neuronNum ++;
        }
    }
}
