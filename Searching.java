package phonebook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Searching {

    /**
     * The linear search algorithm.
     * The data is randomly sorted.
     * A search to find the numbers of a few people whose names are listed in a given file.
     *
     * @param entireList the name of the data list to search.
     * @param findList   the name of the data list to find.
     * @return the numbers of elements found, the number of elements search, and the search time.
     */
    static Map<String, Long> linearSearch(List<String> entireList, List<String> findList) {

        long start = System.currentTimeMillis();

        long itemsFound = 0;
        for (String elemFind : findList) {
            for (String searchElem : entireList) {
                if (searchElem.contains(elemFind)) {
                    itemsFound++;
                    break;
                }
            }
        }

        long end = System.currentTimeMillis();

        long duration = end - start;

        Map<String, Long> searchMap = new HashMap<>();
        searchMap.put("found", itemsFound);
        searchMap.put("listSize", (long) findList.size());
        searchMap.put("duration", duration);

        return searchMap;
    }

    /**
     * The jump search algorithm.
     * The data is sorted.
     * A search to find the numbers of a few people whose names are listed in a given file.
     *
     * @param sortedList the name of the data list to search.
     * @param findList   the name of the data list to find.
     * @return the numbers of elements found, the number of elements search, and the search time.
     */
    static Map<String, Long> jumpSearch(List<String> sortedList, List<String> findList) {

        long start = System.currentTimeMillis();

        long itemsFound = 0;
        int last = sortedList.size() - 1;
        int step = (int) Math.floor(Math.sqrt(last));

        for (String elemFind : findList) {
            int curr = 1;
            int prev = 1;

            while (elemFind.compareTo(sortedList.get(curr).substring(sortedList.get(curr).indexOf(" ") + 1)) > 0) {
                if (curr == last) {
                    break;
                }
                prev = curr;
                curr = Math.min(curr + step, last);
            }
            if (elemFind.compareTo(sortedList.get(curr).substring(sortedList.get(curr).indexOf(" ") + 1)) == 0) {
                itemsFound++;
            } else {
                while (elemFind.compareTo(sortedList.get(curr).substring(sortedList.get(curr).indexOf(" ") + 1)) < 0) {
                    curr--;
                    if (curr <= prev) {
                        break;
                    }
                    if (elemFind.compareTo(sortedList.get(curr).substring(sortedList.get(curr).indexOf(" ") + 1)) == 0) {
                        itemsFound++;
                    }
                }
            }
        }

        long end = System.currentTimeMillis();
        long duration = end - start;

        Map<String, Long> searchMap = new HashMap<>();
        searchMap.put("found", itemsFound);
        searchMap.put("listSize", (long) findList.size());
        searchMap.put("duration", duration);

        return searchMap;
    }

    /**
     * The binary search algorithm.
     * The data is sorted.
     * A search to find the numbers of a few people whose names are listed in a given file.
     *
     * @param sortedList the name of the data list to search.
     * @param findList   the name of the data list to find.
     * @return the numbers of elements found, the number of elements search, and the search time.
     */
    static Map<String, Long> binarySearch(List<String> sortedList, List<String> findList) {
        long itemsFound = 0;

        long start = System.currentTimeMillis();

        for (String name : findList) {
            long index = runBinarySearchIteratively(sortedList, name, 0, sortedList.size() - 1);
            if (index != Integer.MAX_VALUE) {
                itemsFound++;
            }
        }

        long end = System.currentTimeMillis();
        long duration = end - start;

        Map<String, Long> searchMap = new HashMap<>();
        searchMap.put("found", itemsFound);
        searchMap.put("listSize", (long) findList.size());
        searchMap.put("duration", duration);

        return searchMap;
    }

    public static long runBinarySearchIteratively(
            List<String> sortedList, String key, int low, int high) {
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (sortedList.get(mid).substring(sortedList.get(mid).indexOf(" ") + 1).compareTo(key) < 0) {
                low = mid + 1;
            } else if (sortedList.get(mid).substring(sortedList.get(mid).indexOf(" ") + 1).compareTo(key) > 0) {
                high = mid - 1;
            } else if (sortedList.get(mid).substring(sortedList.get(mid).indexOf(" ") + 1).compareTo(key) == 0) {
                index = mid;
                break;
            }
        }
        return index;
    }

}
