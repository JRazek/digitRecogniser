package jrazek.neuralNetwork.abstracts.classes.neurons;

import jrazek.neuralNetwork.abstracts.classes.layers.Layer;
import jrazek.neuralNetwork.netStructure.Bias;
import jrazek.neuralNetwork.netStructure.Connection;
import jrazek.neuralNetwork.netStructure.inputLayer.InputNeuron;

import javax.management.RuntimeErrorException;

public abstract class DerivedNeuron extends Neuron{
    private double netValue;
    private double activationValue;
    private Bias bias;
    public DerivedNeuron(Layer<? extends Neuron> layer, int number) {
        super(layer, number);
    }

    public void setBias(Bias bias) {
        this.bias = bias;
    }

    public void sumInputs() throws RuntimeErrorException{
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
        activationValue = 1/(1+Math.pow(Math.E, -netValue));
    }
    public double getActivationValue() {
        return activationValue;
    }
}
