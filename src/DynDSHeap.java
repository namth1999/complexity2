import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DynDSHeap {
    public static void replacementSelection(String filename, FileWriter output, int memoryLength) throws IOException {
        ArrayList<Integer> listForDebug = new ArrayList<>();
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
        displayArray(memoryArray);

        while (inputLength != 0) {
            listForDebug.add(memoryArray[0]);
            System.out.println(listForDebug);
            output.write(memoryArray[0] + "\n");
            int smallest = memoryArray[0];
            memoryArray = Heap.deleteMinHeap(memoryArray,heapLength);
            displayArray(memoryArray);
            int next = s.nextInt();
            System.out.println("Add to memory: " + next);

            if (next>=smallest){
                memoryArray = Heap.insertMinHeap(memoryArray,heapLength-1,next);
            } else {
                heapLength--;
                dsLength++;
                System.out.println("Increse dead " + dsLength + " heaplength " + heapLength);
                memoryArray = Heap.add(memoryArray.length,memoryArray,next);
            }

            if (heapLength == 0 || dsLength == memoryLength){
                memoryArray = Heap.buildHeap(memoryArray,dsLength);
                heapLength = memoryLength;
                dsLength=0;
                output.write("EndRun\n");
            }

            inputLength--;
            displayArray(memoryArray);
        }

        int counter = heapLength;
        for (int i = 0; i < counter; i++) {
            listForDebug.add(memoryArray[0]);
            output.write(memoryArray[0] + "\n");
            memoryArray = Heap.deleteMinHeap(memoryArray,heapLength);
            heapLength--;
        }
        displayArray(memoryArray);
        output.write("EndRun\n");
        int[] deadSpace = new int[dsLength];
        for (int i = 0; i <dsLength; i++) {
            deadSpace[i] = memoryArray[memoryArray.length-dsLength+i];
        }
        deadSpace = Heap.buildHeap(deadSpace,dsLength);
        displayArray(deadSpace);

        counter = dsLength;
        for (int i = 0; i < counter; i++) {
            listForDebug.add(deadSpace[0]);
            output.write(deadSpace[0] + "\n");
            deadSpace = Heap.deleteMinHeap(deadSpace,dsLength);
            dsLength--;
        }
        System.out.println(listForDebug);
        displayArray(deadSpace);
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
