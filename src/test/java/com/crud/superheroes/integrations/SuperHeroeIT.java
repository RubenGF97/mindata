package com.crud.superheroes.integrations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.crud.superheroes.controllers.SuperheroeControllerImpl;
import com.crud.superheroes.dtos.SuperheroeRequest;
import com.crud.superheroes.dtos.SuperheroeResponse;
import com.crud.superheroes.handlers.BasedatosvaciaException;
import com.crud.superheroes.handlers.NoexistesuperheroeException;
import com.crud.superheroes.services.SuperheroeServiceImpl;


@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class SuperHeroeIT {

  @InjectMocks
  private SuperheroeControllerImpl controller;

  @Mock
  private SuperheroeServiceImpl service;

  private SuperheroeRequest request;
  private SuperheroeResponse response;
  private List<SuperheroeResponse> listaResponse;
  private NoexistesuperheroeException noExisteException;
  private BasedatosvaciaException bbddVaciaException;

  @BeforeEach
  void beforeEach() {
    request = new SuperheroeRequest();
    response = new SuperheroeResponse();
    listaResponse = new ArrayList<>();
    noExisteException = new NoexistesuperheroeException();
    bbddVaciaException = new BasedatosvaciaException();
  }

  @Test
  void alta_201_return_void() {
    request.setNombre("  Mortadelo  ");
    request.setFuerza(6);
    request.setResistencia(7);

    when(service.alta(Mockito.any())).thenReturn(ResponseEntity.created(null).build());

    ResponseEntity<Void> respAlta = controller.alta(request);
    assertEquals(HttpStatus.CREATED, respAlta.getStatusCode());
  }

  @Test
  void consulta_200_return_SuperheroeResponse() {
    response.setId((long) 1);
    response.setNombre("Mortadelo");
    response.setFuerza(6);
    response.setResistencia(7);

    when(service.consulta(Mockito.anyLong())).thenReturn(ResponseEntity.ok(response));

    ResponseEntity<SuperheroeResponse> respConsulta = controller.consulta((long) 1);
    assertEquals(HttpStatus.OK, respConsulta.getStatusCode());
  }

  @Test
  void consultaPorNombreParcial_200_return_list_SuperheroeResponse() {
    response.setId((long) 1);
    response.setNombre("Super Ruben");
    response.setFuerza(3);
    response.setResistencia(4);
    listaResponse.add(response);

    when(service.consultaPorNombreParcial(Mockito.anyString()))
        .thenReturn(ResponseEntity.ok(listaResponse));

    ResponseEntity<List<SuperheroeResponse>> respConsultaPorNombreParcial =
        controller.consultaPorNombreParcial("rub");
    assertEquals(HttpStatus.OK, respConsultaPorNombreParcial.getStatusCode());
  }

  @Test
  void listaTodos_200_return_list_SuperheroeResponse() {
    response.setId((long) 1);
    response.setNombre("Super Ruben");
    response.setFuerza(3);
    response.setResistencia(4);
    listaResponse.add(response);

    when(service.listaTodos()).thenReturn(ResponseEntity.ok(listaResponse));

    ResponseEntity<List<SuperheroeResponse>> respListaTodos = controller.listaTodos();
    assertEquals(HttpStatus.OK, respListaTodos.getStatusCode());
  }

  @Test
  void modificacion_204_return_void() {
    request.setNombre("  Mortadelo  ");
    request.setFuerza(6);
    request.setResistencia(7);

    when(service.modificacion(Mockito.anyLong(), Mockito.any()))
        .thenReturn(ResponseEntity.status(HttpStatus.NO_CONTENT).build());

    ResponseEntity<Void> respModificacion = controller.modificacion((long) 1, request);
    assertEquals(HttpStatus.NO_CONTENT, respModificacion.getStatusCode());
  }

  @Test
  void borrado_204_return_void() {
	when(service.borrado(Mockito.anyLong()))
	    .thenReturn(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	        
	ResponseEntity<Void> respBorrado = controller.borrado((long) 1);       
	assertEquals(HttpStatus.NO_CONTENT, respBorrado.getStatusCode());
  }

  @Test
  void consulta_404_NoexistesuperheroeException() throws NoexistesuperheroeException{
    when(service.consulta(Mockito.anyLong()))
        .thenThrow(new NoexistesuperheroeException());

    Throwable exception = assertThrows(NoexistesuperheroeException.class, () -> 
    controller.consulta((long) 1));        
    assertEquals(noExisteException.getMessage(), exception.getMessage());    
  }

  @Test
  void consultaPorNombreParcial_404_NoexistesuperheroeException() throws NoexistesuperheroeException {
    when(service.consultaPorNombreParcial(Mockito.anyString()))
        .thenThrow(new NoexistesuperheroeException());
    
    Throwable exception = assertThrows(NoexistesuperheroeException.class, () -> 
    controller.consultaPorNombreParcial("sup"));        
    assertEquals(noExisteException.getMessage(), exception.getMessage()); 
  }

  @Test
  void listaTodos_204_BasedatosvaciaException()  throws BasedatosvaciaException {
    when(service.listaTodos())
        .thenThrow(new BasedatosvaciaException());
    
    Throwable exception = assertThrows(BasedatosvaciaException.class, () -> 
    controller.listaTodos());        
    assertEquals(bbddVaciaException.getMessage(), exception.getMessage()); 
  }

}
