package com.lucas.rentx.repositories.querys;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lucas.rentx.entities.QCar;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CarRepositoriyQuery {
	
	
	@PersistenceContext
	private EntityManager em;
	
	JPAQueryFactory queryFactory = new JPAQueryFactory(em);
			
	public void updateAvailable(UUID id, boolean available) {
		QCar entity = QCar.car;
		queryFactory.update(entity)
		.set(entity.avaiable, available)
		.where(entity.id.eq(id))
		.execute();		
	}

}
