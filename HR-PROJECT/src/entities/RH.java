package entities;

import entities.enumerator.Setor;
import entities.enumerator.Turno;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class RH extends Funcionario implements PropriedadesSetor {

    private boolean retirarFuncionario;
    private ArrayList<Pessoa> funcionarios = new ArrayList<>();

    public RH(String nome, String cpf,
              Date dtNascimento, Setor setor,
              double salario, String carteiraTrabalho,
              String funcao, Turno turno) {
        super(nome, cpf, dtNascimento,
                setor, salario, carteiraTrabalho,
                funcao, turno);
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
                System.out.println(funcionario.toString());
                System.out.println();
            }
        }
    }

    private boolean verificandoFuncionarioExistente(String cpf) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
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
            funcionarios.add(new Funcionario(nome, cpf, dtNascimento,
                    setor, salario, carteiraTrabalho,
                    funcao, turno));
            atualizandoLista(empresa);
        }
    }

    public void removerFuncionario(Empresa empresa, String nome) {
        confirmandoPessoas(empresa);

        Iterator<Pessoa> iterator = funcionarios.iterator();
        while (iterator.hasNext()) {
            Pessoa funcionario = iterator.next();
            if (funcionario.getNome().equals(nome)) {
                System.out.println("Removendo o funcionario...\n" + funcionario.toString());
                retirarFuncionario = true;
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
        for (Pessoa funcionario : funcionarios) {
            if (funcionario.getNome().equals(nome)) {
                mudarSalario(this, (Funcionario) funcionario, newSalario);
            }
        }
    }

    public void alterarFuncionario(String nome, Setor setor) {
        for (Pessoa funcionario: funcionarios) {
            if (funcionario.getNome().equals(nome)) {
                mudarSetor(this, (Funcionario) funcionario, setor);
            }
        }
    }

    public void alterarFuncionario(String nome, Turno turno) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario.getNome().equals(nome)) {
                mudarTurno(this, (Funcionario) funcionario, turno);
            }
        }
    }

    public void alterarFuncionario(String nome, double newSalario, Setor setor, Turno turno) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario.getNome().equals(nome)) {
                mudarSalario(this, (Funcionario) funcionario, newSalario);
                mudarSetor(this, (Funcionario) funcionario, setor);
                mudarTurno(this, (Funcionario) funcionario, turno);
            }
        }
    }

}
