package org.example.shopservicelayer.dtoTransformer.dtoTransormInterfaces;

import org.example.shopservicelayer.dto.Specs;

import java.util.List;
import java.util.Map;

public interface SpecsDtoTransformer {
//    void specsTransform(Map<Long, Specs> specsMap, Object... tuples);

    List<Specs> specsTransform(Map<Long, Specs> specsMap, Object... tuples);
}
