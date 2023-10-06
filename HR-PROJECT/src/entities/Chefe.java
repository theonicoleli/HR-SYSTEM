package entities;

import java.util.Date;

public class Chefe extends Pessoa{

    public Chefe(String nome, String cpf, Date dtNascimento) {
        super(nome, cpf, dtNascimento);
    }

    @Override
    public String toString() {
        return "Nome: " + getNome()
                + ", Função: " + getClass().getSimpleName();
    }
}
