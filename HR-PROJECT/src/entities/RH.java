package entities;

import java.util.ArrayList;
import java.util.Date;

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

    public void informacoesFuncionarios(Empresa empresa) {
        copiandoListaPessoas(empresa);
        for (Pessoa funcionario : funcionarios) {
            if (funcionario instanceof Funcionario) {
                System.out.println(funcionario.toString());
                System.out.println();
            }
        }
    }

}
