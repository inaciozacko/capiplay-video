package br.senai.sc.capiplayvideo.video.service;

import br.senai.sc.capiplayvideo.video.exceptions.ObjetoInexistenteException;
import br.senai.sc.capiplayvideo.video.model.dto.VideoDTO;
import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.entity.Video;
import br.senai.sc.capiplayvideo.video.projection.VideoMiniaturaProjection;
import br.senai.sc.capiplayvideo.video.projection.VideoProjection;
import br.senai.sc.capiplayvideo.video.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    @Value("${diretorioVideos}")
    private String diretorio;

    public void salvar(VideoDTO videoDTO) {

        Video video = new Video();
        String diretorioEsse = diretorio + video.getUuid() + "\\";

        try {
            Path of = Path.of(diretorioEsse);
            Files.createDirectories(of);
            Path arquivoTemporario = Files.createTempFile(of, "video_", ".mp4");
            videoDTO.getVideo().transferTo(arquivoTemporario.toFile());
            String caminhoVideo = diretorioEsse + arquivoTemporario.getFileName();

            BufferedImage ImagemRedimencionada = redimencionarImagem(videoDTO.getMiniatura().getInputStream(),
                    1280, 800);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(ImagemRedimencionada, "JPEG", baos);
            byte[] resizedBytes = baos.toByteArray();
            arquivoTemporario = Files.createTempFile(of, "miniatura_", ".jpg");
            Files.write(arquivoTemporario, resizedBytes, StandardOpenOption.CREATE);
            String caminhoMiniatura = diretorioEsse + arquivoTemporario.getFileName();

            System.out.println(caminhoVideo);
            System.out.println(caminhoMiniatura);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private BufferedImage redimencionarImagem(InputStream inputStream, int largura, int altura) throws IOException {
        BufferedImage sourceImage = ImageIO.read(inputStream);
        Image resizedImage = sourceImage.getScaledInstance(largura, altura, Image.SCALE_DEFAULT);

        BufferedImage bufferedResizedImage = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        bufferedResizedImage.getGraphics().drawImage(resizedImage, 0, 0, null);

        return bufferedResizedImage;
    }

    public Page<VideoMiniaturaProjection> buscarTodos(Pageable pageable) {
        return repository.findAllBy(pageable);
    }

    public Page<VideoMiniaturaProjection> buscarPorCategoria(Pageable pageable, Categoria categoria) {
        return repository.findAllByCategoria(categoria, pageable);
    }

    public VideoProjection buscarUm(String uuid) {
        return repository.findByUuid(uuid).orElseThrow(ObjetoInexistenteException::new);
    }

    public void deletar(String uuid) {
        repository.deleteById(uuid);
    }
}
