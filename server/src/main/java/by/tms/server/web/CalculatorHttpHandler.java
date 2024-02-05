package by.tms.server.web;

import by.tms.server.model.Operation;
import by.tms.server.service.OperationService;
import by.tms.server.web.write.WriteFile;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Simon Pirko on 1.02.24
 */
public class CalculatorHttpHandler implements HttpHandler {

	private final OperationService service = new OperationService();
	private final Gson gson = new Gson();

	@Override
	public void handle(HttpExchange exchange) {

		String s = getString(exchange);

		Operation operation = gson.fromJson(s, Operation.class);

		Operation calculate = service.calculate(operation);

		String json = gson.toJson(calculate);

		try {
			exchange.sendResponseHeaders(200, json.length());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		exchange.getResponseHeaders().add("Content-Type", "application/json");

		WriteFile.doWriteFile(json);

		PrintWriter printWriter = new PrintWriter(exchange.getResponseBody());
		printWriter.print(json);
		printWriter.flush();
	}

	private static String getString(HttpExchange exchange) {
		InputStream requestBody = exchange.getRequestBody();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] bytes;
		try {
			bytes = requestBody.readAllBytes();
			byteArrayOutputStream.write(bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

        return byteArrayOutputStream.toString();
	}
}
