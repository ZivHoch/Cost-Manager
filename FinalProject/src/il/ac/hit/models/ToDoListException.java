package il.ac.hit.models;

public class ToDoListException extends Exception
{

    public ToDoListException(String message) {
        super(message);
    }

    public ToDoListException(String message, Throwable cause) {
        super(message, cause);
    }


}
