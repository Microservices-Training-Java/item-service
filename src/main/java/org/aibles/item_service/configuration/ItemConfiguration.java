package org.aibles.item_service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aibles.item_service.client.service.OrderClient;
import org.aibles.item_service.client.service.impl.OrderClientImpl;
import org.aibles.item_service.facade.ItemFacadeService;
import org.aibles.item_service.facade.ItemFacadeServiceImpl;
import org.aibles.item_service.facade.ItemTypeFacadeService;
import org.aibles.item_service.facade.ItemTypeFacadeServiceImpl;
import org.aibles.item_service.repository.*;
import org.aibles.item_service.service.*;
import org.aibles.item_service.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.trainingjava.core.api.exception.configuration.EnableCoreApiException;
import org.trainingjava.coreresttemplate.configuration.EnableRestTemplate;

@Configuration
@EnableCoreApiException
@EnableJpaRepositories(basePackages = {"org.aibles.item_service.repository"})
@ComponentScan(basePackages = {"org.aibles.item_service.repository"})
@EnableRestTemplate
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
  public OrderClient orderClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
    return new OrderClientImpl(restTemplate, objectMapper);
  }

  @Bean
  public ItemService itemService(ItemRepository repository, ItemFieldValueRepository itemFieldValueRepository, OrderClient orderClient) {
    return new ItemServiceImpl(repository, itemFieldValueRepository, orderClient);
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
  public CategoryService categoryService(CategoryRepository repository, RestTemplate restTemplate) {
    return new CategoryServiceImpl(repository,restTemplate);
  }

  @Bean
  public CategoryItemService categoryItemService(CategoryItemRepository categoryItemRepository,CategoryRepository categoryRepository,ItemRepository itemRepository, CategoryService categoryService) {
    return new CategoryItemServiceImpl(categoryItemRepository, categoryRepository, itemRepository, categoryService);
  }

  @Bean
  public ItemTypeFacadeService itemTypeFacadeService(
      ItemTypeService itemTypeService,
      ItemFieldService itemFieldService,
      ItemTypeFieldService itemTypeFieldService,
      ItemFacadeService itemFacadeService
  ) {
    return new ItemTypeFacadeServiceImpl(
        itemTypeService,
        itemFieldService,
        itemTypeFieldService,
        itemFacadeService);
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


}
