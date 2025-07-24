package com.matuprojects.depreciacion_api.service;

import com.matuprojects.depreciacion_api.model.Activo;
import com.matuprojects.depreciacion_api.model.TipoActivo;
import com.matuprojects.depreciacion_api.repository.ActivoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ActivoServiceTest {

    @Mock
    private ActivoRepository activoRepository;

    @InjectMocks
    private ActivoService activoService;

    private Activo activoBase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        activoBase = new Activo();
        activoBase.setId(1L);
        activoBase.setTipoActivo(TipoActivo.Tangible);
        activoBase.setNombre("Computador");
        activoBase.setFechaAdquisicion(LocalDate.of(2023, 7, 6));
        activoBase.setValorInicial(new BigDecimal("3000000"));
        activoBase.setVidaUtil(5);
        activoBase.setValorResidual(new BigDecimal("300000"));
    }

    @Test
    public void testRegistro()
    {
        Activo activo = new Activo();
        activo.setId(1L);
        activo.setTipoActivo(TipoActivo.valueOf("Intangible"));
        activo.setNombre("Patente de software");
        activo.setFechaAdquisicion(LocalDate.of(22, 8, 6));
        activo.setValorInicial(new BigDecimal("50000000"));
        activo.setVidaUtil(5);
        activo.setValorResidual(new BigDecimal("3000000"));

        when(activoRepository.save(activo)).thenReturn(activo);

        Activo resultado = activoService.registro(activo);

        assertEquals(activo.getId(),resultado.getId());
        assertEquals(activo.getTipoActivo(),resultado.getTipoActivo());
        assertEquals(activo.getNombre(),resultado.getNombre());
        assertEquals(activo.getFechaAdquisicion(),resultado.getFechaAdquisicion());
        assertEquals(activo.getValorInicial(),resultado.getValorInicial());
        assertEquals(activo.getVidaUtil(),resultado.getVidaUtil());
        assertEquals(activo.getValorResidual(),resultado.getValorResidual());

    }

    @Test
    public void todosLosActivos()
    {
        Activo activo = new Activo();
        activo.setId(1L);
        activo.setTipoActivo(TipoActivo.valueOf("Intangible"));
        activo.setNombre("Patente de software");
        activo.setFechaAdquisicion(LocalDate.of(22, 8, 6));
        activo.setValorInicial(new BigDecimal("50000000"));
        activo.setVidaUtil(5);
        activo.setValorResidual(new BigDecimal("3000000"));

        when(activoRepository.findAll()).thenReturn(List.of(activoBase,activo));

        List <Activo> resultado = activoService.todosLosActivos();

        assertEquals(2,resultado.size());
        assertTrue(resultado.contains(activoBase));
        assertTrue(resultado.contains(activo));
        verify(activoRepository).findAll();

    }

    @Test
    public void testconsultarActivoPorId()
    {
        Long idBuscado = activoBase.getId();
        when(activoRepository.findById(idBuscado)).thenReturn(Optional.of(activoBase));


        Optional <Activo> resultadoOptional = activoService.consultarActivoPorId(idBuscado);

        assertTrue(resultadoOptional.isPresent());
        Activo resultado = resultadoOptional.get();

        assertEquals(activoBase.getId(),resultado.getId());
        assertEquals(activoBase.getTipoActivo(),resultado.getTipoActivo());
        assertEquals(activoBase.getNombre(),resultado.getNombre());
        assertEquals(activoBase.getFechaAdquisicion(),resultado.getFechaAdquisicion());
        assertEquals(activoBase.getValorInicial(),resultado.getValorInicial());
        assertEquals(activoBase.getVidaUtil(),resultado.getVidaUtil());
        assertEquals(activoBase.getValorResidual(),resultado.getValorResidual());

    }

    @Test
    public void testModificarActivo() {
        long idBuscado = activoBase.getId();


        Activo modificaciones = new Activo();
        modificaciones.setId(idBuscado);
        modificaciones.setTipoActivo(TipoActivo.Tangible);
        modificaciones.setNombre("Computador");
        modificaciones.setFechaAdquisicion(LocalDate.of(2023, 7, 6));
        modificaciones.setValorInicial(new BigDecimal("38000000"));
        modificaciones.setVidaUtil(18);
        modificaciones.setValorResidual(new BigDecimal("250000"));


        Optional<Activo> resultadoOptional = activoService.consultarActivoPorId(idBuscado);
        assertTrue(resultadoOptional.isPresent());

        Activo activoOriginal = resultadoOptional.get();


        Activo activoModificado = activoService.ModificarActivo(modificaciones);


        assertNotEquals(activoModificado, activoOriginal); // Se modificó
    }

    @Test
    public void testEliminarActivo()
    {
        Long idBuscado = activoBase.getId();

        Optional <Activo> activoBuscado = activoService.consultarActivoPorId(idBuscado);
        assertTrue(activoBuscado.isPresent());

        activoService.eliminarActivo(idBuscado);

        Optional <Activo> eliminado = activoService.consultarActivoPorId(idBuscado);
        assertFalse(eliminado.isPresent());

    }

    @Test
    public void testcalcularDepreciacionMensual()
    {
        //valores utilizados, valor compra 3000000, valor residual 300000, meses 5

        BigDecimal resultado = activoService.calcularDepreciacionMensual
                (activoBase.getValorInicial(),
                        activoBase.getValorResidual(),
                        activoBase.getVidaUtil());

        BigDecimal esperado = new BigDecimal("540000");
        assertEquals(resultado,esperado);

    }


}
