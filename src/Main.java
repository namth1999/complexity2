import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main.run(5,33);
    }

    public static void run(int inputLength, int memoryLength) throws IOException {
        FileWriter fw = new FileWriter("input.txt");
        writeRandomArray(fw,createArray(inputLength));
        fw.close();

        fw = new FileWriter("output.txt");
        DynDSHeap.replacementSelection("input.txt",fw,memoryLength);
        fw.close();
    }

    public static int[] createArray(int length){
        int[] array = new int[length];
        for (int i=0; i<length;i++){
            array[i] = (int) (Math.random()*length + 1);
        }
        return array;
    }

    public static void writeRandomArray (FileWriter fw, int[] arr) throws IOException {
        for (int i=0;i<arr.length;i++){
            fw.write(arr[i] + " ");
        }
    }
}
