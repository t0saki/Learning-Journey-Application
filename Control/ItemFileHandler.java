package Control;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 *       The Handler for items such as
 *       achievements,skills,activities,roles and portfolios
 */
public class ItemFileHandler extends BaseHandler {

    /**
     * @param header  the header of the file
     * @param entity  the entity of the item
     * @param content the content of the item
     * @param path    the path of the file
     * @return int 0 if success, 1 if fail
     */
    @Override
    public int ChangeItem(String header, String entity, String content, String path) {
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>();
            int row = CheckExist(entity);
            if (row < 0) {
                System.out.println("Item does not exist!");
                reader.close();
                return 1;
            }

            // 将文件中的所有行读取到列表中
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (count == row + 1) {
                    lines.add(content + ",");
                } else {
                    lines.add(line);
                }
                count++;
            }
            reader.close();

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
}
