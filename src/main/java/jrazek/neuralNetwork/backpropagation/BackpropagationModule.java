package jrazek.neuralNetwork.backpropagation;

import jrazek.neuralNetwork.abstracts.classes.layers.DerivedLayer;
import jrazek.neuralNetwork.abstracts.classes.layers.Layer;
import jrazek.neuralNetwork.abstracts.classes.neurons.DerivedNeuron;
import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;
import jrazek.neuralNetwork.netStructure.Connection;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputNeuron;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static jrazek.neuralNetwork.utils.Rules.gradientDescentRate;

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
        for (Connection conn : net.getConnections()){
            conn.updateWeight(-gradientDescentRate*derivative(conn, errorT));
        }
    }
    private double derivative(Connection c, double Error){
        Connection currentChecked = null;
        double x = c.getWeight();
        //todo
        return 0;
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
