package com.fullstack.fullstack.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventDTO {
    private String name;
    private Date date;
    private String equipmentName;

}
