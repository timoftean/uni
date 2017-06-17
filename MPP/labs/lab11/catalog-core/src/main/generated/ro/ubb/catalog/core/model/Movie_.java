package ro.ubb.catalog.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movie.class)
public abstract class Movie_ extends ro.ubb.catalog.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Movie, String> director;
	public static volatile SingularAttribute<Movie, Integer> availableCopies;
	public static volatile SingularAttribute<Movie, String> name;
	public static volatile SingularAttribute<Movie, String> genre;
	public static volatile SetAttribute<Movie, Rent> rents;

}

