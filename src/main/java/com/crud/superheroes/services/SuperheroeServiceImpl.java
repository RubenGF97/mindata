package com.crud.superheroes.services;


import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.crud.superheroes.daos.SuperheroeDAO;
import com.crud.superheroes.dtos.SuperheroeRequest;
import com.crud.superheroes.dtos.SuperheroeResponse;
import com.crud.superheroes.handlers.BasedatosvaciaException;
import com.crud.superheroes.handlers.NoexistesuperheroeException;
import com.crud.superheroes.mappers.SuperheroeMapper;
import com.crud.superheroes.repositories.SuperheroeRepository;



@Service
public class SuperheroeServiceImpl implements SuperheroeService {
  @Autowired
  private SuperheroeRepository repository;

  @Autowired
  private SuperheroeMapper mapper;
  
  @Override
  public ResponseEntity<Void> alta(SuperheroeRequest request) {
    SuperheroeDAO dao = mapper.toDAO(request);
    repository.save(dao);
    return ResponseEntity.created(null).build();
  }
  @Override
  public ResponseEntity<SuperheroeResponse> consulta(long id) {
    SuperheroeDAO dao = repository.findById(id).orElse(null);
    if (Objects.isNull(dao)) {
      throw new NoexistesuperheroeException();
    }
    return ResponseEntity.ok(mapper.toResponse(dao));
  }
  @Override
  public ResponseEntity<List<SuperheroeResponse>> consultaPorNombreParcial(String nombreParcial) {
    List<SuperheroeDAO> daos = repository.findByNombreContainsIgnoreCase(nombreParcial);
    if (CollectionUtils.isEmpty(daos)) {
      throw new NoexistesuperheroeException();
    }
    return ResponseEntity.ok(mapper.toListaResponse(daos));
  }
  @Override
  public ResponseEntity<List<SuperheroeResponse>> listaTodos() {
    List<SuperheroeDAO> daos = repository.findAll();
    if (CollectionUtils.isEmpty(daos)) {
      throw new BasedatosvaciaException();
    }
    return ResponseEntity.ok(mapper.toListaResponse(daos));
  }
  @Override
  public ResponseEntity<Void> borrado(long id) {
    repository.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
  @Override
  public ResponseEntity<Void> modificacion(long id, SuperheroeRequest request) {
    SuperheroeDAO dao = repository.findById(id).orElse(null);
    if (Objects.isNull(dao)) {
      throw new NoexistesuperheroeException();
    }
    repository.save(mapper.toNewDAO(dao, mapper.toDAO(request)));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
