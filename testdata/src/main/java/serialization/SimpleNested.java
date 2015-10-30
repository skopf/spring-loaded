package serialization;

import java.io.Serializable;

public class SimpleNested implements Serializable
{

    private static final long serialVersionUID = 5357108213404755714L;

	private String message;
	
	public final static String WORLD = "world";
	
	public SimpleNested(String world)
	{
		this.message = "hello, " + world + "!";
	}
	
	public String getMessage()
	{
		return message;
	}
	
}
