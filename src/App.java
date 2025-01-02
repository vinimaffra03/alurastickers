import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar o top 250 filmes 
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair apenas os dados que interessam (titulo , poster e classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados 
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001B[0m" + filme.get("image"));
            System.out.println();
            System.out.println("\u001B[0m" + filme.get("title"));
            System.out.println();
            System.out.print("\u001b[37m \u001b[42m Avaliação : ");
            System.out.print(filme.get("imDbRating"));
            System.out.println("\u001B[0m");
            System.out.println();
            System.out.println();
        }
    }
}