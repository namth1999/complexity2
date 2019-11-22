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
        displayArray(memoryArray);

        while (inputLength != 0) {
//            listForDebug.add(memoryArray[0]);
////            System.out.println(listForDebug);
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
                System.out.println("Increse dead " + dsLength);
                memoryArray = Heap.add(memoryArray.length,memoryArray,next);
            }

            if (heapLength==0){
                memoryArray = Heap.buildHeap(memoryArray,dsLength);
                heapLength=memoryLength;
                dsLength=0;
                output.write("End of run \n");

            }

            inputLength--;
            displayArray(memoryArray);
        }
        for (int i = 0; i < heapLength; i++) {
//            listForDebug.add(memoryArray[i]);
            output.write(memoryArray[i] + "\n");
        }
        output.write("End of run \n");
        int[] deadSpace = new int[dsLength];
        for (int i = 0; i <dsLength; i++) {
            deadSpace[i] = memoryArray[memoryArray.length-dsLength+i];
        }
        Heap.buildHeap(deadSpace,dsLength);
        for (int i = 0; i < dsLength; i++) {
            output.write(deadSpace[i] + "\n");
        }
//        System.out.println(listForDebug);
//        displayArray(deadSpace);
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

    public static void writeOut(FileWriter fw, int i) throws IOException {
        fw.write(i + "\n");
    }
}
