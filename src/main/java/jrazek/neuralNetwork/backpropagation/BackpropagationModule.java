package jrazek.neuralNetwork.backpropagation;

import jrazek.neuralNetwork.abstracts.classes.layers.DerivedLayer;
import jrazek.neuralNetwork.abstracts.classes.layers.Layer;
import jrazek.neuralNetwork.abstracts.classes.neurons.DerivedNeuron;
import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.netStructure.hiddenLayer.HiddenLayer;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputLayer;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputNeuron;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jrazek.neuralNetwork.utils.Rules.gradientDescentRate;
import static jrazek.neuralNetwork.utils.Utils.sigmoid;

public class BackpropagationModule {
    final private Net net;
    final private List<DerivedLayer<? extends DerivedNeuron>> derivedLayers;
    public BackpropagationModule(Net net) {
        this.net = net;
        this.derivedLayers = new ArrayList<>();
        List<Layer<? extends Neuron>> layers = net.getLayers().subList(1, net.getLayers().size());
        for(Layer<? extends Neuron> layer : layers){
            if(layer instanceof DerivedLayer){
                derivedLayers.add((DerivedLayer<?extends DerivedNeuron>)layer);
            }
        }
    }
    public void backPropagate(double [] expected) throws RuntimeErrorException {
        if(expected.length != net.getOutputLayer().getNeurons().size())
            throw new RuntimeErrorException(new Error("3123 ERROR"));
        double errorT = getErrorT(expected);
        Map<Connection, Double> gradient = new HashMap<>(net.getConnections().size());
        for (Connection conn : net.getConnections()){
            gradient.put(conn, -gradientDescentRate*derivative(conn, errorT));
        }
        for(Map.Entry<Connection, Double> entry : gradient.entrySet()){
            entry.getKey().updateWeight(entry.getValue());
            //weights should be updated after calculating all of the derivatives
        }
    }
    private double derivative(Connection c, double Error){
        Connection currentChecked = null;
        double x = c.getWeight();
        DerivedNeuron startingNeuron = ((DerivedNeuron)c.getOutputNeuron());

        double result;
        result = startingNeuron.getNetValue();//1
        result *= getChain(startingNeuron);
        return result;
    }
    private double getChain(DerivedNeuron start){
        double result = 1;
        if(!(net.getLayers().get(start.getLayer().getLayerIndex()+1) instanceof DerivedLayer))
            throw new RuntimeErrorException(new Error("2311"));
        DerivedLayer<? extends DerivedNeuron> layer = (DerivedLayer<? extends DerivedNeuron>)net.getLayers().get(start.getLayer().getLayerIndex()+1);
        result *= start.getActivationValue()*(1-start.getActivationValue());//2

        for(DerivedNeuron n : layer.getNeurons()){
            /// TODO: 25.08.2020 this xhit
        }
        return result;
    }
    private double getErrorT(double [] expected){
        double sum = 0;
        int neuronNum = 0;
        for(OutputNeuron neuron : net.getOutputLayer().getNeurons()){
            sum += Math.pow(neuron.getActivationValue() - expected[neuronNum], 2);
            neuronNum++;
        }
        return sum;
    }
}
