package com.crud.superheroes.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.crud.superheroes.dtos.SuperheroeRequest;
import com.crud.superheroes.dtos.SuperheroeResponse;

public interface SuperheroeService {  
  public ResponseEntity<Void> alta(SuperheroeRequest request);
  public ResponseEntity<SuperheroeResponse> consulta(long id); 
  public ResponseEntity<List<SuperheroeResponse>> consultaPorNombreParcial(String nombreParcial);
  public ResponseEntity<List<SuperheroeResponse>> listaTodos(); 
  public ResponseEntity<Void> borrado(long id); 
  public ResponseEntity<Void> modificacion(long id, SuperheroeRequest request); 
}
