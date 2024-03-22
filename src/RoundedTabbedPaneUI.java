
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class RoundedTabbedPaneUI extends BasicTabbedPaneUI {

    private int arc = 10; // Adjust the value of arc for the desired roundness

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement,
                                      int tabIndex, int x, int y, int w, int h,
                                      boolean isSelected) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.decode("#665651")); // Background color of the tab
        g2.fillRoundRect(x, y, w, h, arc, arc); // Draw a rounded rectangle for tab background
        g2.dispose();
    }

    @Override
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
                                             int selectedIndex, int x, int y, int w, int h) {
        g.setColor(Color.WHITE); // Set border color to white
        g.drawLine(x, y, x + w - 1, y); // Draw top border line
    }

    @Override
    protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement,
                                              int selectedIndex, int x, int y, int w, int h) {
        g.setColor(Color.WHITE); // Set border color to white
        g.drawLine(x, y, x, y + h - 1); // Draw left border line
    }

    @Override
    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
                                                int selectedIndex, int x, int y, int w, int h) {
        g.setColor(Color.WHITE); // Set border color to white
        g.drawLine(x, y + h - 1, x + w - 1, y + h - 1); // Draw bottom border line
    }

    @Override
    protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
                                               int selectedIndex, int x, int y, int w, int h) {
        g.setColor(Color.WHITE); // Set border color to white
        g.drawLine(x + w - 1, y, x + w - 1, y + h - 1); // Draw right border line
    }

}

