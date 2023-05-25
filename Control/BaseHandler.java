package Control;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseHandler {
    private FileReader fileReader;
    private FileWriter fileWriter;
    private Map<String, Integer> headerMap;
    private int lineCount;
    private String[][] fileData;
    private String[] headers = new String[1024];

    public BaseHandler() {
        fileReader = null;
        headerMap = null;
        lineCount = 0;
    }

    public Map<String, Integer> getHeaderMap() {
        return headerMap;
    }

    public String[][] getFileData() {
        return fileData;
    }

    // You should open a file by its path
    public int open(String csvPath) {
        if (fileReader != null) {
            System.out.println("Another file is already opened, close it before open another one.");
            return 0;
        }
        try {
            fileReader = new FileReader(new File(csvPath));
            fileWriter = new FileWriter(new File(csvPath), true);
        } catch (Exception e) {
            System.out.println("Error opening file");
            return 1;
        }

        loadAllCsvData();
        return 0;
    }

    public int create(String csvPath) {
        // if (fileReader != null) {
        // System.out.println("Another file is already opened, close it before open
        // another one.");
        // return 0;
        // }
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(new File(csvPath), true);
        } catch (Exception e) {
            System.out.println("Error creating file");
            return 1;
        }

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
            fileWriter.close();
            fileWriter = null;
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
            System.out.println("loadAllCsvData(): You should open a file before reading from it.");
            return 1;
        }

        // create a new buffered reader
        BufferedReader reader = new BufferedReader(fileReader);

        String headerLine = null;
        try {
            headerLine = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        headers = headerLine.split(",");

        // recreate header map for the current file
        headerMap = new HashMap<>();
        headers = headerLine.split(",");

        for (int i = 0; i < headers.length; i++) {
            headerMap.put(headers[i], i);
        }

        // get the rest of the lines
        String line;
        lineCount = 0;
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

    // append a single string to the end of the current file
    public int append(String str) {
        if (fileReader == null) {
            System.out.println("append(): You should open a file before reading from it.");
            return 1;
        }

        try {
            fileWriter.write(str + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    // -1: val not found
    public int getFirstRowIndexByHeaderAndVal(String header, String val) {
        if (fileReader == null) {
            System.out.println("getFirstRowIndexByHeaderAndVal(): You should open a file before reading from it.");
            return -1;
        }

        int columnIndex = headerMap.get(header);

        for (int rowIndex = 0; rowIndex < lineCount; rowIndex++) {
            System.out.print(rowIndex);
            if (fileData[rowIndex][columnIndex].equals(val)) {
                return rowIndex;
            }
        }

        return -1;
    }

    public String getElement(String header, int rowIndex) {
        if (fileReader == null) {
            System.out.println("getElement(): You should open a file before reading from it.");
            return null;
        }

        int columnIndex = headerMap.get(header);
        return fileData[rowIndex][columnIndex];
    }

    public String getElement(int columnIndex, int rowIndex) {
        if (fileReader == null) {
            System.out.println("getElement(): You should open a file before reading from it.");
            return null;
        }

        return fileData[rowIndex][columnIndex];
    }

    public String[] getRow(int rowIndex) {
        if (fileReader == null) {
            System.out.println("getRow(): You should open a file before reading from it.");
            return null;
        }

        return fileData[rowIndex];
    }

    public String[] getHeaders() {
        if (fileReader == null) {
            System.out.println("getHeaders(): You should open a file before reading from it.");
            return null;
        }

        return headers;
    }

    /**
     * @author XiangzheKong
     * @return int
     * Get number of lines of the content.
     */
    public int getLineCount() {
        int linecount = 0;
        for (int i = 0; i < 1024; i++) {
            if (fileData == null || fileData[i][0] == null || fileData[i][0].length() == 0
                    || fileData[i][0].equals("")) {
                break;
            }
            linecount++;
        }
        return linecount;
    }

    /**
     * @author XiangzheKong
     * @param str
     * @return int
     * Check whether an entity exists.
     * return its row number if exists
     * return a negative number if it does not exist
     */
    public int CheckExist(String str) {
        if (fileReader == null) {
            System.out.println("you should open a file first!");
            return -2;
        }
        for (int i = 0; i < fileData.length; i++) {
            if (str.equals(fileData[i][0])) {
                // System.out.println("Already exist!");
                return i;
            }
        }
        return -1;
    }

    /**
     * @author XiangzheKong
     * @param entity target entity, the first item in a row
     * @param path file path
     * @return int
     * Delete the line with specific entity
     */
    public int DeleteLine(String entity, String path) {
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>();

            // 将文件中的所有行读取到列表中
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            // 检查要删除的行是否存在
            int row = CheckExist(entity);
            if (row < 0) {
                System.out.println("Item does not exist!");
                return 1;
            }

            // 从列表中移除要删除的行
            lines.remove(row + 1);

            // 将更新后的行重新写入文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

    /**
     * @author XiangzheKong
     * @param header
     * @param entity
     * @param content
     * @param path
     * @return int
     * Change the specific item into another item.
     *
     */
    public int ChangeItem(String header, String entity, String content, String path) {
        int val = 0;
        int row = CheckExist(entity);
        if (row == -1 || row == -2) {
            System.out.println("item not exist!");
            return 1;
        }
        long rowoffset = String.join(",", fileData[row]).length();
        val = this.headerMap.get(header);
        fileData[row][val] = content;
        int line_num = 0;
        for (String[] s : fileData) {
            if (s[0] == null) {
                break;
            }
            line_num++;
        }
        String line = String.join(",", fileData[row]);
        long offset = line.getBytes().length - rowoffset;
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(path, "rw");
            long pos = file.getFilePointer();
            String lineToUpdate;
            while ((lineToUpdate = file.readLine()) != null) {
                String[] parts = lineToUpdate.split(",");
                if (parts[0].equals(entity)) {
                    file.seek(pos);
                    long position = pos;
                    int length = lineToUpdate.length();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < length; i++) {
                        stringBuilder.append(" ");
                    }
                    String blank = stringBuilder.toString();
                    file.writeBytes(blank);
                    file.seek(position);
                    file.writeBytes(line);
                    if (offset > 0) {
                        for (int i = row; i < line_num; i++) {
                            if (i + 1 == line_num) {
                                file.writeBytes("\n");
                                break;
                            }
                            pos = file.getFilePointer();
                            file.seek(pos);
                            file.writeBytes("\n");
                            file.writeBytes(String.join(",", fileData[i + 1]));
                        }
                    }
                    break;
                }
                pos = file.getFilePointer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

}
