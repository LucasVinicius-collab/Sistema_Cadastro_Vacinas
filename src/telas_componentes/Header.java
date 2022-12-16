package telas_componentes;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header {
    
    private final String txtHeader;

    public Header(String txtHeader){
        this.txtHeader=txtHeader;
    }
    
    public JPanel criar(){
        JPanel header=new JPanel();
        JLabel titulo= new JLabel();

        header.setLayout(new FlowLayout());
        header.setBackground(null);
        header.setAlignmentX(Component.TOP_ALIGNMENT);
        header.setAlignmentY(Component.TOP_ALIGNMENT);
        
        titulo.setText(txtHeader);
        titulo.setFont(new Font("Arial",1, 30));

         header.add(titulo);
         return header;
    }   
}