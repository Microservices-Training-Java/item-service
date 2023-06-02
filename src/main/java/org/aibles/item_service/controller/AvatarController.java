package org.aibles.item_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.AvatarDTO;
import org.aibles.item_service.entity.Avatar;
import org.aibles.item_service.service.AvatarService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.AVATAR_BASE_URL;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(AVATAR_BASE_URL)
public class AvatarController {
  private final AvatarService service;

//  @PostMapping()
//  public Response uploadFile(@RequestParam("file") MultipartFile file) {
////    return ResponseEntity.ok().body(service.upFile(file));
////    log.info("(uploadFile)file :{}", file);
//    return Response.of(
//            HttpStatus.CREATED.value(),
//            service.upFile(file)
//    );
//  }
//
//  @GetMapping("/download/{fileName:.+}")
//  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
//    // Lấy đường dẫn tới file trong máy tính của bạn
//    return service.downloadFile(fileName);
//  }

  @PostMapping()
  @ResponseStatus(HttpStatus.OK)
  public Avatar uploadFile(
          @RequestParam(name = "file", required = false) MultipartFile file) {
    log.info("(uploadFile)fileName: {}", file.getOriginalFilename());
    return service.save(file);
  }

  @GetMapping("/{file}")
  public InputStreamResource getFile(@PathVariable String file){
    log.info("(getFile)file: {}", file);
    return service.get(file);
  }

  @GetMapping("/image/{imageName:.+}")
  public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
    log.info("(getImage)imageName : {}", imageName);
    return service.getImage(imageName);
    }
  }

//  @Value("E:/anh")
//  private String uploadDir;
//
//  @PersistenceContext
//  private EntityManager entityManager;
//
//  @PostMapping("/uploadImage")
//  @Transactional
//  public ResponseEntity<AvatarDTO> uploadImage(@RequestParam("file") MultipartFile file) {
//    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//    try {
//      // Lưu image vào thư mục upload
//      File dir = new File(uploadDir);
//      if (!dir.exists()) {
//        dir.mkdirs();
//      }
//      String filePath = uploadDir + "/" + fileName;
//      File dest = new File(filePath);
//      file.transferTo(dest);
//
//      // Lưu thông tin về image vào database
//      Avatar image = new Avatar();
//      image.setName(fileName);
//      image.setPath(filePath);
//      entityManager.persist(image);
//
//      // Trả về đường dẫn của image đã được lưu trữ
//      String imageDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//              .path("/downloadImage/")
//              .path(fileName)
//              .toUriString();
//
//      AvatarDTO avatarDTO = new AvatarDTO();
//      avatarDTO.setName(fileName);
//      avatarDTO.setPath(imageDownloadUri);
//      return new ResponseEntity<>(avatarDTO, HttpStatus.OK);
//    } catch (IOException e) {
//      e.printStackTrace();
//      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
