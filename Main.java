import javafx.application.Application;

public class Main {
    public static void main(String[] args) {

        String apiResponse = BuscaAPI.makeAPICall(Interface.getApiUrl());
        System.out.println("Resposta da API:");
        System.out.println(apiResponse);

        Application.launch(Interface.class, args);
    }
}