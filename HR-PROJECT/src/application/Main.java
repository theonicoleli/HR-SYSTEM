package application;

import entities.*;

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


            Turno turnoRoberto = Turno.NOITE;
            Setor setorRoberto = Setor.INDUSTRIAL;
            Date dtNascimentoRoberto = dateFormat.parse("01/01/1987");

            Setor setorJose = Setor.RH;
            Date dtNascimentoJose = dateFormat.parse("05/08/1999");
            Turno turnoJose = Turno.MANHA;

            Pessoa roberto = new Funcionario("Roberto Silva", "12312312312", dtNascimentoRoberto, setorRoberto,
                    8567.50, "12343212", "Mecânico", turnoRoberto);

            Pessoa jose = new RH("Roberto Silva", "12312312312", dtNascimentoJose, setorJose,
                    12900.50, "12343212", "Administrador", turnoJose);

            jequitiba.adicionarPessoas(roberto);
            jequitiba.adicionarPessoas(jose);


            ((RH) jose).informacoesFuncionarios(jequitiba);
            jequitiba.informacoesFuncionarios();

        }
        catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
