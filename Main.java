package phonebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static phonebook.Module.*;
import static phonebook.Searching.linearSearch;
import static phonebook.Upload.uploadData;

public class Main {

    private static final String directory = "C:\\Java\\files\\directory.txt";
    private static final String find = "C:\\Java\\files\\find.txt";
    private static final List<String> entireList = new ArrayList<>();
    private static final List<String> findList = new ArrayList<>();


    public static void main(String[] args) {

        uploadData(directory, entireList, find, findList);

        System.out.println("Start searching (linear search)...");

        Map<String, Long> mapLinearSearch = linearSearch(entireList, findList);
        printResult(mapLinearSearch.get("found"), mapLinearSearch.get("listSize"),
                mapLinearSearch.get("duration"));

        System.out.println();

        bubbleSortAndJumpSearch(mapLinearSearch.get("duration"), entireList, findList);

        System.out.println();

        quickSortAndBinarySearch(entireList, findList);

        System.out.println();

        hashTableAndSearching(entireList, findList);
    }
}
