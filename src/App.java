import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP e armazenar o top 250 filmes numa String
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair só os dados que interessam (titulo, poster, nota)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();
        for (Map<String,String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            
            String nomeArquivo = "figurinhas/" + titulo.replace(":","") + ".png";
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println("\u001b[35m\u001b[45m Rating:\u001b[m" + "\u001b[35m\u001b[45m" + filme.get("imDbRating") + " \u001b[m");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numEstrelas = (int)classificacao;
            for (int i = 0; i < numEstrelas ; i++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }
    }
}
