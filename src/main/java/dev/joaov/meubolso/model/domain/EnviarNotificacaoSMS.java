package dev.joaov.meubolso.model.domain;

public class EnviarNotificacaoSMS extends EnviarNotificacao {
    public EnviarNotificacaoSMS() {
    }

    public EnviarNotificacaoSMS(String mensagem) {
        super(mensagem);
    }

    @Override
    public void notificacao() {
        System.out.println("Notificação SMS: " + mensagem);
    }
}
