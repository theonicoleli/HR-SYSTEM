package entities;

import entities.DataBase.DAO;
import entities.enumerator.Setor;
import entities.enumerator.Turno;

import java.sql.SQLException;
import java.util.*;

public class RH extends Funcionario implements PropriedadesSetor {

    private static DAO dao;
    private boolean retirarFuncionario;
    private static Set<Pessoa> funcionarios;

    static {
        dao = new DAO();
        if (dao.sizeOfTable() > 0) {
            funcionarios = (HashSet<Pessoa>) dao.pegandoFuncionariosBd();
        } else {
            funcionarios = new HashSet<>();
        }
    }

    public RH(String nome, String cpf,
              Date dtNascimento, Setor setor,
              double salario, String carteiraTrabalho,
              String funcao, Turno turno) {
        super(nome, cpf, dtNascimento,
                setor, salario, carteiraTrabalho,
                funcao, turno);
    }

    public void informacoesFuncionarios() {
        System.out.println(funcionarios.size());
        for (Pessoa funcionario : funcionarios) {
            if (funcionario instanceof Funcionario) {
                System.out.println(funcionario);
                System.out.println();
            }
        }
    }

    public String getFuncionario(String cpf) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario instanceof Funcionario && funcionario.getCpf().equals(cpf)) {
                return ((Funcionario) funcionario).getSetor();
            }
        }
        return null;
    }

    public Funcionario getFunc(String cpf) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario instanceof Funcionario && funcionario.getCpf().equals(cpf)) {
                return (Funcionario) funcionario;
            }
        }
        return null;
    }

    public static RH getFuncionarioRh(String cpf) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario instanceof RH && funcionario.getCpf().equals(cpf)) {
                return (RH) funcionario;
            }
        }
        return null;
    }

    public void infoFuncionario(String cpf){
        for (Pessoa funcionario: funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                System.out.println(funcionario);
            }
        }
    }

    public void adicionarFuncionario(Pessoa funcionario) {
        if (funcionario instanceof Funcionario && dao.funcionariosDb(funcionario.getCpf(), true)) {
            dao.addPessoa(this, funcionario);
            funcionarios.add(funcionario);
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
                dao.removerFuncionario(this, funcionario);
                funcionarios.remove(funcionario);
                System.out.println();
                return;
            }
        }
        if (retirarFuncionario != true) {
            throw new FuncionarioException("Não existe nenhum funcionário com este CPF.");
        }

        retirarFuncionario = false;
    }

    public void removerFuncionario(Pessoa funcionario) {
        if (funcionario instanceof Funcionario && dao.funcionariosDb(funcionario.getCpf(), false) == false) {
            dao.removerFuncionario(this, funcionario);
            funcionarios.remove(funcionario);
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

    public void alterarFuncionario(String cpf, double newSalario, Setor setor, Turno turno, String funcao) {
        try {
            for (Pessoa funcionario : funcionarios) {
                if (funcionario.getCpf().equals(cpf)) {
                    mudarSalario(this, (Funcionario) funcionario, newSalario);
                    mudarSetor(this, (Funcionario) funcionario, setor);
                    mudarTurno(this, (Funcionario) funcionario, turno);
                    mudarFuncao(this, (Funcionario) funcionario, funcao);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
