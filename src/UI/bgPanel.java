package UI;

import javax.swing.*;
import java.awt.*;

public class bgPanel extends JPanel {

    private static final long serialVersionUID = -6352788025440244338L;

    ImageIcon ui;
    public bgPanel(int i){
        if(i==1){
            ui=new ImageIcon("Images/UI1.jpg");
        }
        else {
            ui=new ImageIcon("Images/UI2.jpg");
        }
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(ui.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
