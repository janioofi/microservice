package br.janioofi.msavaliadorcredito.domain.exceptions;

public class ErrorSolicitacaoCartaoException extends RuntimeException{
    public ErrorSolicitacaoCartaoException(String msg) {
        super(msg);
    }
}
