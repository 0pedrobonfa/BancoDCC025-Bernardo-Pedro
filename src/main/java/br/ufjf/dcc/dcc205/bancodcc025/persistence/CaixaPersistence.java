package br.ufjf.dcc.dcc205.bancodcc025.persistence;

import br.ufjf.dcc.dcc205.bancodcc025.model.Caixa;
import static br.ufjf.dcc.dcc205.bancodcc025.persistence.Persistence.DIRECTORY;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CaixaPersistence implements Persistence<Caixa> {

    private static final String PATH = DIRECTORY+ File.separator +"usuarios.json";
    @Override
    public void save(List<Caixa> itens) {
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
    public List<Caixa> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Caixa> caixas = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Caixa>>() {
            }.getType();
            caixas = gson.fromJson(json, tipoLista);

            if (caixas == null)
                caixas = new ArrayList<>();
        }

        return caixas;
    }


}