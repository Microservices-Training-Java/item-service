package org.aibles.item_service.controller.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.InterFieldDetailResponse;
import org.aibles.item_service.service.ItemFieldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.INTERNAL_ITEM_FIELD_URL;

@Slf4j
@RequestMapping(INTERNAL_ITEM_FIELD_URL)
@RestController
@RequiredArgsConstructor
public class ItemFieldInternalController {

    private final ItemFieldService itemFieldService;

    @GetMapping("/{id}")
    public InterFieldDetailResponse getFieldDetail(@PathVariable("id")String id) {
        log.info("(getFieldDetail)id : {}", id);
        return InterFieldDetailResponse.builder()
                .id(id)
                .name(itemFieldService.getNameById(id))
                .build();
    }
}
