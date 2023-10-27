package entities;

import entities.DataBase.DAO;
import entities.enumerator.Setor;
import entities.enumerator.Turno;
import entities.records.RHTABLE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class RH extends Funcionario implements PropriedadesSetor {

    private DAO dao;
    private boolean retirarFuncionario;
    private ArrayList<Pessoa> funcionarios;

    public RH(String nome, String cpf,
              Date dtNascimento, Setor setor,
              double salario, String carteiraTrabalho,
              String funcao, Turno turno) {
        super(nome, cpf, dtNascimento,
                setor, salario, carteiraTrabalho,
                funcao, turno);
        this.dao = new DAO();
        if (dao.sizeOfTable() > 0) {
            funcionarios = (ArrayList<Pessoa>) dao.pegandoFuncionariosBd();
        }
    }

    public void informacoesFuncionarios() {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario instanceof Funcionario) {
                System.out.println(funcionario);
                System.out.println();
            }
        }
    }

    public void infoFuncionario(String cpf){
        for (Pessoa funcionario: funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                System.out.println(funcionario);
            }
        }
    }

    public void adicionarFuncionario(Pessoa funcionario) {
        System.out.println("ENTREI");
        if (funcionario instanceof Funcionario && dao.funcionariosDb(funcionario.getCpf(), true)) {
            dao.addPessoa(this, funcionario);
        }
    }

    public void adicionarFuncionario(String nome, String cpf,
                         Date dtNascimento, Setor setor,
                         double salario, String carteiraTrabalho,
                         String funcao, Turno turno) {
        if (dao.funcionariosDb(cpf, true)) {
             Funcionario func = new Funcionario(nome, cpf, dtNascimento,
                    setor, salario, carteiraTrabalho,
                    funcao, turno);
             funcionarios.add(func);
             dao.addPessoa(this, func);
        }
    }

    public void removerFuncionario(String cpf) {

        Iterator<Pessoa> iterator = funcionarios.iterator();
        while (iterator.hasNext()) {
            Pessoa funcionario = iterator.next();
            if (funcionario.getCpf().equals(cpf)) {
                System.out.println("Removendo o funcionario...\n" + funcionario);
                retirarFuncionario = true;
                dao.removerFuncionario(this, (Pessoa) iterator);
                System.out.println();
            }
        }
        if (retirarFuncionario != true) {
            throw new FuncionarioException("Não existe nenhum funcionário com este nome.");
        }

        retirarFuncionario = false;
    }

    public void removerFuncionario(Pessoa funcionario) {
        if (funcionario instanceof Funcionario && dao.funcionariosDb(funcionario.getCpf(), false) == false) {
            dao.removerFuncionario(this, funcionario);
        }
    }

    public void alterarFuncionario(String cpf, double newSalario) {
        try {
            for (Pessoa funcionario : funcionarios) {
                if (funcionario.getCpf().equals(cpf)) {
                    mudarSalario(this, (Funcionario) funcionario, newSalario);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void alterarFuncionario(String cpf, Setor setor) {
        try {
            for (Pessoa funcionario : funcionarios) {
                if (funcionario.getCpf().equals(cpf)) {
                    mudarSetor(this, (Funcionario) funcionario, setor);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void alterarFuncionario(String cpf, Turno turno) {
        try {
            for (Pessoa funcionario : funcionarios) {
                if (funcionario.getCpf().equals(cpf)) {
                    mudarTurno(this, (Funcionario) funcionario, turno);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void alterarFuncionario(String cpf, double newSalario, Setor setor, Turno turno) {
        try {
            for (Pessoa funcionario : funcionarios) {
                if (funcionario.getCpf().equals(cpf)) {
                    mudarSalario(this, (Funcionario) funcionario, newSalario);
                    mudarSetor(this, (Funcionario) funcionario, setor);
                    mudarTurno(this, (Funcionario) funcionario, turno);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
