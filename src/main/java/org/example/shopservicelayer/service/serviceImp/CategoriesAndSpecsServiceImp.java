package org.example.shopservicelayer.service.serviceImp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.shopservicelayer.dto.CategoriesAndSpecsDto;
import org.example.shopservicelayer.repositories.repositoriesInterfaces.CategoriesAndSpecsRepository;
import org.example.shopservicelayer.service.serviceInterfaces.CategoriesAndSpecsService;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class CategoriesAndSpecsServiceImp implements CategoriesAndSpecsService {

    private CategoriesAndSpecsRepository categoriesAndSpecsRepository;

    public  List<CategoriesAndSpecsDto> getAllCategories(boolean cacheable){
        return categoriesAndSpecsRepository.getAllCategories(cacheable);
    }
}
