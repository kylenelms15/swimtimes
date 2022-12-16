package com.apex.swimtime.repository;

import com.apex.swimtime.constants.StrokeEnum;
import com.apex.swimtime.constants.SwimTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SwimTimeRepository extends JpaRepository<SwimTime, Integer> {

    @Query(value = "SELECT timeID FROM SwimTime WHERE swimmerID = :swimmerID", nativeQuery = true)
    List<Integer> findBySwimmerID(Integer swimmerID);

    @Query(value = "SELECT timeID FROM SwimTime WHERE (stroke = :stroke) AND (distance = :distance)", nativeQuery = true)
    List<Integer> findByEvent(Integer stroke, Integer distance);
}
