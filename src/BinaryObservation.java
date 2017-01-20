/**
 *
 * @author purat
 */

public class BinaryObservation
{
	public static final BinaryObservation None = new BinaryObservation(0x0);
	public static final BinaryObservation Yes = new BinaryObservation(0x1);
	public static final BinaryObservation No = new BinaryObservation(0x2);
	public static final BinaryObservation NT = new BinaryObservation(0x4);

	private int intValue;
	private static java.util.HashMap<Integer, BinaryObservation> mappings;
	private static java.util.HashMap<Integer, BinaryObservation> getMappings()
	{
		if (mappings == null)
		{
			synchronized (BinaryObservation.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, BinaryObservation>();
				}
			}
		}
		return mappings;
	}

	private BinaryObservation(int value)
	{
		intValue = value;
		synchronized (BinaryObservation.class)
		{
			getMappings().put(value, this);
		}
	}

	public int getValue()
	{
		return intValue;
	}

	public static BinaryObservation forValue(int value)
	{
		synchronized (BinaryObservation.class)
		{
			BinaryObservation enumObj = getMappings().get(value);
			if (enumObj == null)
			{
				return new BinaryObservation(value);
			}
			else
			{
				return enumObj;
			}
		}
	}
}

