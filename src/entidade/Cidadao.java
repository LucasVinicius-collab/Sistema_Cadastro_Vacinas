package entidade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cidadao implements Comparable {
  
    private String nome;
    private long cpf;
    private Date primeiraDose;
    private Date segundaDose;
    private Date terceiraDose;
    private Date quartaDose;
    
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
    
    public Cidadao(String nome, long cpf, Date primeiraDose, Date segundaDose, Date terceiraDose, Date quartaDose) {
       this.nome=nome; 
       this.cpf=cpf; 
       this.primeiraDose=primeiraDose;
       this.segundaDose=segundaDose;
       this.terceiraDose= terceiraDose;
       this.quartaDose= quartaDose;
    }
     
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

     public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    
    public String getPrimeiraDose() {
        String data1;
        data1=formato.format(primeiraDose);
        return data1;
    }

    public void setPrimeiraDose(Date PrimeiraDose) {
        this.primeiraDose = PrimeiraDose;
    }

    public String getSegundaDose() {
        String data2;
        data2=formato.format(segundaDose);
        return data2;
    }

    public void setSegundaDose(Date SegundaDose) {
        this.segundaDose = SegundaDose;
    }

    public String getTerceiraDose() {
        
         String data3;
        data3=formato.format(terceiraDose);
        return data3;
    }

    public void setTerceiraDose(Date TerceiraDose) {
        this.terceiraDose = TerceiraDose;
    }
    
    public String getQuartaDose() {
        String data4;
        data4=formato.format(quartaDose);
        return data4;
    }

    public void setQuartaDose(Date QuartaDose) {
        this.quartaDose = QuartaDose;
    }

    @Override
	public int compareTo(Object obj ) {
            Cidadao cidadao=(Cidadao)obj;
            int resp= this.getNome().compareTo(cidadao.getNome());
            if(resp!=0){
                return resp;
            }else if(this.getCpf()<cidadao.getCpf()){
                return -1;
            }else if(this.getCpf()>cidadao.getCpf()){
                return 1;
            }else{
                return 0;
            }
       }   

    public boolean equals(Object obj ) {
	return super.equals(obj);
    }
            
    public String toString() {
        return "Nome:"+getNome()+"\nCPF: "+getCpf()+
                "\nData da primeira Dose: "+getPrimeiraDose()+
                "\nData da Segunda Dose: "+getSegundaDose()+
                "\nData da Terceira Dose: "+getTerceiraDose()+
                "\nData da Quarta Dose: "+getQuartaDose()+"\n\n";
    }
}