package dev.joaov.meubolso;

import dev.joaov.meubolso.model.domain.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MeuBolsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuBolsoApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = new Usuario();
        int inputUsuario = 0;

        do {
            System.out.println("""
                1 - Cadastrar usuario
                2 - Adicionar receita
                3 - Adicionar despesa
                4 - Verificar saldo
                5 - Resumo
                0 - Sair
                """);

            System.out.print("Digite uma opção: ");
            inputUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (inputUsuario) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    System.out.print("Digite seu nome: ");
                    usuario.nome = scanner.nextLine();

                    System.out.print("Digite seu email: ");
                    usuario.email = scanner.nextLine();

                    System.out.print("Digite seu total de receita até o momento: ");
                    usuario.totalReceitas = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Digite seu total de despesas até o momento: ");
                    usuario.totalDespesas = scanner.nextDouble();
                    scanner.nextLine();

                    usuario.mostrarInformacoes();
                    break;
                case 2:
                    if (!usuario.estaCadastrado()) {
                        System.out.println("Operação inválida. Usuario nao está cadastrado.");
                        System.out.println();
                        break;
                    }
                    System.out.print("Digite o valor da receita: ");
                    double valorReceita = scanner.nextDouble();
                    scanner.nextLine();
                    usuario.adicionarReceita(valorReceita);
                    break;
                case 3:
                    if (!usuario.estaCadastrado()) {
                        System.out.println("Operação inválida. Usuario nao está cadastrado.");
                        System.out.println();
                        break;
                    }
                    System.out.print("Digite o valor da despesa: ");
                    double valorDespesa = scanner.nextDouble();
                    scanner.nextLine();
                    usuario.adicionarDespesa(valorDespesa);
                    break;
                case 4:
                    if (!usuario.estaCadastrado()) {
                        System.out.println("Operação inválida. Usuario nao está cadastrado.");
                        System.out.println();
                        break;
                    }
                    System.out.printf("Saldo atual: %.2f%n", usuario.saldo);
                    System.out.println();
                    break;
                case 5:
                    usuario.mostrarInformacoes();
                    break;
                default:
                    System.out.println("Opção invalida. Escolha um valor entre 0 e 5.");
            }
        } while (inputUsuario != 0);
        scanner.close();
    }
}
