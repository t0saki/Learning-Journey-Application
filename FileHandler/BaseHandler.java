package FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseHandler {
    private String path;
    private FileReader fileReader;
    private Map<String, Integer> headerMap;
    private String[][] fileData;

    public BaseHandler() {
        fileReader = null;
        headerMap = null;
    }

    // open a file by its path
    public int open(String csvPath) {
        if (fileReader != null) {
            System.out.println("Another file is already opened, close it before open another one.");
            return 0;
        }
        try {
            fileReader = new FileReader(new File(csvPath));
        } catch (Exception e) {
            System.out.println("Error opening file");
            return 1;
        }

        loadAllCsvData();
        return 0;
    }

    // close the current file
    public int close() {
        if (fileReader == null) {
            System.out.println("close(): No file is opened, closing operation is invalid.");
            return 0;
        }
        try {
            fileReader.close();
            fileReader = null;
        } catch (IOException e) {
            System.out.println("close(): Error closing file.");
            return 1;
        }

        return 0;
    }

    // need to be called every time loading a file, or when the current file is
    // changed
    public int loadAllCsvData() {
        if (fileReader == null) {
            System.out.println("loadAllCsvData(): Open a file before reading from it.");
            return 1;
        }

        // Create a new buffered reader
        BufferedReader reader = new BufferedReader(fileReader);

        String headerLine = null;
        try {
            headerLine = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // recreate header map for the current file
        headerMap = new HashMap<>();
        String[] headers = headerLine.split(",");
        for (int i = 0; i < headers.length; i++) {
            headerMap.put(headers[i], i);
        }

        // get the rest of the lines
        String line;
        int lineCount = 0;
        // buffer with maximum data rows of 1024
        fileData = new String[1024][headers.length];
        while (true) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line == null) {
                break;
            }
            String[] data = line.split(",");
            for (int i = 0; i < data.length; i++) {
                fileData[lineCount][i] = data[i];
            }
            lineCount++;
        }

        return 0;
    }

    // -1: not found
    public int getFirstRowIndexByHeaderAndVal(String header, String val) {
        if (fileReader == null) {
            System.out.println("getFirstRowIndexByHeaderAndVal(): Open a file before reading from it.");
            return -1;
        }

        int columnIndex = headerMap.get(header);
        for (int rowIndex = 0; rowIndex < fileData.length; rowIndex++) {
            if (fileData[rowIndex][columnIndex].equals(val)) {
                return rowIndex;
            }
        }

        return -1;
    }

    public String getElement(String header, int rowIndex) {
        if (fileReader == null) {
            System.out.println("getElement(): Open a file before reading from it.");
            return null;
        }

        int columnIndex = headerMap.get(header);
        return fileData[rowIndex][columnIndex];
    }

    public String getElement(int columnIndex, int rowIndex) {
        if (fileReader == null) {
            System.out.println("getElement(): Open a file before reading from it.");
            return null;
        }

        return fileData[rowIndex][columnIndex];
    }

    public String[] getRow(int rowIndex) {
        if (fileReader == null) {
            System.out.println("getRow(): Open a file before reading from it.");
            return null;
        }

        return fileData[rowIndex];
    }
}
