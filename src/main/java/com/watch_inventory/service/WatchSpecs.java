package com.watch_inventory.service;

import com.watch_inventory.entity.Watch;
import com.watch_inventory.entity.enums.BoxMaterial;
import com.watch_inventory.entity.enums.GlassType;
import com.watch_inventory.entity.enums.MovementType;
import org.springframework.data.jpa.domain.Specification;

public class WatchSpecs {

    private WatchSpecs() {}

    public static boolean blank(String str) {
        return str == null || str.isBlank();
    }
    
    public static Specification<Watch> all() {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.conjunction());
    }
    
    public static Specification<Watch> search(String term) {
        if (blank(term)) return all();
        String like = "%" + term.toLowerCase() + "%";
        return (root, q, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("mark")), like),
                    cb.like(cb.lower(root.get("model")), like),
                    cb.like(cb.lower(root.get("reference")), like)
                );
    }
    
    public static Specification<Watch> markEqual(String mark) {
        if (blank(mark)) return all();
        return ((root, query, criteriaBuilder) -> 
                criteriaBuilder.equal(root.get("mark"), mark));
    }


    public static Specification<Watch> movementTypeEqual(MovementType movementType) {
        if (movementType == null) return all();
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("movementType"), movementType));
    }

    public static Specification<Watch> boxMaterialEqual(BoxMaterial boxMaterial) {
        if (boxMaterial == null) return all();
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("boxMaterial"), boxMaterial));
    }

    public static Specification<Watch> glassTypeEqual(GlassType glassType) {
        if (glassType == null) return all();
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("glassType"), glassType));
    }

    public static Specification<Watch> waterResistanceBetween(Integer min, Integer max) {
        if (min == null && max == null) return all();
        return ((root, query, criteriaBuilder) -> {
            if (min != null && max != null)
                    return criteriaBuilder.between(root.get("waterResistanceM"), min, max);
            if (min != null)
                    return criteriaBuilder.greaterThan(root.get("waterResistanceM"), min);
            return criteriaBuilder.lessThan(root.get("waterResistanceM"), max);
        });
    }

    public static Specification<Watch> priceBetween(Long min, Long max) {
        if (min == null && max == null) return all();
        return ((root, query, criteriaBuilder) -> {
            if (min != null && max != null)
                return criteriaBuilder.between(root.get("priceInCents"), min, max);
            if (min != null)
                return criteriaBuilder.greaterThan(root.get("priceInCents"), min);
            return criteriaBuilder.lessThan(root.get("priceInCents"), max);
        });
    }

    public static Specification<Watch> thicknessBetween(Integer min, Integer max) {
        if (min == null && max == null) return all();
        return ((root, query, criteriaBuilder) -> {
            if (min != null && max != null)
                return criteriaBuilder.between(root.get("thicknessMm"), min, max);
            if (min != null)
                return criteriaBuilder.greaterThan(root.get("thicknessMm"), min);
            return criteriaBuilder.lessThan(root.get("thicknessMm"), max);
        });
    }
}
