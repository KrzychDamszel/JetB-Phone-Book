package phonebook;

import java.util.List;

public class Sorting {

    /**
     * The bubble sort algorithm.
     * It sorts the list using a bubble sort algorithm.
     * If the sorting process takes too long (more than 10 times longer than the duration
     * of the linear search algorithm), the method stops sorting.
     *
     * @param duration   of the linear search algorithm.
     * @param sortedList the data list name to sort.
     * @return the duration of the bubble sort algorithm.
     */
    static long bubbleSort(long duration, List<String> sortedList) {
        long sortTime = 0;
        int len = sortedList.size();
        long start = System.currentTimeMillis();
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                String strA = sortedList.get(j);
                String subStrA = strA.substring(strA.indexOf(" ") + 1);
                String strB = sortedList.get(j + 1);
                String subStrB = strB.substring(strB.indexOf(" ") + 1);
                if (subStrA.compareTo(subStrB) > 0) {
                    sortedList.set(j, strB);
                    sortedList.set(j + 1, strA);
                }
                long end = System.currentTimeMillis();
                sortTime = end - start;
                if (sortTime > 10 * duration) {
                    return sortTime;
                }
            }
        }
        return sortTime;
    }

    /**
     * The quick sort algorithm.
     * It sorts the list using a quick sort algorithm.
     *
     * @param sortedList the data list name to sort.
     * @return the duration of the quick sort algorithm.
     */
    static long quickSort(List<String> sortedList, int begin, int end) {
        long startTime = System.currentTimeMillis();
        if (begin < end) {
            int partitionIndex = partition(sortedList, begin, end);

            quickSort(sortedList, begin, partitionIndex - 1);
            quickSort(sortedList, partitionIndex + 1, end);
        }
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    private static int partition(List<String> subList, int begin, int end) {
        String pivot = subList.get(end);
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            String str = subList.get(j);
            if (str.substring(str.indexOf(" ") + 1).compareTo(pivot.substring(pivot.indexOf(" ") + 1)) <= 0) {
                i++;

                String swapTemp = subList.get(i);
                subList.set(i, str);
                subList.set(j, swapTemp);
            }
        }

        String swapTemp = subList.get(i + 1);
        subList.set(i + 1, subList.get(end));
        subList.set(end, swapTemp);

        return i + 1;
    }

}