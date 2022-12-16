package telas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import entidade.*;
import listas.*;
import telas_componentes.*;

public class TelaInicial extends JFrame implements ActionListener, TelaPadrao  {
        
    private final CriarTela tela;
    
    private final int larguraJanela, alturaJanela;
    private int larguraComponente;
    
    private JTextField txtNome;
    private JTextField txtCpf;

    private JButton btnCadastrar;
    private JButton btnSair;
       
    private final ListaEnfermeiros lista;
       
    public TelaInicial(){
        larguraJanela=550;
        alturaJanela=400;
           
        tela=new CriarTela(TituloJanela.TITULO_JANELA,larguraJanela,alturaJanela);
        tela.criar();
        tela.setLayout(null);
        adicionarComponentes();
        tela.centralizar();
        tela.setResizable(false);
        lista=new ListaEnfermeiros();
        tela.mostrar();
    }
       
    public final void adicionarComponentes(){
        larguraComponente=larguraJanela-20;
           
        JPanel header=header();
        header.setSize(larguraComponente, 30);
        header.setLocation(6,20);
           
        JPanel body=body();
        body.setLocation(6,100);
           
        JPanel footer=footer();
        footer.setLocation(2,300);
           
        tela.add(header);
        tela.add(body);
        tela.add(footer);
    }
       
    @Override
    public final JPanel header(){
           return new Header("Sistema de Controle de Vacina").criar();
    }
       
    public final JPanel body(){
        JPanel body=new JPanel();
        JLabel lblsub=new JLabel();
        JLabel lblNome= new JLabel();
        JLabel lblCpf=new JLabel();

       
        txtNome= new JTextField();
        txtCpf=new JTextField();

        body.setLayout(new GridLayout(9,4,8,2));
        body.setSize(larguraComponente,200);
        body.setBackground(null);
        
        //Colocando as labels
        lblsub.setText("Profissional de Enfermagem, Registre os seguintes dados:");
        lblNome.setText("Nome");
        lblCpf.setText("Cpf");
                   
        //adicionando os campos
        body.add(lblsub);
        
        body.add(lblNome);
        body.add(txtNome);
          
        body.add(lblCpf);
        body.add(txtCpf);
       
        return body;
    }
       
    public final JPanel footer(){
           
        JPanel footer=new JPanel();
        btnCadastrar=new JButton();
        btnSair=new JButton();
           
        footer.setLayout(new FlowLayout());
        footer.setSize(larguraComponente,100);
        footer.setBackground(null);
           
        btnCadastrar.setText("Entrar");
        btnCadastrar.addActionListener(this);
           
        btnSair.setText("Sair");
        btnSair.addActionListener(this);
           
        footer.add(btnCadastrar);
        footer.add(btnSair);
           
        return footer;
       }
       
    @Override
    public void actionPerformed(ActionEvent evt){
        String nome;
        long cpf;
        
        if(evt.getSource()==btnSair){
           System.exit(0);
        } 
        
        else if(evt.getSource()==btnCadastrar){
    
           if(CamposVazios()){
                JOptionPane.showMessageDialog(null,"Por gentileza, preencha todos os campos vazios.");
                return ;
            }
           
        VerificaCampoNumero verificarNome=new VerificaCampoNumero(txtNome);
        VerificaCampoNumero verificarCpf=new VerificaCampoNumero(txtCpf);
        
         
        if(verificarNome.verificar()){
            JOptionPane.showMessageDialog(null,"Dados incorretos, por geltileza, preencha o campo do Nome com letras.");
            txtNome.setText("");
            return;
        }
        
        if(!verificarCpf.verificar()){
            JOptionPane.showMessageDialog(null,"Dados incorretos, por geltileza, preencha o campo do Cpf com numeros.");
            txtCpf.setText("");
            return;
        }
            nome=txtNome.getText();
            cpf=Long.parseLong(txtCpf.getText());

            //Adicionando Enfermeiro
            lista.adicionarEnfermeiro(new Enfermeira(nome,cpf));
            

            new MenuVacina();
        
            tela.dispose();    
        }
    }
     private boolean CamposVazios(){
        return txtNome.getText().isEmpty()||
                txtCpf.getText().isEmpty();
    }
}