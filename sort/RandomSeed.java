package main.sorting;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@ToString
public class RandomSeed {

    private static final Random RANDOW = new Random();

    public RandomSeed() {
        this.problemSize = 15;
        this.testData = new ArrayList<Integer>(problemSize);
    }

    public RandomSeed(int problemSize) {
        this.problemSize = problemSize;
        this.testData = new ArrayList<Integer>(problemSize);
    }

    private int problemSize;

    private List<Integer> testData;

//        int[] testData = new int[]{29,62,28,78,83,91,96,64,27,64};

    public Integer[] getRandomSeedForSorting(Integer range) {
        Integer[] a = new Integer[problemSize];
        for (int i = 0; i < problemSize; i++) {
            testData.add(RANDOW.nextInt(range));
        }
        return testData.toArray(a);
    }
}
