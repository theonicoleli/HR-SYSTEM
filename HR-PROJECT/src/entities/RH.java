package entities;

import entities.DataBase.DAO;
import entities.enumerator.Setor;
import entities.enumerator.Turno;

import java.sql.Connection;
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
        dao = new DAO();
    }

    private void copiandoListaPessoas(Empresa empresa) {
        funcionarios = empresa.getPessoas(this);
    }

    private void atualizandoLista(Empresa empresa) {
        empresa.atualizandoLista(funcionarios);
    }

    private void confirmandoPessoas(Empresa empresa) {
        if (funcionarios.size() == 0) {
            copiandoListaPessoas(empresa);
        }
    }

    public void informacoesFuncionarios(Empresa empresa) {
        copiandoListaPessoas(empresa);
        for (Pessoa funcionario : funcionarios) {
            if (funcionario instanceof Funcionario) {
                System.out.println(funcionario);
                System.out.println();
            }
        }
    }

    public boolean verificandoFuncionarioExistente(String cpf) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                System.out.println(funcionario.getCpf() + " " + cpf);
                throw new FuncionarioException("Funcionario já existente.");
            }
        }
        return true;
    }

    public void adicionarFuncionario(Empresa empresa, String nome, String cpf,
                         Date dtNascimento, Setor setor,
                         double salario, String carteiraTrabalho,
                         String funcao, Turno turno) {

        confirmandoPessoas(empresa);

        if (verificandoFuncionarioExistente(cpf)) {
             Funcionario func = new Funcionario(nome, cpf, dtNascimento,
                    setor, salario, carteiraTrabalho,
                    funcao, turno);
             funcionarios.add(func);
             atualizandoLista(empresa);
             dao.addPessoa(this, func);
        }
    }

    public void removerFuncionario(Empresa empresa, String nome) {
        confirmandoPessoas(empresa);

        Iterator<Pessoa> iterator = funcionarios.iterator();
        while (iterator.hasNext()) {
            Pessoa funcionario = iterator.next();
            if (funcionario.getNome().equals(nome)) {
                System.out.println("Removendo o funcionario...\n" + funcionario);
                retirarFuncionario = true;
                dao.removerFuncionario(this, (Pessoa) iterator);
                iterator.remove();
                System.out.println();
            }
        }
        if (retirarFuncionario != true) {
            throw new FuncionarioException("Não existe nenhum funcionário com este nome.");
        }

        retirarFuncionario = false;
        atualizandoLista(empresa);
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
