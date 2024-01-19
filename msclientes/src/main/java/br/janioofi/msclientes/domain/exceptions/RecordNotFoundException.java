package br.janioofi.msclientes.domain.exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String msg){
        super(msg);
    };

}
