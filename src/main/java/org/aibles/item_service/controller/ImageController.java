package org.aibles.item_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.entity.Image;
import org.aibles.item_service.service.ImageService;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.AVATAR_BASE_URL;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(AVATAR_BASE_URL)
public class ImageController {
  private final ImageService service;

  @PostMapping()
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Image> uploadFile(
          @RequestParam(name = "file", required = false) MultipartFile file) {
    log.info("(uploadFile)fileName: {}", file.getOriginalFilename());
    return ResponseEntity.ok().body(service.save(file));

  }

  @GetMapping()
  public ResponseEntity<InputStreamSource> getImage(@RequestParam("id") String imageName) {
    InputStreamSource inputStreamSource = service.getImage(imageName);

    return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .contentType(MediaType.IMAGE_JPEG)
            .body(inputStreamSource);
  }
}
