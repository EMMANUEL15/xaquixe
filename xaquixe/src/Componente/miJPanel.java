package Componente;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * @author Emanuel Lopez
 */
public class miJPanel extends JPanel{
    
    public miJPanel(){
        this.setOpaque(false);
    }
    public miJPanel(String border){
        switch(border){
            case "border":
                this.setOpaque(false);
                setLayout(new BorderLayout());
            break;
        }
        
    }
    
}
