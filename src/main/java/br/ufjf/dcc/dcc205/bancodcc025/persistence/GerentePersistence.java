package br.ufjf.dcc.dcc205.bancodcc025.persistence;

import br.ufjf.dcc.dcc205.bancodcc025.model.Gerente;
import static br.ufjf.dcc.dcc205.bancodcc025.persistence.Persistence.DIRECTORY;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GerentePersistence implements Persistence<Gerente> {

    private static final String PATH = DIRECTORY+ File.separator +"usuarios.json";
    @Override
    public void save(List<Gerente> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

//        System.out.println("JSON gerado: " + json);
//        System.out.println("Caminho do arquivo: " + new File(PATH).getAbsolutePath());

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);


    }

    @Override
    public List<Gerente> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Gerente> gerentes = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Gerente>>() {
            }.getType();
            gerentes = gson.fromJson(json, tipoLista);

            if (gerentes == null)
                gerentes = new ArrayList<>();
        }

        return gerentes;
    }


}