package org.aibles.item_service.constant;

import static org.aibles.item_service.constant.ItemApiConstant.ApiConstant.API_PREFIX;
import static org.aibles.item_service.constant.ItemApiConstant.ApiConstant.API_VERSION;
import static org.aibles.item_service.constant.ItemApiConstant.ResourceConstant.*;

public class ItemApiConstant {

  public static class ApiConstant {
    public static final String API_PREFIX = "/api";
    public static final String API_VERSION = "/v1";
  }

  public static class ResourceConstant {
    public static final String TYPE = "/item-types";
    public static final String FIELD = "/item-fields";
    public static final String TYPE_FIELD= "/item-type-fields";
    public static final String ITEM = "/items";
    public static final String FIELD_VALUE = "/item-field-values";

  }

  public static class BaseUrl {
    public static final String TYPE_BASE_URL = API_PREFIX + API_VERSION + TYPE;
    public static final String FIELD_BASE_URL = API_PREFIX + API_VERSION + FIELD;
    public static final String TYPE_FIELD_BASE_URL = API_PREFIX + API_VERSION + TYPE_FIELD;
    public static final String ITEM_BASE_URL = API_PREFIX + API_VERSION + ITEM;
    public static final String FIELD_VALUE_BASE_URL = API_PREFIX + API_VERSION + FIELD_VALUE;
  }
}
