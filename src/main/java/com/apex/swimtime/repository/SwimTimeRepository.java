package com.apex.swimtime.repository;

import com.apex.swimtime.model.SwimTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SwimTimeRepository extends JpaRepository<SwimTime, Integer> {

    @Query(value = "SELECT timeID FROM SwimTime WHERE swimmerID = :swimmerID", nativeQuery = true)
    List<Integer> findBySwimmerID(Integer swimmerID);


    @Query(value = "SELECT timeID FROM SwimTime WHERE "  +
                    "(:swimmerID is null or swimmerID = :swimmerID) AND " +
                    "(:stroke is null or stroke = :stroke) AND " +
                    "(:distance is null or distance = :distance)",
                    nativeQuery = true)
    List<Integer> findTimes(Integer swimmerID, Integer stroke, Integer distance);

    @Query(value = "SELECT timeID FROM SwimTime WHERE "  +
            "timeID in :timeIDs and  date >= :startDate AND date <= :endDate",
            nativeQuery = true)
    List<Integer> findTimesWithDateRange(List<Integer> timeIDs, Date startDate, Date endDate);

    @Query(value = "SELECT timeID FROM SwimTime WHERE "  +
            "(:swimmerID is null or swimmerID = :swimmerID) AND " +
            "(:stroke is null or stroke = :stroke) AND " +
            "(:distance is null or distance = :distance) AND " +
            "(:startDate is null or date = :startDate)",
            nativeQuery = true)
    List<Integer> findTimesWithDate(Integer swimmerID, Integer stroke, Integer distance, Date startDate);
}
