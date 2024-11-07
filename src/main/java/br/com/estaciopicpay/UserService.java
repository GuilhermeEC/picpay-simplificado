package br.com.estaciopicpay;

import br.com.estaciopicpay.repository.LojistaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@ApplicationScoped
public class UserService {
    @Inject
    LojistaRepository lojistaRepository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario){
        entityManager.persist(usuario);
        return usuario;
    }

    @Transactional
    public Optional<Usuario> encontrarUsuario(Long id) {
        return Optional.ofNullable(entityManager.find(Usuario.class, id));
    }

    @Transactional
    public boolean realizarTransferencia(Long deId, Long paraId, BigDecimal valor) {
        Usuario de = entityManager.find(Usuario.class, deId);
        Usuario para = entityManager.find(Usuario.class, paraId);

        if (de == null || para == null) {
            return false; // Usuário não encontrado
        }

        if (!de.podeTransferir()) {
            return false; // O usuário não pode realizar transferências
        }

        // Lógica para realizar a transferência
        if (de.getSaldo().compareTo(valor) >= 0) { // Use compareTo para comparação
            de.setSaldo(de.getSaldo().subtract(valor)); // Use subtract para BigDecimal
            para.setSaldo(para.getSaldo().add(valor)); // Use add para BigDecimal
            entityManager.merge(de);
            entityManager.merge(para);
            return true; // Transferência realizada com sucesso
        }

        return false; // Saldo insuficiente
    }

    @Transactional
    public boolean receberPagamento(Long lojistaId, BigDecimal valor) {
        Optional<Lojista> lojistaOpt = lojistaRepository.findById(lojistaId);
        if (lojistaOpt.isPresent() && valor.compareTo(BigDecimal.ZERO) > 0) {
            Lojista lojista = lojistaOpt.get();
            lojista.setSaldo(lojista.getSaldo().add(valor)); // Usando add para somar BigDecimal
            lojistaRepository.persist(lojista);
            return true;
        }
        return false;
    }
}


