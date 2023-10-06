package application;

import entities.Empresa;
import entities.Funcionario;
import entities.Pessoa;
import entities.Turno;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            String nomeEmpresa = "Jequitiba";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFundacao = dateFormat.parse("01/01/2000");
            double saldo = 100000.0;

            Empresa jequitiba = new Empresa(nomeEmpresa, dataFundacao, saldo);


            Turno turno = Turno.NOITE;
            Date dtNascimentoRoberto = dateFormat.parse("01/01/1987");

            Pessoa roberto = new Funcionario("Roberto Silva", "12312312312", dtNascimentoRoberto, "Industrial",
                    12900.50, "12343212", "Mecânico", turno);

            jequitiba.adicionarPessoas(roberto);

            jequitiba.informacoesFuncionarios();

        }
        catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
