package jrazek.neuralNetwork.netStructure;

import jrazek.neuralNetwork.abstracts.Layer;
import jrazek.neuralNetwork.abstracts.Neuron;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenLayer;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenNeuron;
import jrazek.neuralNetwork.netStructure.inputLayer.InputLayer;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputLayer;

import java.util.List;

import static jrazek.neuralNetwork.utils.Rules.*;

public class Net {
    private List<Layer> layers;
    public Net(){
        initLayers();
        initConnections();
    }
    void initLayers(){
        for(int layerNum = 0; layerNum < layersNum; layerNum ++){
            if(layerNum == 0){
                layers.add(new InputLayer(layerNum));
            }
            else if(layerNum+1 == layersNum){
                layers.add(new OutputLayer(layers.get(layerNum-1), layerNum));
            }
            else{
                Layer l = new HiddenLayer(layers.get(layerNum-1), layerNum);
                layers.add(l);

                if(layerNum == 1){
                    InputLayer il = (InputLayer)layers.get(0);
                    il.setNextLayer(l);
                }
                else {
                    HiddenLayer il = (HiddenLayer) layers.get(layerNum-1);
                    il.setNextLayer(l);
                }
            }

        }
    }

    void initConnections(){
        int layerNum = 0;
        for(Layer layer : layers){

            layerNum++;
        }
    }
}
