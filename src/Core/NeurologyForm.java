package Core;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
*
* @author purat
*/
import java.util.*;
//import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import java.util.regex.*;

/**
Contains the raw values from a Neurology Form.  Instances of this class can be passed to the Algorithm methods to obtain totals.
*/
public class NeurologyForm
{
	private static final String[] LevelNames = new String[] {"C2", "C3", "C4", "C5", "C6", "C7", "C8", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12", "L1", "L2", "L3", "L4", "L5", "S1", "S2", "S3", "S4_5"};

	private final static String[] KeyMuscles = new String[] {"C5", "C6", "C7", "C8", "T1", "L2", "L3", "L4", "L5", "S1"};
	private LinkedHashMap<String, NeurologyFormLevel> _levels;
	//private static final Regex NtRegex = new Regex("\\bNT\\b");
	private static final String NtRegex = "NT";

	//  private static final Regex NonSciImpairmentRegex = new Regex(".+[!]");
	private static final String NonSciImpairmentRegex = ".!";

	//	private static final Regex NonSciImpairmentFlagsRegex = new Regex("[\\*!]");
	private static final String NonSciImpairmentFlagsRegex = ".*";


	private static final int NormalMotorValue = 5;
	private static final int NormalSensoryValue = 2;

	private NeurologyFormLevel C1;
	public final NeurologyFormLevel getC1()
	{
		return C1;
	}
	protected final void setC1(NeurologyFormLevel value)
	{
		C1 = value;
	}

	private BinaryObservation AnalContraction;
	public final BinaryObservation getAnalContraction()
	{
		return AnalContraction;
	}
	public final void setAnalContraction(BinaryObservation value)
	{
		AnalContraction = value;
	}
	private BinaryObservation AnalSensation;
	public final BinaryObservation getAnalSensation()
	{
		return AnalSensation;
	}
	public final void setAnalSensation(BinaryObservation value)
	{
		AnalSensation = value;
	}
	private NeurologyFormLevel RightLowestNonKeyMuscleWithMotorFunction;
	public final NeurologyFormLevel getRightLowestNonKeyMuscleWithMotorFunction()
	{
		return RightLowestNonKeyMuscleWithMotorFunction;
	}
	protected final void setRightLowestNonKeyMuscleWithMotorFunction(NeurologyFormLevel value)
	{
		RightLowestNonKeyMuscleWithMotorFunction = value;
	}
	private NeurologyFormLevel LeftLowestNonKeyMuscleWithMotorFunction;
	public final NeurologyFormLevel getLeftLowestNonKeyMuscleWithMotorFunction()
	{
		return LeftLowestNonKeyMuscleWithMotorFunction;
	}
	protected final void setLeftLowestNonKeyMuscleWithMotorFunction(NeurologyFormLevel value)
	{
		LeftLowestNonKeyMuscleWithMotorFunction = value;
	}

	public NeurologyForm()
	{
		setC1(new NeurologyFormLevel());
		getC1().setOrdinal(0);
		getC1().setIsKeyMuscle(false);
		getC1().setIsLowerMuscle(false);
		getC1().setName("C1");
		getC1().setPrevious(null);
		getC1().setRightMotorName("5");
		getC1().setRightMotorValue(5);
		getC1().setLeftMotorName("5");
		getC1().setLeftMotorValue(5);
		getC1().setRightTouchName("2");
		getC1().setRightTouchValue(2);
		getC1().setLeftTouchName("2");
		getC1().setLeftTouchValue(2);
		getC1().setRightPrickName("2");
		getC1().setRightPrickValue(2);
		getC1().setLeftPrickName("2");
		getC1().setLeftPrickValue(2);

		_levels = new LinkedHashMap<String, NeurologyFormLevel>();

		NeurologyFormLevel previousLevel = getC1();

		for (int i = 0; i < LevelNames.length; i++)
		{
			String name = LevelNames[i];

			NeurologyFormLevel currentLevel = new NeurologyFormLevel();
			currentLevel.setIsKeyMuscle(Arrays.asList(KeyMuscles).contains(name));
			currentLevel.setIsLowerMuscle (i >= 20 && i <= 24);
			currentLevel.setName ( name );
			currentLevel.setOrdinal ( i + 1);
			currentLevel.setPrevious ( previousLevel );

			previousLevel.setNext ( currentLevel );
			previousLevel = currentLevel;
			_levels.put(name, currentLevel);
		}
	}

	public final void SetRightLowestNonKeyMuscleWithMotorFunction(String levelName)
	{
		if (StringHelper.isNullOrEmpty(levelName))
		{
			return;
		}

		String key = levelName.toUpperCase();

		if (!_levels.containsKey(key))
		{
			return;
		}

		if (getRightLowestNonKeyMuscleWithMotorFunction() != null)
		{
			getRightLowestNonKeyMuscleWithMotorFunction().setHasOtherRightMotorFunction ( false);
		}

		setRightLowestNonKeyMuscleWithMotorFunction(_levels.get(key));
		getRightLowestNonKeyMuscleWithMotorFunction().setHasOtherRightMotorFunction ( true );
	}

	public final void SetLeftLowestNonKeyMuscleWithMotorFunction(String levelName)
	{
		if (StringHelper.isNullOrEmpty(levelName))
		{
			return;
		}

		String key = levelName.toUpperCase();

		if (!_levels.containsKey(key))
		{
			return;
		}

		if (getLeftLowestNonKeyMuscleWithMotorFunction() != null)
		{
			getLeftLowestNonKeyMuscleWithMotorFunction().setHasOtherLeftMotorFunction ( false);
		}

		setLeftLowestNonKeyMuscleWithMotorFunction(_levels.get(key));
		getLeftLowestNonKeyMuscleWithMotorFunction().setHasOtherLeftMotorFunction ( true );
	}

	/**


	@param index
	@return Neurology level at the specified index.
	*/
	public final NeurologyFormLevel GetLevelAt(int index)
	{

		//return index >= 0 && index < _levels.size() ? _levels.ElementAt(index).Value : null;
		return index >= 0 && index < _levels.size() ? (new ArrayList<NeurologyFormLevel>(_levels.values())).get(index) : null;
	}

	/**
	Returns the neurology level based on a level name.  E.g. C2,C3,C4...S1,S2,S3,S4_5

	@param levelName Lavel name being searched.
	@return Neurology level that matches the specified level name.
	*/
	public final NeurologyFormLevel GetLevelWithName(String levelName)
	{
		String key = levelName.toUpperCase();

		return _levels.containsKey(key) ? _levels.get(key) : null;
	}


	/**
	Updates the values of the neurology level with the specified name.
	You can pass strings containing values between 0-2 for touch and prick and 0-5 for motor.
	You can also use the exclamation mark and asterisk at the end of the string to indicate impairment not due to a spinal cord injury.
	Finally, you can also pass NT to indicate that a value was not testable.

	@param levelName Name of the respective neurology level.
	@param rightTouchName Right touch
	@param leftTouchName Left touch
	@param rightPrickName Right Prick
	@param leftPrickName Left prick
	@param rightMotorName Right motor
	@param leftMotorName Left motor
	@return
	*/
	public final NeurologyForm UpdateLevelAt(String levelName, String rightTouchName, String leftTouchName, String rightPrickName, String leftPrickName, String rightMotorName, String leftMotorName)
	{
		NeurologyFormLevel level = GetLevelWithName(levelName);

		if (level != null)
		{
			UpdateLevel(level, rightTouchName, leftTouchName, rightPrickName, leftPrickName, rightMotorName, leftMotorName);
		}

		return this;
	}

	private static int GetDermatomeValue(String text, int normalValue)
	{
		if (text.toUpperCase().equals("NT*"))
		{
			return normalValue;
		}
		else if (text.toUpperCase().equals("NT") || text.toUpperCase().equals("NT!"))
		{
			return 0;
		}

		int value = 0;
		//RefObject<Integer> tempRef_value = new RefObject<Integer>(value);
		//TryParseHelper.tryParseInt(NonSciImpairmentFlagsRegex.replace(text, ""), tempRef_value);
		value = Integer.parseInt(text.replaceAll("[\\*!]", ""));
		//value = tempRef_value.argValue;

		return value;
	}

	private static void UpdateLevel(NeurologyFormLevel level, String rightTouchName, String leftTouchName, String rightPrickName, String leftPrickName, String rightMotorName, String leftMotorName)
	{
		// Right Touch
		level.setRightTouchName (rightTouchName);
		level.setRightTouchValue ( GetDermatomeValue(rightTouchName, NormalSensoryValue));
		level.setRightTouchImpairmentNotDueToSci (rightTouchName.contains("!"));

		// Left Touch
		level.setLeftTouchName ( leftTouchName);
		level.setLeftTouchValue ( GetDermatomeValue(leftTouchName, NormalSensoryValue));
		//level.setLeftTouchImpairmentNotDueToSci ( NonSciImpairmentRegex.IsMatch(leftTouchName));
		level.setLeftTouchImpairmentNotDueToSci (leftTouchName.contains("!"));

		// Right Prick
		level.setRightPrickName ( rightPrickName);
		level.setRightPrickValue ( GetDermatomeValue(rightPrickName, NormalSensoryValue));
		//level.setRightPrickImpairmentNotDueToSci ( NonSciImpairmentRegex.IsMatch(rightPrickName));
		level.setRightPrickImpairmentNotDueToSci (rightPrickName.contains("!"));


		// Left Prick
		level.setLeftPrickName ( leftPrickName);
		level.setLeftPrickValue ( GetDermatomeValue(leftPrickName, NormalSensoryValue));
		//level.setLeftPrickImpairmentNotDueToSci ( NonSciImpairmentRegex.IsMatch(leftPrickName));
		level.setLeftPrickImpairmentNotDueToSci (leftPrickName.contains("!"));


		// Right Motor
		level.setRightMotorName ( rightMotorName);
		level.setRightMotorValue ( GetDermatomeValue(rightMotorName, NormalMotorValue));
		//level.setRightMotorImpairmentNotDueToSci ( NonSciImpairmentRegex.IsMatch(rightMotorName));
		level.setRightMotorImpairmentNotDueToSci (rightMotorName.contains("!"));

		// Left Motor
		level.setLeftMotorName (leftMotorName);
		level.setLeftMotorValue ( GetDermatomeValue(leftMotorName, NormalMotorValue));
		//level.setLeftMotorImpairmentNotDueToSci ( NonSciImpairmentRegex.IsMatch(leftMotorName));
		level.setLeftMotorImpairmentNotDueToSci (leftMotorName.contains("!"));


		if (!level.getIsKeyMuscle())
		{
			if ((level.getRightTouchValue() == 2 || level.getRightTouchImpairmentNotDueToSci()) && (level.getRightPrickValue() == 2 || level.getRightPrickImpairmentNotDueToSci()))
			{
				level.setRightMotorName ( "5" );
				level.setRightMotorValue ( 5 );
			}
			else
			{
				//String name = (Pattern.matches(NtRegex, level.getRightTouchName()) || level.getRightTouchValue() == 2) && (Pattern.matches(NtRegex, level.getRightPrickName()) || level.getRightPrickValue() == 2) ? "NT" : "0";
				String name = (level.getRightTouchName().contains("NT") || level.getRightTouchValue() == 2) && ( level.getRightPrickName().contains("NT") || level.getRightPrickValue() == 2) ? "NT" : "0";
				level.setRightMotorName(name);
				level.setRightMotorValue ( 0);
			}

			if ((level.getLeftTouchValue() == 2 || level.getLeftTouchImpairmentNotDueToSci()) && (level.getLeftPrickValue() == 2 || level.getLeftPrickImpairmentNotDueToSci()))
			{
				level.setLeftMotorName ( "5");
				level.setLeftMotorValue ( 5 );
			}
			else
			{
				String name = (level.getLeftTouchName().contains("NT") || level.getLeftTouchValue() == 2) && (level.getLeftPrickName().contains("NT") || level.getLeftPrickValue() == 2) ? "NT" : "0";
				level.setLeftMotorName(name);
				level.setLeftMotorValue ( 0) ;
			}
		}
	}
}
