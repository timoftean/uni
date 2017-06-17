package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.core.model.Client;

import java.io.Serializable;

/**
 * Created by Nicu on 4/9/17.
 */

public interface ClientRepository extends RentRepository<Client,Long> {
}
