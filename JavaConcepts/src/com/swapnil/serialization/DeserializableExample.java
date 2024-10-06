package com.swapnil.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializableExample {

	public static void main(String[] args) {

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.txt"))) {
		// Deserialize the object
			Employee deserializedEmployee = (Employee) in.readObject();
			System.out.println("Deserialized Employee: " + deserializedEmployee);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
