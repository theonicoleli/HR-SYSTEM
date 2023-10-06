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

            Setor setorRicardo = Setor.ESTOQUE;
            Date dtNascimentoRicardo = dateFormat.parse("17/04/2000");
            Turno turnoRicardo = Turno.TARDE;

            Pessoa roberto = new Funcionario("Roberto Silva", "12312312312", dtNascimentoRoberto,
                    setorRoberto, 8567.50, "12343212", "Mecânico", turnoRoberto);

            Pessoa jose = new RH("José Silva", "12312312312", dtNascimentoJose, setorJose,
                    12900.50, "12343212", "Administrador", turnoJose);

            jequitiba.adicionarPessoas(roberto);
            jequitiba.adicionarPessoas(jose);

            ((RH) jose).adicionarFuncionario(jequitiba, "Ricardo Silva", "12312312312", dtNascimentoRicardo,
                    setorRicardo, 6754.76, "12343212", "Estoquista", turnoRicardo);

            ((RH) jose).informacoesFuncionarios(jequitiba);

            ((RH) jose).removerFuncionario(jequitiba, "Ricardo Silva");

            ((RH) jose).alterarFuncionario(jequitiba, "Roberto Silva", 10000);

            System.out.println();
            System.out.println("ATUALIZADO: ");
            System.out.println();

            jequitiba.informacoesFuncionarios();

        }
        catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
