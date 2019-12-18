import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the replacement selection algorithm
 */
public class DynDSHeap {
    /**
     * Replacement selection algorithm
     * @param filename where to output result to this file
     * @param output writer of the output file
     * @param memoryLength M
     * @throws IOException
     */
    public static void replacementSelection(String filename, FileWriter output, int memoryLength) throws IOException {
        //find out the input length
        int inputLength = inputArrayLength(filename);
        int[] memoryArray = new int[memoryLength];
        //Use an array with a suitable length
        if (inputLength<memoryLength){
            memoryLength = inputLength;
        }
        int heapLength = memoryLength;
        int dsLength = memoryLength - heapLength;
        //read M elements from IN into heap
        Scanner s = new Scanner(new File(filename));
        for (int i = 0; i < memoryLength; i++) {
            memoryArray[i] = s.nextInt();
            inputLength--;
        }

        memoryArray = Heap.buildHeap(memoryArray,heapLength);

        while (inputLength != 0) {
            output.write(memoryArray[0] + "\n");
            //remove smallest element
            int smallest = memoryArray[0];
            memoryArray = Heap.deleteMinHeap(memoryArray,heapLength);
            int next = s.nextInt();

            //Insert next to heap or deadspace
            if (next>=smallest){
                memoryArray = Heap.insertMinHeap(memoryArray,heapLength-1,next);
            } else {
                heapLength--;
                dsLength++;
                memoryArray = Heap.add(memoryArray.length,memoryArray,next);
            }

            //deadspace = N, buildHeap deadspace
            if (heapLength == 0 || dsLength == memoryLength){
                memoryArray = Heap.buildHeap(memoryArray,dsLength);
                heapLength = memoryLength;
                dsLength=0;
                output.write("EndRun\n");
            }

            inputLength--;
        }

        //Run out of output when heaplength != 0
        //Finish the heap
        int counter = heapLength;
        for (int i = 0; i < counter; i++) {
            output.write(memoryArray[0] + "\n");
            memoryArray = Heap.deleteMinHeap(memoryArray,heapLength);
            heapLength--;
        }
        output.write("EndRun\n");

        //Now the deadspace
        int[] deadSpace = new int[dsLength];
        for (int i = 0; i <dsLength; i++) {
            deadSpace[i] = memoryArray[memoryArray.length-dsLength+i];
        }
        deadSpace = Heap.buildHeap(deadSpace,dsLength);

        counter = dsLength;
        for (int i = 0; i < counter; i++) {
            output.write(deadSpace[0] + "\n");
            deadSpace = Heap.deleteMinHeap(deadSpace,dsLength);
            dsLength--;
        }
    }

    /**
     * Number of elements of a input file
     * @param fileName input file name
     * @return number of elements
     * @throws FileNotFoundException if no file exist with such name
     */
    public static int inputArrayLength(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        int length = 0;
        while (s.hasNextInt()) {
            length++;
            s.nextInt();
        }
        s.close();
        return length;
    }
}
