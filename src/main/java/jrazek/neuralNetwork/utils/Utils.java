package jrazek.neuralNetwork.utils;
import java.util.Random;

public class Utils {
    public static class Vector2I{
        private int x, y;
        public Vector2I(int x, int y){
            this.x = x; this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }
        public void add(Vector2I adder){
            x = x + adder.getX();
            y = y + adder.getY();
        }
    }
    public static class Vector2F{
        private float x, y;
        public Vector2F(float x, float y){
            this.x = x; this.y = y;
        }
        public float getX() {
            return x;
        }
        public float getY() {
            return y;
        }
        public void setX(float x) {
            this.x = x;
        }
        public void setY(float y) {
            this.y = y;
        }
        public void add(Vector2F adder){
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
