package com.crud.superheroes.handlers;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoexistesuperheroeException extends RuntimeException {
  private static final long serialVersionUID = 23531538L;
  private static final String MENSAJE = "Super Heroe no encontrado";

  public NoexistesuperheroeException() {
    super(MENSAJE);
  }
}
