package application;

import entities.*;
import entities.DataBase.DAO;
import entities.enumerator.Setor;
import entities.enumerator.Turno;
import swing.Mainframe;
import swing.telalogin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new telalogin().setVisible(true);
                }
            });

            DAO dataBase = new DAO(); //DataBase

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

            Setor setorFelipe = Setor.ESTOQUE;
            Date dtNascimentoFelipe = dateFormat.parse("17/04/2000");
            Turno turnoFelipe = Turno.TARDE;

            //Pessoa jose = new RH("José Silva", "12312312312", dtNascimentoJose, setorJose,
                    //12900.50, "12343212", "Administrador", turnoJose);

            //Pessoa felipe = new Funcionario("Felipe Souza", "78453423487", dtNascimentoFelipe, setorFelipe,
                    //13500.30, "127895423", "Contador de produtos", turnoFelipe);

            //((RH) jose).adicionarFuncionario(felipe);
            //((RH) jose).adicionarFuncionario(jose);

            System.out.println("Database: ");

            //((RH) jose).informacoesFuncionarios();
            System.out.println();

            //((RH) jose).alterarFuncionario("78453423487", 3500);

            //((RH) jose).informacoesFuncionarios();

            System.out.println();
            System.out.println("ATUALIZADO: ");
            System.out.println();
            //((RH) jose).infoFuncionario("78453423487");

            //((RH) jose).removerFuncionario("78453423487");
            //((RH) jose).removerFuncionario("12312312312");

            System.out.println(jequitiba);


        }
        catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (FuncionarioException e) {
            System.out.println("Error: " + e.getMessage());
        }
        /*catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }*/
    }
}
