package payroll;

public class IdNotFoundException extends RuntimeException{


    String message;
    public IdNotFoundException(Long id) {
        super("Dude this id is not in the database"+ id);
        this.message = "Dude this id is not in the database"+ id;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
