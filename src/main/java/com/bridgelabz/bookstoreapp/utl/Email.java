package com.bridgelabz.bookstoreapp.utl;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Email implements Serializable {
    String to;
    String from;
    String subject;
    String body;
}
