package Entity;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Zhengxiao Wu
 * @date 2023/05/25
 *       the clickable item, used to be extended by other items
 *       to make them clickable
 */

public class ClickableItem extends JPanel {
    String details = "Details";

    public ClickableItem() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showDetailsDialog();
            }
        });
    }

    private void showDetailsDialog() {
        // Create a new message dialog
        JOptionPane.showMessageDialog(this, details);
    }
}
