package phonebook;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static phonebook.Searching.*;
import static phonebook.Sorting.bubbleSort;
import static phonebook.Sorting.quickSort;

public class Module {

    /**
     * The method sorts the list of numbers alphabetically by the owner's name.
     * Sorts the list by using the bubble sort algorithm and searches in the list by using
     * the jump search algorithm.
     * If the sorting process takes too long (more than 10 times longer than all iterations of the linear search),
     * the method stops sorting and uses the linear search.
     *
     * @param duration   of the Linear Search algorithm.
     * @param entireList the name of the data list to search.
     * @param findList   the name of the data list to find.
     */
    static void bubbleSortAndJumpSearch(long duration, List<String> entireList, List<String> findList) {

        System.out.println("Start searching (bubble sort + jump search)...");

        List<String> sortedList = new ArrayList<>(entireList);
        long sortTime = bubbleSort(duration, sortedList);
        if (sortTime > 10 * duration) {
            Map<String, Long> mapLinearSearch = linearSearch(entireList, findList);
            printResult(mapLinearSearch.get("found"), mapLinearSearch.get("listSize"),
                    sortTime + mapLinearSearch.get("duration"));
            System.out.printf("Sorting time: %s - STOPPED, moved to linear search\n", timeString(sortTime));
            System.out.printf("Searching time: %s\n", timeString(mapLinearSearch.get("duration")));
        } else {
            Map<String, Long> mapJumpSearch = jumpSearch(sortedList, findList);
            printResult(mapJumpSearch.get("found"), mapJumpSearch.get("listSize"),
                    sortTime + mapJumpSearch.get("duration"));
            System.out.printf("Sorting time: %s\n", timeString(sortTime));
            System.out.printf("Searching time: %s\n", timeString(mapJumpSearch.get("duration")));
        }
    }

    /**
     * The method sorts the list of numbers alphabetically by the owner's name.
     * It sorts the list by using the quick sort algorithm and searches in the list by using
     * the binary search algorithm.
     *
     * @param entireList the name of the data list to search.
     * @param findList   the name of the data list to find.
     */
    static void quickSortAndBinarySearch(List<String> entireList, List<String> findList) {

        System.out.println("Start searching (quick sort + binary search)...");

        List<String> sortedList = new ArrayList<>(entireList);
        long sortTime = quickSort(sortedList, 0, sortedList.size() - 1);
        Map<String, Long> mapBinarySearch = binarySearch(sortedList, findList);

        printResult(mapBinarySearch.get("found"), mapBinarySearch.get("listSize"),
                sortTime + mapBinarySearch.get("duration"));
        System.out.printf("Sorting time: %s\n", timeString(sortTime));
        System.out.printf("Searching time: %s\n", timeString(mapBinarySearch.get("duration")));
    }

    /**
     * The method uploads data to a hash table - the HashMap(key: the owner, value: the phone number)
     * and a search to find the numbers of a few people whose names are listed in a given file.
     *
     * @param entireList the name of the data list to search.
     * @param findList   the name of the data list to find.
     */
    static void hashTableAndSearching(List<String> entireList, List<String> findList) {

        System.out.println("Start searching (hash table)...");

        long startHashMap = System.currentTimeMillis();

        Map<String, String> map = new HashMap<>();
        for (String elem : entireList) {
            int idx = elem.indexOf(" ");
            String key = elem.substring(idx + 1);
            String val = elem.substring(0, idx);
            map.put(key, val);
        }

        long endHashMap = System.currentTimeMillis();
        long durationHashMap = endHashMap - startHashMap;

        long start = System.currentTimeMillis();

        long itemsFound = 0;
        for (String elemFind : findList) {
            if (map.containsKey(elemFind)) {
                itemsFound++;
            }
        }

        long end = System.currentTimeMillis();

        long duration = end - start;

        Map<String, Long> searchMap = new HashMap<>();
        searchMap.put("found", itemsFound);
        searchMap.put("listSize", (long) findList.size());
        searchMap.put("duration", duration);

        printResult(searchMap.get("found"), searchMap.get("listSize"),
                durationHashMap + searchMap.get("duration"));
        System.out.printf("Creating time: %s\n", timeString(durationHashMap));
        System.out.printf("Searching time: %s\n", timeString(searchMap.get("duration")));

    }

    static void printResult(long found, long size, long time) {
        String strTime = timeString(time);
        System.out.printf("Found %d / %d entries. Time taken: %s\n", found, size, strTime);
    }

    private static String timeString(long time) {
        Duration dura = Duration.ofMillis(time);
        long milli = dura.toMillisPart();
        long sec = dura.toSecondsPart();
        long min = dura.toMinutesPart();

        return "" + min + " min. " + sec + " sec. " + milli + " ms.";
    }

}
