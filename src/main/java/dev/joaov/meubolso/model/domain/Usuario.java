package dev.joaov.meubolso.model.domain;

import dev.joaov.meubolso.model.exceptions.UsuarioInvalidoException;

import java.util.Scanner;

public class Usuario {
    private String nome;
    private String email;
    private Financas financas;
    private boolean recebeNotificacoes;
    private Notificador notificador;

    public Usuario() {
        this.financas = new Financas();
    }

    public Usuario(String nome) throws UsuarioInvalidoException {
        this();

        if (nome == null) {
            throw new UsuarioInvalidoException("Nome não pode ser null.");
        }
        this.nome = nome;
    }

    public Usuario(String nome, String email) throws UsuarioInvalidoException {
        this(nome);
        if (email == null) {
            throw new UsuarioInvalidoException("Email não pode ser null.");
        }
        this.email = email;
    }

    public Usuario(String nome, String email, Financas financas) {
        this(nome, email);
        this.financas = financas;
    }

    public Usuario(String nome, String email, Financas financas, boolean recebeNotificacoes) {
        this(nome, email, financas);
        this.recebeNotificacoes = recebeNotificacoes;
    }

    public void mostrarInformacoes() {
        this.mostrarInformacoes(false);
    }

    public void mostrarInformacoes(boolean modoResumido) {
        if (!estaCadastrado()) {
            System.out.println("Usuario nao está cadastrado.");
            System.out.println();
            return;
        }

        double saldo = financas.getSaldo();
        double totalReceitas = financas.getTotalReceitas();
        double totalDespesas = financas.getTotalDespesas();

        if (recebeNotificacoes) {
            notificador.notificacao();
        }

        if (modoResumido) {
            System.out.printf("%nBem Vindo ao seu Dashboard Resumido, %s%nTotal de Receitas: %.2f%nTotal de Despesas: %.2f%nSaldo: %.2f%n",
                    nome, totalReceitas, totalDespesas, saldo);
        } else {
            int score = financas.getPontuacaoScore();

            System.out.printf("%nBem Vindo ao seu Dashboard, %s%nEmail Cadastrado: %s%nTotal de Receitas: %.2f%nTotal de Despesas: %.2f%nSaldo: %.2f%nNotificacoes Ligadas: %b%nScore: %d%n",
                    nome, email, totalReceitas, totalDespesas, saldo, recebeNotificacoes, score);
        }

        System.out.println();
    }

    public void ativarNotificacao() {
        escolherNotificador();
        recebeNotificacoes = true;
    }

    public String textoFormatadoParaSalvar() {
        return "%s;%s;%s;%s;%s".formatted(nome, email, financas.getSaldo(), financas.getTotalReceitas(), financas.getTotalDespesas());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", financas=" + financas +
                '}';
    }

    public boolean estaCadastrado() {
        return nome != null && email != null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Financas getFinancas() {
        return financas;
    }

    public void setFinancas(Financas financas) {
        this.financas = financas;
    }

    public boolean isNotificacaoAtiva() {
        return recebeNotificacoes;
    }

    private Notificador escolherNotificador() {
        Notificador notificadorEscolhido = null;
        System.out.println("""
                1 - Email
                2 - SMS
                """);

        Scanner scanner = new Scanner(System.in);
        int inputUsuario = scanner.nextInt();
        scanner.nextLine();
        switch (inputUsuario) {
            case 1:
                System.out.println("Tipo de Notificador: EMAIL");
                notificadorEscolhido = new EnviarNotificacaoEmail("Novo relatório recebido no Email.");
                break;
            case 2:
                System.out.println("Tipo de Notificador: SMS");
                notificadorEscolhido = new EnviarNotificacaoSMS("Novo relatório recebido no SMS.");
                break;
            default:
                System.out.println("Escolha inválida.");
                break;
        }
        this.notificador = notificadorEscolhido;
        return notificadorEscolhido;
    }
}
