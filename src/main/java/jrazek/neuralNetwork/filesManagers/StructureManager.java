package jrazek.neuralNetwork.filesManagers;

import jrazek.neuralNetwork.abstracts.classes.layers.Layer;
import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;
import jrazek.neuralNetwork.netStructure.Bias;
import jrazek.neuralNetwork.netStructure.Connection;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenLayer;
import jrazek.neuralNetwork.netStructure.inputLayer.InputLayer;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputLayer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.management.RuntimeErrorException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static jrazek.neuralNetwork.utils.Rules.*;

@SuppressWarnings("unchecked")
public class StructureManager {
    private StructureManager(){

    }
    public static void save(Net net){
        JSONObject json = new JSONObject();
        JSONArray layersJSON = new JSONArray();
        JSONArray connectionsJSON = new JSONArray();
        JSONArray biasesJSON = new JSONArray();
        for(Layer<? extends Neuron> layer : net.getLayers()){
            JSONObject layerJSON = new JSONObject();
            if(layer instanceof InputLayer){
                layerJSON.put("type:", "InputLayer");
            }
            else if(layer instanceof HiddenLayer){
                layerJSON.put("type:", "HiddenLayer");
            }
            else if(layer instanceof OutputLayer){
                layerJSON.put("type:", "OutputLayer");
            }
            layerJSON.put("index:", layer.getLayerIndex());
            layerJSON.put("neurons", layer.getNeurons().size());
            layersJSON.add(layerJSON);
        }
        for(Connection c : net.getConnections()){
            JSONObject connectionJSON = new JSONObject();
            connectionJSON.put("id", c.getId());
            connectionJSON.put("fromLayer", c.getInputNeuron().getLayer().getLayerIndex());
            connectionJSON.put("fromNeuron", c.getInputNeuron().getIndexInLayer());
            connectionJSON.put("toLayer", c.getOutputNeuron().getLayer().getLayerIndex());
            connectionJSON.put("toNeuron", c.getOutputNeuron().getIndexInLayer());
            connectionJSON.put("weight", c.getWeight());
            connectionsJSON.add(connectionJSON);
        }
        for(Bias bias : net.getBiases()){
            JSONObject biasJSON = new JSONObject();
            biasJSON.put("id", bias.getBiasID());
            biasJSON.put("value", bias.getValue());
            biasJSON.put("layer", bias.getNeuron().getLayer().getLayerIndex());
            biasJSON.put("neuron", bias.getNeuron().getIndexInLayer());
            biasesJSON.add(biasJSON);
        }
        json.put("layers", layersJSON);
        json.put("biases", biasesJSON);
        json.put("connections", connectionsJSON);
        try {
            File f = new File(savePath);
            if(!f.exists())
                f.createNewFile();
            FileWriter file = new FileWriter(f);
            file.write(FileReader.formatJSON(json.toJSONString()));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONNet load(String path) throws RuntimeErrorException, IOException {
       // readJSONFile
        JSONObject json;
        try {
            json = FileReader.readJSONFile(path);
        }catch (IOException e){
            throw new IOException();
        }
        List<JSONConnection> connectionList = new ArrayList<>();
        List<JSONBias> biasList = new ArrayList<>();
        for(Object o : ((JSONArray)json.get("connections"))){
            JSONObject connJSON = (JSONObject)o;
            //System.out.println();
            int id = Integer.valueOf(connJSON.get("id").toString());
            int toLayer = Integer.valueOf(connJSON.get("toLayer").toString());
            int toNeuron = Integer.valueOf(connJSON.get("toNeuron").toString());
            int fromLayer = Integer.valueOf(connJSON.get("fromLayer").toString());
            int fromNeuron = Integer.valueOf(connJSON.get("fromNeuron").toString());
            double weight = Double.valueOf(connJSON.get("weight").toString());
            connectionList.add(new JSONConnection(id, toLayer, toNeuron, fromLayer, fromNeuron, weight));
        }
        int connSum = hiddenNeurons*(layersNum-2) + hiddenNeurons*(inputNeurons+outputNeurons);
        for(Object o : ((JSONArray)json.get("biases"))){
            JSONObject connJSON = (JSONObject)o;
            int id = Integer.valueOf(connJSON.get("id").toString());
            double value = Double.valueOf(connJSON.get("value").toString());
            int layer = Integer.valueOf(connJSON.get("layer").toString());
            int neuron = Integer.valueOf(connJSON.get("neuron").toString());
            biasList.add(new JSONBias(id, value, layer, neuron));
        }
        return new JSONNet(connectionList, biasList);
    }
    public static class JSONNet{
        public JSONNet(List<JSONConnection> c, List<JSONBias> b){
            this.connectionList = c;
            this.biasList = b;
        }
        public final List<JSONConnection> connectionList;
        public final List<JSONBias> biasList;
    }
    public static class JSONConnection{
        public JSONConnection(int id, int tl, int tn, int fl, int fn, double w){
            this.toLayerNum = tl;
            this.toNeuronNum = tn;
            this.fromLayerNum = fl;
            this.fromNeuronNum = fn;
            this.id = id;
            this.weight = w;
        }
        final public int id;
        final public int toLayerNum;
        final public int toNeuronNum;
        final public int fromLayerNum;
        final public int fromNeuronNum;
        final public double weight;
    }
    public static class JSONBias{
        public JSONBias(int id, double v, int l, int n){
            this.id = id;
            this.value = v;
            this.layer = l;
            this.neuron = n;
        }
        final public int id;
        final public double value;
        final public int layer;
        final public int neuron;
    }
}
