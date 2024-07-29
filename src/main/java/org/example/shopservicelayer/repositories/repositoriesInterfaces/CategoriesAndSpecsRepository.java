package org.example.shopservicelayer.repositories.repositoriesInterfaces;

import org.example.shopservicelayer.dto.CategoriesAndSpecsDto;

import java.util.List;

public interface CategoriesAndSpecsRepository {

    List<CategoriesAndSpecsDto> getAllCategories(boolean cacheable);
}
