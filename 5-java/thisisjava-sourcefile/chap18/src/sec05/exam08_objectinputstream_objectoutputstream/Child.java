package sec05.exam08_objectinputstream_objectoutputstream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Child extends Parent implements Serializable {
	public String field2;
	
	private void writeObject(ObjectOutputStream out) throws IOException {
	   out.writeUTF(field1);
	   out.defaultWriteObject();
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	   field1 = in.readUTF();
	   in.defaultReadObject();
	}
}
