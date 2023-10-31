package org.aibles.item_service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aibles.item_service.client.service.OrderClient;
import org.aibles.item_service.client.service.UserClient;
import org.aibles.item_service.client.service.impl.OrderClientImpl;
import org.aibles.item_service.client.service.impl.UserClientImpl;
import org.aibles.item_service.facade.ItemTypeFacadeService;
import org.aibles.item_service.facade.CategoryFacadeService;
import org.aibles.item_service.facade.ItemFacadeService;
import org.aibles.item_service.facade.ItemTypeFacadeServiceImpl;
import org.aibles.item_service.facade.ItemFacadeServiceImpl;
import org.aibles.item_service.facade.CategoryFacadeServiceImpl;
import org.aibles.item_service.repository.ItemTypeRepository;
import org.aibles.item_service.repository.ItemFieldRepository;
import org.aibles.item_service.repository.ItemRepository;
import org.aibles.item_service.repository.ItemFieldValueRepository;
import org.aibles.item_service.repository.ItemTypeFieldRepository;
import org.aibles.item_service.repository.CategoryRepository;
import org.aibles.item_service.service.ItemTypeFieldService;
import org.aibles.item_service.service.CategoryService;
import org.aibles.item_service.service.CategoryItemService;
import org.aibles.item_service.repository.CategoryItemRepository;
import org.aibles.item_service.service.impl.CategoryItemServiceImpl;
import org.aibles.item_service.service.ItemFieldService;
import org.aibles.item_service.service.ItemFieldValueService;
import org.aibles.item_service.service.ItemService;
import org.aibles.item_service.service.ItemTypeService;
import org.aibles.item_service.service.impl.ItemFieldServiceImpl;
import org.aibles.item_service.service.impl.ItemFieldValueServiceImpl;
import org.aibles.item_service.service.impl.ItemServiceImpl;
import org.aibles.item_service.service.impl.ItemTypeServiceImpl;
import org.aibles.item_service.service.impl.ItemTypeFieldServiceImpl;
import org.aibles.item_service.service.impl.CategoryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.trainingjava.core.api.exception.configuration.EnableCoreApiException;
import org.trainingjava.coreresttemplate.configuration.EnableRestTemplate;

@Configuration
@EnableJpaRepositories(basePackages = {"org.aibles.item_service.repository"})
@ComponentScan(basePackages = {"org.aibles.item_service.repository"})
@EnableRestTemplate
@EnableCoreApiException
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
  public CategoryService categoryService(CategoryRepository repository) {
    return new CategoryServiceImpl(repository);
  }

  @Bean
  public UserClient userClient(RestTemplate restTemplate) {
    return new UserClientImpl(restTemplate);
  }

  @Bean
  public CategoryItemService categoryItemService(CategoryItemRepository categoryItemRepository,CategoryRepository categoryRepository,ItemRepository itemRepository, UserClient userClient) {
    return new CategoryItemServiceImpl(categoryItemRepository, categoryRepository, itemRepository, userClient);
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

  @Bean
  public CategoryFacadeService categoryFacadeService(
      UserClient userClient,
      CategoryService categoryService) {
    return new CategoryFacadeServiceImpl(userClient, categoryService);
  }

}
