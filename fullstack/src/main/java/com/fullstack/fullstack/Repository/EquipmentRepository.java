package com.fullstack.fullstack.Repository;

import com.fullstack.fullstack.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findByName(String name);
}
