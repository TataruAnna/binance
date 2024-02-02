package com.example.binance.service;

import com.example.binance.model.Kline;
import com.example.binance.model.Symbol;
import com.example.binance.repository.KlineRepository;
import com.example.binance.repository.SymbolRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KlineService {
    private KlineRepository klineRepository;


    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    //de pus URL-ul cu symbol ca parametru

    private static final String BASE_URL = "https://testnet.binancefuture.com/fapi/v1/ticker/bookTicker";


    //imi injectez din app properties intr o variabila key pe care nu vreau sa il

    private SymbolRepository symbolRepository;

    @Autowired
    public KlineService(SymbolRepository symbolRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.symbolRepository = symbolRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

    }


    public Kline addKline(String symbol) throws JsonProcessingException {
        // la url de baza adaug locatia lat, lon, apykey dar cheia adaug in apl propert si adaug o constanta
        String url = UriComponentsBuilder
                .fromUriString(BASE_URL)
                .queryParam("symbol", symbol)
                .toUriString();

        // vom primi ca raspuns un json si sa imi returnez
        String response = restTemplate.getForObject(BASE_URL, String.class);

        JsonNode root = objectMapper.readTree(response);
        return mapFromJsonToKline(root);
    }

    public Kline mapFromJsonToKline(JsonNode root) {

        //caut simbol dupa nume
        //iau din root primul element al array-ului = klinearraynode
        for (int i = 0; i < 5; i++) {
            //iau elementul de la fiecare pozitie din klinearraynode si il atribui atributului corespunzator din kline pe care il voi salva
            //leg kline de simbol
            //salvez kline

        }
        return new Kline();
    }


}
