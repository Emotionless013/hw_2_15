package pro.sky.hw_2_15;


import java.util.Arrays;

public class SpeedTest {

    private static Integer[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        Integer[] arr = new Integer[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(800_000) + 100_000;
        }
        return arr;
    }
    private static void swapElements(Integer[] arr, Integer indexA, Integer indexB) {
        Integer tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }
    //эта сортировка самая бытрая из трех
    private static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }
    private static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
    public static void quickSort(Integer[] arr, Integer begin, Integer end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, Integer begin, Integer end) {
        Integer pivot = arr[end];
        int i = (begin - 1);

        for (Integer j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    public static void main(String[] args) {

        long startFourth = System.currentTimeMillis();
        Integer[] arr = generateRandomArray();
        quickSort(arr, 0, 99999);
        System.out.println(System.currentTimeMillis() - startFourth);
    }
}