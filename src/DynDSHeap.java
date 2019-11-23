import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DynDSHeap {
    public static void replacementSelection(String filename, FileWriter output, int memoryLength) throws IOException {
        int inputLength = inputArrayLength(filename);
        int[] memoryArray = new int[memoryLength];
        int heapLength = memoryLength;
        int dsLength = memoryLength - heapLength;
        Scanner s = new Scanner(new File(filename));
        for (int i = 0; i < memoryLength; i++) {
            memoryArray[i] = s.nextInt();
            inputLength--;
        }
        memoryArray = Heap.buildHeap(memoryArray,heapLength);

        while (inputLength != 0) {
            output.write(memoryArray[0] + "\n");
            int smallest = memoryArray[0];
            memoryArray = Heap.deleteMinHeap(memoryArray,heapLength);
            int next = s.nextInt();

            if (next>=smallest){
                memoryArray = Heap.insertMinHeap(memoryArray,heapLength-1,next);
            } else {
                heapLength--;
                dsLength++;
                memoryArray = Heap.add(memoryArray.length,memoryArray,next);
            }

            if (heapLength == 0 || dsLength == memoryLength){
                memoryArray = Heap.buildHeap(memoryArray,dsLength);
                heapLength = memoryLength;
                dsLength=0;
                output.write("EndRun\n");
            }

            inputLength--;
        }

        int counter = heapLength;
        for (int i = 0; i < counter; i++) {
            output.write(memoryArray[0] + "\n");
            memoryArray = Heap.deleteMinHeap(memoryArray,heapLength);
            heapLength--;
        }
        output.write("EndRun\n");

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

    private static void displayArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
