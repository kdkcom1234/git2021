package com.git.myworkspace.opendata.air;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = @Index(name = "idx_air_sigungu_hour_1", columnList = "sidoName, cityName"))
public class AirSigunguHour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String dataTime;
	private String sidoName; // 밀도가 커봤자 5%, 분포도가 커봤자 20, 인덱스 제외
	private String cityName;
	// 값
	private String pm10Value;
	private String pm25Value;
}
