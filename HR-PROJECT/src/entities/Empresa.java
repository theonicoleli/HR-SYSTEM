package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Empresa {

    private final String nome;
    private ArrayList<Pessoa> pessoas;
    private final Date companyAge;
    private final double balance;
    protected SimpleDateFormat sdf;

    public Empresa(String nome, Date companyAge, double balance) {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.nome = nome;
        this.companyAge = companyAge;
        this.balance = balance;
        pessoas = new ArrayList<>();
    }

    public void adicionarPessoas(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public void informacoesFuncionarios() {
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.toString());
            System.out.println();
        }
    }

    public ArrayList<Pessoa> getPessoasParaRH(Pessoa pessoa) {
        if (pessoa instanceof RH) {
            return pessoas;
        }
        return null;
    }

    public void atualizandoLista(ArrayList<Pessoa> funcionarios) {
        pessoas = funcionarios;
    }

    public String getNome() {
        return nome;
    }

    public String getCompanyAge() {
        return sdf.format(companyAge);
    }

    public double getBalance() {
        return balance;
    }
}
