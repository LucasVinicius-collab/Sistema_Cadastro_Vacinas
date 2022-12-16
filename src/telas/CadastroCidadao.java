package telas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import entidade.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import listas.*;
import telas_componentes.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import static javax.swing.JOptionPane.showMessageDialog;

public class CadastroCidadao extends JFrame implements ActionListener, TelaPadrao  {
       
    private String nome;
    private long cpf = 0;
    private Date data1=null, data2=null, data3=null,  data4=null;
    private Date mes2=null, mes3=null, mes4=null;
    
    private final CriarTela tela;
    
    private final int larguraJanela, alturaJanela;
    private int larguraComponente;
    
    //Criando os campos de texto
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtData1;
    private JTextField txtData2;
    private JTextField txtData3;
    private JTextField txtData4;
    
    //Criando os botões
    private JButton btnCadastrar;
    private JButton btnVoltar;
       
    private final ListaCidadao lista;
       
    public CadastroCidadao(){
        //dimensão da janela
        larguraJanela=550;
        alturaJanela=400;
           
        tela=new CriarTela(TituloJanela.TITULO_JANELA,larguraJanela,alturaJanela);
        tela.criar();
        tela.setLayout(null);
        adicionarComponentes();
        tela.centralizar();
        tela.setResizable(false);
        lista=new ListaCidadao();
        tela.mostrar();
    }
       
    public final void adicionarComponentes(){
        //Coordenadas de cada parte do corpo que vai aparecer na janela
        larguraComponente=larguraJanela-20;
           
        JPanel header=header();
        header.setSize(larguraComponente, 50);
        header.setLocation(6,20);
           
        JPanel body=body();
        body.setLocation(115,100);
           
        JPanel footer=footer();
        footer.setLocation(2,300);
           
        tela.add(header);
        tela.add(body);
        tela.add(footer);
    }
       
    public final JPanel header(){
           return new Header("Cadastrar Vacina do Cidadão").criar();
    }
       
    public final JPanel body(){
        JPanel body=new JPanel();
        JLabel lblNome= new JLabel();
        JLabel lblCpf=new JLabel();
        JLabel lblData1=new JLabel();
        JLabel lblData2=new JLabel();
        JLabel lblData3=new JLabel();
        JLabel lblData4=new JLabel();
    
        txtNome= new SomenteLetras(30);
        txtCpf=new SomenteNumeros(11);
        txtData1=new SomenteNumeros(10);
        txtData2=new SomenteNumeros(10);
        txtData3=new SomenteNumeros(10);
        txtData4=new SomenteNumeros(10);
       
        body.setLayout(new GridLayout(6,4,6,2));
        body.setSize(345,170);
        body.setBackground(null);
        
        //Colocando as labels
        lblNome.setText("Nome");
        lblCpf.setText("CPF");
        lblData1.setText("Data da 1º dose: DIA/MES/ANO");
        lblData2.setText("Data da 2º dose: DIA/MES/ANO");
        lblData3.setText("Data da 3º dose: DIA/MES/ANO");
        lblData4.setText("Data da 4º dose: DIA/MES/ANO");
                   
        //adicionando os campos
        body.add(lblNome);
        body.add(txtNome);
          
        body.add(lblCpf);
        body.add(txtCpf);
        
        body.add(lblData1);
        body.add(txtData1);
            
        body.add(lblData2);
        body.add(txtData2);
            
        body.add(lblData3);
        body.add(txtData3);
        
        body.add(lblData4);
        body.add(txtData4);

        return body;
    }
       
    public final JPanel footer(){
           
        JPanel footer=new JPanel();
        btnCadastrar=new JButton();
        btnVoltar=new JButton();
           
        footer.setLayout(new FlowLayout());
        footer.setSize(larguraComponente,100);
        footer.setBackground(null);
           
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(this);
           
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(this);
           
        footer.add(btnCadastrar);
        footer.add(btnVoltar);
           
        return footer;
       }

    @Override
    public void actionPerformed(ActionEvent evt){
        
        if(evt.getSource()==btnVoltar){
            tela.dispose();
            new MenuVacina();
        } 
        
        else if(evt.getSource()==btnCadastrar){
            
            //Verificando campos se estão vazios
           if(CamposVazios()){
               JOptionPane.showMessageDialog(null,"Por gentileza, preencha todos os campos vazios.");
                return ;
            }
           
            //Colocando os dados inseridos nas variaveis declaradas
            nome=txtNome.getText();
            cpf=Long.parseLong(txtCpf.getText());
            inserindoDatas();
            
            if(conferindoDatas()==true){
                if(data2.after(mes2)&& data3.after(mes3)&& data4.after(mes4)){
                    lista.adicionarCidadao(new Cidadao(nome,cpf, data1, data2, data3, data4));//Adicionando o cidadão 
                    
                    int resp=JOptionPane.showConfirmDialog(null,"Cidadão Cadastrado.\nDeseja continuar cadastrando?", TituloJanela.TITULO_JANELA,JOptionPane.YES_NO_OPTION);
              
                    if(resp==0){
                        new CadastroCidadao();
                        tela.dispose(); 
                    }
            
                    if(resp==1){
                        try {
                            lista.imprimirArquivo(); 
                    
                        } catch (IOException ex) {   
                             Logger.getLogger(CadastroCidadao.class.getName()).log(Level.SEVERE, null, ex);
                             showMessageDialog(null, "Erro ao imprimir, arquivo impedido o acesso");
                        }   
                        tela.dispose();
                        return; 
                    }
                }
            }
        }
    }
    
    private void inserindoDatas(){
        try { 
            data1 = new SimpleDateFormat("dd/MM/yyyy").parse(txtData1.getText()); 
        }catch (ParseException ex){
            showMessageDialog(null, "Erro de preenchimento do Campo da PRIMEIRA DOSE, por gentilesa, defina a data conforme: DIA/MES/ANO");
            Logger.getLogger(CadastroCidadao.class.getName()).log(Level.SEVERE, null, ex);       
            txtData1.setText("");
        }
         
        try { 
            data2 = new SimpleDateFormat("dd/MM/yyyy").parse(txtData2.getText());
        }catch (ParseException ex) {
            showMessageDialog(null, "Erro de preenchimento do Campo da SEGUNDA DOSE, por gentilesa, defina a data conforme: DIA/MES/ANO");
             Logger.getLogger(CadastroCidadao.class.getName()).log(Level.SEVERE, null, ex);  
            txtData2.setText(""); 
        }
            
        try { 
            data3 = new SimpleDateFormat("dd/MM/yyyy").parse(txtData3.getText());
        } catch (ParseException ex) {
            showMessageDialog(null, "Erro de preenchimento do Campo da TERCEIRA DOSE, por gentilesa, defina a data conforme: DIA/MES/ANO");
            Logger.getLogger(CadastroCidadao.class.getName()).log(Level.SEVERE, null, ex);
            txtData3.setText(""); 
        }
            
        try { 
            data4 = new SimpleDateFormat("dd/MM/yyyy").parse(txtData4.getText());
        } catch (ParseException ex) {
            showMessageDialog(null, "Erro de preenchimento do Campo da QUARTA DOSE, por gentilesa, defina a data conforme: DIA/MES/ANO");
            Logger.getLogger(CadastroCidadao.class.getName()).log(Level.SEVERE, null, ex);
            txtData4.setText("");
        }
    }
    
    private boolean conferindoDatas(){
        //Trabalhando as datas inseridas
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        cal.setTime(data1);//data informada na Primeira dose
        cal.add(Calendar.MONTH, 4);//adicionando 4 meses
         mes2 = cal.getTime();//
       
        cal.setTime(data1);//data informada
        cal.add(Calendar.MONTH, 8);//adicionando 8 meses
        mes3 = cal.getTime();

        cal.setTime(data1);//data informada
        cal.add(Calendar.MONTH, 12);//adicionando 12 meses
        mes4 = cal.getTime();  
        
        
        if(data2.before(mes2)){
            JOptionPane.showMessageDialog(null,
            "Erro na data da aplicação da Vacina\n A SEGUNDA DOSE deve ser aplicada somente depois de  "+formato.format(mes2));
            txtData2.setText("");
            return false;
        }

        if(data3.before(mes3)){
            JOptionPane.showMessageDialog(null,
            "Erro na data da aplicação da Vacina\n\n A TERCEIRA DOSE depois deve ser aplicada somente depois de "+formato.format(mes3));
            txtData3.setText("");
            return false;
        }
                    
        if(data4.before(mes4)){
            JOptionPane.showMessageDialog(null,
            "Erro na data da aplicação da Vacina\n\n A QUARTA DOSE depois deve ser aplicada somente depois de "+formato.format(mes4));
             txtData4.setText("");
              return false;
        }  
        return true;
    }
      
    private boolean CamposVazios(){
        return txtNome.getText().isEmpty()||
                txtCpf.getText().isEmpty()||
                txtData1.getText().isEmpty()||
                txtData2.getText().isEmpty()||
                txtData3.getText().isEmpty()||
                txtData4.getText().isEmpty();
    }    
}