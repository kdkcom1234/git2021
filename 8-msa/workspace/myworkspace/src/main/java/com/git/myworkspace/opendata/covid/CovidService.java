package com.git.myworkspace.opendata.covid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class CovidService {

	// 서비스키
	private final String SERVICE_KEY = "itBvLQRBMyMkMogfGhCxuSntdkkbcJtmhySH2jR9GD%2FcayyuW4%2FprkAKC0YvgzVTgbo8732gHiA%2Fo%2BZFWZrZAg%3D%3D";

	// 의존성주입
	private CovidSidoDailyRepository repo;

	@Autowired
	public CovidService(CovidSidoDailyRepository repo) {
		this.repo = repo;
	}

	// 시도별 코로나 상황 일별조회
	// 초:0-59 | 분:0-59 | 시:0-23 | 일:1-31 | 월:1-12 | 요일:0-6 | 연:생략가능 2021
	// @Scheduled(cron="초 분 시 일 월 (요일) 년")
	// 매일 오전 10시 5분 스케줄 실행, 데이터 수집
//	@Scheduled(cron = "0 5 10 * * *")

// 샘플데이터
	@Scheduled(fixedRate = 1000 * 60 * 60 * 1)

	// 가장 최신의 데이터를 불러와야 하니까 데이터가 맞물리지 않도록 기존 조회한 데이터 삭제
	// 해당 캐시이름의 모든 키를 삭제, value = 캐시이름
	@CacheEvict(value = "covid-daily", allEntries = true)
	public void requestCovidSidoDaily() throws IOException {

		/* ---------------------- 데이터 요청하고 XML 받아오기 시작 ----------------- */

		// 1. 요청 URL 만들기
		StringBuilder builder = new StringBuilder();
		builder.append("http://openapi.data.go.kr/openapi"); // 호스트 / 게이트웨이
		builder.append("/service/rest/Covid19"); // 서비스
		builder.append("/getCovid19SidoInfStateJson"); // 기능 (코로나19 시도별 감염 상황 json)
		builder.append("?serviceKey=" + SERVICE_KEY); // 서비스키
		builder.append("&pageNo=1&numOfRows=10"); // 페이지 번호 1번 / 게시물 개수 10개
		builder.append("&startCreateDt=20211001"); // 검색할 생성일 범위 시작 , 필수는 아닌데 start 나 end 하나는 있어야 데이터 조회됨

//		System.out.println(builder.toString());

		// 2. URL 객체 생성
		URL url = new URL(builder.toString());

		// 3. Http로 접속하려면 url를 http 접속용 객체로 바꿔야함
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[] 배열로 데이터를 읽어옴
		byte[] result = con.getInputStream().readAllBytes();

		// 5. byte[] -> 문자열 (XML) 변환
		String data = new String(result, "UTF-8");

		/* ---------------------- 데이터 요청하고 XML 받아오기 끝 ----------------- */

		/* ---------------------- XML -> JSON -> Object(Java) 시작 ----------------- */

		// XML(문자열) -> JSON(문자열)
		String json = XML.toJSONObject(data).toString(2); // 왜 2개???
//		System.out.println(json);

		// JSON(문자열) -> Java(object) 객체
		CovidSidoDailyResponse response = new Gson().fromJson(json, CovidSidoDailyResponse.class);
//		System.out.println(response);

		/* ---------------------- XML -> JSON -> Object(Java) 끝 ----------------- */

		//

		/* ---------------------- 응답 객체 -> entity 엔티티 객체 시작 ----------------- */

		List<CovidSidoDaily> list = new ArrayList<CovidSidoDaily>();
		for (CovidSidoDailyResponse.Item item : response.getResponse().getBody().getItems().getItem()) {

			CovidSidoDaily record = CovidSidoDaily.builder().stdDay(item.getStdDay()).gubun(item.getGubun())
					.gubunEn(item.getGubunEn()).overFlowCnt(item.getOverFlowCnt()).localOccCnt(item.getLocalOccCnt())
					.build();

			list.add(record);
		}

		/* ---------------------- 응답 객체 -> entity 엔티티 객체 끝 ----------------- */

		//

		/* ---------------------- 엔티티객체 -> 리포지터리로 저장 시작 ----------------- */
		repo.saveAll(list);
		/* ---------------------- 엔티티객체 -> 리포지터리로 저장 시작 ----------------- */

	}

	// http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=itBvLQRBMyMkMogfGhCxuSntdkkbcJtmhySH2jR9GD%2FcayyuW4%2FprkAKC0YvgzVTgbo8732gHiA%2Fo%2BZFWZrZAg%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20211001&endCreateDt=20211005

}