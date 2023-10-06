package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Pessoa {

    private String nome;
    private String cpf;
    private Date dtNascimento;
    protected SimpleDateFormat sdf;

    public Pessoa(String nome, String cpf, Date dtNascimento) {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.nome = nome;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDtNascimento() {
        return sdf.format(dtNascimento);
    }

    public abstract String toString();
}
