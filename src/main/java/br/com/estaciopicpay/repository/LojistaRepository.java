package br.com.estaciopicpay.repository;

import br.com.estaciopicpay.Lojista;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class LojistaRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Optional<Lojista> findById(Long id) {
        Lojista lojista = entityManager.find(Lojista.class, id);
        return Optional.ofNullable(lojista);
    }

    @Transactional
    public void persist(Lojista lojista) {
        entityManager.persist(lojista);
    }
}
