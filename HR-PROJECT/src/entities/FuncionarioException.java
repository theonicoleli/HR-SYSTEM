package entities;

public class FuncionarioException extends RuntimeException{

    public FuncionarioException(String message) {
        System.out.println(message);
    }
}
