package jrazek.neuralNetwork.netStructure;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenLayer;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenNeuron;
import jrazek.neuralNetwork.netStructure.inputLayer.InputLayer;
import jrazek.neuralNetwork.netStructure.inputLayer.InputNeuron;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputLayer;

import java.util.ArrayList;
import java.util.List;

import static jrazek.neuralNetwork.utils.Rules.*;

public class Net {
    private List<Layer> layers;
    public Net(){
        layers = new ArrayList<>();
        initLayers();
        initNeurons();
        initConnections();
    }
    private void initLayers(){
        for(int layerNum = 0; layerNum < layersNum; layerNum ++){

            if(layerNum == 0){//first
                Layer<InputNeuron> l = new InputLayer(layerNum);
                layers.add(l);
            }
            else if(layerNum+1 == layersNum){//last
                layers.add(new OutputLayer(layers.get(layerNum-1), layerNum));
            }
            else{//between
                layers.add(new HiddenLayer(layers.get(layerNum-1), layerNum));
            }
            if(layerNum != 0){
                //add to previous the next neuron. Doesnt check the 0th as it has no prev.
                if(layers.get(layerNum-1) instanceof InputLayer){
                    ((InputLayer)layers.get(layerNum-1)).setNextLayer(layers.get(layerNum));
                }
                else if(layers.get(layerNum-1) instanceof HiddenLayer){
                    ((HiddenLayer)layers.get(layerNum-1)).setNextLayer(layers.get(layerNum));
                }
            }

        }
    }
    private void initNeurons(){
        for(Layer l : layers){
            if(l != null)
                l.initNeurons();
        }
    }
    private void initConnections(){

    }
    public void showStructure(){
        int layerNum = 0;
        for(Layer currentLayer : layers){
            if(currentLayer != null)
                System.out.println(layerNum + " type: " + currentLayer.getNeurons());
            layerNum++;
        }
    }
}
