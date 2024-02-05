package by.tms.server.web;

import by.tms.server.model.Operation;
import by.tms.server.service.OperationService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Simon Pirko on 25.01.24
 */
public class OperationHttpHandler implements HttpHandler {

	private final OperationService operationService = new OperationService();

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String query = exchange.getRequestURI().getQuery(); //localhost:8080/calculate?num1=2&num2=2&type=+
		String[] parameters = query.split("&");
		Map<String, String> mapParameters = new HashMap<>();

        for (String s : parameters) {
            String parameter = s.substring(5, s.length());
            String key = s.substring(0, 4);
            mapParameters.put(key, parameter);
        }

		Operation operation = new Operation(Double.parseDouble(mapParameters.get("num1")), Double.parseDouble(mapParameters.get("num2")), mapParameters.get("type"));
		Operation calculate = operationService.calculate(operation);

		String result = "Result = " + calculate.getResult();

		exchange.sendResponseHeaders(200, result.length());

		PrintWriter printWriter = new PrintWriter(exchange.getResponseBody());
		printWriter.print(result); //Result = 4
		printWriter.flush();
	}
}
