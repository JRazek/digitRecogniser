package jrazek.neuralNetwork.backpropagation;

import jrazek.neuralNetwork.abstracts.classes.layers.DerivedLayer;
import jrazek.neuralNetwork.abstracts.classes.layers.Layer;
import jrazek.neuralNetwork.abstracts.classes.neurons.DerivedNeuron;
import jrazek.neuralNetwork.abstracts.classes.neurons.Neuron;
import jrazek.neuralNetwork.netStructure.Bias;
import jrazek.neuralNetwork.netStructure.Connection;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.netStructure.inputLayer.InputNeuron;
import jrazek.neuralNetwork.netStructure.outputLayer.OutputNeuron;

import javax.management.RuntimeErrorException;
import java.util.*;

import static jrazek.neuralNetwork.utils.Rules.gradientDescentRate;
import static jrazek.neuralNetwork.utils.Rules.updateRateAverage;

public class BackpropagationModule {
    final private Net net;
    final private List<DerivedLayer<? extends DerivedNeuron>> derivedLayers;
    Map<Connection, List<Double>> gradientWeights = new HashMap<>();
  //  Map<Bias, Double> gradientBiases =
    private double errorT;
    private double [] expected;
    private int iteration;
    public BackpropagationModule(Net net) {
        this.net = net;
        this.derivedLayers = new ArrayList<>();
        this.iteration = 0;
        List<Layer<? extends Neuron>> layers = net.getLayers().subList(1, net.getLayers().size());
        for(Layer<? extends Neuron> layer : layers){
            if(layer instanceof DerivedLayer){
                derivedLayers.add((DerivedLayer<?extends DerivedNeuron>)layer);
            }
        }
        for(Connection c : net.getConnections()){
            gradientWeights.put(c, new LinkedList<>());
        }
    }
    public void backPropagate(double [] exp) throws RuntimeErrorException {
        this.expected = exp;
        this.errorT = getErrorT(expected);
        if(expected.length != net.getOutputLayer().getNeurons().size())
            throw new RuntimeErrorException(new Error("3123 ERROR"));
       // Map<Connection, Double> gradientWeights = new HashMap<>(net.getConnections().size());
        Map<Bias, Double> gradientBiases = new HashMap<>(net.getBiases().size());

        /// TODO: 25.08.2020 averaging gradients

        for (Connection conn : net.getConnections()){
            double delta = -gradientDescentRate * derivativeWeight(conn);
            gradientWeights.get(conn).add(delta);
        }
        for (Bias bias : net.getBiases()){
            double delta = -gradientDescentRate * derivativeBias(bias);
            gradientBiases.put(bias, delta);
        }
        if(iteration != 0 && iteration % updateRateAverage == 0){
            calculateAverageGradient();
        }
        iteration ++;
    }
    private void calculateAverageGradient(){

        for(Map.Entry<Connection, List<Double>> entry : gradientWeights.entrySet()){
            double sum = 0;
            for(Double d : entry.getValue()){
                sum += d;
            }
            sum /= entry.getValue().size();
            entry.getKey().updateWeight(sum);
            entry.getValue().clear();
            //entry.getKey().updateWeight(entry.getValue());
            //weights should be updated after calculating all of the derivatives and biases
        }
       /* for(Map.Entry<Bias, Double> entry : gradientBiases.entrySet()){
            entry.getKey().updateBias(entry.getValue());
            //biases should be updated after calculating all of the derivatives biases
        }*/
        //todo remember to clear List after calculation
    }
    private double derivativeWeight(Connection c){
        DerivedNeuron startingNeuron = ((DerivedNeuron)c.getOutputNeuron());
        double result = 1;

        if(c.getInputNeuron() instanceof InputNeuron)
            result = ((InputNeuron) c.getInputNeuron()).getOutput();//1
        else if(c.getInputNeuron() instanceof DerivedNeuron)
            result = ((DerivedNeuron) c.getInputNeuron()).getActivationValue();//1

        return result * getChain(startingNeuron);
    }
    private double derivativeBias(Bias b){
        return getChain((DerivedNeuron)b.getNeuron());
    }
    private double getChain(DerivedNeuron start){
        double chain = 1;
        chain *= start.getActivationValue()*(1-start.getActivationValue());//a(L) wrt z(L
        if(start instanceof OutputNeuron){
            /// TODO: 26.08.2020 check this
            chain *= 2*(start.getActivationValue() - expected[start.getIndexInLayer()])*start.getActivationValue();
        }
        else {
            double tmp = 1;
            for (Connection c : start.getOutPutConnections()) {
                chain *= c.getWeight();//z(L) wrt a(L-1) (just w)
                tmp += getChain((DerivedNeuron) c.getOutputNeuron()); //further chaining
            }
            chain *= tmp;
        }
        return chain;
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
    public double showError(){
        return errorT;
    }
    public double test(){
        Connection c = null;
        for(Connection co : net.getConnections()){
            if(co.getId() == 4)//static value.
                c = co;
        }
        if(c == null)
            return 0;
        DerivedNeuron in;
        DerivedNeuron out;
        if(c.getInputNeuron() == null || c.getOutputNeuron() == null)
            return 0;
        if(!(c.getInputNeuron() instanceof DerivedNeuron && c.getOutputNeuron() instanceof DerivedNeuron))
            return 0;
        in = ((DerivedNeuron)c.getInputNeuron());
        out = ((DerivedNeuron)c.getOutputNeuron());
        return 2*in.getActivationValue()*out.getActivationValue()*(1-out.getActivationValue())*(0.99-out.getActivationValue());
    }
}
