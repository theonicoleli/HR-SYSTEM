package entities;

import java.util.Date;

public class Funcionario extends Pessoa implements PropriedadesSetor {

    private Setor setor;
    private double salario;
    private String carteiraTrabalho;
    private String funcao;
    private Turno turno;

    public Funcionario(String nome, String cpf,
                       Date dtNascimento,
                       Setor setor,
                       double salario,
                       String carteiraTrabalho,
                       String funcao, Turno turno) {
        super(nome, cpf, dtNascimento);
        this.setor = setor;
        this.salario = salario;
        this.carteiraTrabalho = carteiraTrabalho;
        this.funcao = funcao;
        this.turno = turno;
    }

    public String getTurno() {
        return "" + turno;
    }

    public double getSalario() {
        return salario;
    }

    public String getCarteiraTrabalho() {
        return carteiraTrabalho;
    }

    @Override
    public String getFuncao() {
        return "função: " + funcao;
    }

    @Override
    public String getSetor(){
        return "setor: " + setor;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome()
                + "\nCPF: " + getCpf()
                + "\nSetor: " + getSetor()
                + "\nTurno: " + getTurno().toLowerCase()
                + "\nFunção: " + getFuncao()
                + "\nSalário: " + getSalario();
    }
}
