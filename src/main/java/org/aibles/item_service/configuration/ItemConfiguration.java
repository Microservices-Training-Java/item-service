package org.aibles.item_service.configuration;

import org.aibles.item_service.facade.ItemFacadeService;
import org.aibles.item_service.facade.ItemFacadeServiceImpl;
import org.aibles.item_service.facade.ItemTypeFacadeService;
import org.aibles.item_service.facade.ItemTypeFacadeServiceImpl;
import org.aibles.item_service.repository.*;
import org.aibles.item_service.service.*;
import org.aibles.item_service.service.impl.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"org.aibles.item_service.repository"})
@ComponentScan(basePackages = {"org.aibles.item_service.repository"})
public class ItemConfiguration {

  @Bean
  public ItemTypeService itemTypeService(ItemTypeRepository repository) {
    return new ItemTypeServiceImpl(repository);
  }

  @Bean
  public ItemFieldService itemFieldService(ItemFieldRepository repository) {
    return new ItemFieldServiceImpl(repository);
  }

  @Bean
  public ItemService itemService(ItemRepository repository) {
    return new ItemServiceImpl(repository);
  }

  @Bean
  public ItemFieldValueService itemFieldValueService(ItemFieldValueRepository repository) {
    return new ItemFieldValueServiceImpl(repository);
  }

  @Bean
  public ItemTypeFieldService itemTypeFieldService(ItemTypeFieldRepository repository) {
    return new ItemTypeFieldServiceImpl(repository);
  }

  @Bean
  public ItemTypeFacadeService itemTypeFacadeService(
      ItemTypeService itemTypeService,
      ItemFieldService itemFieldService,
      ItemTypeFieldService itemTypeFieldService
  ) {
    return new ItemTypeFacadeServiceImpl(
        itemTypeService,
        itemFieldService,
        itemTypeFieldService);
  }

  @Bean
  public ItemFacadeService itemFacadeService(
      ItemTypeService itemTypeService,
      ItemFieldService itemFieldService,
      ItemService itemService,
      ItemFieldValueService itemFieldValueService
  ) {
    return new ItemFacadeServiceImpl(
        itemTypeService,
        itemFieldService,
        itemService,
        itemFieldValueService
    );
  }

  @Value("E:\\anh")
  private String fileStorageLocation;

  @Bean
  public AvatarService uploadService(AvatarRepository avatarRepository){
    return new AvatarServiceImpl(avatarRepository, fileStorageLocation);
  }
}
