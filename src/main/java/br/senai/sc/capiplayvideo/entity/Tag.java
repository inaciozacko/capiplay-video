package br.senai.sc.capiplayvideo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Tag {
    @Id
    private String tag;
}
