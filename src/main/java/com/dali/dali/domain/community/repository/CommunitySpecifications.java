package com.dali.dali.domain.community.repository;

import com.dali.dali.domain.city.entity.City;
import com.dali.dali.domain.community.entity.AMPM;
import com.dali.dali.domain.community.entity.Community;
import com.dali.dali.domain.community.entity.Gender;
import com.dali.dali.domain.community.entity.Time;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class CommunitySpecifications {
    public static Specification<Community> hasGender(Gender gender) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("gender"), gender);
    }

    public static Specification<Community> hasTime(Time time) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("time"), time);
    }

    public static Specification<Community> hasAmpm(AMPM ampm) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ampm"), ampm);
    }

    public static Specification<Community> hasSido(String sido) {
        return (root, query, criteriaBuilder) -> {
            Join<Community, City> cityJoin = root.join("city");
            return criteriaBuilder.equal(cityJoin.get("sido"), sido);
        };
    }

    public static Specification<Community> hasSigungu(String sigungu) {
        return (root, query, criteriaBuilder) -> {
            Join<Community, City> cityJoin = root.join("city");
            return criteriaBuilder.equal(cityJoin.get("sigungu"), sigungu);
        };
    }

    public static Specification<Community> hasDong(String dong) {
        return (root, query, criteriaBuilder) -> {
            Join<Community, City> cityJoin = root.join("city");
            return criteriaBuilder.equal(cityJoin.get("dong"), dong);
        };
    }

}