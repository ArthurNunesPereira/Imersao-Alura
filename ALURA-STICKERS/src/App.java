import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e armazenar o top 250 filmes numa String
        API api = API.NASA; // or API api = API.IMDB_TOP_MOVIES;
        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiconteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        var diretorio = new File("ALURA-STICKERS/saida/");
        diretorio.mkdir();

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "ALURA-STICKERS/saida/" + conteudo.titulo().replace(":","") + ".png";
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println("\n");
        }
    }
}
