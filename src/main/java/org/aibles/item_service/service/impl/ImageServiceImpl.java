package org.aibles.item_service.service.impl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.entity.Image;
import org.aibles.item_service.exception.BadRequestException;
import org.aibles.item_service.exception.InternalServerException;
import org.aibles.item_service.repository.ImageRepository;
import org.aibles.item_service.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Slf4j
public class ImageServiceImpl implements ImageService {

  private final ImageRepository imageRepository;

  @Value("E:\\anh")
  private Path fileStorageLocation;

  public ImageServiceImpl(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  @Override
  public Image save(MultipartFile multipartFile) {
    log.info("(save)multipartFile : {}", multipartFile);
    try {
      Path targetLocation = fileStorageLocation.resolve(multipartFile.getOriginalFilename());
      return imageRepository.save(Image.of(multipartFile.getOriginalFilename(),targetLocation.toString()));
    } catch (Exception ex) {
      log.error("(save)exception : {} --> Bad request", ex.getClass().getSimpleName());
      throw new BadRequestException();
    }
  }

  private static final String IMAGE_PATH = "E:\\anh\\";

  @Override
  public InputStreamSource getImage(String id) {
    try {
      String imagePath = imageRepository.getImagePathById(id);
      File file = new File(imagePath);
      byte[] imageBytes = Files.readAllBytes(file.toPath());

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_JPEG);
      headers.setContentLength(imageBytes.length);

      InputStream inputStream = new ByteArrayInputStream(imageBytes);

      return new  InputStreamResource(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
      throw new InternalServerException();
    }
    }
}