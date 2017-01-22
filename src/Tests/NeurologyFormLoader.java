package Tests;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/


import Core.NeurologyFormTotals;
import Core.NeurologyForm;
import Core.NeurologyFormTotalsSummary;
import Core.NeurologyFormLevel;
import Core.BinaryObservation;
import Core.Algorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
/**
*
* @author Jason_-zZ
*/
public class NeurologyFormLoader {
  private static String[] LevelNames = new String[]{"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12", "L1", "L2", "L3", "L4", "L5", "S1", "S2", "S3", "S4_5"};
  private static String[] KeyMuscles = new String[] { "C5", "C6", "C7", "C8", "T1", "L2", "L3", "L4", "L5", "S1" };
  private final int NormalMotorValue = 5;
  private final int NormalSensoryValue = 2;
  private static Pattern NonSciImpairmentRegex = Pattern.compile(".+[!]");
  private static Pattern NonSciImpairmentFlagsRegex = Pattern.compile("[\\*!]");
  private static Pattern NtRegex = Pattern.compile("^nt$",Pattern.CASE_INSENSITIVE);
  private static String ImpairmentNotDueToSciRegex = "!$";
  //    private static Pattern ImpairmentNotDueToSciRegex = Pattern.compile(@"\!$");

  public static NeurologyForm LoadNeurologyFormFrom(Document xmlDocument){
    Element form = (Element)xmlDocument.getElementsByTagName("NeurologyForm").item(0);

    NeurologyForm neurologyForm = new NeurologyForm();
    /**
    * set AnalContraction
    */
    switch(form.getElementsByTagName("AnalContraction").item(0).getTextContent()){
      case "Yes":
      neurologyForm.setAnalContraction(BinaryObservation.Yes);
      break;
      case "No":
      neurologyForm.setAnalContraction(BinaryObservation.No);
      break;
      case "NT":
      neurologyForm.setAnalContraction(BinaryObservation.NT);
      break;
      case "None":
      neurologyForm.setAnalContraction(BinaryObservation.None);
      break;
    }

    /**
    * set AnalSensation
    */
    switch(form.getElementsByTagName("AnalSensation").item(0).getTextContent()){
      case "Yes":
      neurologyForm.setAnalSensation(BinaryObservation.Yes);
      break;
      case "No":
      neurologyForm.setAnalSensation(BinaryObservation.No);
      break;
      case "NT":
      neurologyForm.setAnalSensation(BinaryObservation.NT);
      break;
      case "None":
      neurologyForm.setAnalSensation(BinaryObservation.None);
      break;
    }

    neurologyForm.SetRightLowestNonKeyMuscleWithMotorFunction(form.getElementsByTagName("RightLowestNonKeyMuscleWithMotorFunction").item(0).getTextContent());
    neurologyForm.SetLeftLowestNonKeyMuscleWithMotorFunction(form.getElementsByTagName("LeftLowestNonKeyMuscleWithMotorFunction").item(0).getTextContent());

    NodeList dermatomes = xmlDocument.getElementsByTagName("Dermatome");
    for(int temp = 0; temp < dermatomes.getLength(); temp++){
      Node dermatome = dermatomes.item(temp);
      if(dermatome.getNodeType() == Node.ELEMENT_NODE){
        Element dermatomeElement = (Element)dermatome;
        AddValuesFromDermatomeToForm(dermatomeElement, neurologyForm);
      }
    }

    return neurologyForm;
  }

  public static NeurologyFormTotals LoadNeurologyFormTotalsFrom(Document xmlDocument){
    Element totals = (Element)xmlDocument.getElementsByTagName("NeurologyFormTotals").item(0);
    NeurologyFormTotals neurologyFormTotals = new NeurologyFormTotals();
    neurologyFormTotals.LeftPrickContainsNt = Boolean.parseBoolean(totals.getElementsByTagName("LeftPrickContainsNt").item(0).getTextContent());
    neurologyFormTotals.LeftPrickTotal = GetIntValueFrom(totals.getElementsByTagName("LeftPrickTotal").item(0).getTextContent());
    neurologyFormTotals.LeftPrickTotalHasImpairmentNotDueToSci = DoesTotalHaveImpairmentNotDueToSci(totals.getElementsByTagName("LeftPrickTotal").item(0).getTextContent());
    neurologyFormTotals.LeftTouchContainsNt = Boolean.parseBoolean(totals.getElementsByTagName("LeftTouchContainsNt").item(0).getTextContent());
    neurologyFormTotals.LeftTouchTotal = GetIntValueFrom(totals.getElementsByTagName("LeftTouchTotal").item(0).getTextContent());
    neurologyFormTotals.LeftTouchTotalHasImpairmentNotDueToSci = DoesTotalHaveImpairmentNotDueToSci(totals.getElementsByTagName("LeftTouchTotal").item(0).getTextContent());
    neurologyFormTotals.LeftLowerMotorContainsNt = Boolean.parseBoolean(totals.getElementsByTagName("LeftLowerMotorContainsNt").item(0).getTextContent());
    neurologyFormTotals.LeftLowerMotorTotal = GetIntValueFrom(totals.getElementsByTagName("LeftLowerMotorTotal").item(0).getTextContent());
    neurologyFormTotals.LeftLowerMotorTotalHasImpairmentNotDueToSci = DoesTotalHaveImpairmentNotDueToSci(totals.getElementsByTagName("LeftLowerMotorTotal").item(0).getTextContent());
    neurologyFormTotals.LeftUpperMotorContainsNt = Boolean.parseBoolean(totals.getElementsByTagName("LeftUpperMotorContainsNt").item(0).getTextContent());
    neurologyFormTotals.LeftUpperMotorTotal = GetIntValueFrom(totals.getElementsByTagName("LeftUpperMotorTotal").item(0).getTextContent());
    neurologyFormTotals.LeftUpperMotorTotalHasImpairmentNotDueToSci = DoesTotalHaveImpairmentNotDueToSci(totals.getElementsByTagName("LeftUpperMotorTotal").item(0).getTextContent());
    neurologyFormTotals.LowerMotorTotal = GetIntValueFrom(totals.getElementsByTagName("LowerMotorTotal").item(0).getTextContent());
    neurologyFormTotals.PrickTotal = GetIntValueFrom(totals.getElementsByTagName("PrickTotal").item(0).getTextContent());

    neurologyFormTotals.RightLowerMotorContainsNt = Boolean.parseBoolean(totals.getElementsByTagName("RightLowerMotorContainsNt").item(0).getTextContent());
    neurologyFormTotals.RightLowerMotorTotal = GetIntValueFrom(totals.getElementsByTagName("RightLowerMotorTotal").item(0).getTextContent());
    neurologyFormTotals.RightLowerMotorTotalHasImpairmentNotDueToSci = DoesTotalHaveImpairmentNotDueToSci(totals.getElementsByTagName("RightLowerMotorTotal").item(0).getTextContent());
    neurologyFormTotals.RightUpperMotorContainsNt = Boolean.parseBoolean(totals.getElementsByTagName("RightUpperMotorContainsNt").item(0).getTextContent());
    neurologyFormTotals.RightUpperMotorTotal = GetIntValueFrom(totals.getElementsByTagName("RightUpperMotorTotal").item(0).getTextContent());
    neurologyFormTotals.RightUpperMotorTotalHasImpairmentNotDueToSci = DoesTotalHaveImpairmentNotDueToSci(totals.getElementsByTagName("RightUpperMotorTotal").item(0).getTextContent());
    neurologyFormTotals.RightPrickContainsNt = Boolean.parseBoolean(totals.getElementsByTagName("RightPrickContainsNt").item(0).getTextContent());
    neurologyFormTotals.RightPrickTotal = GetIntValueFrom(totals.getElementsByTagName("RightPrickTotal").item(0).getTextContent());
    neurologyFormTotals.RightPrickTotalHasImpairmentNotDueToSci = DoesTotalHaveImpairmentNotDueToSci(totals.getElementsByTagName("RightPrickTotal").item(0).getTextContent());
    neurologyFormTotals.RightTouchContainsNt = Boolean.parseBoolean(totals.getElementsByTagName("RightTouchContainsNt").item(0).getTextContent());
    neurologyFormTotals.RightTouchTotal = GetIntValueFrom(totals.getElementsByTagName("RightTouchTotal").item(0).getTextContent());
    neurologyFormTotals.RightTouchTotalHasImpairmentNotDueToSci = DoesTotalHaveImpairmentNotDueToSci(totals.getElementsByTagName("RightTouchTotal").item(0).getTextContent());
    neurologyFormTotals.TouchTotal = GetIntValueFrom(totals.getElementsByTagName("TouchTotal").item(0).getTextContent());
    neurologyFormTotals.UpperMotorTotal = GetIntValueFrom(totals.getElementsByTagName("UpperMotorTotal").item(0).getTextContent());

    Map<String, NeurologyFormLevel> levelsDictionary = GetLevelsDictionary();


    for(int i = 0; i < totals.getElementsByTagName("RightSensory").getLength(); i++){
      String[] elements = totals.getElementsByTagName("RightSensory").item(i).getTextContent().toUpperCase().split(",");
      for(String element: elements){
        neurologyFormTotals.AddRightSensoryValue(levelsDictionary.get(element.trim()));
      }
    }

    for(int i = 0; i < totals.getElementsByTagName("LeftSensory").getLength(); i++){
      String[] elements = totals.getElementsByTagName("LeftSensory").item(i).getTextContent().toUpperCase().split(",");
      for(String element: elements){
        neurologyFormTotals.AddLeftSensoryValue(levelsDictionary.get(element.trim()));
      }
    }

    for(int i = 0; i < totals.getElementsByTagName("RightMotor").getLength(); i++){
      String[] elements = totals.getElementsByTagName("RightMotor").item(i).getTextContent().toUpperCase().split(",");
      for(String element: elements){
        neurologyFormTotals.AddRightMotorValue(levelsDictionary.get(element.trim()));
      }
    }

    for(int i = 0; i < totals.getElementsByTagName("LeftMotor").getLength(); i++){
      String[] elements = totals.getElementsByTagName("LeftMotor").item(i).getTextContent().toUpperCase().split(",");
      for(String element: elements){
        neurologyFormTotals.AddLeftMotorValue(levelsDictionary.get(element.trim()));
      }
    }

    for(int i = 0; i < totals.getElementsByTagName("NeurologicalLevelOfInjury").getLength(); i++){
      String[] elements = totals.getElementsByTagName("NeurologicalLevelOfInjury").item(i).getTextContent().toUpperCase().split(",");
      for(String element: elements){
        neurologyFormTotals.AddNeurologicalLevelOfInjury(levelsDictionary.get(element.trim()));
      }
    }

    for(int i = 0; i < totals.getElementsByTagName("RightSensoryZpp").getLength(); i++){
      String[] elements = totals.getElementsByTagName("RightSensoryZpp").item(i).getTextContent().toUpperCase().split(",");
      for(String element: elements){
        if(element != null && !element.isEmpty()){
          neurologyFormTotals.AddRightSensoryZppValue(levelsDictionary.get(element.trim()));
        }
      }
    }

    for(int i = 0; i < totals.getElementsByTagName("LeftSensoryZpp").getLength(); i++){
      String[] elements = totals.getElementsByTagName("LeftSensoryZpp").item(i).getTextContent().toUpperCase().split(",");
      for(String element: elements){
        if(element != null && !element.isEmpty()){
          neurologyFormTotals.AddLeftSensoryZppValue(levelsDictionary.get(element.trim()));
        }
      }
    }

    for(int i = 0; i < totals.getElementsByTagName("RightMotorZpp").getLength(); i++){
      String[] elements = totals.getElementsByTagName("RightMotorZpp").item(i).getTextContent().toUpperCase().split(",");
      for(String element: elements){
        if(element != null && !element.isEmpty()){
          neurologyFormTotals.AddRightMotorZppValue(levelsDictionary.get(element.trim()));
        }
      }
    }

    for(int i = 0; i < totals.getElementsByTagName("LeftMotorZpp").getLength(); i++){
      String[] elements = totals.getElementsByTagName("LeftMotorZpp").item(i).getTextContent().toUpperCase().split(",");
      for(String element: elements){
        if(element != null && !element.isEmpty()){
          neurologyFormTotals.AddLeftMotorZppValue(levelsDictionary.get(element.trim()));
        }
      }
    }

    for(int i = 0; i < totals.getElementsByTagName("AsiaImpairmentScale").getLength(); i++){
      neurologyFormTotals.AddAsiaImpairmentScaleValue(totals.getElementsByTagName("AsiaImpairmentScale").item(i).getTextContent().toUpperCase().trim().replaceAll(" ", ""));
    }
    return neurologyFormTotals;
  }


  private static int GetIntValueFrom(String value){
    int returnValue;
    returnValue = Integer.parseInt(value.replaceAll(ImpairmentNotDueToSciRegex, ""));
    return returnValue;
  }

  private static boolean DoesTotalHaveImpairmentNotDueToSci(String value){
    Pattern regex = Pattern.compile(ImpairmentNotDueToSciRegex);
    return regex.matcher(value).find();
  }

  private static void AddValuesFromDermatomeToForm(Element dermatomeElement, NeurologyForm neurologyForm){
    String rightTouchName = dermatomeElement.getElementsByTagName("RightTouch").item(0).getTextContent();
    String rightPrickName = dermatomeElement.getElementsByTagName("RightPrick").item(0).getTextContent();
    String leftTouchName = dermatomeElement.getElementsByTagName("LeftTouch").item(0).getTextContent();
    String leftPrickName = dermatomeElement.getElementsByTagName("LeftPrick").item(0).getTextContent();

    Element rightMotorElement = (Element)dermatomeElement.getElementsByTagName("RightMotor").item(0);
    String rightMotorName = rightMotorElement == null ? "0" : rightMotorElement.getTextContent();

    Element leftMotorElement = (Element)dermatomeElement.getElementsByTagName("LeftMotor").item(0);
    String leftMotorName = leftMotorElement == null ? "0" : leftMotorElement.getTextContent();

    neurologyForm.UpdateLevelAt(dermatomeElement.getAttribute("name"), rightTouchName, leftTouchName, rightPrickName, leftPrickName, rightMotorName, leftMotorName);
  }

  private static Map<String, NeurologyFormLevel> GetLevelsDictionary(){
    NeurologyFormLevel C1 = new NeurologyFormLevel();
    C1.setOrdinal(0);
    C1.setIsKeyMuscle(false);
    C1.setIsLowerMuscle(false);
    C1.setName("C1");
    C1.setPrevious(null);
    C1.setRightMotorName("5");
    C1.setRightMotorValue(5);
    C1.setLeftMotorName("5");
    C1.setLeftMotorValue(5);
    C1.setRightTouchName("2");
    C1.setRightTouchValue(2);
    C1.setLeftTouchName("2");
    C1.setLeftTouchValue(2);
    C1.setRightPrickName("2");
    C1.setRightPrickValue(2);
    C1.setLeftPrickName("2");
    C1.setLeftPrickValue(2);

    Map<String, NeurologyFormLevel> levels = new HashMap<String, NeurologyFormLevel>() {};
    NeurologyFormLevel previousLevel = C1;

    for (int i = 0; i < LevelNames.length; i++){
      String name = LevelNames[i];
      NeurologyFormLevel currentLevel;
      if(i==0){
        currentLevel = C1;
      }else{
        currentLevel = new NeurologyFormLevel();
        currentLevel.setIsKeyMuscle(Arrays.asList(KeyMuscles).contains(name));
        currentLevel.setIsLowerMuscle((i >= 20 && i <= 24));
        currentLevel.setName(name);
        currentLevel.setOrdinal(i + 1);
        currentLevel.setPrevious(previousLevel);
      }
      previousLevel.setNext(currentLevel);
      previousLevel = currentLevel;
      levels.put(name, currentLevel);
    }

    return levels;
  }

  public static JSONObject LoadJSONFrom(Document xmlDocument) throws JSONException{
    Element form = (Element)xmlDocument.getElementsByTagName("NeurologyForm").item(0);
    NodeList dermatomes = xmlDocument.getElementsByTagName("Dermatome");
    JSONObject json = new JSONObject();

    /**
    * set AnalContraction
    */
    switch(form.getElementsByTagName("AnalContraction").item(0).getTextContent()){
      case "Yes":
        json.put("vac", "Y");
        break;
      case "No":
        json.put("vac", "N");
        break;
      case "NT":
        json.put("vac", "NT");
        break;
    }

    /**
    * set AnalSensation
    */
    switch(form.getElementsByTagName("AnalSensation").item(0).getTextContent()){
      case "Yes":
        json.put("dap", "Y");
        break;
      case "No":
        json.put("dap", "N");
        break;
      case "NT":
        json.put("dap", "NT");
        break;
    }

    for(int temp = 0; temp < dermatomes.getLength(); temp++){
      Node dermatome = dermatomes.item(temp);
      if(dermatome.getNodeType() == Node.ELEMENT_NODE){
        Element dermatomeElement = (Element)dermatome;
        AddValuesFromDermatomeToJSON(dermatomeElement, json);
      }
    }

    NeurologyForm neurologyForm = LoadNeurologyFormFrom(xmlDocument);
    NeurologyFormTotals neurologyTotals = Algorithm.GetTotalsFor(neurologyForm);
    
    NeurologyFormTotalsSummary neurologySummary = Algorithm.GetTotalsSummaryFor(neurologyTotals);
    
    json.put("sensoryRight", neurologySummary.RightSensory);
    json.put("sensoryLeft", neurologySummary.LeftSensory);
    json.put("motorRight", neurologySummary.RightMotor);
    json.put("motorLeft", neurologySummary.LeftMotor);
    json.put("comp", neurologySummary.Completeness);
    json.put("nli", neurologySummary.NeurologicalLevelOfInjury);
    json.put("ais", neurologySummary.AsiaImpairmentScale);
    json.put("sensoryRightPatial", neurologySummary.RightSensoryZpp);
    json.put("sensoryLeftPatial", neurologySummary.LeftSensoryZpp);
    json.put("motorRightPatial", neurologySummary.RightMotorZpp);
    json.put("motorLeftPatial", neurologySummary.LeftMotorZpp);
    json.put("rtrm", neurologySummary.RightMotorTotal);
    json.put("rtrt", neurologySummary.RightTouchTotal);
    json.put("rtrp", neurologySummary.RightPrickTotal);
    json.put("ltlm", neurologySummary.LeftMotorTotal);
    json.put("ltlt", neurologySummary.LeftTouchTotal);
    json.put("ltlp", neurologySummary.LeftPrickTotal);
    json.put("uer", neurologySummary.RightUpperMotorTotal);
    json.put("uel", neurologySummary.LeftUpperMotorTotal);
    json.put("uems", neurologySummary.UpperMotorTotal);
    json.put("ler", neurologySummary.RightLowerMotorTotal);
    json.put("lel", neurologySummary.LeftLowerMotorTotal);
    json.put("lems", neurologySummary.LowerMotorTotal);
    json.put("lt", neurologySummary.TouchTotal);
    json.put("pp", neurologySummary.PrickTotal);
    
    json.put("comment", "");
    

    return json;
  }

  private static void AddValuesFromDermatomeToJSON(Element dermatomeElement, JSONObject json){
    String dermatomeName = dermatomeElement.getAttribute("name");
    dermatomeName = dermatomeName.toLowerCase().replace("_", "");
    String rightTouchName = dermatomeName + "rt";
    String leftTouchName = dermatomeName + "lt";
    String rightPrickName = dermatomeName + "rp";
    String leftPrickName = dermatomeName + "lp";
    String rightMotorName = dermatomeName + "rm";
    String leftMotorName = dermatomeName + "lm";

    String rightTouchValue = dermatomeElement.getElementsByTagName("RightTouch").item(0).getTextContent();
    String rightPrickValue = dermatomeElement.getElementsByTagName("RightPrick").item(0).getTextContent();
    String leftTouchValue = dermatomeElement.getElementsByTagName("LeftTouch").item(0).getTextContent();
    String leftPrickValue = dermatomeElement.getElementsByTagName("LeftPrick").item(0).getTextContent();

    Element rightMotorElement = (Element)dermatomeElement.getElementsByTagName("RightMotor").item(0);
    String rightMotorValue = rightMotorElement == null ? "0" : rightMotorElement.getTextContent();

    Element leftMotorElement = (Element)dermatomeElement.getElementsByTagName("LeftMotor").item(0);
    String leftMotorValue = leftMotorElement == null ? "0" : leftMotorElement.getTextContent();

    try {
      json.put(rightTouchName, rightTouchValue);
      json.put(rightPrickName, rightPrickValue);
      json.put(leftTouchName, leftTouchValue);
      json.put(leftPrickName, leftPrickValue);
      json.put(rightMotorName, rightMotorValue);
      json.put(leftMotorName, leftMotorValue);
    } catch (JSONException ex) {
      Logger.getLogger(NeurologyFormLoader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static String GetValueStringFrom(ArrayList<NeurologyFormLevel> levels){
    Collections.sort(levels, new Comparator<NeurologyFormLevel>() {
      @Override
      public int compare(NeurologyFormLevel level1, NeurologyFormLevel level2) {
        int ordinal1 = level1.getOrdinal();
        int ordinal2 = level2.getOrdinal();
        if(ordinal1 <= ordinal2){
          return -1;
        }else{
          return 1;
        }
      }
    });
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < levels.size(); i++){
      sb.append(levels.get(i).getName());
      if(levels.size() > 1 && i != levels.size() - 1){
        sb.append(",");
      }
    }
    return sb.toString();
  }
}
