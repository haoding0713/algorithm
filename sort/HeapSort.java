package algorithm.sort;

import java.util.Random;

public class HeapSort {

    public static void main(String[] args) {

        int problemSize = 17;
        final Random random = new Random();

        int[] data = new int[]{90,38,5,55,26,76,36,11,51,2,16,64,81,1,48,9,77};


        // Insert的构造方式，复杂度是O( N * LogN )
        int[] maxHeap = new int[problemSize];
        for(int i = 0; i < problemSize; i ++) {
            insert(maxHeap, i, data[i]);
        }

        System.out.println(" -- Create O(N Log N) --");
        printHeap(maxHeap);
        System.out.println();


        for(int i = problemSize -1 ; i > 0; i--) {
            System.out.println(extract(maxHeap, i));
        }


        System.out.println();

        System.out.println(" -- Create O(N) --");
        heapify(data, problemSize);

        printHeap(data);
        System.out.println();

        for(int i = problemSize -1 ; i > 0; i--) {
            System.out.println(extract(data, i));
        }
    }



    static void insert(int[] binaryHeap, int i, int value) {

        // Step 1. put value at the end of binary heap
        binaryHeap[i] = value;

        // Step 2. keep binary heap
        int end = i - 1;
        if(end < 0) {
            return;
        }

        while(i > 0 && binaryHeap[parentIndex(i)] < binaryHeap[i]){

            swap(binaryHeap, parentIndex(i), i);
            i = parentIndex(i);
        }
    }


    static int extract(int[] binaryHeap, int end) {
        int max = binaryHeap[0];

        binaryHeap[0] = binaryHeap[end];
        binaryHeap[end] = 0;

        int i = 0;
        while( leftChildIndex(i) <= (end - 1)) {

            // Select bigger child then swap with it
            int position = i;
            int leftSon = leftChildIndex(i);

            // First, compare with left child
            if(binaryHeap[i] < binaryHeap[leftSon]) {
                position = leftSon;
            }

            // Second, if there has right child and that child is greater
            if( (leftSon + 1) <= (end -1) && binaryHeap[leftSon + 1] > binaryHeap[leftSon]) {
                position = leftSon + 1;
            }

            // Node i holds the largest element, this means that we are done at this level
            if(position == i) {
                return max;

            }else {

                // Continue sifting down the child
                swap(binaryHeap, i, position);
                i = position;
            }
        }

        return max;
    }


    static void heapify(int[] binarayHeap, int length) {

        // 找到最后一个叶子节点的父节点，以此为起点。
        int i = parentIndex(length-1);

        // 每次下标减1，从而遍历整个堆，调整并保持堆的特性。
        for(; i > 0; i--) {
            siftDown(binarayHeap, i);
        }

    }


    // 向下检查比较
    static void siftDown(int[] binaryHeap, int index) {

        int end = binaryHeap.length - 1;

        while( leftChildIndex(index) <= end) {

            // Select bigger child then swap with it
            int position = index;
            int leftSon = leftChildIndex(index);

            // First, compare with left child
            if(binaryHeap[index] < binaryHeap[leftSon]) {
                position = leftSon;
            }

            // Second, if there has right child and that child is greater
            if( (leftSon + 1) <= end && binaryHeap[leftSon + 1] > binaryHeap[position]) {
                position = leftSon + 1;
            }

            // Node i holds the largest element, this means that we are done at this level
            if(position == index) {
                return;

            }else {

                // Continue sifting down the child
                swap(binaryHeap, index, position);
                index = position;
            }
        }
    }


    /** Help Methods **/
    static int parentIndex(int n) {
        return (n-1)/2;
    }


    static int leftChildIndex(int n) {
        return 2*n + 1;
    }


    static int rightChildIndex(int n) {
        return 2*n + 2;
    }


    static void swap(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }


    static void printHeap(int[] heap) {
        int length = heap.length;

        int k = 0;
        int i = 0;

        // Log N Times Loop
        for(; (int)Math.pow(2, i) < length; i++ ) {

            System.out.print("Level " + i + " :    ");
            int distance = (int)Math.pow(2,i);
            if((int)Math.pow(2,i + 1) > length) {
                distance = length - distance + 1;
            }

            for(int j = 0; j < distance; j++, k++) {
                System.out.print(heap[k] + "   ");

            }
            System.out.println();
        }

        if(k < length) {
            System.out.print("Level " + i + " :    ");
            for(; k < length; k++) {
                System.out.print(heap[k] + "   ");
            }
        }

    }

}
