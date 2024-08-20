package kb.KeepBudget.utils.exceptions;

public class DuplicateNicknameException extends RuntimeException{

    private String message;

    public DuplicateNicknameException(){
        this.message = "Nickname is duplicated";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
