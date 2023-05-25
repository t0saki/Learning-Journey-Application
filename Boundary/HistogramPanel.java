package Boundary;

/**
 * @author Zhengxiao Wu
 * @date 2023/05/25
 *     the Histogram panel, used to display the histogram of a student's marks
 */

public class HistogramPanel extends BaseDisplay {

    public HistogramPanel() {
        super("Course Mark Histogram");
    }

    public void update(Histogram histogram) {
        contentPanel = histogram;
    }
}
