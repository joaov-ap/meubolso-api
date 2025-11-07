package dev.joaov.meubolso.model.domain;

public class Usuario {
    public String nome;
    public String email;
    public double saldo;
    public double totalReceitas;
    public double totalDespesas;

    private double calcularSaldo() {
        return totalReceitas - totalDespesas;
    }

    public void mostrarInformacoes() {
        if (!estaCadastrado()) {
            System.out.println("Usuario nao está cadastrado.");
            System.out.println();
            return;
        }
        saldo = calcularSaldo();
        System.out.printf("%nBem Vindo ao seu Dashboard, %s%nEmail Cadastrado: %s%nTotal de Receitas: %.2f%nTotal de Despesas: %.2f%nSaldo: %.2f%n", nome, email, totalReceitas, totalDespesas, saldo);
        System.out.println();
    }

    public void adicionarReceita(double valor) {
        totalReceitas += valor;
    }

    public void adicionarDespesa(double valor) {
        totalDespesas += valor;
    }

    public boolean estaCadastrado() {
        return nome != null && email != null;
    }
}
