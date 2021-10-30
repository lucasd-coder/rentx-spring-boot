package com.lucas.rentx.repositories.querys.impl;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.lucas.rentx.entities.Car;
import com.lucas.rentx.entities.QCar;
import com.lucas.rentx.repositories.CarRepository;
import com.lucas.rentx.repositories.querys.CarRepositoriyQuery;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CarRepositoriyQueryImpl implements CarRepositoriyQuery {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CarRepository carRepository;

	JPAQueryFactory queryFactory = new JPAQueryFactory(em);

	@Override
	public void updateAvailable(UUID id, boolean available) {
		QCar entity = QCar.car;
		queryFactory.update(entity).set(entity.avaiable, available).where(entity.id.eq(id)).execute();
	}

	@Override
	public Page<Car> findAvailable(String name, String brand, UUID categoryId, Pageable pageable) {
		QCar entity = QCar.car;
		BooleanBuilder where = new BooleanBuilder();

		where.and(entity.avaiable.eq(true));

		if (brand != null) {
			where.and(entity.brand.eq(brand));
		}

		if (name != null) {
			where.and(entity.name.eq(name));
		}

		if (categoryId != null) {
			where.and(entity.category.id.eq(categoryId));
		}

		return carRepository.findAll(where ,pageable);
	}

}
