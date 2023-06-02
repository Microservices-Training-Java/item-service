package org.aibles.item_service.service;

import org.aibles.item_service.entity.Avatar;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {

  /**
   * upload file from server
   * <p>
   * //   * @param file - file select from device of client
   *
   * @return path of file uploaded
   */

//  Avatar upFile(MultipartFile file);
//
//  ResponseEntity<Resource> downloadFile(String fileName);

  Avatar save(MultipartFile multipartFile);

  InputStreamResource get(String fileName);

  ResponseEntity<byte[]> getImage(String imageName);
}
