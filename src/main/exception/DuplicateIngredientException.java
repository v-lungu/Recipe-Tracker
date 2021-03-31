package exception;

public class DuplicateIngredientException extends Exception{
    public DuplicateIngredientException(String msg) {
        super(msg);
    }
}
