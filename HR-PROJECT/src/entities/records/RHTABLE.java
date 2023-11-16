package entities.records;

import entities.Funcionario;
import entities.Pessoa;

import java.util.Optional;
import java.util.Set;

public record RHTABLE(Set<? super Pessoa> funcionarios) {

    public void addFuncionarioList (Pessoa func) {
        if (func instanceof Funcionario) {
            funcionarios.add(func);
        }
    }

    public boolean contains(Pessoa funcionario) {
        return funcionarios.contains(funcionario);
    }

    public int sizeOfList() {
        return funcionarios.size();
    }
}
