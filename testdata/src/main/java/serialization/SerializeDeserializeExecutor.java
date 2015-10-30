package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SerializeDeserializeExecutor
{

	public static void serializeDeserializeArrayList() throws Exception
	{
		// prepare
		ArrayList<SimpleNested> testContainer = new ArrayList<SimpleNested>(1);
		testContainer.add(new SimpleNested(SimpleNested.WORLD));
		// serialize
		byte[] data = serialize(testContainer);
		// deserialize
		@SuppressWarnings("unchecked")
        ArrayList<SimpleNested> testDeserialized = (ArrayList<SimpleNested>)deserialize(data);
		testDeserialized.get(0).getMessage();
	}

	public static byte[] serialize(Serializable obj) throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        ObjectOutputStream oout = new ObjectOutputStream(baos);
        oout.writeObject(obj);
        oout.close();
        return baos.toByteArray();
    }

	public static Object deserialize(byte[] objectData) throws Exception
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
        ObjectInputStream oin = new ObjectInputStream(bais);
        return oin.readObject();
    }

}
