package com.matuprojects.depreciacion_api.repository;

import com.matuprojects.depreciacion_api.model.Activo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivoRepository extends JpaRepository<Activo,Long> {

}
