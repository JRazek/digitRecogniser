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
import static jrazek.neuralNetwork.utils.Utils.round;
import static jrazek.neuralNetwork.utils.Utils.sigmoid;

public class BackpropagationModule {
    final private Net net;
    final private List<DerivedLayer<? extends DerivedNeuron>> derivedLayers;
    double errorT;
    double [] expected;
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
    public void backPropagate(double [] exp) throws RuntimeErrorException {
        this.expected = exp;
        this.errorT = getErrorT(expected);
        if(expected.length != net.getOutputLayer().getNeurons().size())
            throw new RuntimeErrorException(new Error("3123 ERROR"));
        Map<Connection, Double> gradient = new HashMap<>(net.getConnections().size());
        for (Connection conn : net.getConnections()){
            double delta = -gradientDescentRate*derivative(conn);
            System.out.println("delta: " + delta);
            gradient.put(conn, -gradientDescentRate*derivative(conn));
        }
        for(Map.Entry<Connection, Double> entry : gradient.entrySet()){
            System.out.println("old: " + entry.getKey().getWeight() + " new: " + entry.getValue());
            entry.getKey().updateWeight(entry.getValue());
            //weights should be updated after calculating all of the derivatives
        }
    }
    private double derivative(Connection c){
        DerivedNeuron startingNeuron = ((DerivedNeuron)c.getOutputNeuron());

        double result;
        result = startingNeuron.getNetValue();//1
        result *= startingNeuron.getActivationValue()*(1-startingNeuron.getActivationValue());//2
        result *= getChain(startingNeuron);
        return result;
    }
    private double getChain(DerivedNeuron start){
        double result = 1;

        //in hidden layer
        double tmp = 1;
        for(Connection conn : start.getOutPutConnections()){
            tmp += conn.getWeight() * getChain((DerivedNeuron)conn.getOutputNeuron());
        }
        result *= tmp;

        //in the final layer
        int go = 0;
        if(net.getLayers().get(start.getLayer().getLayerIndex()) instanceof OutputLayer)
            go = 2;
        else if(net.getLayers().get(start.getLayer().getLayerIndex() + 1) instanceof OutputLayer)
            go = 1;
        if(go != 0){
            Layer<?extends Neuron> l;
            if(go == 1){
                l = net.getLayers().get(start.getLayer().getLayerIndex() + 1);
            }else{
                l = net.getLayers().get(start.getLayer().getLayerIndex());
            }
            for(Neuron n : l.getNeurons()){
                if(n instanceof OutputNeuron){
                    double T = expected[n.getIndexInLayer()];
                    double a = ((OutputNeuron) n).getActivationValue();
                    result *= -2*(T-a)*a;
                }
            }
            return result;
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
