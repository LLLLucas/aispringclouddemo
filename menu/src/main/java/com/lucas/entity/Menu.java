package com.lucas.entity;

import lombok.Data;

@Data
public class Menu {
    private long id;
    private String name;
    private Double price;
    private String flavor;
    private Type type;

}
