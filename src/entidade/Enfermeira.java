package entidade;

public class Enfermeira extends Pessoa implements Comparable{

    private Pessoa pessoa;
    
    public Enfermeira(String nome, long cpf) {
        super(nome, cpf);
	}

    @Override
    public int compareTo(Object obj ) {
        Enfermeira enfermeiro=(Enfermeira)obj;
        int resp= this.getNome().compareTo(enfermeiro.getNome());
        if(resp!=0){
           return resp;
        }else if(this.getCpf()<enfermeiro.getCpf()){
            return -1;
        }else if(this.getCpf()>enfermeiro.getCpf()){
            return 1;
        }else{
            return 0;
        }
    }
        
 @Override
	public boolean equals(Object obj ) {
            return super.equals(obj);
	}
            
    @Override
	public String toString() {
            return "------------------------------------------\n\n----Enfermeiro Responsavel----"+"\n\nNome:"+getNome()+"\nCPF:"+getCpf();
        }		
}