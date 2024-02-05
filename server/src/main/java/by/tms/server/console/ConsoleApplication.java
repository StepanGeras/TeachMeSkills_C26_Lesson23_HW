package by.tms.server.console;


import by.tms.server.Application;
import by.tms.server.model.Operation;
import by.tms.server.service.OperationService;

public class ConsoleApplication implements Application {

	private final OperationService operationService = new OperationService();
	private final ConsoleReader consoleReader = new ConsoleReader();
	private final ConsoleWriter consoleWriter = new ConsoleWriter();

	public void run() {

		consoleWriter.write("Enter num 1");
		double num1 = consoleReader.readNum();
		consoleWriter.write("Enter num 2");
		double num2 = consoleReader.readNum();
		while (num2 == 0) {

			consoleWriter.write("Error");
			num2 = consoleReader.readNum();

		}
		consoleWriter.write("Enter operation");
		String type = consoleReader.readOperationType();

	}
}
