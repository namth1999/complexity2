import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DynDSHeapTest {

    int inputLength = 10000; //N
    int memoryLength = 100; //H

    @Test
    void replacementSelection() throws IOException {
        Main.run(inputLength, memoryLength);
        Scanner s = new Scanner(new File("output.txt"));
        boolean runSorted = true;
        int index = 0;

        List<Integer> output = new ArrayList<>();

        //Number of runs
        List<Integer> splitInput = new ArrayList<>();
        splitInput.add(0);

        //add to output array if next is int
        while (s.hasNext()){
            if (s.hasNextInt()){
                output.add(s.nextInt());
            } else if (s.hasNext("EndRun")){
                splitInput.add(index-splitInput.size()+1);
                s.next();
            }
            index++;
        }

        System.out.println("Runs: " + splitInput.size());
        String error = "";

        //found out the array that have sorting issue, assertTrue fail if 1 array is not sorted
        for (int i = 0; i < splitInput.size(); i++) {
            if (i+1<splitInput.size()){
                List<Integer> array = output.subList(splitInput.get(i),splitInput.get(i+1));
                System.out.println(array);
                if (!checkSorted(array)){
                    runSorted = false;
                    error+=i + " ";
                }
            } else {
                List<Integer> array = output.subList(splitInput.get(i),output.size());
                System.out.println(array);
                if (!array.isEmpty() && !checkSorted(array)){
                    runSorted = false;
                }
            }
        }

        assertTrue(runSorted,error);
    }

    boolean checkSorted(List<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            if (i == array.size() - 1) {
                return true;
            }
            int number = array.get(i);
            if (number > array.get(i+1)) {
                return false;
            }
        }
        return false;
    }
}