package by.tms.server.console;

import java.util.Scanner;

public class ConsoleReader {
	private final Scanner scanner = new Scanner(System.in);

	public String readOperationType() {
		return scanner.next();
	}

	public double readNum() {
		return scanner.nextDouble();
	}

	public String readEndOperation () {
		return scanner.next();
	}
}
