package com.crud.superheroes.unitaries;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.crud.superheroes.daos.SuperheroeDAO;
import com.crud.superheroes.dtos.SuperheroeRequest;
import com.crud.superheroes.dtos.SuperheroeResponse;
import com.crud.superheroes.mappers.SuperheroeMapper;
import com.crud.superheroes.utils.SuperheroeUtils;

public class SuperHeroeUT {
  private SuperheroeMapper mapper;

  private SuperheroeRequest requestIn;
  private SuperheroeRequest requestOut;
  private SuperheroeRequest requestEsperado;
  private SuperheroeDAO daoIn;
  private SuperheroeDAO daoOut;
  private SuperheroeDAO daoEsperado;
  private SuperheroeDAO dao;
  private SuperheroeDAO reqDao;
  private SuperheroeResponse responseEsperado;
  private SuperheroeResponse responseOut;

  @BeforeEach
  void beforeEach() {
    mapper = SuperheroeMapper.INSTANCE;
    requestIn = new SuperheroeRequest();
    requestOut = new SuperheroeRequest();
    requestEsperado = new SuperheroeRequest();
    daoIn = new SuperheroeDAO();
    daoOut = new SuperheroeDAO();
    daoEsperado = new SuperheroeDAO();
    dao = new SuperheroeDAO();
    reqDao = new SuperheroeDAO();
    responseEsperado = new SuperheroeResponse();
    responseOut = new SuperheroeResponse();
  }

  @Test
  void validacion_calidad_datos_request() {
    requestIn.setNombre("  Mortadelo  ");
    requestIn.setFuerza(6);
    requestIn.setResistencia(7);

    requestEsperado.setNombre("Mortadelo");
    requestEsperado.setFuerza(6);
    requestEsperado.setResistencia(7);

    requestOut = SuperheroeUtils.calidadRequest(requestIn);
    assertEquals(requestEsperado, requestOut);
  }

  @Test
  void validacion_mapper_Request_a_DAO() {
    requestIn.setNombre("Mortadelo");
    requestIn.setFuerza(6);
    requestIn.setResistencia(7);

    daoEsperado.setNombre("Mortadelo");
    daoEsperado.setFuerza(6);
    daoEsperado.setResistencia(7);

    daoOut = mapper.toDAO(requestIn);
    assertEquals(daoEsperado, daoOut);
  }

  @Test
  void validacion_mapper_DAO_a_Response() {
    daoIn.setId((long) 1);
    daoIn.setNombre("Mortadelo");
    daoIn.setFuerza(6);
    daoIn.setResistencia(7);

    responseEsperado.setId((long) 1);
    responseEsperado.setNombre("Mortadelo");
    responseEsperado.setFuerza(6);
    responseEsperado.setResistencia(7);

    responseOut = mapper.toResponse(daoIn);
    assertEquals(responseEsperado, responseOut);
  }

  @Test
  void validacion_mapper_DAO_a_NewDAO() {
    dao.setId((long) 1);
    dao.setNombre("Mortadelo");
    dao.setFuerza(6);
    dao.setResistencia(7);

    reqDao.setNombre("Filemon");
    reqDao.setFuerza(1);
    reqDao.setResistencia(2);

    daoEsperado.setId((long) 1);
    daoEsperado.setNombre("Filemon");
    daoEsperado.setFuerza(1);
    daoEsperado.setResistencia(2);

    daoOut = mapper.toNewDAO(dao, reqDao);
    assertEquals(daoEsperado, daoOut);
  }
}
