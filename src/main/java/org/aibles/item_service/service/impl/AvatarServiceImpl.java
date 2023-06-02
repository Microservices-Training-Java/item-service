package org.aibles.item_service.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.entity.Avatar;
import org.aibles.item_service.exception.BadRequestException;
import org.aibles.item_service.exception.InternalServerException;
import org.aibles.item_service.repository.AvatarRepository;
import org.aibles.item_service.service.AvatarService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.StandardCopyOption;

@Slf4j
public class AvatarServiceImpl implements AvatarService {

  private final AvatarRepository avatarRepository;

//  @Override
//  public String save(MultipartFile file){
//
//    log.info("(upFile)file :{}", file);
//
//    try {
//      String fileName = file.getOriginalFilename();
//
//      // Tạo một đối tượng Path để lưu trữ file
//      Path targetLocation = fileStorageLocation.resolve(fileName);
//
//      // Nếu file đã tồn tại, thì xóa file cũ
//      if (Files.exists(targetLocation)) {
//        Files.delete(targetLocation);
//      }
//
//      // Lưu file vào đường dẫn đã được tạo
//      Files.copy(file.getInputStream(), targetLocation);
//
//      // Trả về đường dẫn của file
//      return targetLocation.toString();
//    } catch (Exception ex) {
//    }
//    return null;
//  }


  private final Path fileStorageLocation;

  public AvatarServiceImpl(AvatarRepository avatarRepository, String pathFileStorage) {
    this.avatarRepository = avatarRepository;
    this.fileStorageLocation = Paths.get(pathFileStorage).normalize();
  }

  @Override
  public InputStreamResource get(String fileName) {
    log.info("(get)fileName : {}", fileName);
    try {
      File file = new File(fileName);
      return new InputStreamResource(new FileInputStream(file));
    } catch (Exception e) {
      log.error("(get)e : {}", e.getMessage());
      throw new InternalServerException();
    }
  }

  @Override
  public Avatar save(MultipartFile multipartFile) {
    log.info("(save)multipartFile : {}", multipartFile);
    try {
      Path targetLocation = fileStorageLocation.resolve(multipartFile.getOriginalFilename());
//      Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      return avatarRepository.save(Avatar.of(multipartFile.getOriginalFilename(),targetLocation.toString()));
    } catch (Exception ex) {
      log.error("(save)exception : {} --> Bad request", ex.getClass().getSimpleName());
      throw new BadRequestException();
    }
  }

  private static final String IMAGE_PATH = "E:\\anh\\";

  @Override
  public ResponseEntity<byte[]> getImage(String imageName) {
    try {
      String imagePath = IMAGE_PATH + imageName;
      File file = new File(imagePath);
      byte[] imageBytes = Files.readAllBytes(file.toPath());

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_JPEG);
      headers.setContentLength(imageBytes.length);
      return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    } catch (IOException e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
