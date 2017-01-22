package Tests;



/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/


import Core.NeurologyForm;
import Core.NeurologyFormTotalsSummary;
import Core.NeurologyFormTotals;
import Core.NeurologyFormLevel;
import Core.Algorithm;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
/**
*
* @author Jason_-zZ
*/
public class AlgorithmTests {
  private static final String NotDeterminable = "UTD";

  private static void TestForm(NeurologyForm neurologyForm, NeurologyFormTotals expected){
    // Arrange - Act
    NeurologyFormTotals totals = Algorithm.GetTotalsFor(neurologyForm);

    // Assert
    Assert.assertEquals("Right touch total", expected.RightTouchTotal, totals.RightTouchTotal);
    Assert.assertEquals("Right touch total contains NT", expected.RightTouchContainsNt, totals.RightTouchContainsNt);
    Assert.assertEquals("Right touch total has impairment not due to SCI", expected.RightTouchTotalHasImpairmentNotDueToSci, totals.RightTouchTotalHasImpairmentNotDueToSci);
    Assert.assertEquals("Left touch total", expected.LeftTouchTotal, totals.LeftTouchTotal);
    Assert.assertEquals("Left touch total contains NT", expected.LeftTouchContainsNt, totals.LeftTouchContainsNt);
    Assert.assertEquals("Left touch total has impairment not due to SCI", expected.LeftTouchTotalHasImpairmentNotDueToSci, totals.LeftTouchTotalHasImpairmentNotDueToSci);
    Assert.assertEquals("Right prick total", expected.RightPrickTotal, totals.RightPrickTotal);
    Assert.assertEquals("Right prick total contains NT", expected.RightPrickContainsNt, totals.RightPrickContainsNt);
    Assert.assertEquals("Right prick total has impairment not due to SCI", expected.RightPrickTotalHasImpairmentNotDueToSci, totals.RightPrickTotalHasImpairmentNotDueToSci);
    Assert.assertEquals("Left prick total", expected.LeftPrickTotal, totals.LeftPrickTotal);
    Assert.assertEquals("Left prick total contains NT", expected.LeftPrickContainsNt, totals.LeftPrickContainsNt);
    Assert.assertEquals("Left prick total has impairment not due to SCI", expected.LeftPrickTotalHasImpairmentNotDueToSci, totals.LeftPrickTotalHasImpairmentNotDueToSci);
    Assert.assertEquals("Touch total", expected.TouchTotal, totals.TouchTotal);
    Assert.assertEquals("Prick", expected.PrickTotal, totals.PrickTotal);
    Assert.assertEquals("Right upper motor total", expected.RightUpperMotorTotal, totals.RightUpperMotorTotal);
    Assert.assertEquals("Right upper motor total has impairment not due to SCI", expected.RightUpperMotorTotalHasImpairmentNotDueToSci, totals.RightUpperMotorTotalHasImpairmentNotDueToSci);
    Assert.assertEquals("Right upper motor total contains NT", expected.RightUpperMotorContainsNt, totals.RightUpperMotorContainsNt);
    Assert.assertEquals("Left upper motor total", expected.LeftUpperMotorTotal, totals.LeftUpperMotorTotal);
    Assert.assertEquals("Left upper motor total has impairment not due to SCI", expected.LeftUpperMotorTotalHasImpairmentNotDueToSci, totals.LeftUpperMotorTotalHasImpairmentNotDueToSci);
    Assert.assertEquals("Left upper motor total contains NT", expected.LeftUpperMotorContainsNt, totals.LeftUpperMotorContainsNt);
    Assert.assertEquals("Right lower motor total", expected.RightLowerMotorTotal, totals.RightLowerMotorTotal);
    Assert.assertEquals("Right lower motor total has impairment not due to SCI", expected.RightLowerMotorTotalHasImpairmentNotDueToSci, totals.RightLowerMotorTotalHasImpairmentNotDueToSci);
    Assert.assertEquals("Right lower motor total contains NT", expected.RightLowerMotorContainsNt, totals.RightLowerMotorContainsNt);
    Assert.assertEquals("Left lower motor total", expected.LeftLowerMotorTotal, totals.LeftLowerMotorTotal);
    Assert.assertEquals("Left lower motor total has impairment not due to SCI", expected.LeftLowerMotorTotalHasImpairmentNotDueToSci, totals.LeftLowerMotorTotalHasImpairmentNotDueToSci);
    Assert.assertEquals("Left lower motor total contains NT", expected.LeftLowerMotorContainsNt, totals.LeftLowerMotorContainsNt);
    Assert.assertEquals("Upper motor total", expected.UpperMotorTotal, totals.UpperMotorTotal);
    Assert.assertEquals("Lower motor total", expected.LowerMotorTotal, totals.LowerMotorTotal);


    Assert.assertEquals("Right Sensory", GetValueStringFrom(expected.GetRightSensoryValues()), GetValueStringFrom(totals.GetRightSensoryValues()));
    Assert.assertEquals("Left Sensory", GetValueStringFrom(expected.GetLeftSensoryValues()), GetValueStringFrom(totals.GetLeftSensoryValues()));
    Assert.assertEquals("Right Motor", GetValueStringFrom(expected.GetRightMotorValues()), GetValueStringFrom(totals.GetRightMotorValues()));
    Assert.assertEquals("Left Motor", GetValueStringFrom(expected.GetLeftMotorValues()), GetValueStringFrom(totals.GetLeftMotorValues()));
    Assert.assertEquals("Neurological Level of Injury", GetValueStringFrom(expected.GetNeurologicalLevelsOfInjury()), GetValueStringFrom(totals.GetNeurologicalLevelsOfInjury()));

    Assert.assertEquals("Asia impairment scale", expected.GetAsiaImpairmentScaleValues(), totals.GetAsiaImpairmentScaleValues());
    Assert.assertEquals("Right sensory ZPP", GetValueStringFrom(expected.GetRightSensoryZppValues()), GetValueStringFrom(totals.GetRightSensoryZppValues()));
    Assert.assertEquals("Left sensory ZPP", GetValueStringFrom(expected.GetLeftSensoryZppValues()), GetValueStringFrom(totals.GetLeftSensoryZppValues()));
    Assert.assertEquals("Right Motor ZPP", GetValueStringFrom(expected.GetRightMotorZppValues()), GetValueStringFrom(totals.GetRightMotorZppValues()));
    Assert.assertEquals("Left Motor ZPP", GetValueStringFrom(expected.GetLeftMotorZppValues()), GetValueStringFrom(totals.GetLeftMotorZppValues()));
  }

  @Test
  public void RunAllTests(){
    System.out.println("Running Neurology form totals tests for all xml.");
    try {
      ArrayList<File> files = new ArrayList<File>();

      listf("src/Tests/Resources/isncsciTestCases/", files);
      for(File file: files){
        System.out.println("Testing " + file);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        TestForm(NeurologyFormLoader.LoadNeurologyFormFrom(doc), NeurologyFormLoader.LoadNeurologyFormTotalsFrom(doc));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Neurology form totals tests for all xml are done.");
  }

  private static void listf(String directoryName, ArrayList<File> files) {
    File directory = new File(directoryName);

    // get all the xml files from a directory
    File[] fList = directory.listFiles();
    for (File file : fList) {
      if (file.isFile() && (file.getName().endsWith(".xml") || file.getName().endsWith(".XML"))) {
        files.add(file);
      } else if (file.isDirectory()) {
        listf(file.getAbsolutePath(), files);
      }
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
  @Test
  //Test summary for NtCase2
  public void CanGetTotalsSummaryForNtCase2(){
    System.out.println("Running Neurology form totals summary for ISNCSCI NT Case 2.xml");
    try {
      String filePath = "src/Tests/Resources/isncsciTestCases/NtCases/ISNCSCI NT Case 2.xml";
      File file = new File(filePath);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(file);
      doc.getDocumentElement().normalize();
      NeurologyFormTotalsSummary summary = Algorithm.GetTotalsSummaryFor(NeurologyFormLoader.LoadNeurologyFormFrom(doc));

      // Assert
      Assert.assertEquals("B,C,D", summary.AsiaImpairmentScale);
      Assert.assertEquals("I", summary.Completeness);
      Assert.assertEquals(NotDeterminable, summary.LeftLowerMotorTotal);
      Assert.assertEquals("C5", summary.LeftMotor);
      Assert.assertEquals(NotDeterminable, summary.LeftMotorTotal);
      Assert.assertEquals("NA", summary.LeftMotorZpp);
      Assert.assertEquals(NotDeterminable, summary.LeftPrickTotal);
      Assert.assertEquals("C5", summary.LeftSensory);
      Assert.assertEquals("NA", summary.LeftSensoryZpp);
      Assert.assertEquals(NotDeterminable, summary.LeftTouchTotal);
      Assert.assertEquals("6", summary.LeftUpperMotorTotal);
      Assert.assertEquals(NotDeterminable, summary.LowerMotorTotal);
      Assert.assertEquals("C5", summary.NeurologicalLevelOfInjury);
      Assert.assertEquals(NotDeterminable, summary.PrickTotal);
      Assert.assertEquals(NotDeterminable, summary.RightLowerMotorTotal);
      Assert.assertEquals("C5", summary.RightMotor);
      Assert.assertEquals(NotDeterminable, summary.RightMotorTotal);
      Assert.assertEquals("NA", summary.RightMotorZpp);
      Assert.assertEquals(NotDeterminable, summary.RightPrickTotal);
      Assert.assertEquals("C5", summary.RightSensory);
      Assert.assertEquals("NA", summary.RightSensoryZpp);
      Assert.assertEquals(NotDeterminable, summary.RightTouchTotal);
      Assert.assertEquals("6", summary.RightUpperMotorTotal);
      Assert.assertEquals(NotDeterminable, summary.TouchTotal);
      Assert.assertEquals("12", summary.UpperMotorTotal);
    }catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Neurology form totals summary for ISNCSCI NT Case 2.xml is done.");
  }
  @Test
  //Test summary for NtCase2
  public void CanGetTotalsSummaryForNtCase4(){
     System.out.println("Running Neurology form totals summary for ISNCSCI NT Case 4.xml");
    try {
      String filePath = "src/Tests/Resources/isncsciTestCases/NtCases/ISNCSCI NT Case 4.xml";
      File file = new File(filePath);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(file);
      doc.getDocumentElement().normalize();
      NeurologyFormTotalsSummary summary = Algorithm.GetTotalsSummaryFor(NeurologyFormLoader.LoadNeurologyFormFrom(doc));

      // Assert
      Assert.assertEquals("A,D", summary.AsiaImpairmentScale);
      Assert.assertEquals("C,I", summary.Completeness);
      Assert.assertEquals("16", summary.LeftLowerMotorTotal);
      Assert.assertEquals("C5", summary.LeftMotor);
      Assert.assertEquals("26", summary.LeftMotorTotal);
      Assert.assertEquals("NA,S1", summary.LeftMotorZpp);
      Assert.assertEquals(NotDeterminable, summary.LeftPrickTotal);
      Assert.assertEquals("C4", summary.LeftSensory);
      Assert.assertEquals("NA,S2-S3", summary.LeftSensoryZpp);
      Assert.assertEquals(NotDeterminable, summary.LeftTouchTotal);
      Assert.assertEquals("10", summary.LeftUpperMotorTotal);
      Assert.assertEquals("32", summary.LowerMotorTotal);
      Assert.assertEquals("C4", summary.NeurologicalLevelOfInjury);
      Assert.assertEquals(NotDeterminable, summary.PrickTotal);
      Assert.assertEquals("16", summary.RightLowerMotorTotal);
      Assert.assertEquals("C5", summary.RightMotor);
      Assert.assertEquals("26", summary.RightMotorTotal);
      Assert.assertEquals("NA,S1", summary.RightMotorZpp);
      Assert.assertEquals(NotDeterminable, summary.RightPrickTotal);
      Assert.assertEquals("C4", summary.RightSensory);
      Assert.assertEquals("NA,S2-S3", summary.RightSensoryZpp);
      Assert.assertEquals(NotDeterminable, summary.RightTouchTotal);
      Assert.assertEquals("10", summary.RightUpperMotorTotal);
      Assert.assertEquals(NotDeterminable, summary.TouchTotal);
      Assert.assertEquals("20", summary.UpperMotorTotal);
    }catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Neurology form totals summary for ISNCSCI NT Case 4.xml is done.");
  }

  @Test
  //Test click calculate btn and assert summary for NTCase4
  public void CalculateTestNTCase4(){
      System.out.println("Running Neurology calculate test for ISNCSCI NT Case 4.xml");
    try {
      String filePath = "src/Tests/Resources/isncsciTestCases/NtCases/ISNCSCI NT Case 4.xml";
      File file = new File(filePath);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(file);
      doc.getDocumentElement().normalize();

      NeurologyFormTotalsSummary summary = CalculateAlgorithmTests.doPostTestCalculate(NeurologyFormLoader.LoadJSONFrom(doc));


      // Assert
      Assert.assertEquals("A,D", summary.AsiaImpairmentScale);
      Assert.assertEquals("C,I", summary.Completeness);
      Assert.assertEquals("16", summary.LeftLowerMotorTotal);
      Assert.assertEquals("C5", summary.LeftMotor);
      Assert.assertEquals("26", summary.LeftMotorTotal);
      Assert.assertEquals("NA,S1", summary.LeftMotorZpp);
      Assert.assertEquals(NotDeterminable, summary.LeftPrickTotal);
      Assert.assertEquals("C4", summary.LeftSensory);
      Assert.assertEquals("NA,S2-S3", summary.LeftSensoryZpp);
      Assert.assertEquals(NotDeterminable, summary.LeftTouchTotal);
      Assert.assertEquals("10", summary.LeftUpperMotorTotal);
      Assert.assertEquals("32", summary.LowerMotorTotal);
      Assert.assertEquals("C4", summary.NeurologicalLevelOfInjury);
      Assert.assertEquals(NotDeterminable, summary.PrickTotal);
      Assert.assertEquals("16", summary.RightLowerMotorTotal);
      Assert.assertEquals("C5", summary.RightMotor);
      Assert.assertEquals("26", summary.RightMotorTotal);
      Assert.assertEquals("NA,S1", summary.RightMotorZpp);
      Assert.assertEquals(NotDeterminable, summary.RightPrickTotal);
      Assert.assertEquals("C4", summary.RightSensory);
      Assert.assertEquals("NA,S2-S3", summary.RightSensoryZpp);
      Assert.assertEquals(NotDeterminable, summary.RightTouchTotal);
      Assert.assertEquals("10", summary.RightUpperMotorTotal);
      Assert.assertEquals(NotDeterminable, summary.TouchTotal);
      Assert.assertEquals("20", summary.UpperMotorTotal);
    }catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Neurology calculate test for ISNCSCI NT Case 4.xml is done");
  }

  @Test
  // Test click save btn, save with or without modid
  public void SaveToDBTest(String modid, String dbName, String driverName, String serverName, String port, String userName, String password){
    if(modid.equals("")){
        System.out.println("Running update database test without modid for ISNCSCI NT Case 4.xml");
    }else{
      System.out.println("Running update database test with modid for ISNCSCI NT Case 4.xml");
    }
      try {
      String filePath = "src/Tests/Resources/isncsciTestCases/NtCases/ISNCSCI NT Case 4.xml";
      File file = new File(filePath);
      Random rand = new Random();
      String pid = String.valueOf(rand.nextInt(10000));
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(file);
      doc.getDocumentElement().normalize();
      JSONObject request = NeurologyFormLoader.LoadJSONFrom(doc);
      CalculateAlgorithmTests test = new CalculateAlgorithmTests();

      test.setUpContext(dbName, driverName, serverName, port, userName, password);
      test.doPostTestSave(request, modid, pid);

    }catch (Exception e) {
      e.printStackTrace();
    }
      System.out.println("Update database test for ISNCSCI NT Case 4.xml is done.");
  }
   public static void main(String [] args){
      String testDB = args[0];
      // set up required info before test
      String dbName = "spinal_test";
      String driverName = "com.mysql.jdbc.Driver";
      String serverName = "localhost";
      String port = "3306";
      String userName = "root";
      String password = "password";


      AlgorithmTests test = new AlgorithmTests();
      test.RunAllTests();
      System.out.println();
      test.CanGetTotalsSummaryForNtCase2();
      System.out.println();
      test.CanGetTotalsSummaryForNtCase4();
      System.out.println();
      test.CalculateTestNTCase4();
      System.out.println();
      System.out.println("Test database update: " + testDB);
      if(testDB.equals("YES")){
          //test saveSpinalAlgoResult without modid
          test.SaveToDBTest("", dbName, driverName, serverName, port, userName, password);
          System.out.println();
          //test saveSpinalAlgoResult with modid
          test.SaveToDBTest("123", dbName, driverName, serverName, port, userName, password);
      }
   }
}
