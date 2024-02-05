package by.tms.console;


import by.tms.model.Operation;

import java.util.Objects;

public class ConsoleApplication {

	private final ConsoleReader consoleReader = new ConsoleReader();
	private final ConsoleWriter consoleWriter = new ConsoleWriter();

	public Operation run() {

		consoleWriter.write("Enter num 1");
		double num1 = consoleReader.readNum();
		consoleWriter.write("Enter num 2");
		double num2 = consoleReader.readNum();
		consoleWriter.write("Enter operation");
		String type = consoleReader.readOperationType();

		while (type.equals("/") && num2 == 0) {
			consoleWriter.write("Error");
			consoleWriter.write("Enter num 2");
			num2 = consoleReader.readNum();
		}

		return new Operation(num1, num2, type);
	}
}
