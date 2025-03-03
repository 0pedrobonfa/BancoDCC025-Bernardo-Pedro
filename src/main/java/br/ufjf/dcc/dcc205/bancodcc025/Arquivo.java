package br.ufjf.dcc.dcc205.bancodcc025;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Arquivo {

    public void leCliente(String filePath) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Usuario.class, new UsuarioTypeAdapter())
                    .create();

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Desserializa o JSON para um Map
            Type type = new TypeToken<Map<String, List<Usuario>>>() {}.getType();
            Map<String, List<Usuario>> jsonMap = gson.fromJson(reader, type);

            // Obtém a lista de usuários
            List<Usuario> usuarios = jsonMap.get("usuarios");

            // Processa apenas os usuários do tipo "cliente"
            for (Usuario usuario : usuarios) {
                if (usuario instanceof Cliente) {
                    Cliente cliente = (Cliente) usuario;
                    System.out.println("Nome: " + cliente.getNome());
                    System.out.println("Saldo: " + cliente.getSaldoAtual());
                    //System.out.println("Extrato: " + cliente.getExtrato());
                    System.out.println("-----");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void salva(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void adicionaUsuario(String filePath, Usuario novoUsuario) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Usuario.class, new UsuarioTypeAdapter())
                .setPrettyPrinting()
                .create();

        List<Usuario> usuarios;

        try {
            File arquivo = new File(filePath);
            if (arquivo.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                Type type = new TypeToken<Map<String, List<Usuario>>>() {}.getType();
                Map<String, List<Usuario>> jsonMap = gson.fromJson(reader, type);
                reader.close();

                // Obtém a lista de usuários (caso exista)
                usuarios = jsonMap.get("usuarios");
                if (usuarios == null) {
                    usuarios = new ArrayList<>();
                }
            } else {
                // Se o arquivo não existir, cria uma nova lista
                usuarios = new ArrayList<>();
            }

            // Adiciona o novo usuário
            usuarios.add(novoUsuario);

            // Atualiza o JSON
            Map<String, List<Usuario>> novoJson = Map.of("usuarios", usuarios);
            String jsonAtualizado = gson.toJson(novoJson);

            // Salva no arquivo
            salva(filePath, jsonAtualizado);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class UsuarioTypeAdapter implements JsonDeserializer<Usuario> {
    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String tipoDeUsuario = jsonObject.get("tipoDeUsuario").getAsString();

        // Decide qual classe derivada instanciar com base no tipoDeUsuario

            if (tipoDeUsuario.equals("cliente")){
                return context.deserialize(jsonObject, Cliente.class);
            // Adicionar casos para gerente e caixa
        }
        return null;
    }
}