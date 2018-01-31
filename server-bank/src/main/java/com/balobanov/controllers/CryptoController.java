package com.balobanov.controllers;

import com.balobanov.models.coins.AvailableCoins;
import com.balobanov.models.coins.CoinData;
import com.balobanov.models.coins.CryptoCoin;
import com.balobanov.models.dto.AvailableCoinsDto;
import com.balobanov.services.abstraction.AvailableCoinsService;
import com.balobanov.services.abstraction.CryptoCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "coins")
public class CryptoController {

    private RestTemplate restTemplate;
    private CryptoCoinService cryptoCoinService;
    private AvailableCoinsService availableCoinsService;

    @RequestMapping(value = "history")
    public void getHistoryByCoin() throws ExecutionException, InterruptedException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("Key", "83af6014dedc90f58cd0c58d70371ab8");
        params.add("Secret", "1f6465f074d39409b2b86b1a9f9ad162");
        HttpEntity<String> entity = new HttpEntity<>("parameters", params);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        CryptoCoin cryptoCoin = null;

        List<AvailableCoins> all = availableCoinsService.getAll();

        for (AvailableCoins availableCoins : all) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime localDateTime = now.minusYears(5);
            for (int i = 0; i < 5; i++) {
                String startDate = localDateTime.format(dateTimeFormatter);
                localDateTime = localDateTime.plusYears(1);
                String end = localDateTime.format(dateTimeFormatter);

                if (cryptoCoin == null) {
                    cryptoCoin = restTemplate.postForObject( "https://www.cryptocurrencychart.com/api/coin/history/" + availableCoins.getCoin_id() + "/" + startDate + "/" + end + "/price/usd", entity, CryptoCoin.class);
                } else {
                    List<CoinData> data = restTemplate.postForObject( "https://www.cryptocurrencychart.com/api/coin/history/" + availableCoins.getCoin_id() + "/" + startDate + "/" + end + "/price/usd", entity, CryptoCoin.class).getData();
                    cryptoCoin.getData().addAll(data);
                }
            }
            cryptoCoinService.save(cryptoCoin);
            cryptoCoin = null;
        }
    }

    //    @RequestMapping(value = "availablecoins")
    public void getAvailableCoins() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("Key", "83af6014dedc90f58cd0c58d70371ab8");
        params.add("Secret", "1f6465f074d39409b2b86b1a9f9ad162");
        HttpEntity<String> entity = new HttpEntity<>("parameters", params);

        AvailableCoinsDto availableCoinsDto = restTemplate.postForObject("https://www.cryptocurrencychart.com/api/coin/list", entity, AvailableCoinsDto.class);
        List<AvailableCoins> collect = availableCoinsDto.getCoins().stream().map(tempCoins -> {
            AvailableCoins availableCoins = new AvailableCoins();
            availableCoins.setCode(tempCoins.getCode());
            availableCoins.setCoin_id(tempCoins.getId());
            availableCoins.setName(tempCoins.getName());
            availableCoins.setSymbol(tempCoins.getSymbol());
            return availableCoins;
        }).collect(Collectors.toList());
        availableCoinsService.save(collect);
    }

    @Autowired
    public void setCryptoCoinService(CryptoCoinService cryptoCoinService) {
        this.cryptoCoinService = cryptoCoinService;
    }

    @Autowired
    public void setAvailableCoinsService(AvailableCoinsService availableCoinsService) {
        this.availableCoinsService = availableCoinsService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
