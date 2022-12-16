package telas;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import telas_componentes.*;
import listas.*;

public class MenuVacina extends JFrame implements ActionListener,TelaPadrao {
    private final CriarTela tela = null;
    private final CriarTela telaInicial;
    private final int larguraJanela, alturaJanela;
    private int larguraComponente;
    
    private JButton btnCadastrarCidadao;
    private JButton btnListarCidadao;
    private JButton btnVoltar;
    
    ListaCidadao lista=new ListaCidadao();
     
    public MenuVacina(){
        larguraJanela=550;
        alturaJanela=300;
        
        telaInicial=new CriarTela(TituloJanela.TITULO_JANELA,larguraJanela,alturaJanela);
        telaInicial.criar();
        
        telaInicial.setLayout(null);
        adicionarComponentes();
        telaInicial.centralizar();
        telaInicial.setResizable(false);
        telaInicial.mostrar();
    }

    public final void adicionarComponentes(){
        larguraComponente=larguraJanela-20;
        
        JPanel header=header();
        header.setSize(larguraComponente, 30);
        header.setLocation(6,15);
        
        JPanel body=body();
        body.setLocation(6,100);
        
        JPanel footer=footer();
        footer.setLocation(2,200);
        
        telaInicial.add(header);
        telaInicial.add(body);
        telaInicial.add(footer);
    }
    
    public final JPanel header(){
        return new Header("Menu de Controle de Vacinas").criar();
    }
    
    public final JPanel body(){
        JPanel body= new JPanel();
        btnCadastrarCidadao=new JButton();
        btnListarCidadao=new JButton();
        
        body.setLayout(new GridLayout(2,1));
        body.setSize(larguraComponente,100);
        body.setBackground(Color.CYAN);
              
        btnCadastrarCidadao.setText("Vacinar de Cidadão");
        btnCadastrarCidadao.addActionListener(this);
              
        btnListarCidadao.setText("Listar Cidadão");
        btnListarCidadao.addActionListener(this);
              
        body.add(btnCadastrarCidadao);
        body.add(btnListarCidadao);
        
        return body;        
    }
    
    public final JPanel footer(){
        JPanel footer= new JPanel();
        btnVoltar=new JButton();
        
        footer.setLayout(new FlowLayout());
        footer.setSize(larguraComponente,100);
        footer.setBackground(null);
              
        btnVoltar.setText("Sair");
        btnVoltar.addActionListener(this);
              
        footer.add(btnVoltar);
              
        return footer;
    }
    
    public void actionPerformed(ActionEvent evt){
       
        if(evt.getSource()==btnCadastrarCidadao){
            new CadastroCidadao();
            telaInicial.dispose();
        }
        
        if(evt.getSource()==btnListarCidadao){
                if (verificArquivo()==true){
                    try {
                        lista.imprimirArquivo();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuVacina.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }else{
                    int resp=JOptionPane.showConfirmDialog(null,"Arquivo de cadastro não encontrado, deseja Cadastrar?", 
                             TituloJanela.TITULO_JANELA,JOptionPane.YES_NO_OPTION);
              
                    if(resp==0){
                        new CadastroCidadao();
                        telaInicial.dispose();
                    }
                    
                    if(resp==1){
                          new MenuVacina();
                         telaInicial.dispose();     
                    }
                }                  
            telaInicial.dispose();
            return;
        }
        
        if(evt.getSource()==btnVoltar)
            System.exit(0);
        }
    
        private boolean verificArquivo(){
            File arquivo = new File("C:\\Users\\Public\\Documents/Cidadao_Vacinados.txt");
                if (!arquivo.exists()){
                    return false;
                }else
                     return true;
        } 
    }