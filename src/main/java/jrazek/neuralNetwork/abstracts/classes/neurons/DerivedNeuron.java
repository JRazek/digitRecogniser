package jrazek.neuralNetwork.abstracts.classes.neurons;

import jrazek.neuralNetwork.abstracts.classes.layers.Layer;
import jrazek.neuralNetwork.netStructure.Bias;
import jrazek.neuralNetwork.netStructure.Connection;
import jrazek.neuralNetwork.netStructure.inputLayer.InputNeuron;

import javax.management.RuntimeErrorException;

import java.util.LinkedList;
import java.util.List;

import static jrazek.neuralNetwork.utils.Utils.sigmoid;

public abstract class DerivedNeuron extends Neuron{
    private double netValue;
    private double activationValue;
    private Bias bias;
    public DerivedNeuron(Layer<? extends Neuron> layer, int number) {
        super(layer, number);
    }
    public void reset(){
        netValue = 0;
        activationValue = 0;
    }
    public void setBias(Bias bias) {
        this.bias = bias;
    }

    public void sumInputs() throws RuntimeErrorException{
        netValue = 0;
        for(Connection conn : super.connections){
            if(conn.getOutputNeuron().equals(this)){
                if(conn.getInputNeuron() instanceof DerivedNeuron){
                    netValue+=((DerivedNeuron) conn.getInputNeuron()).getActivationValue()
                    *
                    conn.getWeight();
                }else if(conn.getInputNeuron() instanceof InputNeuron){
                    netValue += ((InputNeuron) conn.getInputNeuron()).getOutput()
                    *
                    conn.getWeight();
                }else{
                    throw new RuntimeErrorException(new Error("GOD HELP ME, TERRIBLE ERROR"));
                }
            }
        }
        netValue += bias.getValue();
    }
    public void countActivation(){
        try {
            activationValue = sigmoid(netValue);
        }catch(RuntimeErrorException e){
            //System.out.println("======================" + super.getLayer().getLayerIndex());
            //System.exit(1);
        }
    }
    public double getTest(){
        return 1;
    }
    public double getNetValue() {
        return netValue;
    }

    public Bias getBias() {
        return bias;
    }

    public List<Connection> getOutPutConnections(){
        List<Connection> derived = new LinkedList<>();
        for(Connection conn : super.getConnections()){
            if(conn.getInputNeuron().equals(this))
                derived.add(conn);
        }
        return derived;
    }
    public double getActivationValue() {
        return activationValue;
    }
}
