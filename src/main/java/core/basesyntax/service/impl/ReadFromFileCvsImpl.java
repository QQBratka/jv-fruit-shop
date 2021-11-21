package core.basesyntax.service.impl;

import core.basesyntax.service.ReadFromFileCvs;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFileCvsImpl implements ReadFromFileCvs {
    private static final String FIRST_LINE_REPORT = "type,fruit,quantity";

    public List<String> readFromFile(String filePath) {
        List<String> readReport = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String value = reader.readLine();
            while (value != null) {
                if (value.equals(FIRST_LINE_REPORT)) {
                    value = reader.readLine();
                }
                readReport.add(value);
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + filePath, e);
        }
        return readReport;
    }
}
