import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoLinguagens implements ExtratorDeConteudo {

    public List<Conteudo> extraiconteudos(String json) {
        // Extrair só os dados que interessam (titulo, poster, nota)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        return listaDeAtributos.stream().map((atributos) -> {
            return new Conteudo(atributos.get("title"), atributos.get("image"));
        }).toList();
    }
}
