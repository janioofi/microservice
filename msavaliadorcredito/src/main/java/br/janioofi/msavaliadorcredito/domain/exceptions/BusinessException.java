package br.janioofi.msavaliadorcredito.domain.exceptions;

import lombok.Getter;

public class BusinessException extends RuntimeException{

    @Getter
    private Integer status;

    public BusinessException(String msg, Integer status){
        super(msg);
        this.status = status;
    }

}
