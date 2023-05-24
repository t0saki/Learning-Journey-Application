package Entity;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
