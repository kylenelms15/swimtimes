package com.apex.swimtime.repository;

import com.apex.swimtime.model.SwimTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SwimTimeRepository extends JpaRepository<SwimTime, Integer> {

    @Query(value = "SELECT timeID FROM SwimTime WHERE swimmerID = :swimmerID", nativeQuery = true)
    List<Integer> findBySwimmerID(Integer swimmerID);

    @Query(value = "SELECT timeID FROM SwimTime WHERE (stroke = :stroke) AND (distance = :distance)", nativeQuery = true)
    List<Integer> findByEvent(Integer stroke, Integer distance);

    @Query(value = "SELECT timeID FROM SwimTime WHERE "  +
                    "(:swimmerID is null or swimmerID = :swimmerID) AND " +
                    "(:stroke is null or stroke = :stroke) AND " +
                    "(:distance is null or distance = :distance)",
                    nativeQuery = true)
    List<Integer> findTime(Integer swimmerID, Integer stroke, Integer distance);
}
