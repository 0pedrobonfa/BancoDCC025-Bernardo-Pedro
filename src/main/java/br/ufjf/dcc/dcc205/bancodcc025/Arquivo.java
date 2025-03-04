package br.ufjf.dcc.dcc205.bancodcc025;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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

        List<Usuario> usuarios = new ArrayList<>();

        try {
            File arquivo = new File(filePath);
            if (arquivo.exists() && arquivo.length() > 0) {  // Evita erro se o arquivo estiver vazio
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                Type type = new TypeToken<Map<String, List<Usuario>>>() {}.getType();
                Map<String, List<Usuario>> jsonMap = gson.fromJson(reader, type);
                reader.close();

                if (jsonMap != null && jsonMap.get("usuarios") != null) {
                    usuarios = jsonMap.get("usuarios");  // Recupera os usuários já cadastrados
                }
            }

            // Adiciona o novo usuário
            usuarios.add(novoUsuario);

            // Atualiza o JSON corretamente usando um HashMap mutável
            Map<String, List<Usuario>> novoJson = new HashMap<>();
            novoJson.put("usuarios", usuarios);

            String jsonAtualizado = gson.toJson(novoJson);

            // Salva no arquivo
            salva(filePath, jsonAtualizado);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

class UsuarioTypeAdapter implements JsonDeserializer<Usuario>, JsonSerializer<Usuario> {
    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String tipoDeUsuario = jsonObject.get("tipoDeUsuario").getAsString();

        if (tipoDeUsuario.equals("cliente")) {
            return context.deserialize(jsonObject, Cliente.class);
        }
//        else if (tipoDeUsuario.equals("caixa")) {
//            return context.deserialize(jsonObject, Caixa.class);
//        } else if (tipoDeUsuario.equals("gerente")) {
//            return context.deserialize(jsonObject, Gerente.class);
//        }
        return null;
    }

    @Override
    public JsonElement serialize(Usuario usuario, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(usuario).getAsJsonObject();

        if (usuario instanceof Cliente) {
            jsonObject.addProperty("tipoDeUsuario", "cliente");
        }
//        else if (usuario instanceof Caixa) {
//            jsonObject.addProperty("tipoDeUsuario", "caixa");
//        } else if (usuario instanceof Gerente) {
//            jsonObject.addProperty("tipoDeUsuario", "gerente");
//        }

        return jsonObject;
    }
}
