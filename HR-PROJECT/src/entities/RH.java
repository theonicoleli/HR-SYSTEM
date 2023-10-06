package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class RH extends Funcionario implements PropriedadesSetor {

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
        funcionarios = empresa.getPessoasParaRH(this);
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

    public void adicionarFuncionario(Empresa empresa, String nome, String cpf,
                         Date dtNascimento, Setor setor,
                         double salario, String carteiraTrabalho,
                         String funcao, Turno turno) {

        confirmandoPessoas(empresa);

        funcionarios.add(new Funcionario(nome, cpf, dtNascimento,
                setor, salario, carteiraTrabalho,
                funcao, turno));

        atualizandoLista(empresa);
    }

    public void removerFuncionario(Empresa empresa, String nome) {
        confirmandoPessoas(empresa);

        Iterator<Pessoa> iterator = funcionarios.iterator();
        while (iterator.hasNext()) {
            Pessoa funcionario = iterator.next();
            if (funcionario.getNome().equals(nome)) {
                System.out.println("Removendo o funcionario...\n" + funcionario.toString());
                iterator.remove();
                System.out.println();
            }
        }

        atualizandoLista(empresa);
    }

    public void alterarFuncionario(Empresa empresa, String nome, double newSalario) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario.getNome().equals(nome)) {
                mudarSalario(this, (Funcionario) funcionario, newSalario);
            }
        }
    }


}
