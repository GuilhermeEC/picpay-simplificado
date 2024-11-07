package br.com.estaciopicpay;

import java.math.BigDecimal;

public class TransferenciaDTO {

    private Long deId;
    private Long paraId;
    private BigDecimal valor;

    // Getters e Setters
    public Long getDeId() {
        return deId;
    }

    public void setDeId(Long deId) {
        this.deId = deId;
    }

    public Long getParaId() {
        return paraId;
    }

    public void setParaId(Long paraId) {
        this.paraId = paraId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
