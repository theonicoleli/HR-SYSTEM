package entities;

public class FuncionarioException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public FuncionarioException(String message) {
        super(message);
    }
}
