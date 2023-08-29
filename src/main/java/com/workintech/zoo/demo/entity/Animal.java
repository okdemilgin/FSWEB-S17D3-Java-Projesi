package com.workintech.zoo.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal {
    private int id;
    private String name;
    private double weight;
    private Gender gender;
}