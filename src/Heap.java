public class Heap {

    public static int[] buildHeap(int[] arr, int arrLength) {
        int result[] = {};

        for (int i = 0; i < arrLength; i++) {
            result = Heap.insertMinHeap(result, result.length, arr[i]);
        }
        return result;
    }

    public static int[] insertMinHeap(int[] heap, int heapLength, int insertNum) {
        int insertNumIndex = -1;
        int parentIndex = -1;
        int[] arr = new int[heapLength];

        for (int i = 0; i < heapLength; i++) {
            arr[i] = heap[i];
        }

        arr = add(arr.length, arr, insertNum);
        insertNumIndex = arr.length - 1;
        parentIndex = (insertNumIndex - 1) / 2;

        while (heapLength != 0 && arr[parentIndex] > arr[insertNumIndex]) {
            swap(arr, insertNumIndex, parentIndex);
            insertNumIndex = parentIndex;
            parentIndex = (insertNumIndex - 1) / 2;
        }

        for (int i = heapLength; i < heap.length; i++) {
            arr = add(arr.length, arr, heap[i]);
        }

        return arr;
    }

    public static int[] deleteMinHeap(int[] heap, int heapLength) {
        if (heapLength == 0 || heapLength == 1) {
            int[] arr = {};
            for (int i = heapLength; i < heap.length; i++) {
                arr = add(arr.length, arr, heap[i]);
            }
            return arr;
        }

        if (heapLength == 2) {
            int lastE = heap[heapLength - 1];
            int[] arr = {};
            arr = add(arr.length, arr, lastE);
            for (int i = heapLength; i < heap.length; i++) {
                arr = add(arr.length, arr, heap[i]);
            }
            return arr;
        }

        if (heapLength == 3) {
            int[] arr = {};
            heap = removeTheElement(heap, 0);
            heapLength--;
            if (heap[0] < heap[1]) {
                arr = add(arr.length, arr, heap[0]);
                arr = add(arr.length, arr, heap[1]);
            } else {
                arr = add(arr.length, arr, heap[1]);
                arr = add(arr.length, arr, heap[0]);
            }

            for (int i = heapLength; i < heap.length; i++) {
                arr = add(arr.length, arr, heap[i]);
            }
            return arr;
        }

        int lIndex = -1;
        int rIndex = -1;
        int parentIndex = 0;
        int lastE = heap[heapLength - 1];
        heap = removeTheElement(heap, heapLength - 1);
        heapLength--;
        heap[0] = lastE;

        lIndex = 2 * parentIndex + 1;
        rIndex = 2 * parentIndex + 2;
        boolean endOfHeap = false;
        while (!endOfHeap) {
            if (lIndex < heapLength && rIndex < heapLength && heap[lIndex] <= heap[rIndex] && heap[parentIndex]>=heap[lIndex]) {
                swap(heap, parentIndex, lIndex);
                parentIndex = lIndex;
            } else if (lIndex < heapLength && rIndex < heapLength && heap[lIndex] > heap[rIndex]&& heap[parentIndex]>=heap[rIndex]) {
                swap(heap, parentIndex, rIndex);
                parentIndex = rIndex;
            } else if (rIndex>= heapLength && lIndex<heapLength && heap[parentIndex]>heap[lIndex] && heap[parentIndex]>=heap[lIndex]){
                swap(heap, parentIndex, lIndex);
                endOfHeap = true;
            } else endOfHeap = true;
            lIndex = 2 * parentIndex + 1;
            rIndex = 2 * parentIndex + 2;
        }

        return heap;
    }

    private static int[] removeTheElement(int[] arr,
                                          int index) {

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        int[] anotherArray = new int[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
    }

    public static int[] add(int n, int arr[], int x) {

        // create a new array of size n+1
        int[] newarr = new int[n + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (int i = 0; i < n; i++) {
            newarr[i] = arr[i];
        }

        newarr[n] = x;

        return newarr;
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];

        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
