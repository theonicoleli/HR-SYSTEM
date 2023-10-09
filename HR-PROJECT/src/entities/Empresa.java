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
        for (Pessoa pessoa1: pessoas) {
            if (pessoa1.equals(pessoa)) {
                throw new FuncionarioException("Pessoa já existente na lista de funcionários.");
            }
        }
        pessoas.add(pessoa);
    }

    public void informacoesFuncionarios() {
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.toString());
            System.out.println();
        }
    }

    public ArrayList<Pessoa> getPessoas(Pessoa pessoa) {
        if (pessoa instanceof RH) {
            return pessoas;
        }
        throw new FuncionarioException("Apenas o RH, tem direito de pegar dados de todos os funcionários.");
    }

    public void atualizandoLista(ArrayList<Pessoa> funcionarios) {
        pessoas = funcionarios;
    }

    public String getNome() {
        return nome;
    }

    public int getCompanyAge() {
        Date currentDate = new Date();
        int currentYear = Integer.parseInt(sdf.format(currentDate).split("/")[2]);
        int companyStartYear = Integer.parseInt(sdf.format(companyAge).split("/")[2]);

        int age = currentYear - companyStartYear;
        return age;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Empresa: " + getNome()
                + "\nIdade: " + getCompanyAge()
                + "\nLucro aproximado: R$" + getBalance();
    }
}
