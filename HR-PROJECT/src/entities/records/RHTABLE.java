package entities.records;

import entities.Funcionario;
import entities.Pessoa;

import java.util.ArrayList;

public record RHTABLE(ArrayList<? super Pessoa> funcionarios) {

    public void addFuncionarioList (Pessoa func) {
        if (func instanceof Funcionario) {
            funcionarios.add(func);
        }
    }

    public int sizeOfList() {
        return funcionarios.size();
    }
}
