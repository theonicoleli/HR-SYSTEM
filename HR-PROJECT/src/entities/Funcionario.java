package entities;

import entities.enumerator.Setor;
import entities.enumerator.Turno;

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

    protected void mudarSalario(Funcionario administrador, Funcionario funcionario, double newSalario) {
        if (administrador instanceof RH) {
            funcionario.setSalario(administrador, funcionario, newSalario);
            System.out.println("Salário de " + funcionario.getNome() + " atualizado para: " + newSalario);
        } else {
            throw new FuncionarioException("Você não tem permissão de alterar salário de outros funcionários.");
        }
    }


    public String getTurno() {
        return "" + turno;
    }

    public double getSalario() {
        return salario;
    }

    private void setSalario(Funcionario administrador, Funcionario funcionario, double newSalario) {
        if (administrador instanceof RH) {
            funcionario.salario = newSalario;
        } else {
            throw new FuncionarioException("Você não tem permissão de alterar salário de outros funcionários.");
        }
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
