package com.lucas.rentx.repositories.metamodel;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.lucas.rentx.entities.Car;
import com.lucas.rentx.entities.Category;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Car.class)
public abstract class Car_ {
	public static volatile SingularAttribute<Car, UUID> id;
	public static volatile SingularAttribute<Car, String> name;
	public static volatile SingularAttribute<Car, String> description;
	public static volatile SingularAttribute<Car, Integer> dailyRate;
	public static volatile SingularAttribute<Car, Boolean> avaiable;
	public static volatile SingularAttribute<Car, String> licensePlate;
	public static volatile SingularAttribute<Car, Integer> fineAmount;
	public static volatile SingularAttribute<Car, String> brand;
	public static volatile SingularAttribute<Car, Category> category;
	public static volatile SingularAttribute<Car, LocalDateTime> createdAt;

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String DAILY_RATE = "dailyRate";
	public static final String AVAIABLE= "avaiable";
	public static final String LICENSE_PLATE="licensePlate";
	public static final String FINE_AMOUNT = "fineAmount";
	public static final String BRAND = "brand";
	public static final String CATEGORY = "category";
	public static final String CREATEDAT = "createdAt";

}
