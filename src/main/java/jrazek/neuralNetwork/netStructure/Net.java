package jrazek.neuralNetwork.netStructure;

import jrazek.neuralNetwork.abstracts.classes.Layer;
import jrazek.neuralNetwork.abstracts.classes.Neuron;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenLayer;
import jrazek.neuralNetwork.netStructure.inputLayer.InputLayer;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputLayer;

import java.util.ArrayList;
import java.util.List;

import static jrazek.neuralNetwork.utils.Rules.*;

public class Net {
    private List<Layer> layers;
    public Net(){
        layers = new ArrayList<>();
        initLayers();
        initConnections();
    }
    private void initLayers(){
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
    private void initConnections(){
        int layerNum = 0;
        for(Layer layer1 : layers){
            for(Neuron inputNeuron : layer1.getNeurons()){//input stands for input in connection
                for(Neuron outputNeuron : layer1.getNeurons()){//same as for input
                    Connection conn = new Connection(inputNeuron, outputNeuron);
                    inputNeuron.addConnection(conn);
                    outputNeuron.addConnection(conn);
                }
            }
            layerNum++;
            if(layerNum + 1 == layersNum){
                break;
            }
        }
    }
//    public forwardPass(){}
}
