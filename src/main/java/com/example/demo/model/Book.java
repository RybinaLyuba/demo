package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
}
