package jrazek.neuralNetwork.netStructure.outputLayer;

import jrazek.neuralNetwork.abstracts.Layer;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenNeuron;
import jrazek.neuralNetwork.netStructure.inputLayer.InputNeuron;

import static jrazek.neuralNetwork.utils.Rules.hiddenNeurons;
import static jrazek.neuralNetwork.utils.Rules.inputNeurons;

public class OutputLayer extends Layer {
    private Layer previousLayer;
    OutputLayer(int index){
        super(index);
    }

    @Override
    protected void initNeurons() {
        for(int i = 0; i < inputNeurons; i ++){
            super.addNeuron(new OutputNeuron(this));
        }
    }
}
