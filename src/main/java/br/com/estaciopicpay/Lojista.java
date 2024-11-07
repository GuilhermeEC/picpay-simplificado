
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Outros campos e métodos
}

@Entity
@DiscriminatorValue("lojista") // Valor a ser usado no campo de discriminação
public class Lojista extends Usuario {
    // Campos específicos da classe Lojista
}
