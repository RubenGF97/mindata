package com.crud.superheroes.handlers;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DuplicadoException extends RuntimeException {
  private static final long serialVersionUID = 23531538L;
  private static final String MENSAJE = "Super Heroe no encontrado";

  public DuplicadoException() {
    super(MENSAJE);
  }
}
