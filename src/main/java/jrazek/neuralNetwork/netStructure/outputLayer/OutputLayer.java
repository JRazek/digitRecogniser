package jrazek.neuralNetwork.netStructure.outputLayer;
import jrazek.neuralNetwork.abstracts.classes.layers.DerivedLayer;
import jrazek.neuralNetwork.abstracts.classes.neurons.DerivedNeuron;

import static jrazek.neuralNetwork.utils.Rules.outputNeurons;

public class OutputLayer extends DerivedLayer<OutputNeuron> {
    public OutputLayer(int index){
        super(index);
    }

    @Override
    public void initNeurons() {
        for(int i = 0; i < outputNeurons; i ++){
            super.addNeuron(new OutputNeuron(this, i));
        }
    }
    public void showOutput(){
        for(DerivedNeuron neuron : super.getNeurons()){
            System.out.println(neuron.getActivationValue());
        }
    }
}