
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class RoundedTabbedPaneUI extends BasicTabbedPaneUI {

    private int arc = 10; // Adjust the value of arc for the desired roundness

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement,
                                      int tabIndex, int x, int y, int w, int h,
                                      boolean isSelected) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.lightGray); // Background color of the tab
        g2.fillRoundRect(x, y, w, h, arc, arc); // Draw a rounded rectangle for tab background
        g2.dispose();
    }

    @Override
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
                                             int selectedIndex, int x, int y, int w, int h) {
        // Don't paint the content border top edge
    }

    @Override
    protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement,
                                              int selectedIndex, int x, int y, int w, int h) {
        // Don't paint the content border left edge
    }

    @Override
    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
                                                int selectedIndex, int x, int y, int w, int h) {
        // Don't paint the content border bottom edge
    }

    @Override
    protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
                                               int selectedIndex, int x, int y, int w, int h) {
        // Don't paint the content border right edge
    }

}

