package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.core.model.Client;

import java.io.Serializable;

/**
 * Created by macbookpro on 4/9/17.
 */

@Transactional
public interface ClientRepository extends JpaRepository<Client,Long> {
}