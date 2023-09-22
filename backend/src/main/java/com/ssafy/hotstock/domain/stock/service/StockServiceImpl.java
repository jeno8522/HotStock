package com.ssafy.hotstock.domain.stock.service;


import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stock.dto.StockByCodeNameResponseDto;
import com.ssafy.hotstock.domain.stock.repository.StockRepository;
import com.ssafy.hotstock.global.advice.exception.StockFoundException;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
            List<Stock> stockByCode = stockRepository.findByCode(code)
                    .orElseThrow(() -> new IllegalArgumentException("DB에 해당 코드의 종목이 존재하지 않습니다. 코드: " + code));

            StockByCodeNameResponseDto updatedDto = stockByCodeNameResponseDto.toBuilder()
                    .name(stockByCode.get(0).getName())
                    .build();
            stockByCodeNameResponseDtos.add(updatedDto);
        }


        return stockByCodeNameResponseDtos;
    }

    private List<StockByCodeNameResponseDto> getStockDetailsFromPython(List<Stock> stocks) {
        List<String> stockCodes = new ArrayList<>();
        String pythonURL = "http://hot-stock.shop:5000/stock/";

        for (Stock stock : stocks) {
            stockCodes.add(String.valueOf(stock.getCode()));
        }

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
