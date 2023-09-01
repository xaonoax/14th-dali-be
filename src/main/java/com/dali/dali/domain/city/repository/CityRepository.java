package com.dali.dali.domain.city.repository;

import com.dali.dali.domain.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT DISTINCT c.sigungu FROM City c WHERE c.sido = :sido")
    List<String> findDistinctSigunguBySido(@Param("sido") String sido);

    @Query("SELECT DISTINCT c.dong FROM City c WHERE c.sido =:sido AND c.sigungu = :sigungu")
    List<String> findDistinctDongBySigungu(@Param("sido") String sido, @Param("sigungu") String sigungu);
}
