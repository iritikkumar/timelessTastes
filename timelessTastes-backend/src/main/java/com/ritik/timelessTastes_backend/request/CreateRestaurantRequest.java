package com.ritik.timelessTastes_backend.request;

import com.ritik.timelessTastes_backend.model.Address;
import com.ritik.timelessTastes_backend.model.ContactInformation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
