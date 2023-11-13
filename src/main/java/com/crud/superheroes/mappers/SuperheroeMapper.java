package com.crud.superheroes.mappers;

import java.util.List;
import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.crud.superheroes.daos.SuperheroeDAO;
import com.crud.superheroes.dtos.SuperheroeRequest;
import com.crud.superheroes.dtos.SuperheroeResponse;

@Mapper(componentModel = "spring")
public interface SuperheroeMapper {

  SuperheroeMapper INSTANCE = Mappers.getMapper(SuperheroeMapper.class);

  @Mapping(source = "request.nombre", target = "nombre")
  @Mapping(source = "request.fuerza", target = "fuerza")
  @Mapping(source = "request.resistencia", target = "resistencia")
  public SuperheroeDAO toDAO(SuperheroeRequest request);

  @Mapping(source = "dao.id", target = "id")
  @Mapping(source = "dao.nombre", target = "nombre")
  @Mapping(source = "dao.fuerza", target = "fuerza")
  @Mapping(source = "dao.resistencia", target = "resistencia")
  public SuperheroeResponse toResponse(SuperheroeDAO dao);

  @Mapping(source = "daos.id", target = "id")
  @Mapping(source = "daos.nombre", target = "nombre")
  @Mapping(source = "daos.fuerza", target = "fuerza")
  @Mapping(source = "daos.resistencia", target = "resistencia")
  public List<SuperheroeResponse> toListaResponse(List<SuperheroeDAO> daos);

  default SuperheroeDAO toNewDAO(SuperheroeDAO dao, SuperheroeDAO reqDao) {
    SuperheroeDAO newDao = new SuperheroeDAO();
    newDao.setId(dao.getId());
    newDao.setNombre(dao.getNombre());
    newDao.setFuerza(dao.getFuerza());
    newDao.setResistencia(dao.getResistencia());

    if (!Objects.isNull(reqDao.getNombre())) {
      newDao.setNombre(reqDao.getNombre());
    }
    if (!Objects.isNull(reqDao.getFuerza())) {
      newDao.setFuerza(reqDao.getFuerza());
    }
    if (!Objects.isNull(reqDao.getResistencia())) {
      newDao.setResistencia(reqDao.getResistencia());
    }
    return newDao;
  }

}
