package algorithm.sort;


import java.util.Random;


/**
 * <p>
 * Merge Sort is a Divide and Conquer sorting algorithm.
 * </p>
 *
 * Divide Step :
 *      Divide the current array into two halves and then recursively sort the two halves.
 *
 * Conquer Step :
 *      Merge the two (sorted) halves to form a sorted array.
 */
public class MergeSort {


    public static void main(String[] args) {

        int problemSize = 15;
        final Random random = new Random();

//        int[] data = new int[]{29,62,28,78,83,91,96,64,27,64};

        int[] data = new int[problemSize];
        for(int i = 0; i < problemSize; i ++) {
            data[i] = random.nextInt(150);
            System.out.println(data[i]);
        }

        mergeSort(data, 0, data.length - 1);

        System.out.println(" -- Sorted --");
        for(int i = 0; i < problemSize; i ++) {
            System.out.println(data[i]);
        }
    }


    /**
     *
     *
     * @param array
     * @param low
     * @param high
     */
    public static void mergeSort(int array[], int low, int high) {

        if (low < high) { // 结束条件，分解到只有一个元素

            int mid = (low+high) / 2;

            mergeSort(array, low  , mid ); // divide into two halves
            mergeSort(array, mid+1, high); // then recursively sort them

            merge(array, low, mid, high); // conquer: the merge routine
        }
    }


    /**
     * 原地合并数组的左右两部分。
     * 循环遍历两个部分，通过比较head元素，实现合并。
     * 代码中依赖copy空间来临时存放排序后的结果。
     * @param array
     * @param low
     * @param mid
     * @param high
     */
    public static void merge(int[] array, int low, int mid, int high) {

        int n = high - low + 1;

        int leftHead = low;
        int rightHead = mid + 1;

        int[] copy = new int[n];

        int idx = 0;
        while( leftHead <= mid && rightHead <= high ) {

            if( array[leftHead] <= array[rightHead]) {
                copy[idx++] = array[leftHead++];

            } else {
                copy[idx++] = array[rightHead++];
            }
        }

        while(leftHead <= mid) {
            copy[idx++] = array[leftHead++];
        }

        while(rightHead <= high) {
            copy[idx++] = array[rightHead++];
        }

        for(int k = 0; k < n; k++) {
            array[low + k] = copy[k];
        }
    }
}
