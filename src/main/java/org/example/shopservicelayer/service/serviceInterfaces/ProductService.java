package org.example.shopservicelayer.service.serviceInterfaces;





import org.example.shopservicelayer.dto.SpecValue;
import org.example.shopservicelayer.dto.Specs;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Map<String, List<SpecValue>> fromListToMap(List<Specs> list);


}
