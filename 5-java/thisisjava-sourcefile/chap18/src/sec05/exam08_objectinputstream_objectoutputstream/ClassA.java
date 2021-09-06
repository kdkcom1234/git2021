package sec05.exam08_objectinputstream_objectoutputstream;

import java.io.Serializable;

public class ClassA implements Serializable {
	int field1;
	ClassB field2 = new ClassB();	
	static int field3;
	transient int field4;
}
