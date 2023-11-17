package com.crud.superheroes.repositories;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import com.crud.superheroes.daos.SuperheroeDAO;
import com.crud.superheroes.handlers.DuplicadoException;

public interface SuperheroeRepositoryCache extends SuperheroeRepository {

  default void alta(SuperheroeDAO dao) {
    if (detectarDuplicados(cargarCache(), dao.getNombre())) {
      throw new DuplicadoException();
    }
    save(dao);
    actualizarCache();
  }

  default void borrado(Long id) {
    deleteById(id);
    actualizarCache();
  }

  private Boolean detectarDuplicados(List<SuperheroeDAO> daos, String nombre) {
    return daos.stream().anyMatch(a -> a.getNombre().equalsIgnoreCase(nombre));
  }

  @Cacheable(value = "todos")
  private List<SuperheroeDAO> cargarCache() {
    return findAll();
  }

  @CacheEvict(value = "todos")
  private void borrarcache() {}

  @CachePut(value = "todos")
  private List<SuperheroeDAO> actualizarCache() {
    return findAll();
  }

}
