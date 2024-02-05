package by.tms.client;

import by.tms.console.ConsoleApplication;
import by.tms.model.Operation;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Simon Pirko on 29.01.24
 */

//XML p(JAX-P - DOM, SAX, StAX) , b(JAX-B)
//JSON b(Jackson, Gson)

public class ClientApplication {
	public static void main(String[] args) {
		Gson gson = new Gson();

		ConsoleApplication consoleApplication = new ConsoleApplication();

		Operation operation = consoleApplication.run();

		String json = gson.toJson(operation);

		HttpRequest request = null;
		try {
			request = HttpRequest.newBuilder()
					.uri(new URI("http://localhost:8080/calc"))
					.headers("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json))
					.build();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		HttpClient httpClient = HttpClient.newHttpClient();
		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}

        Operation operation1 = gson.fromJson(response.body(), Operation.class);
		System.out.println(operation1);
	}
}
