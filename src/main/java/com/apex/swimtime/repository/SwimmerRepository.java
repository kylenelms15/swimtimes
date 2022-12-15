package com.apex.swimtime.repository;

import com.apex.swimtime.constants.Swimmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwimmerRepository extends JpaRepository<Swimmer, Integer> {
}
