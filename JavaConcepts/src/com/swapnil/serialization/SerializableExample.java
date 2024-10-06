package com.swapnil.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableExample {

	public static void main(String[] args) {
		
		  Employee employee = new Employee(101, "Alice", "password");
		  
	        // Serialize the object
	        try {
	        	FileOutputStream fos = new FileOutputStream("employee.txt");
	        	ObjectOutputStream out = new ObjectOutputStream(fos);
	            out.writeObject(employee);
	            System.out.println("Object serialized successfully!");
	            out.close();
	            fos.close();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}

}
