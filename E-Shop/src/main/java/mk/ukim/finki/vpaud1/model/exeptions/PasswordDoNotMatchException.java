package mk.ukim.finki.vpaud1.model.exeptions;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException() {
        super("Passwords do not match exception");
    }
}
