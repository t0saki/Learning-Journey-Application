package Control;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhengxiao Wu
 * @date 2023/05/25
 *     the string search handler, used to search string
 */

public class StringSearch {
    private List<String> entries;

    public StringSearch() {
        entries = new ArrayList<>();
    }

    public void addEntry(String entry) {
        entries.add(entry);
    }

    public void searchKeyword() {
        String keyword = JOptionPane.showInputDialog(null, "Enter a keyword to search:");

        if (keyword == null) {
            return;
        }
        List<String> matchingEntries = new ArrayList<>();
        for (String entry : entries) {
            if (entry.contains(keyword)) {
                matchingEntries.add(entry);
            }
        }

        if (matchingEntries.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No matching entries found for the keyword: " + keyword);
        } else {
            StringBuilder message = new StringBuilder("The keyword '" + keyword + "' found in the following entries:\n");
            for (String matchingEntry : matchingEntries) {
                message.append("- ").append(matchingEntry).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        }
    }
}
