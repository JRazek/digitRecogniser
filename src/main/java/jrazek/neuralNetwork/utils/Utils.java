package jrazek.neuralNetwork.utils;
import org.w3c.dom.css.RGBColor;

import javax.management.RuntimeErrorException;
import java.util.Random;

import static jrazek.neuralNetwork.utils.Rules.sigmoidDivider;

public class Utils {
    public static class Vector2D{
        private double x, y;
        public Vector2D(double x, double y){
            this.x = x; this.y = y;
        }
        public double getX() {
            return x;
        }
        public double getY() {
            return y;
        }
        public void setX(double x) {
            this.x = x;
        }
        public void setY(double y) {
            this.y = y;
        }
        public void add(Vector2D adder){
            x = x + adder.getX();
            y = y + adder.getY();
        }
    }
    public static double randomDouble(){
        Random r = new Random();
        int m = 1;
        if(randomBoolean())
            m = -1;
        return r.nextDouble()*m;
    }
    public static double randomDouble(double min, double max){
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }
    public static float randomFloat(float min, float max){
        Random r = new Random();
        return min + (max - min) * r.nextFloat();
    }
    public static int randomInt(){
        return randomInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public static int randomInt(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    public static double sigmoid(double x) throws RuntimeErrorException{
        double c = (1d/(1d+Math.pow(Math.E, -(x/sigmoidDivider))));
        if(c >= 1 || c < 0)
            throw new RuntimeErrorException(new Error("INVALID ACTIVATION VALUE!" + c + " from " + x));
        //System.out.println("sigmoid: " + c);
        return c;
    }
    public static float toGrayscale(int p){

        int a = (p>>24)&0xff;
        int r = (p>>16)&0xff;
        int g = (p>>8)&0xff;
        int b = p&0xff;

        return p;
    }
    public static double round(double num, int n) {
        num *= Math.pow(10, n);
        num = Math.round(num);
        return num / Math.pow(10, n);
    }
    public static boolean randomBoolean(){
        Random rand = new Random();
        return rand.nextBoolean();
    }
    public static class Domain{
        float min, max;
        public Domain(float min, float max){
            this.min = min;
            this.max = max;
        }
        public float getMin() {
            return min;
        }
        public float getMax() {
            return max;
        }
    }
}
