package kb.KeepBudget.utils.exceptions;

public class DuplicateNicknameException extends RuntimeException{

    private String message;

    public DuplicateNicknameException(){
        this.message = "이미 등록된 닉네임입니다.";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
