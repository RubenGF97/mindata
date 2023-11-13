package com.crud.superheroes.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.crud.superheroes.dtos.SuperheroeRequest;
import com.crud.superheroes.dtos.SuperheroeResponse;
import com.crud.superheroes.services.SuperheroeService;
import com.crud.superheroes.utils.SuperheroeUtils;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping({"${superheroes.path}${superheroes.version}"})
public class SuperheroeControllerImpl implements SuperheroeController {

  @Autowired
  private SuperheroeService service;
  
  @Override 
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> alta(@Valid @RequestBody SuperheroeRequest superheroeRequest) {
    return service.alta(SuperheroeUtils.calidadRequest(superheroeRequest));
  }
  @Override 
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SuperheroeResponse> consulta(
      @PathVariable(name = "id", required = true) Long id) {
    return service.consulta(id);
  }
  @Override 
  @GetMapping(value = "/nombre/{nombreParcial}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<SuperheroeResponse>> consultaPorNombreParcial(
      @PathVariable(name = "nombreParcial", required = true) String nombreParcial) {
    return service.consultaPorNombreParcial(nombreParcial);
  }
  @Override 
  @GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<SuperheroeResponse>> listaTodos() {
    return service.listaTodos();
  }
  @Override 
  @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> modificacion(@PathVariable(name = "id", required = true) Long id,
      @RequestBody SuperheroeRequest superheroeRequest) {
    return service.modificacion(id, SuperheroeUtils.calidadRequest(superheroeRequest));
  }
  @Override 
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> borrado(@PathVariable(name = "id", required = true) Long id) {
    return service.borrado(id);
  }

}
