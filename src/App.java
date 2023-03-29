import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP e armazenar o top 250 filmes numa String
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        var diretorio = new File("saida/");
        diretorio.mkdir();

        for (int i = 0; i < 3; i++) {
            Map<String,String> conteudo = listaDeConteudos.get(i);

            // conteudo.get("image")
            String urlImagem = conteudo.get("url").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String titulo = conteudo.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + titulo.replace(":","") + ".png";
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println("\n");
        }
    }
}
