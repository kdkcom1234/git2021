package com.git.myworkspace.opendata.air;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirSigunguHourId implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dataTime;
	private String sidoName;
	private String cityName;

}
