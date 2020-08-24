package jrazek.neuralNetwork.netStructure;

import jrazek.neuralNetwork.abstracts.classes.layers.DerivedLayer;
import jrazek.neuralNetwork.abstracts.classes.layers.FeedableLayer;
import jrazek.neuralNetwork.abstracts.classes.layers.Layer;
import jrazek.neuralNetwork.abstracts.classes.neurons.DerivedNeuron;
import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenLayer;
import jrazek.neuralNetwork.netStructure.inputLayer.InputLayer;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputLayer;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

import static jrazek.neuralNetwork.utils.Rules.*;
import static jrazek.neuralNetwork.utils.Utils.randomDouble;

public class Net {
    private List<Layer<? extends Neuron>> layers;
    public Net(){
        layers = new ArrayList<>();
        initLayers();
        initNeurons();
        initBiases();
        initConnections();
    }
    private void initLayers(){
        for(int layerNum = 0; layerNum < layersNum; layerNum ++){

            if(layerNum == 0){//first
                layers.add(new InputLayer(layerNum));
            }
            else if(layerNum+1 == layersNum){//last
                layers.add(new OutputLayer(layerNum));
            }
            else{//between
                layers.add(new HiddenLayer(layerNum));
            }
        }
    }
    private void initNeurons(){
        for(Layer<? extends Neuron> l : layers){
            if(l != null)
                l.initNeurons();
        }
    }
    private void initBiases(){
        for(Layer<? extends Neuron> layer : layers.subList(1, layers.size())){
            if(layer instanceof DerivedLayer){
                for(Neuron n : layer.getNeurons()){
                    if(n instanceof DerivedNeuron){
                        double randBiasVal = randomDouble(-10, 10);
                        ((DerivedNeuron) n).setBias(new Bias(n, randBiasVal));
                    }
                }
            }
        }
    }
    private void initConnections(){
        //the first layer catches the next except the last layer

        int layerNum = 0;
        for(Layer<? extends Neuron> layer : layers){

            for(Neuron catcher : layer.getNeurons()){
                for(Neuron caught : layers.get(layerNum + 1).getNeurons()){
                    Connection conn = new Connection(catcher, caught);
                    catcher.addConnection(conn);
                    caught.addConnection(conn);
                }
            }

            layerNum++;
            if(layerNum + 1 == layersNum){
                break;
            }
        }
    }
    public void forwardPass(double[] argsArr)throws RuntimeException{
        if(argsArr.length != inputNeurons)
            throw new RuntimeException(new Error("WRONG INITIAL CAPACITY!"));
        if(layers.get(0) instanceof FeedableLayer){
            ((FeedableLayer) layers.get(0)).feed(argsArr);
        }
        //iterates except for first
        for (Layer<? extends Neuron> layer : layers.subList(1, layers.size())){
            if(layer instanceof DerivedLayer){
                ((DerivedLayer<? extends DerivedNeuron>) layer).takeFromPreviousLayer();
            }
        }
    }
    public void showStructure(){
        for(Layer<? extends Neuron> currentLayer : layers){
            System.out.println(currentLayer.getLayerIndex() + " neurons: " + currentLayer.getNeurons());
        }
    }
    public void showConnections(int layerNum, int neuronNum){

    }
    public void showOutput(){
        Layer<? extends Neuron> l = layers.get(layersNum - 1);
        if(l instanceof OutputLayer){
            ((OutputLayer) l).showOutput();
        }
    }

    public OutputLayer getOutputLayer() throws  RuntimeErrorException{
        if(layers.get(layersNum-1) instanceof OutputLayer)
            return (OutputLayer)layers.get(layersNum-1);
        else
            throw new RuntimeErrorException(new Error("2123 ERROR"));
    }
}
