package org.aibles.item_service.configuration;

import org.aibles.item_service.facade.ItemFacadeService;
import org.aibles.item_service.facade.ItemFacadeServiceImpl;
import org.aibles.item_service.repository.ItemFieldRepository;
import org.aibles.item_service.repository.ItemTypeFieldRepository;
import org.aibles.item_service.repository.ItemTypeRepository;
import org.aibles.item_service.service.ItemFieldService;
import org.aibles.item_service.service.ItemFieldValueService;
import org.aibles.item_service.service.ItemService;
import org.aibles.item_service.service.ItemTypeFieldService;
import org.aibles.item_service.service.ItemTypeService;
import org.aibles.item_service.service.impl.ItemFieldServiceImpl;
import org.aibles.item_service.service.impl.ItemFieldValueServiceImpl;
import org.aibles.item_service.service.impl.ItemServiceImpl;
import org.aibles.item_service.service.impl.ItemTypeFieldServiceImpl;
import org.aibles.item_service.service.impl.ItemTypeServiceImpl;
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
  public ItemService itemService() {
    return new ItemServiceImpl();
  }

  @Bean
  public ItemFieldValueService itemFieldValueService() {
    return new ItemFieldValueServiceImpl();
  }

  @Bean
  public ItemTypeFieldService itemTypeFieldService(ItemTypeFieldRepository repository,
       ItemFieldService itemFieldService) {
    return new ItemTypeFieldServiceImpl(repository, itemFieldService);
  }

  @Bean
  public ItemFacadeService itemFacadeService(
      ItemTypeService itemTypeService,
      ItemFieldService itemFieldService,
      ItemService itemService,
      ItemTypeFieldService itemTypeFieldService,
      ItemFieldValueService itemFieldValueService
  ) {
    return new ItemFacadeServiceImpl(
        itemTypeService,
        itemFieldService,
        itemTypeFieldService,
        itemService,
        itemFieldValueService);
  }
}
