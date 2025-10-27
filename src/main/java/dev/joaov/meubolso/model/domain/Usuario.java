package dev.joaov.meubolso.model.domain;

public class Usuario {
    public String nome;
    public String email;
    public double saldo;
    public double totalReceitas;
    public double totalDespesas;

    private double calcularSaldo() {
        return totalDespesas - totalReceitas;
    }

    public void mostrarInformacoes() {
        saldo = calcularSaldo();
        System.out.printf("%nBem Vindo ao seu Dashboard, %s%nEmail Cadastrado: %s%nTotal de Receitas: %.2f%nTotal de Despesas: %.2f%nSaldo: %.2f%n", nome, email, totalReceitas, totalDespesas, saldo);
    }
}
