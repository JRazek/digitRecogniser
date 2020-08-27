package jrazek.neuralNetwork.netStructure;

import jrazek.neuralNetwork.abstracts.classes.layers.DerivedLayer;
import jrazek.neuralNetwork.abstracts.classes.layers.FeedableLayer;
import jrazek.neuralNetwork.abstracts.classes.layers.Layer;
import jrazek.neuralNetwork.abstracts.classes.neurons.DerivedNeuron;
import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;
import jrazek.neuralNetwork.filesManagers.StructureManager;
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
    private List<Connection> connections;
    private List<Bias> biases;
    public Net(){
        layers = new ArrayList<>();
        connections = new ArrayList<>();
        biases = new ArrayList<>();
        initLayers();
        initNeurons();
        initBiases();
        setConnections();
        reset();
    }
    public Net(StructureManager.JSONNet jsonNet){
        layers = new ArrayList<>();
        connections = new ArrayList<>();
        biases = new ArrayList<>();
        initLayers();
        initNeurons();
        setBiases(jsonNet.biasList);
        setConnections(jsonNet.connectionList);
        reset();
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
        int biasID = 0;
        for(Layer<? extends Neuron> layer : layers.subList(1, layers.size())){
            if(layer instanceof DerivedLayer){
                for(Neuron n : layer.getNeurons()){
                    if(n instanceof DerivedNeuron){
                        Bias b = new Bias(n, biasID, 0);
                        ((DerivedNeuron) n).setBias(b);
                        biases.add(b);
                        biasID ++;
                    }
                }
            }
        }
    }
    private void setConnections(){
        int layerNum = 0;
        int connNum = 0;
        for(Layer<? extends Neuron> layer : layers){
            for(Neuron catcher : layer.getNeurons()){
                for(Neuron caught : layers.get(layerNum + 1).getNeurons()){
                    Connection conn = new Connection(catcher, caught,connNum);
                    catcher.addConnection(conn);
                    caught.addConnection(conn);
                    connections.add(0, conn);//just the reverse waay
                    connNum++;
                }
            }

            layerNum++;
            if(layerNum + 1 == layersNum){
                break;
            }
        }
    }
    public void setWeights(Double[] w) throws RuntimeErrorException{
        if(w.length != connections.size())
            throw new RuntimeErrorException(new Error("sffsdf"));
        int wNum = 0;
        for(double d : w){
            connections.get(wNum).setWeight((float)d);
            wNum ++;
        }
    }

    public void forwardPass(double[] argsArr)throws RuntimeException{
        reset();
        if(argsArr.length != layers.get(0).getNeurons().size())
            throw new RuntimeException(new Error("WRONG INITIAL CAPACITY!" + argsArr.length + " =/= " + inputNeurons));
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
    private void reset(){
        List<Layer<? extends Neuron>> layers = this.layers.subList(1, this.layers.size());
        for(Layer<? extends Neuron> layer : layers){
            if(layer instanceof DerivedLayer){
                ((DerivedLayer<? extends DerivedNeuron>) layer).reset();
            }
        }
    }
    public void showStructure(){
        for(Layer<? extends Neuron> currentLayer : layers){
            System.out.println(currentLayer.getLayerIndex() + " neurons: " + currentLayer.getNeurons());
        }
    }
    public void showConnections(){
        for(Connection conn : connections){
            System.out.println(conn.getInputNeuron() + " --" + conn.getId() + "--> " + conn.getOutputNeuron());
        }
    }
    public void showOutput(){
        Layer<? extends Neuron> l = layers.get(layersNum - 1);
        if(l instanceof OutputLayer){
            ((OutputLayer) l).showOutput();
        }
    }

    public OutputLayer getOutputLayer() throws  RuntimeErrorException{
        if(layers.get(layers.size()-1) instanceof OutputLayer)
            return (OutputLayer)layers.get(layers.size()-1);
        else
            throw new RuntimeErrorException(new Error("2123 ERROR"));
    }

    public List<Bias> getBiases() {
        return biases;
    }

    public List<Connection> getConnections() {
        return connections;
    }
    public List<Layer<? extends Neuron>> getLayers() {
        return layers;
    }

    private void setConnections(List<StructureManager.JSONConnection> connectionList){
        for(StructureManager.JSONConnection jsonConnection : connectionList){
            Neuron from = layers.get(jsonConnection.fromLayerNum).getNeurons().get(jsonConnection.fromNeuronNum);
            Neuron to = layers.get(jsonConnection.toLayerNum).getNeurons().get(jsonConnection.toNeuronNum);
            Connection conn = new Connection(from, to, jsonConnection.id);
            this.connections.add(conn);
            conn.setWeight((float) jsonConnection.weight);
            from.addConnection(conn);
            to.addConnection(conn);
        }
    }
    private void setBiases(List<StructureManager.JSONBias> biasList){
        for(StructureManager.JSONBias bias: biasList){
            DerivedNeuron n = (DerivedNeuron)layers.get(bias.layer).getNeurons().get(bias.neuron);
            Bias bias1 = new Bias(n, bias.id, bias.value);
            this.biases.add(bias1);
            n.setBias(bias1);
        }
    }
}
