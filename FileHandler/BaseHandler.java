package FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseHandler {
    private final String path;
    private FileReader fileReader;
    private Map<String, Integer> headerMap = new HashMap<>();
    private String[][] fileData;
    private boolean isOpen = false;
    private int ID = -1;

    public BaseHandler(String path) {
        this.path = path;
    }

    // open a file by its path
    public int open(String name) {
        if (isOpen) {
            System.out.println("Another file is already opened, close it before open another one.");
            return 0;
        }

        try {
            fileReader = new FileReader(new File(path));
            isOpen = true;
        } catch (Exception e) {
            System.console().printf("Error opening file");
            return 1;
        }

        CSVHandler();
        getID(name);
        return 0;
    }

    private int CSVHandler() {
        if (!isOpen) {
            System.console().printf("File is not open");
            return 1;
        }

        // Create a new buffered reader
        BufferedReader reader = new BufferedReader(fileReader);

        // Get the header line
        String headerLine = null;
        try {
            headerLine = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] headers = headerLine.split(",");
        for (int i = 0; i < headers.length; i++) {
            headerMap.put(headers[i], i);
        }

        // Get the rest of the lines
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

    public int getID(String name) {
        if (!isOpen) {
            return -1;
        }
        int index = headerMap.get("Name");
        for (int i = 0; i < fileData.length; i++) {
            if (fileData[i][index].equals(name)) {
                ID = i;
                return i;
            }
        }
        return -1;
    }

    public int close() {
        if (!isOpen) {
            return 0;
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            System.console().printf("Error closing file");
            return 1;
        }
        return 0;
    }

    public String getData(String column) {
        if (!isOpen) {
            return null;
        }
        int index = headerMap.get(column);
        return fileData[ID][index];
    }

    public String[] getData() {
        if (!isOpen) {
            return null;
        }
        return fileData[ID];
    }
}
