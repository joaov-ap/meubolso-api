package dev.joaov.meubolso.model.domain;

public class Usuario {
    private String nome;
    private String email;
    private Financas financas;

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

    public void mostrarInformacoes() {
        if (!estaCadastrado()) {
            System.out.println("Usuario nao está cadastrado.");
            System.out.println();
            return;
        }
        double saldo = financas.getSaldo();
        double totalReceitas = financas.getTotalReceitas();
        double totalDespesas = financas.getTotalDespesas();
        System.out.printf("%nBem Vindo ao seu Dashboard, %s%nEmail Cadastrado: %s%nTotal de Receitas: %.2f%nTotal de Despesas: %.2f%nSaldo: %.2f%n", nome, email, totalReceitas, totalDespesas, saldo);
        System.out.println();
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
}
