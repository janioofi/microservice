package br.janioofi.msavaliadorcredito.domain.exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String msg){
        super(msg);
    }
}
