import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        DynDSHeap.replacementSelection("input.txt",fw,4);
        fw.close();

    }
}
