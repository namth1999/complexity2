import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main.run(33);
    }

    public static void run(int memoryLength) throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        DynDSHeap.replacementSelection("input.txt",fw,memoryLength);
        fw.close();
    }
}
