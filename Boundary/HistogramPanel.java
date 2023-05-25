package Boundary;

public class HistogramPanel extends BaseDisplay {

    public HistogramPanel() {
        super("Course Mark Histogram");
    }

    public void update(Histogram histogram) {
        contentPanel = histogram;
    }
}
