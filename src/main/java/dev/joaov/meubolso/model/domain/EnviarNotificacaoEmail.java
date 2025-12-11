package dev.joaov.meubolso.model.domain;

public class EnviarNotificacaoEmail extends EnviarNotificacao {

    public EnviarNotificacaoEmail() {
    }

    public EnviarNotificacaoEmail(String mensagem) {
        super(mensagem);
    }

    @Override
    public void notificacao() {
        System.out.println("Notificação Email: " + mensagem);
    }
}
