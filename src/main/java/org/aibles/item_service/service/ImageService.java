package org.aibles.item_service.service;

import org.aibles.item_service.entity.Image;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

  Image save(MultipartFile multipartFile);

  InputStreamSource getImage(String imageName);
}
