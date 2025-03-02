package br.ufjf.dcc.dcc205.bancodcc025;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
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