package dev.joaov.meubolso;

import dev.joaov.meubolso.model.domain.Financas;
import dev.joaov.meubolso.model.domain.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MeuBolsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuBolsoApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = new Usuario();
        int inputUsuario = 0;

        do {
            if (!usuario.estaCadastrado()) {
                System.out.println("""
                1 - Cadastrar usuario
                2 - Adicionar receita
                3 - Adicionar despesa
                4 - Verificar saldo
                5 - Informações Completas
                6 - Informações Resumida
                7 - Ativar notificações
                0 - Sair
                """);
            } else {
                System.out.println("""
                1 - Adicionar receita
                2 - Adicionar despesa
                3 - Verificar saldo
                4 - Informações Completas
                5 - Informações Resumida
                6 - Ativar notificações
                0 - Sair
                """);

            }
            System.out.print("Digite uma opção: ");
            inputUsuario = scanner.nextInt();
            scanner.nextLine();

            if (usuario.estaCadastrado() && inputUsuario != 0) {
                inputUsuario++;
            }

            switch (inputUsuario) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    System.out.print("Digite seu nome: ");
                    usuario.setNome(scanner.nextLine());

                    System.out.print("Digite seu email: ");
                    usuario.setEmail(scanner.nextLine());

                    System.out.print("Digite seu total de receita até o momento: ");
                    usuario.getFinancas().setTotalReceitas(scanner.nextDouble());
                    scanner.nextLine();

                    System.out.print("Digite seu total de despesas até o momento: ");
                    usuario.getFinancas().setTotalDespesas(scanner.nextDouble());
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
                    usuario.getFinancas().adicionarReceita(valorReceita);
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
                    usuario.getFinancas().adicionarDespesa(valorDespesa);
                    break;
                case 4:
                    if (!usuario.estaCadastrado()) {
                        System.out.println("Operação inválida. Usuario nao está cadastrado.");
                        System.out.println();
                        break;
                    }
                    System.out.printf("Saldo atual: %.2f%n", usuario.getFinancas().getSaldo());
                    System.out.println();
                    break;
                case 5:
                    usuario.mostrarInformacoes();
                    break;
                case 6:
                    boolean ativarModoResumido = true;
                    usuario.mostrarInformacoes(ativarModoResumido);
                    break;
                case 7:
                    usuario.ativarNotificacao();
                    System.out.println("Notificações ativadas.");
                    System.out.println();
                    break;
                default:
                    System.out.println("Opção invalida. Escolha um valor entre 0 e 6.");
            }
        } while (inputUsuario != 0);
        scanner.close();

        Financas financasUsuario1 = new Financas(1200, 5000, 3800);
        Usuario usuario1 = new Usuario("Joao", "joao@email.com", financasUsuario1);

        Financas financasUsuario2 = new Financas(780);
        Usuario usuario2 = new Usuario("Vitor");

        Financas financasUsuario3 = new Financas(700, 700);
        Usuario usuario3 = new Usuario("Igor", "igor@email.com");

        usuario2.setFinancas(financasUsuario2);
        usuario3.setFinancas(financasUsuario3);

        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        for (Usuario u : usuarios) {
            if (u.getFinancas().getSaldo() < 800) {
                continue;
            }
            System.out.println(u);
        }

    }
}
