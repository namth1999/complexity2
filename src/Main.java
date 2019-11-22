import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("output.txt");
//
//        int[] inputArray = DynDSHeap.readInputAsArray("/Users/riccard/Downloads/complexity2/src/input.txt");
//        int[] outputArray = new int[inputArray.length];
//
//        DynDSHeap.outputResult(fw, outputArray);
//        DynDSHeap.outputResult(fw, inputArray);
//
//        fw.close();

        DynDSHeap.replacementSelection("input.txt",fw,4);
        fw.close();

    }
}
