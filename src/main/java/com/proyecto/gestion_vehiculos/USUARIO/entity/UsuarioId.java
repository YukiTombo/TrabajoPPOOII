package com.proyecto.gestion_vehiculos.USUARIO.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioId implements Serializable {

        private Long personaId;
    private String login;

    public UsuarioId() {
    }

    public UsuarioId(Long personaId, String login) {
        this.personaId = personaId;
        this.login = login;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioId)) return false;
        UsuarioId that = (UsuarioId) o;
        return Objects.equals(personaId, that.personaId) &&
               Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personaId, login);
    }

}
