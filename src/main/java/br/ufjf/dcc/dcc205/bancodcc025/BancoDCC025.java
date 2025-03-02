package br.ufjf.dcc.dcc205.bancodcc025;


public class BancoDCC025 {

    public static void main(String[] args) {
        System.out.println("Iniciando Banco...");

//        GerenciaArquivos gerenciador = new GerenciaArquivos();
//        gerenciador.exibirUsuarios();

        Arquivo persistence = new Arquivo();
        persistence.leCliente("/home/pedrobonfa/Codes/java/BancoDCC025-Bernardo-Pedro/src/main/java/br/ufjf/dcc/dcc205/bancodcc025/Usuarios.json");
        System.out.println("Altera valor dinheiro de Ricardo pra 1k");



        //Cria tela de login - inicia app
        TelaLogin janela = new TelaLogin();
        janela.iniciaTela();


    }
}