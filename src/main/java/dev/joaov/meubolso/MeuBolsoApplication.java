package dev.joaov.meubolso;

import dev.joaov.meubolso.model.domain.Financas;
import dev.joaov.meubolso.model.domain.Usuario;
import dev.joaov.meubolso.model.exceptions.UsuarioInvalidoException;
import dev.joaov.meubolso.model.services.FileHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MeuBolsoApplication {

	public static void main(String[] args) {
        File arquivo = new File("usuarios.txt");
		SpringApplication.run(MeuBolsoApplication.class, args);
        List<Usuario> usuarios = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        try {
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
                8 - Salvar informações
                0 - Sair
                """);
                } else if (!usuario.isNotificacaoAtiva()){
                    System.out.println("""
                1 - Adicionar receita
                2 - Adicionar despesa
                3 - Verificar saldo
                4 - Informações Completas
                5 - Informações Resumida
                6 - Ativar notificações
                7 - Salvar informações
                0 - Sair
                """);
                } else {
                    System.out.println("""
                1 - Adicionar receita
                2 - Adicionar despesa
                3 - Verificar saldo
                4 - Informações Completas
                5 - Informações Resumida
                6 - Trocar notificador
                7 - Salvar informações
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
                        usuarios.add(usuario);
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
                        break;
                    case 8:
                        FileHandler.saveFile(arquivo, usuario.textoFormatadoParaSalvar());
                        break;
                    default:
                        System.out.println("Opção invalida. Escolha um valor entre 0 e 7.");
                }
            } while (inputUsuario != 0);
            scanner.close();
        } catch (UsuarioInvalidoException e) {
            System.err.println("[ERRO] Usuario: " + e.getMessage());
        } finally {
            System.out.println("Sistema finalizado.");
        }


        Financas financasUsuario1 = new Financas(1200, 5000, 3800);
        Usuario usuario1 = new Usuario("Joao", "joao@email.com", financasUsuario1);

        Financas financasUsuario2 = new Financas(780);
        Usuario usuario2 = new Usuario("Vitor");

        Financas financasUsuario3 = new Financas(700, 700);
        Usuario usuario3 = new Usuario("Igor", "igor@email.com");

        usuario2.setFinancas(financasUsuario2);
        usuario3.setFinancas(financasUsuario3);

        Financas[] financasList = new Financas[3];
        financasList[0] = financasUsuario1;
        financasList[1] = financasUsuario2;
        financasList[2] = financasUsuario3;

        for (Financas f : financasList) {
            System.out.println(f);
        }

        List<Usuario> tempUsuarios = new ArrayList<>();
        tempUsuarios.add(usuario1);
        tempUsuarios.add(usuario2);
        tempUsuarios.add(usuario3);

        for (Usuario u : tempUsuarios) {
            if (u.getFinancas().getSaldo() < 800) {
                continue;
            }
            System.out.println(u);
        }

        System.out.println();
        System.out.println("usuarios salvo no arquivo, agora convertidos para Classe");
        List<Usuario> usuariosDoArquivo = FileHandler.readFile(arquivo);
        for (Usuario u : usuariosDoArquivo) {
            System.out.println(u);
        }

    }
}
