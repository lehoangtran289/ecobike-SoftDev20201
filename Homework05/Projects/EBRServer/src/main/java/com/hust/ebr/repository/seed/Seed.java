package com.hust.ebr.repository.seed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.ebr.model.Bike;
import com.hust.ebr.model.CreditCard;
import com.hust.ebr.model.DockingStation;
import com.hust.ebr.model.Rental;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public class Seed {
//    src/main/java/com/hust/ebr/repository/seed/json/
//    F:\Hoc Tap\20201\Software Design and Construction\Lab01\ict1.k62s.20201-09\Homework05\Projects\EBRServer\src\main\java\com\hust\ebr\repository\seed\json\
    private static final String ROOT_PATH = "src/main/java/com/hust/ebr/repository/seed/json/";
    private final ObjectMapper mapper;
    private List<Bike> bikes;
    private List<DockingStation> dockingStations;
    private List<CreditCard> creditCards;
    private List<Rental> rentals;

    @PostConstruct
    public void init() throws JsonProcessingException {
        bikes = new ArrayList<>();
        bikes.addAll(generateBikeFromFile(new File(ROOT_PATH + "normalBikes.json").getAbsolutePath()));
        bikes.addAll(generateBikeFromFile(new File(ROOT_PATH + "twinBikes.json").getAbsolutePath()));
        bikes.addAll(generateBikeFromFile(new File(ROOT_PATH + "eBikes.json").getAbsolutePath()));

        dockingStations = new ArrayList<>();
        String dockingStationsJson = FileReader.read(new File(ROOT_PATH + "dockingStations.json").getAbsolutePath());
        dockingStations.addAll(mapper.readValue(dockingStationsJson, new TypeReference<>() {}));

        creditCards = new ArrayList<>();
        String creditCardsJson = FileReader.read(new File(ROOT_PATH + "creditCards.json").getAbsolutePath());
        creditCards.addAll(mapper.readValue(creditCardsJson, new TypeReference<>() {}));

        rentals = new ArrayList<>();
        String rentalJson = FileReader.read(new File(ROOT_PATH + "rentalDemo.json").getAbsolutePath());
        rentals.addAll(mapper.readValue(rentalJson, new TypeReference<>() {}));
    }

    private ArrayList<Bike> generateBikeFromFile(String filePath) {
        ArrayList<Bike> res = new ArrayList<>();
        String json = FileReader.read(filePath);
        try {
            res = mapper.readValue(json, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Invalid JSON input data from " + filePath);
        }
        return res;
    }
}
