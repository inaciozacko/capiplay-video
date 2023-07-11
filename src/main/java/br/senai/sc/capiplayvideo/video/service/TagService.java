package br.senai.sc.capiplayvideo.video.service;

import br.senai.sc.capiplayvideo.video.model.entity.Tag;
import br.senai.sc.capiplayvideo.video.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository repository;

    public void salvar(Tag tag) {
        repository.save(tag);
    }

}
