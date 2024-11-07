package br.com.estaciopicpay;

import java.math.BigDecimal;

public class RecebimentoDTO {

    private Long lojistaId;  // ID do lojista
    private BigDecimal valor; // Valor a ser recebido

    // Getters e Setters
    public Long getLojistaId() {
        return lojistaId;
    }

    public void setLojistaId(Long lojistaId) {
        this.lojistaId = lojistaId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
