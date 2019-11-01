package task3_University;

import java.util.ArrayList;

public interface CSV {
    String toCSV();
    void fromCSV(String str);
    void saveToCSV();
    void readFromCSV();
}
