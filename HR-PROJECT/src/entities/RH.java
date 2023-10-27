package entities;

import entities.DataBase.DAO;
import entities.enumerator.Setor;
import entities.enumerator.Turno;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class RH extends Funcionario implements PropriedadesSetor {

    private DAO dao;
    private boolean retirarFuncionario;
    private ArrayList<Pessoa> funcionarios = new ArrayList<>();

    public RH(String nome, String cpf,
              Date dtNascimento, Setor setor,
              double salario, String carteiraTrabalho,
              String funcao, Turno turno) {
        super(nome, cpf, dtNascimento,
                setor, salario, carteiraTrabalho,
                funcao, turno);
        this.dao = new DAO();
    }

    public void informacoesFuncionarios(Empresa empresa) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario instanceof Funcionario) {
                System.out.println(funcionario);
                System.out.println();
            }
        }
    }

    public boolean verificandoFuncionarioExistente(String cpf) {
        if (dao.funcionariosDb(cpf)) return true;
        return false;
    }

    public void adicionarFuncionario(Pessoa funcionario) {
        if (funcionario instanceof Funcionario && verificandoFuncionarioExistente(funcionario.getCpf())) {
            dao.addPessoa(this, funcionario);
        }
    }

    public void adicionarFuncionario(String nome, String cpf,
                         Date dtNascimento, Setor setor,
                         double salario, String carteiraTrabalho,
                         String funcao, Turno turno) {
        if (verificandoFuncionarioExistente(cpf)) {
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
        if (funcionario instanceof Funcionario && verificandoFuncionarioExistente(getCpf()) != true) {
            dao.removerFuncionario(this, funcionario);
        }
    }

    public void alterarFuncionario(String nome, double newSalario) {
        try {
            for (Pessoa funcionario : funcionarios) {
                if (funcionario.getNome().equals(nome)) {
                    mudarSalario(this, (Funcionario) funcionario, newSalario);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void alterarFuncionario(String nome, Setor setor) {
        try {
            for (Pessoa funcionario : funcionarios) {
                if (funcionario.getNome().equals(nome)) {
                    mudarSetor(this, (Funcionario) funcionario, setor);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void alterarFuncionario(String nome, Turno turno) {
        try {
            for (Pessoa funcionario : funcionarios) {
                if (funcionario.getNome().equals(nome)) {
                    mudarTurno(this, (Funcionario) funcionario, turno);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void alterarFuncionario(String nome, double newSalario, Setor setor, Turno turno) {
        try {
            for (Pessoa funcionario : funcionarios) {
                if (funcionario.getNome().equals(nome)) {
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
