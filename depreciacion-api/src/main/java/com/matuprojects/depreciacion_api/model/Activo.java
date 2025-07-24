package com.matuprojects.depreciacion_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "activos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo de activo no puede ser nulo")
    private TipoActivo tipoActivo;

    @NotNull(message = "El valor inicial no puede ser nulo")
    private BigDecimal valorInicial;

    @NotNull(message = "La vida útil no puede ser nula")
    private Integer vidaUtil;

    private BigDecimal valorResidual;

    @NotNull(message = "La fecha de adquisición no puede ser nula")
    private LocalDate fechaAdquisicion;
}
