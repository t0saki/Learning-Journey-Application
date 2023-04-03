package FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseHandler {
    private final String path;
    private FileReader file;
    private Map<String, Integer> headerMap = new HashMap<>();
    private String[][] fileData;
    private boolean isOpen = false;
    private int ID = -1;

    public BaseHandler(String path) {
        this.path = path;
    }

    public int open(boolean readonly, String name) {
        // Open the file
        if (isOpen) {
            return 0;
        }
        try {
            // Create a new file object
            File f = new File(path);
            // Create a new file reader
            file = new FileReader(f);
            isOpen = true;
        } catch (Exception e) {
            // If there is an error, return null
            System.console().printf("Error opening file");
            return 1;
        }

        // Set the file to open
        CSVHandler();
        getID(name);
        return 0;
    }

    public int open(String name) {
        // Open the file
        return open(true, name);
    }

    private int CSVHandler(){
        if (!isOpen) {
            System.console().printf("File is not open");
            return 1;
        }

        // Create a new buffered reader
        BufferedReader reader = new BufferedReader(file);

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
            file.close();
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
