package dev.joaov.meubolso.model.domain;

public class Usuario {
    private String nome;
    private String email;
    private Financas financas;
    private boolean recebeNotificacoes;

    public Usuario() {
        this.financas = new Financas();
    }

    public Usuario(String nome) {
        this();
        this.nome = nome;
    }

    public Usuario(String nome, String email) {
        this(nome);
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
        recebeNotificacoes = true;
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
}
