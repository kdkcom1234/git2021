package com.git.myworkspace.opendata.covid;

import java.util.List;

import lombok.Data;

@Data
public class CovidSidoDailyResponse {
	private Response response;

	@Data
	public class Response {
		private Header header;
		private Body body;
	}

	@Data
	public class Header {
		private String resultCode;
		private String resultMsg;
	}

	@Data
	public class Body {
		private Items items;
	}

	@Data
	public class Items {
		private List<Item> item;
	}

	@Data
	public class Item {
		// 기준일, (도시)구분, 해외유입, 지역발생
		// 필수
		private String stdDay;
		private String gubun;
		private String gubunEn;
		private String overFlowCnt;
		private String localOccCnt;
		// 선택
		private String defCnt;
		private String deathCnt;
		private String incDec;
	}

//    <deathCnt>15</deathCnt>			 	사망자수
//    <defCnt>6120</defCnt> 				확진자수
//    <gubun>검역</gubun> 					도시구분 **
//    <gubunEn>Lazaretto</gubunEn>			구분영어 *
//    <incDec>9</incDec>   					전일대비 증감수

//    <isolClearCnt>5917</isolClearCnt> 	격리해제수
//    <isolIngCnt>188</isolIngCnt>			격리중 환자수

//    <localOccCnt>0</localOccCnt>			지역발생수 **
//    <overFlowCnt>9</overFlowCnt>			해외 유입수 **
//    <stdDay>2021년 10월 04일 00시</stdDay>	기준일시 **

}
