import javax.swing.*;
import java.awt.*;

public class View extends JFrame{
    Model model;
    JPanel panel = new JPanel();
    public JButton readFromFile = new JButton("Read from file");
    public JButton writeToFile = new JButton("Write to file");
    public JButton drawGraphic = new JButton("Draw graphic");
    public JButton calculateR = new JButton("Calculate r");

    public DisplayGraphics g = new DisplayGraphics();

    View(Model model){

        this.model = model;


        readFromFile.setBounds(10,150,130,30);
        writeToFile.setBounds(10,190,130,30);
        drawGraphic.setBounds(10,230,130,30);
        calculateR.setBounds(10,270,130,30);

        panel.add(readFromFile);
        panel.add(writeToFile);
        panel.add(drawGraphic);
        panel.add(calculateR);

        panel.add(g);
        panel.setLayout(null);


        this.add(panel, BorderLayout.CENTER);
        setSize(600,760);

        g.setBounds(150,10,getWidth()-40,getHeight()-120);
        g.setInit(model);
        g.repaint();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
