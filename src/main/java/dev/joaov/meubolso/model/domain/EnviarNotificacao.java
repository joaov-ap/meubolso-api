package dev.joaov.meubolso.model.domain;

public abstract class EnviarNotificacao implements Notificador {
    protected String mensagem;

    public EnviarNotificacao() {
    }

    public EnviarNotificacao(String mensagem) {
        this.mensagem = mensagem;
    }
}
