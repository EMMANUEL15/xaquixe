package Componente;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * @author equipo
 */
public class tabla extends JTable{
    public tabla(){
        //CONFIGURACION DE LA TABLA
        Font fuente = new Font("Bodoni", Font.ITALIC, 15);
            this.setFont(fuente);
            this.setDragEnabled(false);
        //CONFIGURACION DEL ENCABEZADO DE LA TABLA
            JTableHeader th; 
            th = getTableHeader(); 
    }
    public tabla(DefaultTableModel model){
        Font fuente = new Font("Bodoni", Font.ITALIC, 25);
            this.setFont(fuente);
            this.setDragEnabled(false);
            
            JTableHeader th; 
            th = getTableHeader(); 
            
            this.setModel(model);
    }
}
