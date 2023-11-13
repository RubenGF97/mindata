package com.crud.superheroes.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperheroeRequest {
  @NotNull
  @Size(min = 1)
  private String nombre;
  @NotNull
  @Min(1)
  @Max(10)
  private Integer fuerza;
  @NotNull
  @Min(1)
  @Max(10)
  private Integer resistencia;
}
