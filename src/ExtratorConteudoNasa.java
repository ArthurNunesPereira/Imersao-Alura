import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa {
    public List<Conteudo> extraionteudos (String json) {
        // Extrair só os dados que interessam (titulo, poster, nota)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // Popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("url");
            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
