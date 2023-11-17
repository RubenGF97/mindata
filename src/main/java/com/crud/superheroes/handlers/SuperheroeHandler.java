package com.crud.superheroes.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class SuperheroeHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = NoexistesuperheroeException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Superheroe no encontrado")
  public void noExisteSuperHeroe(NoexistesuperheroeException e) {}

  @ExceptionHandler(value = BasedatosvaciaException.class)
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Base de datos vacia")
  public void baseDatosVacia(BasedatosvaciaException e) {}

  @ExceptionHandler(value = DuplicadoException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Super Heroe duplicado")
  public void duplicado(DuplicadoException e) {}

   

}
