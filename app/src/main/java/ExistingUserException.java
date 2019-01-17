class ExistingUserException extends Exception {

    public String message ;

    public ExistingUserException(){
        super();
    }
    public ExistingUserException(String message){
        this.message = message;
    }
}

