package com.crud.superheroes.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.crud.superheroes.dtos.SuperheroeRequest;
import com.crud.superheroes.dtos.SuperheroeResponse;

public interface SuperheroeController {
  public ResponseEntity<Void> alta(SuperheroeRequest superheroeRequest);
  public ResponseEntity<SuperheroeResponse> consulta(Long id);
  public ResponseEntity<List<SuperheroeResponse>> consultaPorNombreParcial(String nombreParcial);
  public ResponseEntity<List<SuperheroeResponse>> listaTodos();
  public ResponseEntity<Void> modificacion(Long id, SuperheroeRequest superheroeRequest);
  public ResponseEntity<Void> borrado(Long id);
}
