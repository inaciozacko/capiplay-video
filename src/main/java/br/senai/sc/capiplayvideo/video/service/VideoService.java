package br.senai.sc.capiplayvideo.video.service;

import br.senai.sc.capiplayvideo.video.exceptions.ObjetoInexistenteException;
import br.senai.sc.capiplayvideo.video.model.dto.VideoDTO;
import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.entity.Video;
import br.senai.sc.capiplayvideo.video.model.enums.ResolucaoEnum;
import br.senai.sc.capiplayvideo.video.projection.VideoMiniaturaProjection;
import br.senai.sc.capiplayvideo.video.projection.VideoProjection;
import br.senai.sc.capiplayvideo.video.repository.VideoRepository;
import br.senai.sc.capiplayvideo.video.utils.GeradorUuidUtils;
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

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoriaService categoriaService;

    @Value("${diretorioVideos}")
    private String diretorio;

    public void salvar(VideoDTO videoDTO) {
        String uuid = GeradorUuidUtils.gerarUuid();
        String diretorioEsse = diretorio + uuid + "\\";
        try {
            Path caminho = Path.of(diretorioEsse);
            Files.createDirectories(caminho);
            Path arquivoTemporario = Files.createTempFile(caminho, "video_", ".mp4");
            videoDTO.video().transferTo(arquivoTemporario.toFile());
            for (ResolucaoEnum resolucaoEnum : ResolucaoEnum.values()) {
                BufferedImage imagemRedimensionada = redimensionarImagem(
                        videoDTO.miniatura().getInputStream(),
                        resolucaoEnum.getLargura(), resolucaoEnum.getAltura());
                arquivoTemporario = Files.createTempFile(caminho, "miniatura_" + resolucaoEnum + "_", ".jpeg");
                ImageIO.write(imagemRedimensionada, "JPEG", arquivoTemporario.toFile());
            }
            Video video = new Video(uuid, videoDTO, diretorioEsse);
            video.getTags().forEach(tagService::salvar);
            categoriaService.salvar(video.getCategoria());
            repository.save(video);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private BufferedImage redimensionarImagem(InputStream inputStream, int largura, int altura) throws IOException {
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