package pl.com.bottega.cineman.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cineman.application.DbCleaner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class JPADbCleaner implements DbCleaner {

	private static final String TRUNCATE_SCHEMA_QUERY = "TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK";

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void clean() {
		entityManager.createNativeQuery(TRUNCATE_SCHEMA_QUERY).executeUpdate();
	}

}
