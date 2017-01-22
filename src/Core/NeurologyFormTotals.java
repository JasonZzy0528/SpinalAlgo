package Core;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author purat
 */
public class NeurologyFormTotals
{
    public int RightUpperMotorTotal;
    public boolean RightUpperMotorTotalHasImpairmentNotDueToSci;
    public boolean RightUpperMotorContainsNt;
    public int LeftUpperMotorTotal;
    public boolean LeftUpperMotorTotalHasImpairmentNotDueToSci;
    public boolean LeftUpperMotorContainsNt;
    public int RightLowerMotorTotal;
    public boolean RightLowerMotorTotalHasImpairmentNotDueToSci;
    public boolean RightLowerMotorContainsNt;
    public int LeftLowerMotorTotal;
    public boolean LeftLowerMotorTotalHasImpairmentNotDueToSci;
    public boolean LeftLowerMotorContainsNt;
    public int RightTouchTotal;
    public boolean RightTouchTotalHasImpairmentNotDueToSci;
    public boolean RightTouchContainsNt;
    public int LeftTouchTotal;
    public boolean LeftTouchTotalHasImpairmentNotDueToSci;
    public boolean LeftTouchContainsNt;
    public int RightPrickTotal;
    public boolean RightPrickTotalHasImpairmentNotDueToSci;
    public boolean RightPrickContainsNt;
    public int LeftPrickTotal;
    public boolean LeftPrickTotalHasImpairmentNotDueToSci;
    public boolean LeftPrickContainsNt;

    public int UpperMotorTotal;
    public int LowerMotorTotal;
    public int TouchTotal;
    public int PrickTotal;

    ///#region Right Sensory
    private final ArrayList<NeurologyFormLevel> _rightSensoryValues = new ArrayList<NeurologyFormLevel>();
    public boolean RightSensoryHasOnlySoftValues = true;

    public final boolean RightSensoryIsEmpty()
    {
        return _rightSensoryValues.isEmpty();
    }

    public final void AddRightSensoryValue(NeurologyFormLevel level)
    {
        /*if (_rightSensoryValues.Any(l -> l.Name.toUpperCase() == level.Name))
        {
        return;
        }
        */

        if(_rightSensoryValues.contains(level)){
            return;
        }
        _rightSensoryValues.add(level);
    }

    public final ArrayList<NeurologyFormLevel> GetRightSensoryValues()
    {
        return this._rightSensoryValues; //new ArrayList<NeurologyFormLevel>();
    }

    public final boolean RightSensoryContains(String levelName)
    {
//		return _rightSensoryValues.Any(l = levelName.toUpperCase().equals(> l.Name.toUpperCase()));
        for(NeurologyFormLevel level: _rightSensoryValues){
            if(level.getName().toUpperCase().equals(levelName.toUpperCase())){
                return true;
            }
        }
        return false;
//            return _rightMotorValues.contains(levelName.toUpperCase());
    }
    ///#endregion
    ///#region Left Sensory
    private final ArrayList<NeurologyFormLevel> _leftSensoryValues = new ArrayList<NeurologyFormLevel>();
    public boolean LeftSensoryHasOnlySoftValues = true;

    public final boolean LeftSensoryIsEmpty()
    {
        return _leftSensoryValues.isEmpty();
    }

    public final void AddLeftSensoryValue(NeurologyFormLevel level)
    {
        /*if (_leftSensoryValues.Any(l -> l.Name.toUpperCase() == level.Name))
        {
        return;
        }*/
        if(_leftSensoryValues.contains(level)){
            return;
        }
        _leftSensoryValues.add(level);
    }

    public final ArrayList<NeurologyFormLevel> GetLeftSensoryValues()
    {
        return this._leftSensoryValues; //new ArrayList<NeurologyFormLevel>();
    }

    public final boolean LeftSensoryContains(String levelName)
    {
        // return _leftSensoryValues.Any(l = levelName.toUpperCase().equals(> l.Name.toUpperCase()));
//            return _leftSensoryValues.contains(levelName.toUpperCase());
        for(NeurologyFormLevel level: _leftSensoryValues){
            if(level.getName().toUpperCase().equals(levelName.toUpperCase())){
                return true;
            }
        }
        return false;
    }
    ///#endregion
    ///#region Right Motor
    private final ArrayList<NeurologyFormLevel> _rightMotorValues = new ArrayList<NeurologyFormLevel>();
    public boolean RightMotorHasOnlySoftValues = true;
    public boolean HasRightCollins = false;
    private NeurologyFormLevel MostRostralRightMotor;
    public final NeurologyFormLevel getMostRostralRightMotor()
    {
        return MostRostralRightMotor;
    }
    protected final void setMostRostralRightMotor(NeurologyFormLevel value)
    {
        MostRostralRightMotor = value;
    }
    private NeurologyFormLevel MostCaudalRightMotor;
    public final NeurologyFormLevel getMostCaudalRightMotor()
    {
        return MostCaudalRightMotor;
    }
    protected final void setMostCaudalRightMotor(NeurologyFormLevel value)
    {
        MostCaudalRightMotor = value;
    }

    public final boolean RightMotorIsEmpty()
    {
        return _rightMotorValues.isEmpty();
    }

    public final void AddRightMotorValue(NeurologyFormLevel level)
    {
        /*if (_rightMotorValues.Any(l -> l.Name.toUpperCase() == level.Name))
        {
        return;
        }*/
        if(_rightMotorValues.contains(level)){
            return;
        }

        if (getMostRostralRightMotor() == null || level.getOrdinal() < getMostRostralRightMotor().getOrdinal())
        {
            setMostRostralRightMotor(level);
        }

        if (getMostCaudalRightMotor() == null || level.getOrdinal() > getMostCaudalRightMotor().getOrdinal())
        {
            setMostCaudalRightMotor(level);
        }

        _rightMotorValues.add(level);
    }

    public final ArrayList<NeurologyFormLevel> GetRightMotorValues()
    {
        return this._rightMotorValues; //new ArrayList<NeurologyFormLevel>();
    }

    public final boolean RightMotorContains(String levelName)
    {
        //return _rightMotorValues.Any(l = levelName.toUpperCase().equals(> l.Name.toUpperCase()));
//                        return _rightMotorValues.contains(levelName);
        for(NeurologyFormLevel level: _rightMotorValues){
            if(level.getName().toUpperCase().equals(levelName.toUpperCase())){
                return true;
            }
        }
        return false;
    }
    ///#endregion

    ///#region Left Motor
    private final ArrayList<NeurologyFormLevel> _leftMotorValues = new ArrayList<NeurologyFormLevel>();
    public boolean LeftMotorHasOnlySoftValues = true;
    public boolean HasLeftCollins = false;

    public final boolean LeftMotorIsEmpty()
    {
        return _leftMotorValues.isEmpty();
    }

    public final void AddLeftMotorValue(NeurologyFormLevel level)
    {
        if (_leftMotorValues.contains(level)){
            return;
        }

        if (MostRostralLeftMotor == null || level.getOrdinal() < MostRostralLeftMotor.getOrdinal())
        {
            MostRostralLeftMotor = level;
        }

        if (MostCaudalLeftMotor == null || level.getOrdinal() > MostCaudalLeftMotor.getOrdinal())
        {
            MostCaudalLeftMotor = level;
        }

        _leftMotorValues.add(level);
    }

    public final ArrayList<NeurologyFormLevel> GetLeftMotorValues()
    {
        return this._leftMotorValues; //return new ArrayList<NeurologyFormLevel>();
    }

    public final boolean LeftMotorContains(String levelName)
    {
        //return _leftMotorValues.Any(l = levelName.toUpperCase().equals(> l.Name.toUpperCase()));
//        return _leftMotorValues.contains(levelName);
        for(NeurologyFormLevel level: _leftMotorValues){
                if(level.getName().toUpperCase().equals(levelName.toUpperCase())){
                    return true;
                }
            }
            return false;
    }
    ///#endregion
    ///#region Neurological Level of Injury
    private final ArrayList<NeurologyFormLevel> _neurologicalLevelsOfInjury = new ArrayList<NeurologyFormLevel>();
    public boolean NeurologicalLevelOfInjuryHasOnlySoftValues = true;

    public final void AddNeurologicalLevelOfInjury(NeurologyFormLevel level)
    {
        if (_neurologicalLevelsOfInjury.contains(level))
        {
            return;
        }

        if (MostRostralNeurologicalLevelOfInjury == null || level.getOrdinal() < MostRostralNeurologicalLevelOfInjury.getOrdinal())
        {
            MostRostralNeurologicalLevelOfInjury = level;
        }

        if (MostCaudalNeurologicalLevelOfInjury == null || level.getOrdinal() > MostCaudalNeurologicalLevelOfInjury.getOrdinal())
        {
            MostCaudalNeurologicalLevelOfInjury = level;
        }

        _neurologicalLevelsOfInjury.add(level);
    }

    public final ArrayList<NeurologyFormLevel> GetNeurologicalLevelsOfInjury()
    {
        return this._neurologicalLevelsOfInjury;// new ArrayList<NeurologyFormLevel>();
    }
    ///#endregion
    ///#region Right Sensory ZPP
    private final ArrayList<NeurologyFormLevel> _rightSensoryZppValues = new ArrayList<NeurologyFormLevel>();
    public boolean RightSensoryZppHasOnlySoftValues = true;

    public final boolean RightSensoryZppIsEmpty()
    {
        return _rightSensoryZppValues.isEmpty();
    }

    public final void AddRightSensoryZppValue(NeurologyFormLevel level)
    {
        if (_rightSensoryZppValues.contains(level) || level.getName().equals("S4_5"))
        {
            return;
        }

        _rightSensoryZppValues.add(level);
    }

    public final ArrayList<NeurologyFormLevel> GetRightSensoryZppValues()
    {
        return this._rightSensoryZppValues; //new ArrayList<NeurologyFormLevel>();
    }
    ///#endregion

    ///#region Left Sensory ZPP
    private final ArrayList<NeurologyFormLevel> _leftSensoryZppValues = new ArrayList<NeurologyFormLevel>();
    public boolean LeftSensoryZppHasOnlySoftValues = true;
    public final boolean LeftSensoryZppIsEmpty()
    {
        return _leftSensoryZppValues.isEmpty();
    }

    public final void AddLeftSensoryZppValue(NeurologyFormLevel level)
    {
        if (_leftSensoryZppValues.contains(level) || "S4_5".equals(level.getName()))
        {
            return;
        }

        _leftSensoryZppValues.add(level);
    }

    public final ArrayList<NeurologyFormLevel> GetLeftSensoryZppValues()
    {
        return this._leftSensoryZppValues; //new ArrayList<NeurologyFormLevel>();
    }

    ///#endregion

    ///#region Right Motor ZPP
    private final ArrayList<NeurologyFormLevel> _rightMotorZppValues = new ArrayList<NeurologyFormLevel>();
    public boolean RightMotorZppHasOnlySoftValues = true;

    public final boolean RightMotorZppIsEmpty()
    {
        return _rightMotorZppValues.isEmpty();
    }

    public final void AddRightMotorZppValue(NeurologyFormLevel level)
    {
        if (_rightMotorZppValues.contains(level.getName()) || "S4_5".equals(level.getName()))
        {
            return;
        }

        _rightMotorZppValues.add(level);
    }

    public final ArrayList<NeurologyFormLevel> GetRightMotorZppValues()
    {
        return this._rightMotorZppValues; //new ArrayList<NeurologyFormLevel>();
    }
    ///#endregion

    ///#region Left Motor ZPP
    private final ArrayList<NeurologyFormLevel> _leftMotorZppValues = new ArrayList<NeurologyFormLevel>();
    public boolean LeftMotorZppHasOnlySoftValues = true;

    public final boolean LeftMotorZppIsEmpty()
    {
        return _leftMotorZppValues.isEmpty();
    }

    public final void AddLeftMotorZppValue(NeurologyFormLevel level)
    {
        if (_leftMotorZppValues.contains(level.getName()) || "S4_5".equals(level.getName()))
        {
            return;
        }

        _leftMotorZppValues.add(level);
    }

    public final ArrayList<NeurologyFormLevel> GetLeftMotorZppValues()
    {
        return this._leftMotorZppValues; //new ArrayList<NeurologyFormLevel>();
    }
    ///#endregion

    ///#region Asia Impairment Scale
    //public string Complete;
    //public bool IsSensoryIncomplete;
    //public bool IsMotorIncomplete;
    private final ArrayList<String> _asiaImpairmentScaleValues = new ArrayList<String>();

    public final void AddAsiaImpairmentScaleValue(String value)
    {
        if (StringHelper.isNullOrEmpty(value) || _asiaImpairmentScaleValues.contains(value.toUpperCase()))
        {
            return;
        }

        _asiaImpairmentScaleValues.add(value.toUpperCase());
    }

    public final String GetAsiaImpairmentScaleValues()
    {
        Collections.sort(_asiaImpairmentScaleValues);
        String[] array = new String[_asiaImpairmentScaleValues.size()];
        array = _asiaImpairmentScaleValues.toArray(array);
        return StringHelper.join(",", array);
    }
    ///#endregion


    private NeurologyFormLevel MostRostralLeftMotor;
    public final NeurologyFormLevel getMostRostralLeftMotor()
    {
        return MostRostralLeftMotor;
    }
    protected final void setMostRostralLeftMotor(NeurologyFormLevel value)
    {
        MostRostralLeftMotor = value;
    }
    private NeurologyFormLevel MostCaudalLeftMotor;
    public final NeurologyFormLevel getMostCaudalLeftMotor()
    {
        return MostCaudalLeftMotor;
    }
    protected final void setMostCaudalLeftMotor(NeurologyFormLevel value)
    {
        MostCaudalLeftMotor = value;
    }

    private NeurologyFormLevel MostRostralNeurologicalLevelOfInjury;
    public final NeurologyFormLevel getMostRostralNeurologicalLevelOfInjury()
    {
        return MostRostralNeurologicalLevelOfInjury;
    }
    protected final void setMostRostralNeurologicalLevelOfInjury(NeurologyFormLevel value)
    {
        MostRostralNeurologicalLevelOfInjury = value;
    }
    private NeurologyFormLevel MostCaudalNeurologicalLevelOfInjury;
    public final NeurologyFormLevel getMostCaudalNeurologicalLevelOfInjury()
    {
        return MostCaudalNeurologicalLevelOfInjury;
    }
    protected final void setMostCaudalNeurologicalLevelOfInjury(NeurologyFormLevel value)
    {
        MostCaudalNeurologicalLevelOfInjury = value;
    }

    private NeurologyFormLevel MostRostralRightLevelWithMotorFunction;
    public final NeurologyFormLevel getMostRostralRightLevelWithMotorFunction()
    {
        return MostRostralRightLevelWithMotorFunction;
    }
    public final void setMostRostralRightLevelWithMotorFunction(NeurologyFormLevel value)
    {
        MostRostralRightLevelWithMotorFunction = value;
    }
    private NeurologyFormLevel MostCaudalRightLevelWithMotorFunction;
    public final NeurologyFormLevel getMostCaudalRightLevelWithMotorFunction()
    {
        return MostCaudalRightLevelWithMotorFunction;
    }
    public final void setMostCaudalRightLevelWithMotorFunction(NeurologyFormLevel value)
    {
        MostCaudalRightLevelWithMotorFunction = value;
    }
    private NeurologyFormLevel MostRostralLeftLevelWithMotorFunction;
    public final NeurologyFormLevel getMostRostralLeftLevelWithMotorFunction()
    {
        return MostRostralLeftLevelWithMotorFunction;
    }
    public final void setMostRostralLeftLevelWithMotorFunction(NeurologyFormLevel value)
    {
        MostRostralLeftLevelWithMotorFunction = value;
    }
    private NeurologyFormLevel MostCaudalLeftLevelWithMotorFunction;
    public final NeurologyFormLevel getMostCaudalLeftLevelWithMotorFunction()
    {
        return MostCaudalLeftLevelWithMotorFunction;
    }
    public final void setMostCaudalLeftLevelWithMotorFunction(NeurologyFormLevel value)
    {
        MostCaudalLeftLevelWithMotorFunction = value;
    }
}
