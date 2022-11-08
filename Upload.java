package phonebook;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Upload {

    /**
     * The method uploads data from files into the computer's memory.
     *
     * @param directory  the data file name to search.
     * @param entireList List<String> - the list for "directory".
     * @param find       the data file name to find.
     * @param findList   List<String> - the list for "find".
     */
    static void uploadData(String directory, List<String> entireList,
                           String find, List<String> findList) {

        try (Scanner allItems = new Scanner(Paths.get(directory));
             Scanner findItems = new Scanner(Paths.get(find))) {

            while (allItems.hasNextLine()) {
                entireList.add(allItems.nextLine());
            }

            while (findItems.hasNext()) {
                findList.add(findItems.nextLine());
            }
        } catch (IOException exp) {
            System.out.println("Cannot read file: " + exp.getMessage());
        }
    }

}
