package com.git.myworkspace.opendata.covid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = @Index(name = "idx_covid_sido_daily_1", columnList = "stdDay, gubun"))
@IdClass(CovidSidoDailyId.class)
public class CovidSidoDaily {

	// Id
	@Id
	private String stdDay;

	@Id
	@Column(columnDefinition = "varchar(255) collate \"ko_KR.utf8\"")
	private String gubun;

	// value
	private String gubunEn;
	private String overFlowCnt;
	private String localOccCnt;

}
