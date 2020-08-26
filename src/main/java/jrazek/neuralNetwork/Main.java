package jrazek.neuralNetwork;

import jrazek.neuralNetwork.backpropagation.BackpropagationModule;
import jrazek.neuralNetwork.filesManagers.FileDecoder;
import jrazek.neuralNetwork.filesManagers.HandWrittenNumber;
import jrazek.neuralNetwork.filesManagers.StructureManager;
import jrazek.neuralNetwork.netStructure.Net;
import jrazek.neuralNetwork.utils.Accuracy;

import static jrazek.neuralNetwork.utils.Rules.*;

public class Main {
    public static void main(String[] args) {
        FileDecoder fileDecoder = new FileDecoder();
        Net net;
        if(loadFromFile && !loadFile.equals("")){
            net = new Net(StructureManager.load(loadFile));
        }else{
            net = new Net();
        }
        BackpropagationModule backpropagationModule = new BackpropagationModule(net);
        Accuracy accuracy = new Accuracy(net);
        int iteration = 0;
        while(fileDecoder.hasNext() && (maxIterations == -1 || iteration < maxIterations)){
            HandWrittenNumber num = fileDecoder.getNextImage();
            //net.forwardPass(new double[]{0.99, 0, 0, 0.99, 0, 0, 0, 0.13, 0, 0,0.1,0.543, .21, .765, .123, .765});
            net.forwardPass(num.getPixels());
            accuracy.check(num.getTarget());

            double [] target = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            //double [] target = new double[]{0, 0, 0, 0.99, 0, 0, 0, 0, 0, 0};
            target[num.getTarget()] = 0.99;
            backpropagationModule.backPropagate(target);
            iteration ++;

            if((iteration+1) % accuracyResetRate == 0){
                System.out.println("Iteration: " + iteration + ", accuracy = " + accuracy.getAccuracy()*100 + "%");
                System.out.println("Error " + iteration + ": " + backpropagationModule.showError());
                accuracy.reset();
            }
        }
        StructureManager.save(net);
        //todo should make the sum of shuffled n training examples
    }
}
