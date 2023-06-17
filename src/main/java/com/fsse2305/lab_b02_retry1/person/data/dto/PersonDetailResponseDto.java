package com.fsse2305.lab_b02_retry1.person.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDetailData;

public class PersonDetailResponseDto {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("hkid_number")
    private String hkid;

    public PersonDetailResponseDto(PersonDetailData personDetailData) {
        this.firstName = personDetailData.getFirstName();
        this.lastName = personDetailData.getLastName();
        this.hkid = personDetailData.getHkid();
    }
}
