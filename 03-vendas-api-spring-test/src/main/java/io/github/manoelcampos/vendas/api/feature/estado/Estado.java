package io.github.manoelcampos.vendas.api.feature.estado;

import io.github.manoelcampos.vendas.api.config.ConstraintKeys;
import io.github.manoelcampos.vendas.api.model.AbstractBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = ConstraintKeys.UC_ESTADO_DESCRICAO, columnNames = "descricao"),
})
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Estado extends AbstractBaseModel {
    @NotNull @NotBlank
    private String descricao;

    @NotNull @NotBlank
    private String sigla;

    public Estado(final long id) {
        this.setId(id);
    }

    public Estado(final String descricao) {
        this.setDescricao(descricao);
    }
}
