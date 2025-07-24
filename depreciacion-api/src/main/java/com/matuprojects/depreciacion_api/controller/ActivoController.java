package com.matuprojects.depreciacion_api.controller;

import com.matuprojects.depreciacion_api.model.Activo;
import com.matuprojects.depreciacion_api.repository.ActivoRepository;
import com.matuprojects.depreciacion_api.service.ActivoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class ActivoController {

    @Autowired
    private ActivoService activoService;

    @PostMapping
    public ResponseEntity<Activo> registrarActivo(@RequestBody Activo activo)
    {
        Activo nuevoActivo = activoService.registro(activo);
        return ResponseEntity.ok(nuevoActivo);
    }

    @GetMapping
    public ResponseEntity<List<Activo>> listarActivos()
    {
        List<Activo> activos = activoService.todosLosActivos();
        return ResponseEntity.ok(activos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Activo>obtenerActivoPorId(@PathVariable Long id)
    {
        Optional<Activo> activoOpt = activoService.consultarActivoPorId(id);
        return activoOpt.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activo> actualizarActivo(@PathVariable Long id,
                                                   @RequestBody Activo activoActualizado) {
        Optional<Activo> existente = activoService.consultarActivoPorId(id);
        if (existente.isPresent()) {
            Activo actualizado = activoService.ModificarActivo(activoActualizado);
            return ResponseEntity.ok(actualizado);

        } else {
            return ResponseEntity.notFound().build();

        }
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarActivo(@PathVariable Long id) {
        if (activoService.consultarActivoPorId(id).isPresent()) {
            activoService.eliminarActivo(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/calcular/{id}")
    public ResponseEntity<BigDecimal> calcularDepreciaciones(@PathVariable Long id)
    {
        Optional <Activo> activoOpt = activoService.consultarActivoPorId(id);
        if(activoOpt.isPresent())
        {
            Activo activo = activoOpt.get();
            BigDecimal resultado = activoService.calcularDepreciacionMensual(
                    activo.getValorInicial(),
                    activo.getValorResidual(),
                    activo.getVidaUtil());

         return ResponseEntity.ok(resultado);
        }else
        {
            return ResponseEntity.notFound().build();
        }
    }



}
