package entities;

import entities.DataBase.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Empresa {

    private DAO dao;
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
        dao = new DAO();
    }

    public ArrayList<Pessoa> getPessoas(Pessoa pessoa) {
        if (pessoa instanceof RH) {
            return pessoas;
        }
        throw new FuncionarioException("Apenas o RH, tem direito de pegar dados de todos os funcionários.");
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
