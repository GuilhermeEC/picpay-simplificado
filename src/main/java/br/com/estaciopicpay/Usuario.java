package br.com.estaciopicpay;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal saldo = BigDecimal.ZERO;

    public abstract boolean podeTransferir();

    public void realizarTransferencia(Usuario destinatario, BigDecimal valor) {
        if (!this.podeTransferir()) {
            throw new UnsupportedOperationException("Este usuário não tem permissão para transferir.");
        }
        if (saldo.compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        this.saldo = this.saldo.subtract(valor);
        destinatario.receberTransferencia(valor);
    }

    public void receberTransferencia(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}



