# Spinal Algorithm

This algorithm is designed to produce a spinal cord injury classification consistent with the International Standards for Neurological Classification of Spinal Cord Injury developed and maintained by the American Spinal Injury Association (ASIA).

It was written in Java. Information on about each class and method can be found at [Modules](#modules)

## Table Of Contents
- [Modules](#modules)
  - [SpinalAlgo.Algorithm](#spinalalgoalgorithm)
  - [SpinalAlgo.BinaryObservation](#spinalalgobinaryobservation)
  - [SpinalAlgo.NeurologyForm](#spinalalgoneurologyform)
  - [SpinalAlgo.NeurologyFormLevel](#spinalalgoneurologyformlevel)
  - [SpinalAlgo.NeurologyFormTotals](#spinalalgoneurologyformtotals)
  - [SpinalAlgo.NeurologyFormTotalsSummary](#spinalalgoneurologyformtotalssummary)
- [Tests](#tests)
- [Changes History](#changes-history)


## Modules

### SpinalAlgo.Algorithm
#### DESCRIPTION
The SpinalAlgo.Algorithm is used to produce summary for Neurology form and calculate totals from Neurology form.

#### METHOD(S)

Method | Params | Returns | Description
--------- | ----- | ------- | -----------
GetTotalsSummaryFor(NeurologyForm neurologyForm) | NeurologyForm Object| NeurologyFormTotalsSummary Object: Summarized version of the totals where the enumerations get replaced by ranges. | Produces a summarized version of a NeurologyFormTotals object which can be used to be displayed in an interface or be stored in a database.
GetTotalsSummaryFor(NeurologyFormTotals totals) | NeurologyFormTotals Object| NeurologyFormTotalsSummary Object: Summarized version of the totals where the enumerations get replaced by ranges. | Produces a summarized version of a NeurologyFormTotals object which can be used to be displayed in an interface or be stored in a database.
GetTotalsFor(NeurologyForm neurologyForm) | NeurologyForm Object | NeurologyFormTotals object: Totals in raw values format.  The results contain lists with every prossible value for each field. | Produces a neurology form totals by the ISNCSCI Algorithm in a raw value format.

### SpinalAlgo.BinaryObservation
#### DESCRIPTION
The SpinalAlgo.BinaryObservation is used to enum values("Yes", "No", "NT", "None") which may appear in a neurology form.

#### METHOD(S)
Method | Params | Returns | Description
------ | ------ | ------- | -----------
None || BinaryObservation Object | Returns BinaryObservation Object of "None".
Yes || BinaryObservation Object | Returns BinaryObservation Object of "Yes".
No || BinaryObservation Object | Returns BinaryObservation Object of "No".
NT || BinaryObservation Object | Returns BinaryObservation Object of "NT".
getValue() || int | Returns int value of BinaryObservation Object.
forValue(int value) | int | BinaryObservation Object | Returns corresponding BinaryObservation object of an int value.

### SpinalAlgo.NeurologyForm
#### DESCRIPTION
Contains the raw values from a Neurology Form.  Instances of this class can be passed to the Algorithm methods to obtain totals.

#### METHOD(S)
Method | Params | Returns | Description
------ | ------ | ------- | -----------
getAnalContraction() || BinaryObservation Object | Gets Anal Contraction.
setAnalContraction(BinaryObservation value) | BinaryObservation Object || Sets value for Anal Contraction.
getAnalSensation() || BinaryObservation Object | Gets Anal Sensation.
setAnalSensation(BinaryObservation value) | BinaryObservation Object || Sets value for Anal Sensation.
getRightLowestNonKeyMuscleWithMotorFunction() || NeurologyFormLevel Object | Gets a neurology level by right lowest non key muscle with motor function.
getLeftLowestNonKeyMuscleWithMotorFunction() |  NeurologyFormLevel Object || Gets a neurology level by left lowest non key muscle with motor function.
SetRightLowestNonKeyMuscleWithMotorFunction() ||| Sets the value of right lowest non key muscle with motor function
SetLeftLowestNonKeyMuscleWithMotorFunction() ||| Sets the value of left lowest non key muscle with motor function
GetLevelAt(int index) | int | NeurologyFormLevel Object | Returns Neurology level at the specified index.
GetLevelWithName(String levelName) | String | NeurologyFormLevel Object | Returns Neurology level that matches the specified level name.
UpdateLevelAt(String levelName, String rightTouchName, String leftTouchName, String rightPrickName, String leftPrickName, String rightMotorName, String leftMotorName) | String, String, String, String, String, String, String || Updates the values of the neurology level with the specified name. You can pass strings containing values between 0-2 for touch and prick and 0-5 for motor. You can also use the exclamation mark and asterisk at the end of the string to indicate impairment not due to a spinal cord injury. Finally, you can also pass NT to indicate that a value was not testable.

### SpinalAlgo.NeurologyFormLevel
#### DESCRIPTION
Contains the raw values from a Neurology level

#### Methods
Method | Params | Returns | Description
------ | ------ | ------- | -----------
getIsKeyMuscle() || <code>true</code> if the Neurology level is key muscle; <code>false</code> otherwise. | Check if the Neurology level is key muscle
setIsKeyMuscle(boolean value) | boolean ||Set the value of IsKeyMuscle of the Neurology level
getIsLowerMuscle() ||<code>true</code> if the Neurology level is lower muscle; <code>false</code> otherwise. | Check if the Neurology level is lower muscle
setIsLowerMuscle(boolean value) | boolean || Set the value of IsLowerMuscle of the Neurology level
getLeftTouchImpairmentNotDueToSci() || <code>true</code> if the left touch impairment is caused by Sci; <code>false</code> otherwise. | Check if left touch impairment is caused by Sci
setLeftTouchImpairmentNotDueToSci(boolean value) | boolean || Sets the value of LeftTouchImpairmentNotDueToSci of the Neurology level
getLeftTouchName() || String | Gets the name of left touch
setLeftTouchName(String value) | String || Sets the name of left touch
getLeftTouchValue() || String | Gets the value of left touch
setLeftTouchValue(int value) | int || Set the value of left touch
getLeftPrickImpairmentNotDueToSci() || <code>true</code> if the left prick impairment is not caused by Sci; <code>false</code> otherwise. | Checks if left prick impairment is not caused by Sci.
setLeftPrickImpairmentNotDueToSci(boolean value) | boolean || Sets the value of LeftPrickImpairmentNotDueToSci of the Neurology level.
getLeftPrickName() || String | Get the value of left prick.
setLeftPrickName(String value) | String || Set the name of left prick.
getLeftPrickValue() || String | Get the value of left prick.
setLeftPrickValue(int value) | int || Set the value of left prick
getLeftMotorImpairmentNotDueToSci() || <code>true</code> if the left motor impairment is not caused by Sci; <code>false</code> otherwise. | Check if left motor impairment is not caused by Sci.
getLeftMotorImpairmentNotDueToSci(boolean value) | boolean || Sets the value of LeftMotorImpairmentNotDueToSci.
getHasOtherLeftMotorFunction() || <code>true</code> if there is other left motor function; <code>false</code> otherwise. | Check if there is other left motor function
setHasOtherLeftMotorFunction(boolean value) | boolean || Sets the value of HasOtherLeftMotorFunction
getLeftMotorName() || String |Get the value of left motor.
setLeftMotorName(String value) | String || Sets the name of left motor.
getLeftMotorValue() || int | Gets the value of left motor.
setLeftMotorValue(int value) | int || Sets the value of left motor.
getName() || String | Gets the name of the Neurology level.
setName(String value) | String || Sets the name of the Neurology level.
getNext() || NeurologyFormLevel Object | Gets next Neurology level.
setNext(NeurologyFormLevel value) | NeurologyFormLevel Object || Sets next Neurology form level.
getOrdinal() || int | Gets the ordinal of Neurology level.
 setOrdinal(int value) | int || Sets the ordinal of Neurology level.
getPrevious() || NeurologyFormLevel Object | Gets previous Neurology level.
setPrevious(NeurologyFormLevel value) | NeurologyFormLevel Object || Sets previous Neurology level.
getRightTouchImpairmentNotDueToSci() || boolean | Checks if right touch impairment is not caused by Sci.
setRightTouchImpairmentNotDueToSci(boolean value) | boolean || Set. the value of RightTouchImpairmentNotDueToSci of the Neurology level.
getRightTouchName() || String | Gets the name of right touch.
setRightTouchName(String value) | String || Sets the name of right touch.
getRightTouchValue() || String | Gets the value of right touch.
setRightTouchValue(int value) | int || Sets the value of right touch.
getRightPrickImpairmentNotDueToSci() || boolean | Checks if right prick impairment is not caused by Sci.
setRightPrickImpairmentNotDueToSci(boolean value) | boolean || Sets the value of RightPrickImpairmentNotDueToSci of the Neurology level.
getRightPrickName() || String | Gets the name of right prick.
setRightPrickName(String value) | String || Sets the name of right prick.
getRightPrickValue() || int | Gets the value of right prick.
setRightPrickValue(int value) | int || Sets the value of right prick.
getRightMotorImpairmentNotDueToSci() || boolean | Checks if right motor impairment is not caused by Sci.
setRightMotorImpairmentNotDueToSci(boolean value) | boolean || Sets the value of RightMotorImpairmentNotDueToSci.
getHasOtherRightMotorFunction() || boolean | Checks if there is other right motor function.
setHasOtherRightMotorFunction(boolean value) | boolean | Sets the value of HasOtherRightMotorFunction.
getRightMotorName() || String | Gets the name of right motor.
setRightMotorName(String value) | String || Sets the name of right motor.
getRightMotorValue() || int | Gets the value of right motor.
setRightMotorValue(int value) | int || Sets the value of right mortor.
SetValues(int ordinal, boolean isKeyMuscle, boolean isLowerMuscle, String rightTouchName, int rightTouchValue, boolean rightTouchImpairmentNotDueToSci, String leftTouchName, int leftTouchValue, boolean leftTouchImpairmentNotDueToSci, String rightPrickName, int rightPrickValue, boolean rightPrickImpairmentNotDueToSci, String leftPrickName, int leftPrickValue, boolean leftPrickImpairmentNotDueToSci, String rightMotorName, int rightMotorValue, boolean rightMotorImpairmentNotDueToSci, String leftMotorName, int leftMotorValue, boolean leftMotorImpairmentNotDueToSci) | int, boolean, boolean, String, int, boolean, String, int, boolean, String, int, boolean, String, int, boolean, String, int, boolean, String, int, boolean || Sets the Neurology level.

### SpinalAlgo.NeurologyFormTotals
#### DESCRIPTION
Calculates the neurology form totals from neurology form raw values.
#### METHOD(S)
Method | Params | Returns | Description
------ | ------ | ------- | -----------
RightSensoryIsEmpty() || boolean | Checks whether the values of right sensory is empty.
AddRightSensoryValue(NeurologyFormLevel level) | NeurologyFormLevel Object || Adds a neurology form level to the values of right sensory.
GetRightSensoryValues() || ArrayList<NeurologyFormLevel> | Gets the values of right sensory.
RightSensoryContains(String levelName) | String | boolean | Checks whether a level is in the values of right sensory.
LeftSensoryIsEmpty() || boolean | Checks whether the values of left sensory is empty.
AddLeftSensoryValue(NeurologyFormLevel level) | NeurologyFormLevel Object || Adds a neurology form level to the values of left sensory.
GetLeftSensoryValues() || ArrayList<NeurologyFormLevel> | Gets the values LeftSensoryContains(String levelName)
getMostRostralRightMotor() || NeurologyFormLevel Object | Gets the most rostral right motor neurology form level.
setMostRostralRightMotor(NeurologyFormLevel value) | NeurologyFormLevel Object || Sets the value of the most rostral right motor.
getMostCaudalRightMotor() || NeurologyFormLevel Object | Gets the most caudal right motor neurology form level.
setMostCaudalRightMotor(NeurologyFormLevel value) | NeurologyFormLevel Object || Sets the value of the most caudal right motor.
RightMotorIsEmpty() || boolean | Checks whether the values of right motor is empty.
AddRightMotorValue(NeurologyFormLevel level) | NeurologyFormLevel Object || Added a level to the values of right motor.
GetRightMotorValues() || ArrayList<NeurologyFormLevel> | Gets the values of right motor.
RightMotorContains(String levelName) | String | boolean | Checks whether the values of right motor contains a level.
LeftMotorIsEmpty() || boolean | Checks whether the values of left motor is empty.
AddLeftMotorValue(NeurologyFormLevel level) | NeurologyFormLevel Object || Added a level to the values of left motor.
GetLeftMotorValues() || ArrayList<NeurologyFormLevel> | Gets the values of left motor.
LeftMotorContains(String levelName) | String | boolean | Checks whether the values of left motor contains a level.
AddNeurologicalLevelOfInjury(NeurologyFormLevel level) | NeurologyFormLevel Object || Adds a Neurology form level to the values of Neurological level of injury.
GetNeurologicalLevelsOfInjury() || ArrayList<NeurologyFormLevel> | Gets the values of Neurological level of injury.
RightSensoryZppIsEmpty() || boolean | Checks whether the values of right sensory zpp is empty
AddRightSensoryZppValue(NeurologyFormLevel level) | NeurologyFormLevel Object || Adds a neurology form level to the values of right sensory zpp.
GetRightSensoryZppValues() || ArrayList<NeurologyFormLevel> | Gets the values of right sensory zpp.
LeftSensoryZppIsEmpty() || boolean | Checks whether the values of left sensory zpp is empty
AddLeftSensoryZppValue(NeurologyFormLevel level) | NeurologyFormLevel Object || Adds a neurology form level to the values of left sensory zpp.
GetLeftSensoryZppValues() || ArrayList<NeurologyFormLevel> | Gets the values of left sensory zpp.
AddRightMotorZppValue(NeurologyFormLevel level) | NeurologyFormLevel Object || Adds a neurology form level to the values of right motor zpp.
GetRightMotorZppValues() || ArrayList<NeurologyFormLevel> | Gets the values of right motor zpp.
AddLeftMotorZppValue(NeurologyFormLevel level) | NeurologyFormLevel Object || Adds a neurology form level to the values of left motor zpp.
GetLeftMotorZppValues() || ArrayList<NeurologyFormLevel> | Gets the values of left motor zpp.
AddAsiaImpairmentScaleValue(String value) | String || Adds a value to the values of Asia impairment scale.
GetAsiaImpairmentScaleValues() || String | Gets the values of Asia impairment scale as a String.
getMostRostralLeftMotor() || NeurologyFormLevel Object | Gets the most rostral left motor neurology form level.
setMostRostralLeftMotor(NeurologyFormLevel value) | NeurologyFormLevel Object || Sets the value of the most rostral left motor.
getMostCaudalLeftMotor() || NeurologyFormLevel Object | Gets the most caudal left motor neurology form level.
setMostCaudalLeftMotor(NeurologyFormLevel value) | NeurologyFormLevel Object || Sets the value of the most caudal left motor.
getMostRostralNeurologicalLevelOfInjury() || NeurologyFormLevel | Gets the most rostral neurological level of injury level.
setMostRostralNeurologicalLevelOfInjury(NeurologyFormLevel value) | NeurologyFormLevel || Sets the value of the most rostral neurological level of injury level.
getMostCaudalNeurologicalLevelOfInjury() || NeurologyFormLevel | Gets the most caudal neurological level of injury level.
setMostCaudalNeurologicalLevelOfInjury(NeurologyFormLevel value) | NeurologyFormLevel || Sets the value of the most caudal neurological level of injury level.
getMostRostralRightLevelWithMotorFunction() || NeurologyFormLevel | Gets the level by most rostral right level with motor function.
setMostRostralRightLevelWithMotorFunction(NeurologyFormLevel value) | NeurologyFormLevel || Sets the value of the most rostral right level with motor function.
getMostCaudalRightLevelWithMotorFunction() || NeurologyFormLevel | Gets the level by most caudal right level with motor function.
setMostCaudalRightLevelWithMotorFunction(NeurologyFormLevel value) | NeurologyFormLevel || Sets the value of the most caudal right level with motor function.
getMostRostralLeftLevelWithMotorFunction() || NeurologyFormLevel | Gets the level by most rostral left level with motor function.
setMostRostralLeftLevelWithMotorFunction(NeurologyFormLevel value) | NeurologyFormLevel || Sets the value of the most rostral left level with motor function.
getMostCaudalLeftLevelWithMotorFunction() || NeurologyFormLevel | Gets the level by most caudal left level with motor function.
setMostCaudalLeftLevelWithMotorFunction(NeurologyFormLevel value) | NeurologyFormLevel || Sets the value of the most caudal left level with motor function.

### SpinalAlgo.NeurologyFormTotalsSummary
#### DESCRIPTION
Contains the summarized value for Neurology form totals summary.

#### VARIABLES
Variable | Type
-------- | ----
AsiaImpairmentScale | String
Completeness | String
LeftLowerMotorTotal | String
LeftMotor | String
LeftMotorZpp | String
LeftMotorTotal | String
LeftPrickTotal | String
LeftSensory | String
LeftSensoryZpp | String
LeftTouchTotal | String
LeftUpperMotorTotal | String
LowerMotorTotal | String
NeurologicalLevelOfInjury | String
PrickTotal | String
RightLowerMotorTotal | String
RightMotor | String
RightMotorZpp | String
RightMotorTotal | String
RightPrickTotal | String
RightSensory | String
RightSensoryZpp | String
RightTouchTotal | String
RightUpperMotorTotal | String
TouchTotal | String
UpperMotorTotal | String

## Tests
#### Tests include:
  - RunAllTests: loads data from xml files that compare NeurologyForm data with expected data.
  - CanGetTotalsSummaryForNtCase2: compare neurology form summary for ISNCSCI NT Case 2.xml
  - CanGetTotalsSummaryForNtCase4: compare neurology form summary for ISNCSCI NT Case 4.xml
  - CalculateTestNTCase4: test click calculate button and compare neurology form summary for ISNCSCI NT Case 4.xml
  - SaveToDBTest: test click save button, save with or without modid (need to set up dbName, serverName, port, userName and password before test)

#### Test Instruction
- Set up database conf at the main method AlgorithmTests.java
```java
String dbName = "databasename";
String driverName = "com.mysql.jdbc.Driver";
String serverName = "localhost";
String port = "3306";
String userName = "username";
String password = "password";
```
- Set modid for test saveSpinalAlgoResult which will update result(the data from ISNCSCI NT Case 4.xml) for modid if modid exists) for modid at main method AlgorithmTests.java
- Run AlgorithmTests.java with args for testing: one argument "YES" for test database update, "NO" for skip test database update

## Changes History:
- *Monday, January 9 2017:*
  - RightSensoryContains: fixed check whether _rightSensoryValues contains a level not level's name
  - RightMotorContains: fixed check whether _rightMotorValues contains a level not level's name
  - LeftSensoryContains: fixed check whether _leftSensoryValues contains a level not level's name
  - LeftMotorContains: fixed check whether _leftMotorValues contains a level not level's name
- *Tuesday, January 10 2017:*
  - AddRightSensoryValue: added check whether _rightSensoryValues contains the level before add it
  - AddRightMotorValue: added check whether _rightMotorValues contains the level before add it
  - AddLeftSensoryValue: added check whether _leftSensoryValues contains the level before add it
  - AddLeftMotorValue: added check whether _leftMotorValues contains the level before add it
  - UpdateLevel: replaced "level.getRightPrickValue()" to "level.getLeftPrickValue()" at line 309 NeurologyForm.java
- *Wednesday, January 11 2017:*
  - AddNeurologicalLevelOfInjury: check whether _neurologicalLevelsOfInjury contains a level not a level's name
  - AddRightSensoryZppValue: check whether _rightSensoryZppValues contains a level not a level's name or level's name is not equals to "S4_5"
  - AlgorithmTests.java now can retrieve and calculate all input data(NeurologyForm elements in the xml files) from Resources/IsncsciTestCases. The outputs are matched with original data(NeurologyFormTotals elements in xml files) from Resources/IsncsciTestCases.
  - added summary test for Nt Case2 and Nt Case4
  - fixed GetLevelsRange by adding sort levels
- *Thursday, January 12 2017:*
  - added SpinalAlgo classes and methods descriptions to ReadMe.md
- *Tuesday, January 17 2017:*
  - added CalculateAlgorithmTests.java and SpinalUpdateTests.java
  - test click calculate button and compare neurology form summary for ISNCSCI NT Case 4.xml
  - test click save button, save with or without modid
  - 'asia_sensory_right_neuro', 'asia_sensory_left_neuro', 'asia_motor_right_neuro' and 'asia_motor_left_neuro' length issue in DB
- *Monday, January 23 2017:*
  - fixed db Access denied error
  - added test with args
