package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NameSorterService {
    public void nameSorter() {
        String inputFile = "/Users/ashuvaidwan/Desktop/unsorted-names-list.txt";
        String line;
        List<Name> nameList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                Name name = new Name();
                name.setLastName(line.substring(line.lastIndexOf(" ") + 1));
                name.setGivenName(line);
                nameList.add(name);
            }
            bufferedReader.close();
        } catch (
                FileNotFoundException ex) {
            System.out.println("Unable to open file '" + inputFile + "'");
        } catch (
                IOException ex) {
            System.out.println("Error reading file '" + inputFile + "'");

        }

        Collections.sort(nameList, Comparator.comparing(Name::getLastName).thenComparing(Name::getGivenName));

        String outputFile = "sorted-names-list.txt";

        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Name item : nameList) {
                bufferedWriter.write(item.getGivenName());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + outputFile + "'");
        }

        for (Name item : nameList) {
            System.out.println(item.getGivenName());
        }
    }
}
