package jrazek.neuralNetwork.utils;
import java.util.Random;

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
        return randomDouble(Double.MIN_VALUE, Double.MAX_VALUE);
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
