package com.crud.superheroes.handlers;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BasedatosvaciaException extends RuntimeException {
  private static final long serialVersionUID = 81912197L;
  private static final String MENSAJE = "Base de datos vacia";

  public BasedatosvaciaException() {
    super(MENSAJE);
  }
}
