import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->new MyFame("正弦曲线"));
    }
}

class MyFame extends JFrame{

    MyFame(String title)
    {
        int left,top,width,height;
        left=400;
        top= 300;
        width=806;
        height=535;
        setTitle(title);
        setBounds(left,top,width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Image image = new ImageIcon("./image/Nitori_Wrench.png").getImage();
        setIconImage(image);

        Container container=getContentPane();
        container.add(new MyJComponent());


        setVisible(true);
    }
}

class MyJComponent extends JComponent
{
    private static final int PREFERRED_WIDTH=800;
    private static final int PREFERRED_HEIGHT=500;
    private static final double pi=Math.PI;
    private static final double h=4;
    private static final double grow=1.0/PREFERRED_WIDTH;
    private Graphics2D graphics2D;

    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        graphics2D=(Graphics2D) g;
        paintBackground();
        paintFunction();
        paintText();
    }

    private void paintBackground()
    {
        graphics2D.drawImage(new ImageIcon("./image/20210416175941.jpg").getImage(),
                0,0,PREFERRED_WIDTH,PREFERRED_HEIGHT,null);
    }

    private void paintFunction()
    {
        graphics2D.setStroke(new BasicStroke(0.015f));
        Line2D line2D=new Line2D.Double(0,0,2*pi,0);
        graphics2D.scale(PREFERRED_WIDTH/(2*pi),PREFERRED_HEIGHT/h);
        graphics2D.translate(pi/200,3*h/8);
        graphics2D.draw(line2D);
        double x1 = 0;double y1 = 0;
        for(double i=0;i<2*pi;i+=grow)
        {
            double y2=(-Math.sin(i)*1.5);
            Line2D sin_line2D=new Line2D.Double(x1,y1, i,y2);
            graphics2D.draw(sin_line2D);
            x1= i;
            y1=y2;
        }

    }

    private void paintText()
    {
        graphics2D.translate(2.75,2);
        graphics2D.scale((2*pi)/PREFERRED_WIDTH,h/PREFERRED_HEIGHT);
        Font font=new Font("LucidaSans",Font.BOLD,25);
        graphics2D.setFont(font);
        graphics2D.drawString("一个周期",0,0);
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREFERRED_WIDTH,PREFERRED_HEIGHT);
    }
}
