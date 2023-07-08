package br.senai.sc.capiplayvideo.video.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SecondaryTable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class Tag {

    @Id
    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    public static List<Tag> converterLista(List<String> tags) {
        return tags.stream().map(Tag::new).collect(Collectors.toList());
    }

}
