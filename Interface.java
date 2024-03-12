import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Interface extends Application {

    private static String apiUrl = "http://apiadvisor.climatempo.com.br/api/v1/anl/synoptic/locale/BR?token=08929cd6f9e2f1c2a7771f5b486dc36b";

    private static BuscaAPI buscaAPI = new BuscaAPI(); // Instância da classe BuscaAPI

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        Label responseLabel = new Label("Aguardando resposta da API...");

        root.getChildren().add(responseLabel);

        Scene scene = new Scene(root, 740, 440);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exemplo de Integração com API");
        primaryStage.show();

        // Faz a solicitação HTTP e atualiza a interface de usuário com a resposta da API
        new Thread(() -> {
            String response = buscaAPI.makeAPICall(apiUrl);
            // Formata a resposta para torná-la mais legível
            response = formatApiResponse(response);
            // Atualiza o Label dentro da Thread JavaFX
            final String finalResponse = response;
            Platform.runLater(() -> responseLabel.setText(finalResponse));
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static String getApiUrl() {
        return apiUrl;
    }

    // Método para formatar a resposta da API
    private static String formatApiResponse(String response) {
        // Aqui você pode adicionar a lógica para formatar a resposta conforme necessário
        // Por exemplo, dividindo em linhas ou fazendo outras manipulações

        // Substitui caracteres de escape Unicode para que sejam exibidos corretamente
        response = response.replace("\\u00e9", "é");
        response = response.replace("\\u00e1", "á");
        response = response.replace("\\u00f3", "ó");
        response = response.replace("\\u00e3", "ã");
        response = response.replace("\\u00e0", "à");
        response = response.replace("\\u00e2", "â");
        response = response.replace("\\u00ea", "ê");
        response = response.replace("\\u00ed", "í");
        response = response.replace("\\u00e7", "ç");

        // Adiciona quebras de linha para tornar o texto mais legível
        response = response.replace(",", ",\n");
        response = response.replace(".", ".\n");

        return response;
    }
}
