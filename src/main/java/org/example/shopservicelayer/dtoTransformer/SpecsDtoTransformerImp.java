package org.example.shopservicelayer.dtoTransformer;

import org.example.shopservicelayer.dto.SpecValue;
import org.example.shopservicelayer.dto.Specs;
import org.example.shopservicelayer.dtoTransformer.dtoTransormInterfaces.SpecsDtoTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpecsDtoTransformerImp implements SpecsDtoTransformer {

    @Override
//    public void specsTransform(Map<Long, Specs>specsMap,Object... tuples){
    public List<Specs> specsTransform(Map<Long, Specs> specsMap, Object... tuples){
        Long specId = (Long) tuples[0];
        specsMap.computeIfAbsent(specId, k -> new Specs(specId, (String) tuples[1]))
                .getProductSpecValues().add(new SpecValue((Long) tuples[2], (String) tuples[3], specId));
        return specsMap.values().stream().toList();
    }
}
