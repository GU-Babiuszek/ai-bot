package com.grapeup.aibot.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.util.Vector;

@Entity
@Table(name = "documents")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private Vector<Double> embedding;
}
