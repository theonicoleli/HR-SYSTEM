package entities;

import java.util.Date;

public class RH extends Funcionario implements Setor{

    public RH(String nome, String cpf,
              Date dtNascimento, String setor,
              double salario, String carteiraTrabalho,
              String funcao, Turno turno) {
        super(nome, cpf, dtNascimento,
                setor, salario, carteiraTrabalho,
                funcao, turno);
    }

    @Override
    public String getSetor() {
        return "Setor: RH";
    }

    @Override
    public String getFuncao() {
        return "Função: Administrador";
    }
}
