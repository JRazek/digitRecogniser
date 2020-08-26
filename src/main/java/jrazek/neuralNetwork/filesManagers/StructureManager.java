package jrazek.neuralNetwork.filesManagers;

import jrazek.neuralNetwork.abstracts.classes.layers.DerivedLayer;
import jrazek.neuralNetwork.abstracts.classes.layers.Layer;
import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;
import jrazek.neuralNetwork.netStructure.Bias;
import jrazek.neuralNetwork.netStructure.Connection;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenLayer;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenNeuron;
import jrazek.neuralNetwork.netStructure.inputLayer.InputLayer;
import jrazek.neuralNetwork.netStructure.inputLayer.InputNeuron;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputLayer;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputNeuron;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StructureManager {
    private StructureManager(){

    }
    private static String savePath = "nets/";
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
            layersJSON.put(layerJSON);
        }
        for(Connection c : net.getConnections()){
            JSONObject connectionJSON = new JSONObject();
            connectionJSON.put("id", c.getId());
            connectionJSON.put("fromLayer", c.getInputNeuron().getLayer().getLayerIndex());
            connectionJSON.put("fromNeuron", c.getInputNeuron().getIndexInLayer());
            connectionJSON.put("toLayer", c.getOutputNeuron().getLayer().getLayerIndex());
            connectionJSON.put("toNeuron", c.getOutputNeuron().getIndexInLayer());
            connectionJSON.put("weight", c.getWeight());
            connectionsJSON.put(connectionJSON);
        }
        for(Bias bias : net.getBiases()){
            JSONObject biasJSON = new JSONObject();
            biasJSON.put("id", bias.getBiasID());
            biasJSON.put("value", bias.getValue());
            biasJSON.put("layer", bias.getNeuron().getLayer().getLayerIndex());
            biasJSON.put("neuron", bias.getNeuron().getIndexInLayer());
            biasesJSON.put(biasJSON);
        }
        json.put("layers", layersJSON);
        json.put("connections", connectionsJSON);
        try {
            File f = new File(savePath + "cp.json");
            if(!f.exists())
                f.createNewFile();
            FileWriter file = new FileWriter(f);
            file.write(json.toString(4));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Net load(String path){
        return null;
    }
}
