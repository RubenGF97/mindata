package com.crud.superheroes.utils;

import org.apache.commons.lang3.StringUtils;
import com.crud.superheroes.dtos.SuperheroeRequest;

public class SuperheroeUtils {

  static public SuperheroeRequest calidadRequest(SuperheroeRequest request) {
    if (!StringUtils.isEmpty(request.getNombre())) {
      request.setNombre(request.getNombre().trim());
    }
    return request;
  }
}
