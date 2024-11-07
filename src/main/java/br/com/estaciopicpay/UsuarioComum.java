package br.com.estaciopicpay;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "USUARIO_COMUM")
public class UsuarioComum extends Usuario {
    @Override
    public boolean podeTransferir() {
        return true; // Usuário comum pode transferir
    }
}
