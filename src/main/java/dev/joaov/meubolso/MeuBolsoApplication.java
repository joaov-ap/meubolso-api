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

        System.out.print("Digite seu nome: ");
        usuario.nome = scanner.nextLine();

        System.out.print("Digite seu email: ");
        usuario.email = scanner.nextLine();

        System.out.print("Digite seu total de receita até o momento: ");
        usuario.totalReceitas = scanner.nextDouble();

        System.out.print("Digite seu total de despesas até o momento: ");
        usuario.totalDespesas = scanner.nextDouble();

        usuario.mostrarInformacoes();
    }

}
