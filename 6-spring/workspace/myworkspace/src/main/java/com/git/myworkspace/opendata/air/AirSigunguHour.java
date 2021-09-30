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
	private String sidoName; // �е��� Ŀ���� 5%, �������� Ŀ���� 20, �ε��� ����
	private String cityName;
	// ��
	private String pm10Value;
	private String pm25Value;
}
