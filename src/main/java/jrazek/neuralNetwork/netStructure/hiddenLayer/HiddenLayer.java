package jrazek.neuralNetwork.netStructure.hiddenLayer;
import jrazek.neuralNetwork.abstracts.classes.DerivedLayer;

import static jrazek.neuralNetwork.utils.Rules.hiddenNeurons;

public class HiddenLayer extends DerivedLayer<HiddenNeuron> {
    public HiddenLayer(int index){
        super(index);
    }


    @Override
    public void initNeurons() {
        for(int i = 0; i < hiddenNeurons; i ++){
            super.addNeuron(new HiddenNeuron(this));
        }
    }

}
