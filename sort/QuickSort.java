package algorithm.sort;

import java.util.Random;

/**
 *
 * Created by yixin on 2017-07-27.
 */
public class QuickSort {


    public static void main(String[] args) {

        int problemSize = 30;
        final Random random = new Random();

//        int[] data = new int[]{29,62,28,78,83,91,96,64,27,64};

        int[] data = new int[problemSize];
        for(int i = 0; i < problemSize; i ++) {
            data[i] = random.nextInt(100);
            System.out.println(data[i]);
        }


        quickSort(data, 0, data.length - 1);


        System.out.println(" -- Sorted --");
        for(int i = 0; i < problemSize; i ++) {
            System.out.println(data[i]);
        }

    }


    static void quickSort(int[] array, int left, int right) {
        // 判断条件很重要，否则会陷入递归爆炸
        if(left < right) {
            int p = partitionHoare(array, left, right);
            quickSort(array, left, p - 1);
            quickSort(array, p + 1, right);
        }
    }


    /**
     * Lomuto Partition Scheme
     *
     * <p>
     * Conquer Step : Choose an item p as the pivot
     * Then partition the items of a[i..j] into three parts: a[i..m-1], a[m], a[m+1..j]
     *
     * a[i..m-1] (possibly empty) contains items that are smaller than p.
     * a[m] is the pivot p.
     * a[m+1..j] (possibly empty) contains items that are greater than or equal to p.
     *
     * Then, recursively sort the two parts.
     * </p>
     *
     * <p>
     * Conquer step: Don't be surprised... We do nothing :O!
     * </p>
     *
     * Note:
     * This scheme degrades to O(n2) when the array is already sorted as well as when the array has all equal elements
     *
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    static int partitionLomuto(int a[], int lo, int hi) {
        int p = a[lo]; // p is the pivot
        int m = lo; // S1 and S2 are initially empty
        for (int k = lo+1; k <= hi; k++) { // explore the unknown region
            if (a[k] < p) { // case 2
                m++;
                swap(a, m, k);
            }
        }
        swap(a, lo, m); // final step, swap pivot with a[m]
        return m; // return the index of pivot, to be used by Quick Sort
    }


    /**
     * Original Partition Schema By C.A.R. Hoare:
     *
     * <p>
     * Uses two indices that start at the ends of the array being partitioned,
     * then move toward each other, until they detect an inversion. Then swap these two elements.
     * </p>
     *
     * But also can not avoid O(n2) when the input array is already sorted.
     *
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    static int partitionHoare(int a[], int lo, int hi) {
        int p = a[hi]; // select the first element as the pivot
        int i = lo;
        int j = hi-1;

        while(i < j) {

            while(a[i] <= p && i<j) {
                i++;
            }

            while(a[j] >= p && i<j) {
                j--;
            }

            swap(a, i, j);
        }

        if(a[i] >= p) {
            swap(a, hi, i);
        } else {
            j++;
            swap(a, hi, j);
        }

        return j;
    }




    // Simple Swap Function using a temp value
    static void swap(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
}
