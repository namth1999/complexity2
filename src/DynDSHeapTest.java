import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DynDSHeapTest {

    @Test
    void replacementSelection() throws FileNotFoundException {
        Scanner s = new Scanner(new File("output.txt"));
        boolean runSorted = true;
        int index = 0;

        List<Integer> output = new ArrayList<>();
        List<Integer> splitInput = new ArrayList<>();
        splitInput.add(0);
        while (s.hasNext()){
            if (s.hasNextInt()){
                output.add(s.nextInt());
            } else if (s.hasNext("EndRun")){
                splitInput.add(index);
                s.next();
            }
            index++;
        }

        for (int i = 0; i < splitInput.size(); i++) {
            if (i+1<splitInput.size()){
                List<Integer> array = output.subList(i,i+1);
                if (!checkSorted(array)){
                    runSorted = false;
                }
            } else {
                List<Integer> array = output.subList(i,splitInput.size());
                if (!checkSorted(array)){
                    runSorted = false;
                }
            }
        }

        assertTrue(runSorted);
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