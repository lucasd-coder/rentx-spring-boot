package com.lucas.rentx.repositories.customization;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.lucas.rentx.entities.Car;
import com.lucas.rentx.repositories.metamodel.Car_;

public class CarRepositorySpecification {

	public static Specification<Car> findByAvailableTrue(String brand, String name, UUID categoryId) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (brand != null) {
				predicates.add(criteriaBuilder.equal(root.get("brand"), brand));
			}
			if (name != null) {
				predicates.add(criteriaBuilder.equal(root.get("name"), name));
			}

			if (categoryId != null) {
				predicates.add(criteriaBuilder.equal(root.get("category_id"), categoryId));
			}

			return criteriaQuery.where(criteriaBuilder.equal(root.get(Car_.avaiable), true))
					.subquery(Car.class)
					.where(predicates.toArray(new Predicate[0])).getRestriction();

		};
	}

}
