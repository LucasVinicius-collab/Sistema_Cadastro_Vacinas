package entidade;

public class Pessoa {

    private String nome;
    long cpf;

    public Pessoa(String nome, long cpf) {
        this.nome=nome;
        this.cpf=cpf;
	}

    public void setNome(String nome) {
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }
        
    public void setCpf(long cpf) {
        this.cpf=cpf;
    }

    public long getCpf() {
        return cpf;
    }
}