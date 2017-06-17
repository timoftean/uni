package ro.ubb.catalog.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rent.class)
public abstract class Rent_ {

	public static volatile SingularAttribute<Rent, Integer> nocopies;
	public static volatile SingularAttribute<Rent, Movie> movie;
	public static volatile SingularAttribute<Rent, Client> client;

}

