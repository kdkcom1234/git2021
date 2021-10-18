package com.git.myworkspace.opendata.covid;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidSidoDailyId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stdDay;
	private String gubun;

}
