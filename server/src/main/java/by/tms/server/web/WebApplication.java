package by.tms.server.web;

import by.tms.server.Application;
import com.sun.net.httpserver.HttpServer;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;

/**
 * @author Simon Pirko on 25.01.24
 */
public class WebApplication implements Application {

	@SneakyThrows
	@Override
	public void run() {
		HttpServer httpServer
				= HttpServer.create(new InetSocketAddress("localhost", 8080), 1);
		httpServer.createContext("/calc", new CalculatorHttpHandler()); //localhost:8080/calculate
		httpServer.start();
	}
}
