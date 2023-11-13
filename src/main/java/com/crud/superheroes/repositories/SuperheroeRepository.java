package com.crud.superheroes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crud.superheroes.daos.SuperheroeDAO;

@Repository
public interface SuperheroeRepository extends JpaRepository<SuperheroeDAO, Long> {

  List<SuperheroeDAO> findByNombreContainsIgnoreCase(String nombreParcial);

}
