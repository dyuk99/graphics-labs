import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

@SuppressWarnings("serial")
public class WallAnimation extends JPanel implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;

    Timer timer;

    //picture settings
    private double pictureWidth = 180;
    private double pictureHeight = 114;

    // Для анімації масштабування
    private double scale = 1;
    private double delta = 0.01;

    // for movement animation
    private double deltaX = 1;
    private int radius = 200;
    private int radiusExtention = 110;
    private double tx = -(radius+radiusExtention- pictureWidth/2 - 16 );
    private double ty = -(radius+radiusExtention- pictureHeight/2 + 16 );

    public WallAnimation() {
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering params.
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        // Set background color.
        g2d.setBackground(new Color(109, 111, 3));
        g2d.clearRect(0, 0, maxWidth, maxHeight);

        // Set (0;0) to the center to draw main Frame.
        g2d.translate(maxWidth/2, maxHeight/2);
        // Draw the main Frame.
        BasicStroke bs2 = new BasicStroke(16, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs2);
        g2d.drawRect(
                -(radius + radiusExtention),
                -(radius + radiusExtention),
                (radius + radiusExtention)*2,
                (radius + radiusExtention)*2
        );

        // Reset center to default value for the main animation.
        g2d.translate(tx, ty);
        g2d.scale(scale, scale);

        // Draw wall background
        g2d.setColor(new Color(107, 0, 1));
        g2d.fillRect(-90, -25, (int)pictureWidth, (int)pictureHeight);

        // Draw bricks
        GradientPaint gp = new GradientPaint(
                25, 50,
                new Color(202, 0, 66),
                60, 5,
                new Color(252, 163, 205),
                true
        );
        g2d.setPaint(gp);
        BasicStroke lineStroke = new BasicStroke(4, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
        g2d.setStroke(lineStroke);

        int[][][] lines = {
                {
                        {0, -23},
                        {0, 8}
                },
                {
                        {0, 55},
                        {0, 86}
                },
                {
                        {-(int)pictureWidth/2+2, 12},
                        {(int)pictureWidth/2-2, 12}
                },
                {
                        {-(int)pictureWidth/2+2, 51},
                        {(int)pictureWidth/2-2, 51}
                },
                {
                        {-(int)pictureWidth/4+2, 16},
                        {-(int)pictureWidth/4+2, 47}
                },
                {
                        {(int)pictureWidth/4+2, 16},
                        {(int)pictureWidth/4+2, 47}
                }
        };

        for (int[][] line : lines) {
            g2d.drawLine(line[0][0], line[0][1], line[1][0], line[1][1]);
        }


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new WallAnimation());

        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    public void actionPerformed(ActionEvent e) {
        // scaling
        if (scale < 0.01) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }

        // movement
        if (tx <= radius+radiusExtention - pictureWidth/2 - 16 && ty < -(radius+radiusExtention - pictureHeight/2 + 16)){
            tx += deltaX;
        }else if(tx > 0 && ty <= radius+radiusExtention - pictureHeight/2 -48){
            ty += deltaX;
        }else if(tx >= -(radius+radiusExtention - pictureWidth/2 - 16) && ty > 0){
            tx -= deltaX;
        }else if(tx < 0 && ty <= radius+radiusExtention - pictureHeight/2 - 47){
            ty -= deltaX;
        }
        scale += delta;

        repaint();
    }
}
