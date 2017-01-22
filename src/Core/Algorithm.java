package Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
//import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import java.util.regex.*;
/**
*
* @author purat
*/
public final class Algorithm
{
  //private static final Regex NtRegex = new Regex("\\bNT\\b");
  private static final String NtRegex = "NT";

  private static final String NotDeterminable = "UTD";
  private static final String NotApplicable = "NA";
  private static final String Intact = "INT";

  /**
  * Produces a summarized version of a NeurologyFormTotals object which can be used to be displayed in an interface or be stored in a database.
  * The values in the summary return will have ranges instead of lists of values.
  *
  * @param neurologyForm Neurology form that has been populated with the values to be used in the algorithm calculations.
  * @return
  * Summarized version of the totals which presents the results as ranges, rather than lists containing every possible value for every field.
  *
  */
  public static NeurologyFormTotalsSummary GetTotalsSummaryFor(NeurologyForm neurologyForm)
  {
    return GetTotalsSummaryFor(GetTotalsFor(neurologyForm));
  }

  /**
  * Produces a summarized version of a NeurologyFormTotals object which can be used to be displayed in an interface or be stored in a database.
  * The values in the summary return will have ranges instead of lists of values.
  *
  * @param totals The form totals object to be used to generate the summary.
  * @return
  * Summarized version of the totals where the enumerations get replaced by ranges.
  *
  */
  public static NeurologyFormTotalsSummary GetTotalsSummaryFor(NeurologyFormTotals totals)
  {
    String ais = totals.GetAsiaImpairmentScaleValues();
    boolean isAsiaA = ais.contains("A");
    boolean couldBeOtherThanAsiaA = !isAsiaA || ais.length()> 1;

    NeurologyFormTotalsSummary summary = new NeurologyFormTotalsSummary();
    summary.AsiaImpairmentScale = ais;
    summary.Completeness = isAsiaA ? (couldBeOtherThanAsiaA ? "C,I" : "C") : "I";
    summary.LeftLowerMotorTotal = GetSummaryStringFor(totals.LeftLowerMotorTotal, totals.LeftLowerMotorTotalHasImpairmentNotDueToSci, totals.LeftLowerMotorContainsNt);
    summary.LeftMotor = GetLevelsRange(totals.GetLeftMotorValues(), false);
    summary.LeftMotorZpp = isAsiaA ? GetLevelsRange(totals.GetLeftMotorZppValues(), couldBeOtherThanAsiaA) : NotApplicable;
    summary.LeftMotorTotal = GetSummaryStringFor(totals.LeftUpperMotorTotal + totals.LeftLowerMotorTotal, totals.LeftUpperMotorTotalHasImpairmentNotDueToSci || totals.LeftLowerMotorTotalHasImpairmentNotDueToSci, totals.LeftUpperMotorContainsNt || totals.LeftLowerMotorContainsNt);
    summary.LeftPrickTotal = GetSummaryStringFor(totals.LeftPrickTotal, totals.LeftPrickTotalHasImpairmentNotDueToSci, totals.LeftPrickContainsNt);
    summary.LeftSensory = GetLevelsRange(totals.GetLeftSensoryValues(), false);
    summary.LeftSensoryZpp = isAsiaA ? GetLevelsRange(totals.GetLeftSensoryZppValues(), couldBeOtherThanAsiaA) : NotApplicable;
    summary.LeftTouchTotal = GetSummaryStringFor(totals.LeftTouchTotal, totals.LeftTouchTotalHasImpairmentNotDueToSci, totals.LeftTouchContainsNt);
    summary.LeftUpperMotorTotal = GetSummaryStringFor(totals.LeftUpperMotorTotal, totals.LeftUpperMotorTotalHasImpairmentNotDueToSci, totals.LeftUpperMotorContainsNt);
    summary.LowerMotorTotal = GetSummaryStringFor(totals.LowerMotorTotal, totals.RightLowerMotorTotalHasImpairmentNotDueToSci || totals.LeftLowerMotorTotalHasImpairmentNotDueToSci, totals.RightLowerMotorContainsNt || totals.LeftLowerMotorContainsNt);
    summary.NeurologicalLevelOfInjury = GetLevelsRange(totals.GetNeurologicalLevelsOfInjury(), false);
    summary.PrickTotal = GetSummaryStringFor(totals.RightPrickTotal + totals.LeftPrickTotal, totals.RightPrickTotalHasImpairmentNotDueToSci || totals.LeftPrickTotalHasImpairmentNotDueToSci, totals.RightPrickContainsNt || totals.LeftPrickContainsNt);
    summary.RightLowerMotorTotal = GetSummaryStringFor(totals.RightLowerMotorTotal, totals.RightLowerMotorTotalHasImpairmentNotDueToSci, totals.RightLowerMotorContainsNt);
    summary.RightMotor = GetLevelsRange(totals.GetRightMotorValues(), false);
    summary.RightMotorZpp = isAsiaA ? GetLevelsRange(totals.GetRightMotorZppValues(), couldBeOtherThanAsiaA) : NotApplicable;
    summary.RightMotorTotal = GetSummaryStringFor(totals.RightUpperMotorTotal + totals.RightLowerMotorTotal, totals.RightUpperMotorTotalHasImpairmentNotDueToSci || totals.RightLowerMotorTotalHasImpairmentNotDueToSci, totals.RightUpperMotorContainsNt || totals.RightLowerMotorContainsNt);
    summary.RightPrickTotal = GetSummaryStringFor(totals.RightPrickTotal, totals.RightPrickTotalHasImpairmentNotDueToSci, totals.RightPrickContainsNt);
    summary.RightSensory = GetLevelsRange(totals.GetRightSensoryValues(), false);
    summary.RightSensoryZpp = isAsiaA ? GetLevelsRange(totals.GetRightSensoryZppValues(), couldBeOtherThanAsiaA) : NotApplicable;
    summary.RightTouchTotal = GetSummaryStringFor(totals.RightTouchTotal, totals.RightTouchTotalHasImpairmentNotDueToSci, totals.RightTouchContainsNt);
    summary.RightUpperMotorTotal = GetSummaryStringFor(totals.RightUpperMotorTotal, totals.RightUpperMotorTotalHasImpairmentNotDueToSci, totals.RightUpperMotorContainsNt);
    summary.TouchTotal = GetSummaryStringFor(totals.RightTouchTotal + totals.LeftTouchTotal, totals.RightTouchTotalHasImpairmentNotDueToSci || totals.LeftTouchTotalHasImpairmentNotDueToSci, totals.RightTouchContainsNt || totals.LeftTouchContainsNt);
    summary.UpperMotorTotal = GetSummaryStringFor(totals.UpperMotorTotal, totals.RightUpperMotorTotalHasImpairmentNotDueToSci || totals.LeftUpperMotorTotalHasImpairmentNotDueToSci, totals.RightUpperMotorContainsNt || totals.LeftUpperMotorContainsNt);

    return summary;
  }
  /**
  * Returns the results produced by the ISNCSCI Algorithm ir a raw values format.
  *
  * @param neurologyForm Neurology form that has been populated with the values to be used in the algorithm calculations.
  * @return
  * Totals in raw values format.  The results contain lists with every prossible value for each field.
  * You can use the resulting object to obtained a summarized version, which uses ranges, by passing the result to the method GetTotalsSummaryFor
  *
  */
  public static NeurologyFormTotals GetTotalsFor(NeurologyForm neurologyForm)
  {
    NeurologyFormTotals totals = new NeurologyFormTotals();
    UpdateTotalsWithLevelAt(neurologyForm, totals, neurologyForm.GetLevelWithName("C2"), false, false);

    totals.UpperMotorTotal = totals.RightUpperMotorTotal + totals.LeftUpperMotorTotal;
    totals.LowerMotorTotal = totals.RightLowerMotorTotal + totals.LeftLowerMotorTotal;
    totals.TouchTotal = totals.RightTouchTotal + totals.LeftTouchTotal;
    totals.PrickTotal = totals.RightPrickTotal + totals.LeftPrickTotal;

    NeurologyFormLevel s4_5 = neurologyForm.GetLevelWithName("S4_5");

    NeurologyFormLevel c1 = neurologyForm.GetLevelWithName("C2").getPrevious();

    if (totals.RightSensoryZppHasOnlySoftValues)
    {
      totals.AddRightSensoryZppValue(c1);
    }

    if (totals.LeftSensoryZppHasOnlySoftValues)
    {
      totals.AddLeftSensoryZppValue(c1);
    }

    if (totals.RightMotorZppHasOnlySoftValues)
    {
      totals.AddRightMotorZppValue(c1);
    }

    if (totals.LeftMotorZppHasOnlySoftValues)
    {
      totals.AddLeftMotorZppValue(c1);
    }

    if (totals.getMostRostralRightLevelWithMotorFunction() == null)
    {
      totals.setMostRostralRightLevelWithMotorFunction(c1);
    }

    if (totals.getMostRostralLeftLevelWithMotorFunction() == null)
    {
      totals.setMostRostralLeftLevelWithMotorFunction(c1);
    }

    if (totals.getMostCaudalRightLevelWithMotorFunction() == null)
    {
      totals.setMostCaudalRightLevelWithMotorFunction(c1);
    }

    if (totals.getMostCaudalLeftLevelWithMotorFunction() == null)
    {
      totals.setMostCaudalLeftLevelWithMotorFunction(c1);
    }

    // [ASIA learning center 2012-11-14] Sensory incomplete: Sacral sparing of sensory function
    boolean isSensoryIncomplete = neurologyForm.getAnalSensation() == BinaryObservation.Yes || neurologyForm.getAnalSensation() == BinaryObservation.NT || !"0".equals(s4_5.getRightTouchName()) || !"0".equals(s4_5.getLeftTouchName()) || !"0".equals(s4_5.getRightPrickName()) || !"0".equals(s4_5.getLeftPrickName());

    boolean couldNotHaveMotorFunctionMoreThan3LevelsBelowMotorLevel = CouldNotHaveMotorFunctionMoreThan3LevelsBelowMotorLevel(neurologyForm, totals);

    boolean couldNotBeMotorIncomplete = (neurologyForm.getAnalContraction() == BinaryObservation.No || neurologyForm.getAnalContraction() == BinaryObservation.NT) && isSensoryIncomplete && couldNotHaveMotorFunctionMoreThan3LevelsBelowMotorLevel;

    if ((neurologyForm.getAnalContraction() == BinaryObservation.No || neurologyForm.getAnalContraction() == BinaryObservation.NT) && (neurologyForm.getAnalSensation() == BinaryObservation.No || neurologyForm.getAnalSensation() == BinaryObservation.NT) && s4_5.getRightTouchValue() == 0 && !s4_5.getRightTouchImpairmentNotDueToSci() && s4_5.getRightPrickValue() == 0 && !s4_5.getRightPrickImpairmentNotDueToSci() && s4_5.getLeftTouchValue() == 0 && !s4_5.getLeftTouchImpairmentNotDueToSci() && s4_5.getLeftPrickValue() == 0 && !s4_5.getLeftPrickImpairmentNotDueToSci())
    {
      totals.AddAsiaImpairmentScaleValue("A");
    }

    if (couldNotBeMotorIncomplete && !totals.getMostRostralNeurologicalLevelOfInjury().getName().equals("S4_5"))
    {
      totals.AddAsiaImpairmentScaleValue("B");
    }


    //// Not an ASIA E only
    //// AND not an ASIA A only
    if (!totals.getMostRostralNeurologicalLevelOfInjury().getName().equals("S4_5") && (isSensoryIncomplete || neurologyForm.getAnalContraction() == BinaryObservation.Yes || neurologyForm.getAnalContraction() == BinaryObservation.NT))
    {
      boolean couldBeAsiaC = false;
      boolean couldBeAsiaD = false;

      String cOrD = CouldBeAsiaCorD(neurologyForm, totals, couldBeAsiaC, couldBeAsiaD);
      couldBeAsiaC = Boolean.parseBoolean(cOrD.split(",")[0]); //tempRef_couldBeAsiaC.argValue;
      couldBeAsiaD = Boolean.parseBoolean(cOrD.split(",")[1]);

      if (couldBeAsiaC)
      {
        totals.AddAsiaImpairmentScaleValue("C");
      }

      if (couldBeAsiaD)
      {
        totals.AddAsiaImpairmentScaleValue("D");
      }
    }

    if (totals.RightSensoryContains("S4_5") && totals.LeftSensoryContains("S4_5") && totals.RightMotorContains("S4_5") && totals.LeftMotorContains("S4_5"))
    {
      totals.AddAsiaImpairmentScaleValue("E");
    }

    return totals;
  }

  /**
  * Formats a total value depending on the specified flags.
  *
  * @param total Raw total value.
  * @param hasImpairmentNotDueToSci Flag indicating if any value used in the calculation of this total presents impairment not due to a spinal cord injury.
  * @param containsNt Flag indicating if any value used in the calculation of this total is set to Not Testable.
  * @return
  * The value, followed by an exclamation mark if the hasImpairmentNotDueToSci is set to true
  * or
  * UTD (Unable to determine) if the containsNt flag is set to true.
  *
  */
  private static String GetSummaryStringFor(int total, boolean hasImpairmentNotDueToSci, boolean containsNt)
  {
    if (containsNt)
    {
      return NotDeterminable;
    }

    return String.format("%1$s%2$s", total, hasImpairmentNotDueToSci ? "!" : "");
  }

  /**
  * Returns a range string based on the specified list.
  *
  * @param levels Neurological levels returned in a calculation.
  * @param addNa Used in the Zone of Partial Preservation to indicate that one of the possible Asia Impairment Scale values is &quot;A&quot;
  * @return A representation of the list as a range string.  E.g. NA,C4-C6,T1,T2
  */
  private static String GetLevelsRange(ArrayList<NeurologyFormLevel> levels, boolean addNa)
  {
    if (levels.isEmpty())
    {
      return addNa ? NotApplicable : "";
    }
    //Collections.sort(levels, CompareNeurologyFormTotalsLevelByOrdinal);
    Collections.sort(levels, new Comparator<NeurologyFormLevel>() {
      @Override
      public int compare(NeurologyFormLevel a, NeurologyFormLevel b) {
        return CompareNeurologyFormTotalsLevelByOrdinal(a, b);
      }
    });
    NeurologyFormLevel previous = null;
    String text = "";
    boolean isRange = false;

    for (NeurologyFormLevel level : levels)
    {
      if (previous == null || previous.getOrdinal() < level.getOrdinal())
      {
        String name = level.getOrdinal() == 28 ? Intact : level.getName();

        if (previous == null)
        {
          text = name;
        }
        else if (level.getOrdinal() == previous.getOrdinal() + 1)
        {
          isRange = true;
        }
        else
        {
          text += (isRange ? "-" + previous.getName() + "," : ",") + name;
          isRange = false;
        }

        previous = level;
      }
    }

    if (isRange)
    {
      text += "-" + (previous.getOrdinal() == 28 ? Intact : previous.getName());
    }

    return addNa ?  StringHelper.isNullOrEmpty(text) ? NotApplicable : String.format("%1$s,%2$s", NotApplicable, text) : text;
  }

  /**
  * Compares two neurology levels based on property &quot;Ordinal&quot;.
  *
  * @param a Neurology form level A
  * @param b Neurology form level B
  * @return
  * Returns
  * &ndash; 1 if a is more caudal (the ordinal of a is greater than the one of b)
  * &ndash; 0 if both ordinal values are the same
  * &ndash; -1 if b is more caudal (the ordinal of b is greater than the one of a).
  *
  */
  private static int CompareNeurologyFormTotalsLevelByOrdinal(NeurologyFormLevel a, NeurologyFormLevel b){
    // Both are null so they are equal
    // OR
    // a is null and b is not so b is greater
    if (a == null)
    {
      return b == null ? 0 : -1;
    }

    // b is null and a is not so a is greater
    if (b == null)
    {
      return 1;
    }

    return a.getOrdinal() > b.getOrdinal() ? 1 : a.getOrdinal() == b.getOrdinal() ? 0 : -1;
  }
  /**
  * Recursive method which iterates through the values in a nuerology form while it updates the totals generating the results produced by the algorithm.
  *
  * @param neurologyForm Form being evaluated.
  * @param totals Brand new totals object where the results are to be stored.
  * @param level Current neurology level being evaluated
  * @param nextNonKeyMuscleShouldBeRightMotor Flag used to evaluate the Kathy Collins condition on the right motor results.
  * @param nextNonKeyMuscleShouldBeLeftMotor Flag used to evaluate the Kathy Collins condition on the left motor results.
  */
  private static void UpdateTotalsWithLevelAt(NeurologyForm neurologyForm, NeurologyFormTotals totals, NeurologyFormLevel level, boolean nextNonKeyMuscleShouldBeRightMotor, boolean nextNonKeyMuscleShouldBeLeftMotor)
  {
    totals.RightTouchTotal += level.getRightTouchValue();
    totals.LeftTouchTotal += level.getLeftTouchValue();
    totals.RightPrickTotal += level.getRightPrickValue();
    totals.LeftPrickTotal += level.getLeftPrickValue();

    if (level.getIsKeyMuscle())
    {
      if (level.getIsLowerMuscle())
      {
        if (level.getRightMotorImpairmentNotDueToSci() && !totals.RightLowerMotorTotalHasImpairmentNotDueToSci)
        {
          totals.RightLowerMotorTotalHasImpairmentNotDueToSci = true;
        }

        if (level.getLeftMotorImpairmentNotDueToSci()&& !totals.LeftLowerMotorTotalHasImpairmentNotDueToSci)
        {
          totals.LeftLowerMotorTotalHasImpairmentNotDueToSci = true;
        }

        if (NtRegex.matches(level.getRightMotorName()) && !level.getRightMotorImpairmentNotDueToSci())
        {
          totals.RightLowerMotorContainsNt = true;
        }

        if (NtRegex.matches(level.getLeftMotorName()) && !level.getLeftMotorImpairmentNotDueToSci())
        {
          totals.LeftLowerMotorContainsNt = true;
        }
      }
      else
      {
        if (level.getRightMotorImpairmentNotDueToSci() && !totals.RightUpperMotorTotalHasImpairmentNotDueToSci)
        {
          totals.RightUpperMotorTotalHasImpairmentNotDueToSci = true;
        }

        if (level.getLeftMotorImpairmentNotDueToSci() && !totals.LeftUpperMotorTotalHasImpairmentNotDueToSci)
        {
          totals.LeftUpperMotorTotalHasImpairmentNotDueToSci = true;
        }

        if (NtRegex.matches(level.getRightMotorName()) && !level.getRightMotorImpairmentNotDueToSci())
        {
          totals.RightUpperMotorContainsNt = true;
        }

        if (NtRegex.matches(level.getLeftMotorName()) && !level.getLeftMotorImpairmentNotDueToSci())
        {
          totals.LeftUpperMotorContainsNt = true;
        }
      }
    }
    else
    {
      if (nextNonKeyMuscleShouldBeRightMotor)
      {
        nextNonKeyMuscleShouldBeRightMotor = false;
        totals.AddRightMotorValue(level.getPrevious());

        if (!totals.RightSensoryHasOnlySoftValues)
        {
          totals.RightMotorHasOnlySoftValues = false;
        }
      }

      if (nextNonKeyMuscleShouldBeLeftMotor)
      {
        nextNonKeyMuscleShouldBeLeftMotor = false;
        totals.AddLeftMotorValue(level.getPrevious());

        if (!totals.LeftSensoryHasOnlySoftValues)
        {
          totals.LeftMotorHasOnlySoftValues = false;
        }
      }
    }

    if (level.getRightTouchImpairmentNotDueToSci() && !totals.RightTouchTotalHasImpairmentNotDueToSci)
    {
      totals.RightTouchTotalHasImpairmentNotDueToSci = true;
    }

    if (level.getLeftTouchImpairmentNotDueToSci() && !totals.LeftTouchTotalHasImpairmentNotDueToSci)
    {
      totals.LeftTouchTotalHasImpairmentNotDueToSci = true;
    }

    if (level.getRightPrickImpairmentNotDueToSci() && !totals.RightPrickTotalHasImpairmentNotDueToSci)
    {
      totals.RightPrickTotalHasImpairmentNotDueToSci = true;
    }

    if (level.getLeftPrickImpairmentNotDueToSci() && !totals.LeftPrickTotalHasImpairmentNotDueToSci)
    {
      totals.LeftPrickTotalHasImpairmentNotDueToSci = true;
    }

    // Check if a column contains any NT value so we can properly format the total presented to the user
    if (level.getRightTouchName().contains(NtRegex) && !level.getRightTouchImpairmentNotDueToSci() && !totals.RightTouchContainsNt)
    {
      totals.RightTouchContainsNt = true;
    }

    if (level.getLeftTouchName().contains(NtRegex) && !level.getLeftTouchImpairmentNotDueToSci() && !totals.LeftTouchContainsNt)
    {
      totals.LeftTouchContainsNt = true;
    }

    if (level.getRightPrickName().contains(NtRegex) && !level.getRightPrickImpairmentNotDueToSci() && !totals.RightPrickContainsNt)
    {
      totals.RightPrickContainsNt = true;
    }

    if (level.getLeftPrickName().contains(NtRegex) && !level.getLeftPrickImpairmentNotDueToSci() && !totals.LeftPrickContainsNt)
    {
      totals.LeftPrickContainsNt = true;
    }
    if (totals.RightSensoryHasOnlySoftValues && ((level.getRightTouchValue() != 2 && !level.getRightTouchImpairmentNotDueToSci()) || (level.getRightPrickValue() != 2 && !level.getRightPrickImpairmentNotDueToSci())))
    {
      totals.AddRightSensoryValue(level.getPrevious());

      if ("S4_5".equals(level.getName()) && (level.getRightTouchValue() == 2 || level.getRightTouchName().contains(NtRegex)) && (level.getRightPrickValue() == 2 || level.getRightPrickName().contains(NtRegex)))
      {
        totals.AddRightSensoryValue(level);

        if (totals.NeurologicalLevelOfInjuryHasOnlySoftValues)
        {
          totals.AddNeurologicalLevelOfInjury(level);
        }
      }

      if (totals.NeurologicalLevelOfInjuryHasOnlySoftValues)
      {
        totals.AddNeurologicalLevelOfInjury(level.getPrevious());
      }

      if ((level.getRightTouchValue() != 2 && !(level.getRightTouchName().contains(NtRegex))) || (level.getRightPrickValue() != 2 && !(level.getRightPrickName().contains(NtRegex))))
      {
        totals.RightSensoryHasOnlySoftValues = false;
        totals.NeurologicalLevelOfInjuryHasOnlySoftValues = false;
      }

      if (level.getIsKeyMuscle())
      {
        nextNonKeyMuscleShouldBeRightMotor = true;
        totals.HasRightCollins = true;
      }
    }

    if (totals.LeftSensoryHasOnlySoftValues && ((level.getLeftTouchValue() != 2 && !level.getLeftTouchImpairmentNotDueToSci()) || (level.getLeftPrickValue() != 2 && !level.getLeftPrickImpairmentNotDueToSci())))
    {
      totals.AddLeftSensoryValue(level.getPrevious());

      if ("S4_5".equals(level.getName()) && (level.getLeftTouchValue() == 2 || level.getLeftTouchName().contains(NtRegex)) && (level.getLeftPrickValue() == 2 || level.getLeftPrickName().contains(NtRegex)))
      {
        totals.AddLeftSensoryValue(level);

        if (totals.NeurologicalLevelOfInjuryHasOnlySoftValues)
        {
          totals.AddNeurologicalLevelOfInjury(level);
        }
      }

      if (totals.NeurologicalLevelOfInjuryHasOnlySoftValues)
      {
        totals.AddNeurologicalLevelOfInjury(level.getPrevious());
      }

      if ((level.getLeftTouchValue() != 2 && !(level.getLeftTouchName().contains(NtRegex))) || (level.getLeftPrickValue() != 2 && !(level.getLeftPrickName().contains(NtRegex))))
      {
        totals.LeftSensoryHasOnlySoftValues = false;
        totals.NeurologicalLevelOfInjuryHasOnlySoftValues = false;
      }

      if (level.getIsKeyMuscle())
      {
        nextNonKeyMuscleShouldBeLeftMotor = true;
        totals.HasLeftCollins = true;
      }
    }

    if (totals.RightMotorHasOnlySoftValues && level.getRightMotorValue() != 5 && !level.getRightMotorImpairmentNotDueToSci())
    {
      if (level.getIsKeyMuscle() && (level.getRightMotorValue() >= 3 || level.getRightMotorName().contains(NtRegex)))
      {
        totals.AddRightMotorValue(level);

        // Check if left won't make the NLI have to be the previous level.
        // Let the logic for left motor handle the SNL instead
        if (totals.NeurologicalLevelOfInjuryHasOnlySoftValues && (level.getLeftMotorValue() > 2 || level.getLeftMotorImpairmentNotDueToSci() || level.getLeftMotorName().contains(NtRegex)))
        {
          totals.AddNeurologicalLevelOfInjury(level);

          if (!(level.getRightMotorName().contains(NtRegex)))
          {
            totals.NeurologicalLevelOfInjuryHasOnlySoftValues = false;
          }
        }
      }

      if (level.getRightMotorValue() < 3 || level.getRightMotorName().contains(NtRegex))
      {
        totals.AddRightMotorValue(level.getPrevious());

        if (totals.NeurologicalLevelOfInjuryHasOnlySoftValues)
        {
          totals.AddNeurologicalLevelOfInjury(level.getPrevious());

          if (!(level.getRightMotorName().contains(NtRegex)))
          {
            totals.NeurologicalLevelOfInjuryHasOnlySoftValues = false;
          }
        }
      }

      if (!(level.getRightMotorName().contains(NtRegex)))
      {
        totals.RightMotorHasOnlySoftValues = false;
      }
    }

    if (totals.LeftMotorHasOnlySoftValues && level.getLeftMotorValue() != 5 && !level.getLeftMotorImpairmentNotDueToSci())
    {
      if (level.getIsKeyMuscle() && (level.getLeftMotorValue() >= 3 || level.getLeftMotorName().contains(NtRegex)))
      {
        totals.AddLeftMotorValue(level);

        if (totals.NeurologicalLevelOfInjuryHasOnlySoftValues)
        {
          totals.AddNeurologicalLevelOfInjury(level);
        }
      }

      if (level.getLeftMotorValue() < 3 || level.getLeftMotorName().contains(NtRegex))
      {
        totals.AddLeftMotorValue(level.getPrevious());

        if (totals.NeurologicalLevelOfInjuryHasOnlySoftValues)
        {
          totals.AddNeurologicalLevelOfInjury(level.getPrevious());
        }
      }

      if (!(level.getLeftMotorName().contains(NtRegex)))
      {
        totals.LeftMotorHasOnlySoftValues = false;
        totals.NeurologicalLevelOfInjuryHasOnlySoftValues = false;
      }
    }

    /* -- RECURSIVE CALL --------------- */
    if (level.getNext() != null)
    {
      UpdateTotalsWithLevelAt(neurologyForm, totals, level.getNext(), nextNonKeyMuscleShouldBeRightMotor && totals.RightMotorHasOnlySoftValues, nextNonKeyMuscleShouldBeLeftMotor && totals.LeftMotorHasOnlySoftValues);
    }

    ///#region This happens when there are INTACT values -------------------------------------------
    if ("S4_5".equals(level.getName()))
    {
      if (totals.RightSensoryHasOnlySoftValues && totals.LeftSensoryHasOnlySoftValues && totals.RightMotorHasOnlySoftValues && totals.LeftMotorHasOnlySoftValues)
      {
        totals.AddNeurologicalLevelOfInjury(level);
      }

      if (totals.RightSensoryHasOnlySoftValues)
      {
        totals.AddRightSensoryValue(level);
        totals.RightSensoryHasOnlySoftValues = false;
      }

      if (totals.LeftSensoryHasOnlySoftValues)
      {
        totals.AddLeftSensoryValue(level);
        totals.LeftSensoryHasOnlySoftValues = false;
      }

      if (totals.RightMotorHasOnlySoftValues)
      {
        totals.AddRightMotorValue(level);
        totals.RightMotorHasOnlySoftValues = false;
      }

      if (totals.LeftMotorHasOnlySoftValues)
      {
        totals.AddLeftMotorValue(level);
        totals.LeftMotorHasOnlySoftValues = false;
      }
    }
    ///#endregion

    if (totals.RightSensoryZppHasOnlySoftValues && (!"0".equals(level.getRightTouchName()) || !"0".equals(level.getRightPrickName())))
    {
      if ((level.getRightTouchValue() > 0 || level.getRightTouchImpairmentNotDueToSci()) || (level.getRightPrickValue() > 0 || level.getRightPrickImpairmentNotDueToSci()))
      {
        totals.RightSensoryZppHasOnlySoftValues = false;
      }

      totals.AddRightSensoryZppValue(level);
    }

    if (totals.LeftSensoryZppHasOnlySoftValues && (!"0".equals(level.getLeftTouchName()) || !"0".equals(level.getLeftPrickName())))
    {
      if ((level.getLeftTouchValue() > 0 || level.getLeftTouchImpairmentNotDueToSci()) || (level.getLeftPrickValue() > 0 || level.getLeftPrickImpairmentNotDueToSci()))
      {
        totals.LeftSensoryZppHasOnlySoftValues = false;
      }

      totals.AddLeftSensoryZppValue(level);
    }

    if (totals.RightMotorZppHasOnlySoftValues && (level.getHasOtherRightMotorFunction() || (!"0".equals(level.getRightMotorName()) && (level.getIsKeyMuscle() || totals.RightMotorContains(level.getName())))))
    {
      if ((level.getRightMotorImpairmentNotDueToSci() || level.getHasOtherRightMotorFunction() || !(level.getRightMotorName().contains(NtRegex))) && (level.getIsKeyMuscle() || level.getOrdinal() < 4 || (level.getOrdinal() > 25 && !totals.RightUpperMotorContainsNt && !totals.RightLowerMotorContainsNt && !totals.HasRightCollins) || (level.getOrdinal() > 8 && level.getOrdinal() < 21 && !totals.RightUpperMotorContainsNt)))
      {
        totals.RightMotorZppHasOnlySoftValues = false;
      }

      totals.AddRightMotorZppValue(level);
    }

    if (totals.LeftMotorZppHasOnlySoftValues && (level.getHasOtherLeftMotorFunction() || (!"0".equals(level.getLeftMotorName()) && (level.getIsKeyMuscle() || totals.LeftMotorContains(level.getName())))))
    {
      if ((level.getLeftMotorImpairmentNotDueToSci() || level.getHasOtherLeftMotorFunction() || !(level.getLeftMotorName().contains(NtRegex))) && (level.getIsKeyMuscle() || level.getOrdinal() < 4 || (level.getOrdinal() > 25 && !totals.LeftUpperMotorContainsNt && !totals.LeftLowerMotorContainsNt && !totals.HasLeftCollins) || (level.getOrdinal() > 8 && level.getOrdinal() < 21 && !totals.LeftUpperMotorContainsNt)))
      {
        totals.LeftMotorZppHasOnlySoftValues = false;
      }

      totals.AddLeftMotorZppValue(level);
    }

    // Update most Rostral levels with motor function
    if ((level.getIsKeyMuscle() || level.getHasOtherRightMotorFunction()) && totals.getMostRostralRightLevelWithMotorFunction() == null && (level.getRightMotorImpairmentNotDueToSci() || level.getHasOtherRightMotorFunction() || (level.getRightMotorValue() != 0 && level.getIsKeyMuscle())))
    {
      totals.setMostRostralRightLevelWithMotorFunction(level);
    }

    if ((level.getIsKeyMuscle() || level.getHasOtherLeftMotorFunction()) && totals.getMostRostralLeftLevelWithMotorFunction() == null && (level.getLeftMotorImpairmentNotDueToSci() || level.getHasOtherLeftMotorFunction() || (level.getLeftMotorValue() != 0 && level.getIsKeyMuscle())))
    {
      totals.setMostRostralLeftLevelWithMotorFunction (level);
    }

    // Update most Caudal levels with motor function
    if ((level.getIsKeyMuscle() || level.getHasOtherRightMotorFunction()) && totals.getMostCaudalRightLevelWithMotorFunction() == null && (!"0".equals(level.getRightMotorName()) || level.getHasOtherRightMotorFunction()))
    {
      totals.setMostCaudalRightLevelWithMotorFunction ( level);
    }

    if ((level.getIsKeyMuscle() || level.getHasOtherLeftMotorFunction()) && totals.getMostCaudalLeftLevelWithMotorFunction() == null && (!"0".equals(level.getLeftMotorName()) || level.getHasOtherLeftMotorFunction()))
    {
      totals.setMostCaudalLeftLevelWithMotorFunction ( level);
    }


    if (!level.getIsKeyMuscle())
    {
      return;
    }

    if (level.getIsLowerMuscle())
    {
      totals.RightLowerMotorTotal += level.getRightMotorValue();
      totals.LeftLowerMotorTotal += level.getLeftMotorValue();
    }
    else
    {
      totals.RightUpperMotorTotal += level.getRightMotorValue();
      totals.LeftUpperMotorTotal += level.getLeftMotorValue();
    }
  }
  /**
  * Evaluates the specified form and totals to determine if any of the different
  * return conditions could produce a case where there could be motor function more
  * than 3 levels below the injury level.
  *
  * @param neurologyForm Form that was used to produce the totals.
  * @param totals Totals retunred by the algorithm.
  * @return Flag indicating if any combination in the totals could have a case with motor function more than 3 levels below the injury level.
  */
  private static boolean CouldNotHaveMotorFunctionMoreThan3LevelsBelowMotorLevel(NeurologyForm neurologyForm, NeurologyFormTotals totals)
  {
    NeurologyFormLevel mostRostralRightLevelWithMotor = null;
    NeurologyFormLevel mostRostralLeftLevelWithMotor = null;

    NeurologyFormLevel currentLevel = neurologyForm.GetLevelWithName("S1");

    while ((mostRostralRightLevelWithMotor == null || mostRostralLeftLevelWithMotor == null) && currentLevel != null && currentLevel.getOrdinal() >= totals.getMostCaudalNeurologicalLevelOfInjury().getOrdinal()) // Could happen if SNL is C1
    {
      if (mostRostralRightLevelWithMotor == null && (currentLevel.getRightMotorImpairmentNotDueToSci() || currentLevel.getHasOtherRightMotorFunction() || (currentLevel.getRightMotorValue() != 0 && currentLevel.getIsKeyMuscle())))
      {
        mostRostralRightLevelWithMotor = currentLevel;
      }

      if (mostRostralLeftLevelWithMotor == null && (currentLevel.getLeftMotorImpairmentNotDueToSci() || currentLevel.getHasOtherLeftMotorFunction() || (currentLevel.getLeftMotorValue() != 0 && currentLevel.getIsKeyMuscle())))
      {
        mostRostralLeftLevelWithMotor = currentLevel;
      }

      currentLevel = currentLevel.getPrevious();
    }

    return (mostRostralRightLevelWithMotor == null || mostRostralRightLevelWithMotor.getOrdinal() - totals.getMostCaudalRightMotor().getOrdinal() <= 3) && (mostRostralLeftLevelWithMotor == null || mostRostralLeftLevelWithMotor.getOrdinal() - totals.getMostCaudalLeftMotor().getOrdinal() <= 3);
  }

  /**
  * Evaluates the specified form and totals to determine if any of the different
  * return conditions could produce a case where the Asia Impairment Scale is C o D.
  *
  * @param neurologyForm Form that was used to produce the totals.
  * @param totals Totals retunred by the algorithm.
  * @param couldBeAsiaC out variable use as flag indicating if this case is a possible ASIA C.
  * @param couldBeAsiaD out variable use as flag indicating if this case is a possible ASIA D.
  */
  private static String CouldBeAsiaCorDOld(NeurologyForm neurologyForm, NeurologyFormTotals totals, Boolean couldBeAsiaC, Boolean couldBeAsiaD)
  {
    couldBeAsiaC = false;
    couldBeAsiaD = false;
    NeurologyFormLevel rightMotor = null;
    NeurologyFormLevel leftMotor = null;

    // Check if the patient could be motor incoplete via sphincter contraction
    // Otherwise we need to check for motor function more than 3 levels below motor level.
    boolean couldHaveAnalContraction = neurologyForm.getAnalContraction() == BinaryObservation.Yes || neurologyForm.getAnalContraction() == BinaryObservation.NT;
    //var couldNotHaveAnalContraction = neurologyForm.AnalContraction == BinaryObservation.No || neurologyForm.AnalContraction == BinaryObservation.NT;

    Iterator<NeurologyFormLevel> nliEnum = totals.GetNeurologicalLevelsOfInjury().iterator();

    while (nliEnum.hasNext() && (!couldBeAsiaC || !couldBeAsiaD))
    {
      //var otherMotorIsMoreThanThreeLevelsBelowNli = (neurologyForm.)

      // RIGHT MOTOR
      // If not motor incomplete already, find the right motor level that correspond to this particular neurological level
      if (!couldHaveAnalContraction && (rightMotor == null || rightMotor.getOrdinal() < nliEnum.next().getOrdinal()))
      {
        Iterator<NeurologyFormLevel> rightMotorValues = totals.GetRightMotorValues().iterator();
        rightMotor = null;

        while (rightMotor == null && rightMotorValues.hasNext())
        {
          if (rightMotorValues.next().getOrdinal() >= nliEnum.next().getOrdinal())
          {
            rightMotor = rightMotorValues.next();
          }
        }

        //rightMotorValues.Dispose();
      }

      // LEFT MOTOR
      // If not motor incomplete already, find the left motor level that correspond to this particular neurological level
      if (!couldHaveAnalContraction && (leftMotor == null || leftMotor.getOrdinal() < nliEnum.next().getOrdinal()))
      {
        Iterator<NeurologyFormLevel> leftMotorValues = totals.GetLeftMotorValues().iterator();
        leftMotor = null;

        while (leftMotor == null && leftMotorValues.hasNext())
        {
          if (leftMotorValues.next().getOrdinal() >= nliEnum.next().getOrdinal())
          {
            leftMotor = leftMotorValues.next();
          }
        }

        //leftMotorValues.Dispose();
      }

      //if (couldNotHaveAnalContraction
      //    && (rightMotor == null || totals.MostCaudalRightLevelWithMotorFunction.Ordinal - rightMotor.Ordinal <= 3)
      //    && (leftMotor == null || totals.MostCaudalLeftLevelWithMotorFunction.Ordinal - leftMotor.Ordinal <= 3))
      //    couldBeAsiaB = true;

      // If the test is not motor incomplete at this level, do not continue to count motor levels, move to the next nli available.
      if (!couldHaveAnalContraction && totals.getMostCaudalRightLevelWithMotorFunction().getOrdinal() - rightMotor.getOrdinal() <= 3 && totals.getMostCaudalLeftLevelWithMotorFunction().getOrdinal() - leftMotor.getOrdinal() <= 3)
      {
        continue;
      }

      // When motor incomplete and the nli is S1 or more caudal, it is automatically D since there are no myotomes to count from.
      // We can break the loop.
      if (nliEnum.next().getOrdinal() > 24) // Greater than L5 (24)
      {
        couldBeAsiaD = true;
        break;
      }

      // If motor incomplete, count the motor levels with muscle grade greater than two
      int levelsWithMuscleGradeGreaterThanTwo = 0;
      int levelsWithMuscleGradeLessThanThree = 0;
      int eligibleLevelCount = 0;
      NeurologyFormLevel currentLevel = null;
      try{

        currentLevel = nliEnum.next().getNext();
      }
      catch(Exception e)
      {
        throw new RuntimeException(e);
      }

      while (currentLevel != null)
      {
        if (currentLevel.getIsKeyMuscle())
        {
          eligibleLevelCount += 2;

          if (currentLevel.getRightMotorValue() > 2 || currentLevel.getRightMotorImpairmentNotDueToSci() || currentLevel.getRightMotorName().contains(NtRegex))
          {
            levelsWithMuscleGradeGreaterThanTwo++;
          }

          if ((currentLevel.getRightMotorValue() < 3 || currentLevel.getRightMotorName().contains(NtRegex)) && !currentLevel.getRightMotorImpairmentNotDueToSci())
          {
            levelsWithMuscleGradeLessThanThree++;
          }

          if (currentLevel.getLeftMotorValue() > 2 || currentLevel.getLeftMotorImpairmentNotDueToSci() || currentLevel.getLeftMotorName().contains(NtRegex))
          {
            levelsWithMuscleGradeGreaterThanTwo++;
          }

          if ((currentLevel.getLeftMotorValue() < 3 || currentLevel.getLeftMotorName().contains(NtRegex)) && !currentLevel.getLeftMotorImpairmentNotDueToSci())
          {
            levelsWithMuscleGradeLessThanThree++;
          }
        }

        currentLevel = currentLevel.getNext();
      }

      // If not more than half the myotomes contain values less to 3, this is an Asia C
      if (levelsWithMuscleGradeLessThanThree > eligibleLevelCount / 2)
      {
        couldBeAsiaC = true;
      }

      // If at least half the myotomes below the current NLI containing values greater or equal to 3, hooray! it is ASIA D.
      if (levelsWithMuscleGradeGreaterThanTwo >= eligibleLevelCount / 2)
      {
        couldBeAsiaD = true;
      }
    }

    String cOrD = couldBeAsiaC.toString() + "," + couldBeAsiaD.toString();
    return cOrD;
    //nliEnum.Dispose();
  }

  /**
  * Evaluates the specified form and totals to determine if any of the different
  * return conditions could produce a case where the Asia Impairment Scale is C o D.
  *
  * @param neurologyForm Form that was used to produce the totals.
  * @param totals Totals retunred by the algorithm.
  * @param couldBeAsiaC out variable use as flag indicating if this case is a possible ASIA C.
  * @param couldBeAsiaD out variable use as flag indicating if this case is a possible ASIA D.
  */
  private static String CouldBeAsiaCorD(NeurologyForm neurologyForm, NeurologyFormTotals totals, Boolean couldBeAsiaC, Boolean couldBeAsiaD)
  {
    couldBeAsiaC = false;
    couldBeAsiaD = false;
    NeurologyFormLevel rightMotor = null;
    NeurologyFormLevel leftMotor = null;

    // Check if the patient could be motor incoplete via sphincter contraction
    // Otherwise we need to check for motor function more than 3 levels below motor level.
    boolean couldHaveAnalContraction = neurologyForm.getAnalContraction() == BinaryObservation.Yes || neurologyForm.getAnalContraction() == BinaryObservation.NT;
    //var couldNotHaveAnalContraction = neurologyForm.AnalContraction == BinaryObservation.No || neurologyForm.AnalContraction == BinaryObservation.NT;


    for(NeurologyFormLevel nli : totals.GetNeurologicalLevelsOfInjury() )
    {
      if(!couldBeAsiaC || !couldBeAsiaD)
      {
        // RIGHT MOTOR
        // If not motor incomplete already, find the right motor level that correspond to this particular neurological level

        if (!couldHaveAnalContraction && (rightMotor == null || rightMotor.getOrdinal() < nli.getOrdinal()))
        {
          rightMotor = null;

          for(NeurologyFormLevel rightMotorValues : totals.GetRightMotorValues())
          {
            if(rightMotor == null)
            if (rightMotorValues.getOrdinal() >= nli.getOrdinal())
            {
              rightMotor = rightMotorValues;
            }
          }
        }


        // RIGHT MOTOR
        // If not motor incomplete already, find the right motor level that correspond to this particular neurological level


        // LEFT MOTOR
        // If not motor incomplete already, find the left motor level that correspond to this particular neurological level
        if (!couldHaveAnalContraction && (leftMotor == null || leftMotor.getOrdinal() < nli.getOrdinal()))
        {
          leftMotor = null;

          for(NeurologyFormLevel leftMotorValues : totals.GetLeftMotorValues())
          {
            if(leftMotor == null)
            {
              if (leftMotorValues.getOrdinal() >= nli.getOrdinal())
              {
                leftMotor = leftMotorValues;
              }
            }

            //leftMotorValues.Dispose();
          }
        }

        //if (couldNotHaveAnalContraction
        //    && (rightMotor == null || totals.MostCaudalRightLevelWithMotorFunction.Ordinal - rightMotor.Ordinal <= 3)
        //    && (leftMotor == null || totals.MostCaudalLeftLevelWithMotorFunction.Ordinal - leftMotor.Ordinal <= 3))
        //    couldBeAsiaB = true;

        // If the test is not motor incomplete at this level, do not continue to count motor levels, move to the next nli available.
        if (!couldHaveAnalContraction && totals.getMostCaudalRightLevelWithMotorFunction().getOrdinal() - rightMotor.getOrdinal() <= 3 && totals.getMostCaudalLeftLevelWithMotorFunction().getOrdinal() - leftMotor.getOrdinal() <= 3)
        {
          continue;
        }

        // When motor incomplete and the nli is S1 or more caudal, it is automatically D since there are no myotomes to count from.
        // We can break the loop.
        if (nli.getOrdinal() > 24) // Greater than L5 (24)
        {
          couldBeAsiaD = true;
          break;
        }

        // If motor incomplete, count the motor levels with muscle grade greater than two
        int levelsWithMuscleGradeGreaterThanTwo = 0;
        int levelsWithMuscleGradeLessThanThree = 0;
        int eligibleLevelCount = 0;



        NeurologyFormLevel currentLevel = null;
        try{

          currentLevel = nli.getNext();
        }
        catch(Exception e)
        {
          throw new RuntimeException(e);
        }


        while (currentLevel != null)
        {
          if (currentLevel.getIsKeyMuscle())
          {
            eligibleLevelCount += 2;

            if (currentLevel.getRightMotorValue() > 2 || currentLevel.getRightMotorImpairmentNotDueToSci() || currentLevel.getRightMotorName().contains(NtRegex))
            {
              levelsWithMuscleGradeGreaterThanTwo++;
            }

            if ((currentLevel.getRightMotorValue() < 3 || currentLevel.getRightMotorName().contains(NtRegex)) && !currentLevel.getRightMotorImpairmentNotDueToSci())
            {
              levelsWithMuscleGradeLessThanThree++;
            }

            if (currentLevel.getLeftMotorValue() > 2 || currentLevel.getLeftMotorImpairmentNotDueToSci() || currentLevel.getLeftMotorName().contains(NtRegex))
            {
              levelsWithMuscleGradeGreaterThanTwo++;
            }

            if ((currentLevel.getLeftMotorValue() < 3 || currentLevel.getLeftMotorName().contains(NtRegex)) && !currentLevel.getLeftMotorImpairmentNotDueToSci())
            {
              levelsWithMuscleGradeLessThanThree++;
            }
          }

          currentLevel = currentLevel.getNext();
        }

        // If not more than half the myotomes contain values less to 3, this is an Asia C
        if (levelsWithMuscleGradeLessThanThree > eligibleLevelCount / 2)
        {
          couldBeAsiaC = true;
        }

        // If at least half the myotomes below the current NLI containing values greater or equal to 3, hooray! it is ASIA D.
        if (levelsWithMuscleGradeGreaterThanTwo >= eligibleLevelCount / 2)
        {
          couldBeAsiaD = true;
        }
      }
    }

    String cOrD = couldBeAsiaC.toString() + "," + couldBeAsiaD.toString();
    return cOrD;
    //nliEnum.Dispose();
  }
}
