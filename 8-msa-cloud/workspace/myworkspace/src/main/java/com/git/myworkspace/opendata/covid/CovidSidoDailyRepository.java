package com.git.myworkspace.opendata.covid;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidSidoDailyRepository extends JpaRepository<CovidSidoDaily, Long> {

	List<CovidSidoDaily> findByGubun(Pageable page, String gubun);

}
