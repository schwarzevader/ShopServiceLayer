package org.example.shopservicelayer.service.serviceInterfaces;

import org.example.shopservicelayer.dto.CategoriesAndSpecsDto;

import java.util.List;

public interface CategoriesAndSpecsService {

    List<CategoriesAndSpecsDto> getAllCategories(boolean cacheable);
}
