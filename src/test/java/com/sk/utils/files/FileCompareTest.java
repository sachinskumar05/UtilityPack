package com.sk.utils.files;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.*;

class FileCompareTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    String file1= "src/test/resources/files/testdata/FileA.txt";
    String file2= "src/test/resources/files/testdata/FileB.txt";

    @Test
    void testGetDifference() {

        Map<String, Set<String>> result = FileCompare.getDifference( file1, file2 );

        Assertions.assertEquals(new HashMap<String, Set<String>>() {{
            put(file1, new HashSet<>(List.of("Sample A4")));
            put(file2, new HashSet<>(List.of("Sample B4")));
        }}, result);
    }

    @Test
    void testGetLines() throws IOException {
         List<String> result = FileCompare.getLines(file1);
        Assertions.assertTrue(result.size() > 0 );
    }
}
