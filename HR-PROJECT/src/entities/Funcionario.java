package entities;

import entities.DataBase.DAO;
import entities.enumerator.Setor;
import entities.enumerator.Turno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Funcionario extends Pessoa implements PropriedadesSetor {

    private DAO dao;
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

    protected void mudarSalario(Funcionario administrador, Funcionario funcionario, double newSalario) throws SQLException {
        if (administrador instanceof RH) {
            funcionario.setSalario(administrador, funcionario, newSalario);
            Connection con = dao.conectar();
            if (dao.possivelAlteracao(administrador, funcionario)) {
                String changingData = "UPDATE FUNCIONARIO SET SALARIO = ? WHERE CPF = ?";
                PreparedStatement preparedStatement = con.prepareStatement(changingData);
                preparedStatement.setDouble(1, newSalario);
                preparedStatement.setString(2, funcionario.getCpf());
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Salário de " + funcionario.getNome() + " atualizado para: " + newSalario);
                if (rowsAffected == 0) {
                    System.out.println("Nenhum registro foi atualizado. Verifique o CPF.");
                }
                preparedStatement.close();
            }
        } else {
            throw new FuncionarioException("Você não tem permissão para alterar o salário de outros funcionários.");
        }
    }

    protected void mudarSetor(Funcionario administrador, Funcionario funcionario, Setor setor) throws SQLException {
        if (administrador instanceof RH) {
            funcionario.setSetor(administrador, funcionario, setor);
            Connection con = dao.conectar();
            if (dao.possivelAlteracao(administrador, funcionario)) {
                String changingData = "UPDATE FUNCIONARIO SET NomeSetor = ? WHERE CPF = ?";
                PreparedStatement preparedStatement = con.prepareStatement(changingData);
                preparedStatement.setString(1, String.valueOf(setor));
                preparedStatement.setString(2, funcionario.getCpf());
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Setor de " + funcionario.getNome() + " atualizado para: " + setor);
                if (rowsAffected == 0) {
                    System.out.println("Nenhum registro foi atualizado. Verifique o CPF.");
                }
                preparedStatement.close();
            }
        } else {
            throw new FuncionarioException("Você não tem permissão de alterar o setor de outros funcionários.");
        }
    }

    protected void mudarTurno(Funcionario administrador, Funcionario funcionario, Turno turno) throws SQLException {
        if (administrador instanceof RH) {
            funcionario.setTurno(administrador, funcionario, turno);
            Connection con = dao.conectar();
            if (dao.possivelAlteracao(administrador, funcionario)) {
                String changingData = "UPDATE FUNCIONARIO SET Turno = ? WHERE CPF = ?";
                PreparedStatement preparedStatement = con.prepareStatement(changingData);
                preparedStatement.setString(1, String.valueOf(turno));
                preparedStatement.setString(2, funcionario.getCpf());
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Turno de " + funcionario.getNome() + " atualizado para: " + turno);
                if (rowsAffected == 0) {
                    System.out.println("Nenhum registro foi atualizado. Verifique o CPF.");
                }
                preparedStatement.close();
            }
        } else {
            throw new FuncionarioException("Você não tem permissão de alterar o turno de outros funcionários.");
        }
    }

    public void setTurno(Funcionario administrador, Funcionario funcionario, Turno turno) {
        if (administrador instanceof RH) {
            funcionario.turno = turno;
        } else {
            throw new FuncionarioException("Você não tem permissão de alterar o turno de outros funcionários.");
        }
    }

    public void setSetor(Funcionario administrador, Funcionario funcionario, Setor setor) {
        if (administrador instanceof RH) {
            funcionario.setor = setor;
        } else {
            throw new FuncionarioException("Você não tem permissão de alterar o setor de outros funcionários.");
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
        return "" + funcao;
    }

    @Override
    public String getSetor(){
        return "" + setor;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome()
                + "\nCPF: " + getCpf()
                + "\nCarteira de Trabalho: " + getCarteiraTrabalho()
                + "\nSetor: " + getSetor()
                + "\nTurno: " + getTurno().toLowerCase()
                + "\nFunção: " + getFuncao()
                + "\nSalário: " + getSalario();
    }
}
