package com.matuprojects.depreciacion_api.service;


import com.matuprojects.depreciacion_api.model.Activo;
import com.matuprojects.depreciacion_api.repository.ActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ActivoService {

    @Autowired
    private ActivoRepository activoRepository;

    //Registrar un activo
    public Activo registro (Activo activo)
    {
        return activoRepository.save(activo);
    }

    // Listar los Activos Registrados

    public List<Activo> todosLosActivos()
    {
        return activoRepository.findAll();
    }

     // Consultar activos por el id
     public Optional<Activo> consultarActivoPorId(Long id)
     {
         return activoRepository.findById(id);
     }

     // Hacer modificacion a los activos
     public Activo ModificarActivo(Activo activo)
     {
         return activoRepository.save(activo);
     }

     // Eliminar un activo
     public void eliminarActivo(Long id)
     {
          activoRepository.deleteById(id);
     }

     //Calcular depreciacion o amortizacion
     public BigDecimal calcularDepreciacionMensual(BigDecimal valorInicial,
                                                   BigDecimal valorResidual,
                                                   int vidaUtil) {
         if (vidaUtil <= 0) {
             throw new IllegalArgumentException("La vida útil debe ser mayor a cero.");
         }

         return valorInicial.subtract(valorResidual)
                 .divide(BigDecimal.valueOf(vidaUtil), 2, RoundingMode.HALF_UP);
     }

}
