package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pessoa pessoa = (Pessoa) obj;
        return Objects.equals(nome, pessoa.nome) && Objects.equals(cpf, pessoa.cpf);
    }

    public abstract String toString();
}
