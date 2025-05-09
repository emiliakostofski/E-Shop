package mk.ukim.finki.vpaud1.model.exeptions;

public class InvalidUserCredentialsExeption extends RuntimeException{
    public InvalidUserCredentialsExeption() {
        super("Invalid User Credentials Exeption");
    }
}
