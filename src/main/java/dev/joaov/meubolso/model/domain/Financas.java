package dev.joaov.meubolso.model.domain;

import java.util.Scanner;

public class Financas {
    private final double PORCENTAGEM_TAXA_SCORE = 0.7;
    private final double ACRESCIMO_SCORE = 1.2;

    private double saldo;
    private double totalReceitas;
    private double totalDespesas;
    private int pontuacaoScore;

    public Financas() {
        this.pontuacaoScore = 1000;
    }

    public Financas(double saldo) {
        this.saldo = saldo;
    }

    public Financas(double saldo, double totalReceitas) {
        this(saldo);
        this.totalReceitas = totalReceitas;
    }

    public Financas(double saldo, double totalReceitas, double totalDespesas) {
        this(saldo, totalReceitas);
        this.totalDespesas = totalDespesas;
    }

    public Financas(double saldo, double totalReceitas, double totalDespesas, int pontuacaoScore) {
        this(saldo, totalReceitas, totalDespesas);
        this.pontuacaoScore = pontuacaoScore;
    }

    public void adicionarReceita(double valor) {
        escolherTipoReceita();
        totalReceitas += valor;
    }

    public void adicionarDespesa(double valor) {
        escolherTipoDespesa();
        totalDespesas += valor;
    }

    private double calcularSaldo() {
        return totalReceitas - totalDespesas;
    }

    private void calcularScore() {
        if (saldo < 0) {
            this.pontuacaoScore = this.pontuacaoScore - (int) (Math.abs(saldo) * PORCENTAGEM_TAXA_SCORE);
        } else {
            this.pontuacaoScore = this.pontuacaoScore + (int) (saldo * ACRESCIMO_SCORE);
        }

        if (this.pontuacaoScore > 1000) {
            this.pontuacaoScore = 1000;
        } else if (this.pontuacaoScore < 0) {
            this.pontuacaoScore = 0;
        }
    }

    @Override
    public String toString() {
        return "Financas{" +
                "saldo=" + saldo +
                ", totalReceitas=" + totalReceitas +
                ", totalDespesas=" + totalDespesas +
                '}';
    }

    public double getSaldo() {
        this.saldo = calcularSaldo();
        return saldo;
    }

    public double getTotalReceitas() {
        return totalReceitas;
    }

    public void setTotalReceitas(double totalReceitas) {
        this.totalReceitas = totalReceitas;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public int getPontuacaoScore() {
        calcularScore();
        return pontuacaoScore;
    }

    private TipoReceita escolherTipoReceita() {
        TipoReceita tipoReceita = null;
        System.out.println("""
                1 - Salario
                2 - Serviço
                3 - Venda
                4 - Investimento
                5 - Outro
                """);

        Scanner scanner = new Scanner(System.in);
        int inputUsuario = scanner.nextInt();
        scanner.nextLine();
        switch (inputUsuario) {
            case 1:
                System.out.println("Tipo de Receita: SALARIO");
                tipoReceita = TipoReceita.SALARIO;
                break;
            case 2:
                System.out.println("Tipo de Receita: SERVIÇO");
                tipoReceita = TipoReceita.SERVICO;
                break;
            case 3:
                System.out.println("Tipo de Receita: VENDA");
                tipoReceita = TipoReceita.VENDA;
                break;
            case 4:
                System.out.println("Tipo de Receita: INVESTIMENTO");
                tipoReceita = TipoReceita.INVESTIMENTO;
                break;
            case 5:
                System.out.println("EscoTipo de Receita: OUTRO");
                tipoReceita = TipoReceita.OUTRO;
                break;
            default:
                System.out.println("Escolha inválida.");
                break;
        }

        return tipoReceita;
    }

    private TipoDespesa escolherTipoDespesa() {
        TipoDespesa tipoDespesa = null;
        System.out.println("""
                1 - Contas
                2 - Transporte
                3 - Lazer
                4 - Alimentação
                5 - Outros
                """);

        Scanner scanner = new Scanner(System.in);
        int inputUsuario = scanner.nextInt();
        scanner.nextLine();
        switch (inputUsuario) {
            case 1:
                System.out.println("Tipo de Receita: CONTAS");
                tipoDespesa = TipoDespesa.CONTAS;
                break;
            case 2:
                System.out.println("Tipo de Receita: TRANSPORTE");
                tipoDespesa = TipoDespesa.TRANSPORTE;
                break;
            case 3:
                System.out.println("Tipo de Receita: LAZER");
                tipoDespesa = TipoDespesa.LAZER;
                break;
            case 4:
                System.out.println("Tipo de Receita: ALIMENTACAO");
                tipoDespesa = TipoDespesa.ALIMENTACAO;
                break;
            case 5:
                System.out.println("EscoTipo de Receita: OUTROS");
                tipoDespesa = TipoDespesa.OUTROS;
                break;
            default:
                System.out.println("Escolha inválida.");
                break;
        }

        return tipoDespesa;
    }
}
