package jrazek.neuralNetwork.netStructure.outputLayer;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.inputLayer.InputNeuron;

import javax.management.RuntimeErrorException;

import static jrazek.neuralNetwork.utils.Rules.inputNeurons;
import static jrazek.neuralNetwork.utils.Rules.outputNeurons;

public class OutputLayer extends Layer {
    private Layer previousLayer;
    public OutputLayer(Layer prev, int index){
        super(index);
        this.previousLayer = prev;
    }

    @Override
    protected void initNeurons() {
        for(int i = 0; i < inputNeurons; i ++){
            super.addNeuron(new OutputNeuron(this));
        }
    }


    public void feed(double [] inputArr) throws RuntimeErrorException {
        if(inputArr.length != super.getNeurons().size())
            throw new RuntimeErrorException(new Error("Invalid size!"));
        int neuronNum = 0;
        for(Neuron n : super.getNeurons()){
            if(!(n instanceof OutputNeuron))
                throw new RuntimeErrorException(new Error("WRONG NEURON TYPE!"));
            OutputNeuron outputNeuron = (OutputNeuron) n;
            outputNeuron.gainInput(inputArr[neuronNum]);
            neuronNum ++;
        }
    }
}
