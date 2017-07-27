package com.taobao.teadmin.notify.sender;

import java.util.Random;

/**
 *
 * Created by yixin on 2017-07-27.
 */
public class QuickSort {


    public static void main(String[] args) {

        int problemSize = 10;
        final Random random = new Random();

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
            int p = partition(array, left, right);
            quickSort(array, left, p - 1);
            quickSort(array, p + 1, right);
        }
    }


    static int partition(int a[], int i, int j) {
        int p = a[i]; // p is the pivot
        int m = i; // S1 and S2 are initially empty
        for (int k = i+1; k <= j; k++) { // explore the unknown region
            if (a[k] < p) { // case 2
                m++;
                swap(a, m, k);
            }
        }
        swap(a, i, m); // final step, swap pivot with a[m]
        return m; // return the index of pivot, to be used by Quick Sort
    }


    static void swap(int[] array, int m, int k) {
        int temp = array[m];
        array[m] = array[k];
        array[k] = temp;
    }
}
