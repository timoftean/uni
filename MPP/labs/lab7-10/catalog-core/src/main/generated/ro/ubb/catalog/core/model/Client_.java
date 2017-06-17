package ro.ubb.catalog.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ extends ro.ubb.catalog.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Client, Integer> cnp;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SetAttribute<Client, Rent> rents;

}

