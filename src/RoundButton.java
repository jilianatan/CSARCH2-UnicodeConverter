import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundButton extends JButton {

    private Color defaultColor = new Color(200, 200, 200);
    private Color hoverColor = new Color(230, 230, 230);

    public RoundButton(String label) {
        super(label);
        setBackground(Color.LIGHT_GRAY);
        setContentAreaFilled(false); // Ensure the button is transparent
        setForeground(Color.BLACK); // Set text color to black
        setFont(new Font("Arial", Font.BOLD, 12)); // Set font

        // Add mouse listener to handle hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor); // Change background color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultColor); // Restore default background color on exit
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(new Color(150, 150, 150)); // Darker color when pressed
        } else {
            g.setColor(getBackground()); // Use the current background color
        }
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20); // Adjust the values for roundness
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white); // Set border color
      //  g2d.setStroke(new BasicStroke(2)); // Set border thickness
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20); // Adjust the values for roundness and border
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(170, 40); // Adjust the preferred size of the button
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize(); // Ensure minimum size is same as preferred size
    }
}
