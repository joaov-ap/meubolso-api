package dev.joaov.meubolso.model.services;

import dev.joaov.meubolso.model.domain.Financas;
import dev.joaov.meubolso.model.domain.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<Usuario> readFile(File file) {
        List<Usuario> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] usuarioEncontrado = line.split(";");

                Financas financasUsuario = new Financas(Double.valueOf(usuarioEncontrado[2]),
                        Double.valueOf(usuarioEncontrado[3]), Double.valueOf(usuarioEncontrado[4]));
                Usuario usuario = new Usuario(usuarioEncontrado[0], usuarioEncontrado[1], financasUsuario);
                list.add(usuario);
            }

        } catch (FileNotFoundException e) {
            System.err.println("[ERRO] Arquivo: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("[ERRO] Arquivo: " + e.getMessage());
        }

        return list;
    }

    public static String saveFile(File file, String textoToWrite) {
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            writer.write(textoToWrite);
            writer.flush();
        } catch (FileNotFoundException e) {
            System.err.println("[ERRO] Arquivo: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("[ERRO] Arquivo: " + e.getMessage());
        }

        return "Arquivo %s salvo com sucesso.".formatted(file.getName());
    }
}
