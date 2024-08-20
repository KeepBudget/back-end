package kb.KeepBudget.utils.exceptions;

public class DistrictNotExistException extends RuntimeException{

    private String message;

    public DistrictNotExistException(){
        this.message = "District is not existed";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
