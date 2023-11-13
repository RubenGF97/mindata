package com.crud.superheroes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperheroeResponse {
  private Long id;
  private String nombre;
  private Integer fuerza;
  private Integer resistencia;
}
