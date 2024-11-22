package com.yootiful.service;

import com.yootiful.models.Quotation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class QuotationService {
    private final RestTemplate restTemplate;

    public QuotationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public Quotation fetch(String symbol) {
        Map<String, Object> responseMap = restTemplate.getForObject(
                "https://api.diadata.org/v1/quotation/" + symbol, Map.class
        );

        if (responseMap == null) throw new RuntimeException("Could not fetch quotation");

        System.out.println(responseMap);

        return new Quotation(
                (String) responseMap.get("Symbol"),
                (String) responseMap.get("Name"),
                (Double) responseMap.get("Price"),
                (Double) responseMap.get("PriceYesterday"),
                (Double) responseMap.get("VolumeYesterdayUSD")
        );
    }
}
