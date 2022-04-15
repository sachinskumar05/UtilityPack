package com.sk.utils.files;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Log4j2
public class FileCompare {

    private FileCompare(){throw new UnsupportedOperationException("Instantiation Restricted");}

    public static Map<String, Set<String>> getDifference(String file1, String file2) {
        Map<String, Set<String>> result = new HashMap<>();
        List<String> linesOfFileOne ;
        List<String> linesOfFileTwo ;
        try{
            linesOfFileOne = getLines(file1);
        } catch (IOException e) {
            log.error("Failed to read file {}", file1, e);
            return result;
        }
        try{
            linesOfFileTwo = getLines(file2);
        } catch (IOException e) {
            log.error("Failed to read file {}", file2, e);
            return result;
        }

        Set<String> onlyInFileOne = new HashSet<>(linesOfFileOne);
        linesOfFileTwo.forEach(onlyInFileOne::remove);

        Set<String> onlyInFileTwo = new HashSet<>(linesOfFileTwo);
        linesOfFileOne.forEach(onlyInFileTwo::remove);

        result.put(file1, onlyInFileOne);
        result.put(file2, onlyInFileTwo);

        return result;
    }

    public static List<String> getLines(String fileName) throws IOException {
        return Files.readAllLines(Path.of(fileName));
    }

}
