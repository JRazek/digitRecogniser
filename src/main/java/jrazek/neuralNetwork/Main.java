package jrazek.neuralNetwork;

import jrazek.neuralNetwork.backpropagation.BackpropagationModule;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.utils.Rules;

public class Main {
    public static void main(String[] args) {
        Net net = new Net();
        BackpropagationModule backpropagationModule = new BackpropagationModule(net);
        double e1 = 0;
        double e2 = 0;
        Double [] weights = {0.1, 0.1, 0.1, 0.1, 0.3, 0.1, 0.1, 0.1};
        //net.setWeights(weights);
        for(int i = 0; i < Rules.iterations; i++){
            net.forwardPass(new double[]{1d,2d});
            System.out.println( net.getConnections().get(4).getWeight());
            backpropagationModule.backPropagate(new double[]{0.99, 0.01});
            if(i == 0)
                e1 = backpropagationModule.showError();
            else if(i + 1 == Rules.iterations)
                e2 = backpropagationModule.showError();

        }
       // net.showStructure();
       // net.showOutput();
        System.out.println("Error 1:" + e1);
        System.out.println("Error 2:" + e2);
       // net.showConnections();
      //  System.out.println("test: " + backpropagationModule.test());
      //  System.out.println("Error 2:" + backpropagationModule.showError());
        //todo should make the sum of shuffled n training examples
    }
}
