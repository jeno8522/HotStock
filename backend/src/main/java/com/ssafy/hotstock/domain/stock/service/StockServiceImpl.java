package com.ssafy.hotstock.domain.stock.service;


import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stock.dto.StockByCodeNameResponseDto;
import com.ssafy.hotstock.domain.stock.dto.StockResponseDto;
import com.ssafy.hotstock.domain.stock.repository.StockRepository;
import com.ssafy.hotstock.global.advice.exception.StockFoundException;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);
    @Override
    public Stock insertStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> insertStockList(List<Stock> stockList) {
        return stockRepository.saveAll(stockList);
    }


    @Override
    public List<StockByCodeNameResponseDto> findStocksByCodeName(String codeName) {


        List<Stock> stocks;
        if (isCode(codeName)) {
            stocks = stockRepository.findByCode(Integer.parseInt(codeName))
                    .orElseThrow(() -> new StockFoundException("입력한 종목 코드에 대한 검색 과정에서 에러 발생, 입력값 : " + codeName));
        } else {
            stocks = stockRepository.findByNameContaining(codeName)
                    .orElseThrow(() -> new StockFoundException("입력한 종목 이름에 대한 검색 과정에서 에러 발생, 입력값 : " + codeName));
        }


        List<StockByCodeNameResponseDto> initStockByCodeNameResponseDtos = getStockDetailsFromPython(stocks);

        List<StockByCodeNameResponseDto> stockByCodeNameResponseDtos = new ArrayList<>();

        for (StockByCodeNameResponseDto stockByCodeNameResponseDto : initStockByCodeNameResponseDtos) {
            int code = Integer.parseInt(stockByCodeNameResponseDto.getCode());
//            int code = 220;
            List<Stock> stockByCode = stockRepository.findByCode(code)
                    .orElseThrow(() -> new IllegalArgumentException("DB에 해당 코드의 종목이 존재하지 않습니다. 코드: " + code));

            StockByCodeNameResponseDto updatedDto = stockByCodeNameResponseDto.toBuilder()
                    .name(stockByCode.get(0).getName())
                    .code(codeToSixString(code))
                    .build();
            stockByCodeNameResponseDtos.add(updatedDto);
        }
//        for (StockByCodeNameResponseDto stockDetail : stockByCodeNameResponseDtos) {
//            logger.info("파이썬 결과333 ================ " + stockDetail.toString()); // toString() 메서드는 StockByCodeNameResponseDto에 구현되어 있어야 합니다.
//        }


        return stockByCodeNameResponseDtos;
    }

    @Override
    public List<StockByCodeNameResponseDto> getStockDetailsFromPython(int code) {
        List<String> stockCode = new ArrayList<>();
        stockCode.add(codeToSixString(code));
        String pythonURL = "http://hot-stock.shop:5000/stock/";

        RestTemplate restTemplate = new RestTemplate();

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);  // JSON 형식으로 전송

        // 요청 본문과 헤더 설정
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(stockCode, headers);

        // POST 요청 보내기
        ResponseEntity<List<StockByCodeNameResponseDto>> response = restTemplate.exchange(
            pythonURL,
            HttpMethod.POST,
            requestEntity,
            new ParameterizedTypeReference<List<StockByCodeNameResponseDto>>() {}
        );

        return response.getBody();
    }

    @Override
    public List<StockResponseDto> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        List<StockResponseDto> stockResponseDtos = new ArrayList<>();
        for (Stock stock: stocks
             ) {
            StockResponseDto stockResponseDto = StockResponseDto.builder()
                    .code(codeToSixString(stock.getCode()))
                    .name(stock.getName())
                    .build();
            stockResponseDtos.add(stockResponseDto);

        }
        return stockResponseDtos;

    }


    private List<StockByCodeNameResponseDto> getStockDetailsFromPython(List<Stock> stocks) {
        List<String> stockCodes = new ArrayList<>();
        String pythonURL = "http://hot-stock.shop:5000/stock/";

        for (Stock stock : stocks) {
            stockCodes.add(codeToSixString(stock.getCode()));
        }
//        stockCodes.add("000220");
        // RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);  // JSON 형식으로 전송

        // 요청 본문과 헤더 설정
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(stockCodes, headers);

        // POST 요청 보내기
        ResponseEntity<List<StockByCodeNameResponseDto>> response = restTemplate.exchange(
                pythonURL,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<StockByCodeNameResponseDto>>() {}
        );


//        List<StockByCodeNameResponseDto> result = response.getBody();
//        // 로그 출력
//        for (StockByCodeNameResponseDto stockDetail : result) {
//            logger.info("파이썬 결과 ================ " + stockDetail.toString()); // toString() 메서드는 StockByCodeNameResponseDto에 구현되어 있어야 합니다.
//        }
        // 응답 받기
        return response.getBody();
    }



    // 무조건 6자리 숫자인지?
    private boolean isCode(String value) {
        return value.matches("^[0-9]{6}$");
    }

    // 6자리 이하의 숫자의 앞에 부족한 만큼 0 붙여서 String 으로 변환
    private String codeToSixString(int code) {
        return String.format("%06d", code);
    }
}
