package Tests;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import Core.NeurologyForm;
import Core.Algorithm;
import Core.BinaryObservation;
import Core.NeurologyFormTotalsSummary;
import Core.NeurologyMusclePoints;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import org.json.JSONException;
import org.json.JSONObject;

/**
*
* @author Jason_-zZ
*/
public final class CalculateAlgorithmTests extends HttpServlet{
  // Right Motor
  String c5rm = "";
  String c6rm = "";
  String c7rm = "";
  String c8rm = "";
  String t1rm = "";

  String l2rm = "";
  String l3rm = "";
  String l4rm = "";
  String l5rm = "";
  String s1rm = "";

  // Right Touch
  String c2rt = "";
  String c3rt = "";
  String c4rt = "";
  String c5rt = "";
  String c6rt = "";
  String c7rt = "";
  String c8rt = "";

  String t1rt = "";
  String t2rt = "";
  String t3rt = "";
  String t4rt = "";
  String t5rt = "";
  String t6rt = "";
  String t7rt = "";
  String t8rt = "";
  String t9rt = "";
  String t10rt = "";
  String t11rt = "";
  String t12rt = "";

  String  l1rt = "";
  String l2rt = "";
  String l3rt = "";
  String l4rt = "";
  String l5rt = "";
  String s1rt = "";
  String s2rt = "";
  String s3rt = "";
  String s45rt = "";

  // Right Prick

  String c2rp = "";
  String c3rp = "";
  String c4rp = "";
  String c5rp = "";
  String c6rp = "";
  String c7rp = "";
  String c8rp = "";

  String t1rp = "";
  String t2rp = "";
  String t3rp = "";
  String t4rp = "";
  String t5rp = "";
  String t6rp = "";
  String t7rp = "";
  String t8rp = "";
  String t9rp = "";
  String t10rp = "";
  String t11rp = "";
  String t12rp  = "";

  String l1rp  = "";
  String l2rp  = "";
  String l3rp = "";
  String l4rp = "";
  String l5rp = "";
  String s1rp = "";
  String s2rp = "";
  String s3rp  = "";
  String s45rp  = "";


  // Left Motor
  String c5lm  = "";
  String c6lm  = "";
  String c7lm  = "";
  String c8lm  = "";
  String t1lm  = "";

  String l2lm  = "";
  String l3lm  = "";
  String l4lm  = "";
  String l5lm  = "";
  String s1lm  = "";

  // Left Touch
  String c2lt  = "";
  String c3lt  = "";
  String c4lt  = "";
  String c5lt  = "";
  String c6lt  = "";
  String c7lt  = "";
  String c8lt  = "";

  String t1lt  = "";
  String t2lt  = "";
  String t3lt  = "";
  String t4lt  = "";
  String t5lt  = "";
  String t6lt  = "";
  String t7lt  = "";
  String t8lt  = "";;
  String t9lt  = "";
  String t10lt  = "";
  String t11lt  = "";
  String t12lt  = "";

  String l1lt  = "";
  String l2lt  = "";
  String l3lt  = "";
  String l4lt  = "";
  String l5lt  = "";
  String s1lt  = "";
  String s2lt  = "";
  String s3lt  = "";
  String s45lt  = "";

  // Left Prick

  String c2lp  = "";
  String c3lp  = "";
  String c4lp  = "";
  String c5lp  = "";
  String c6lp  = "";
  String c7lp  = "";
  String c8lp  = "";

  String t1lp  = "";
  String t2lp  = "";
  String t3lp  = "";
  String t4lp  = "";
  String t5lp  = "";
  String t6lp  = "";;
  String t7lp  = "";
  String t8lp = "";
  String t9lp  = "";
  String t10lp  = "";
  String t11lp  = "";
  String t12lp  = "";

  String l1lp  = "";
  String l2lp  = "";
  String l3lp  = "";
  String l4lp  = "";
  String l5lp  = "";
  String s1lp  = "";
  String s2lp  = "";
  String s3lp  = "";
  String s45lp  = "";

  String vac = "";
  String dap = "";

  String rtrm = "";
  String rtrt = "";
  String rtrp = "";
  String ltlm = "";
  String ltlt = "";
  String ltlp = "";
  String uer = "";
  String uel = "";
  String uems = "";
  String ler = "";
  String lel = "";
  String lems = "";
  String lt = "";
  String pp = "";

  JSONObject context = new JSONObject();

  public void setUpContext(String dbName, String driverName, String serverName, String port, String userName, String password) throws JSONException{
    context.put("dbName", dbName);
    context.put("driverName", driverName);
    context.put("serverName", serverName);
    context.put("port", port);
    context.put("userName", userName);
    context.put("password", password);
  }

  public static NeurologyFormTotalsSummary doPostTestCalculate(JSONObject request) throws IOException, JSONException{
    NeurologyFormTotalsSummary summary = LoadSpinalAlgoFormResult(request);
    return summary;
  }

  public void doPostTestSave(JSONObject request, String modid, String pid) throws IOException, JSONException{
    NeurologyMusclePoints mPoints = setMusclePoints(request);
    try {
      if(modid.equals("")){
        saveSpinalAlgoResult(request, mPoints, pid);

      }else{
        saveSpinalAlgoResult(request, mPoints, pid, modid);
      }
    } catch (SQLException ex) {
          Logger.getLogger(CalculateAlgorithmTests.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  public void saveSpinalAlgoResult(JSONObject request, NeurologyMusclePoints  mPoints, String pid ) throws SQLException, JSONException, IOException
  {
    SpinalAlgoUpdateTests spUpdate = new SpinalAlgoUpdateTests();

    int update_result = spUpdate.saveSpinalAsiaAssessmentValues(context,mPoints,pid);

    //      response.setContentType("application/json");
    PrintWriter out = new PrintWriter("response.txt");

    JSONObject json = new JSONObject();
    json.put("update_result",  Integer.toString(update_result));
    out.print(json);

  }

  public void saveSpinalAlgoResult(JSONObject request, NeurologyMusclePoints  mPoints, String pid, String modid) throws SQLException, JSONException, IOException
  {
    SpinalAlgoUpdateTests spUpdate = new SpinalAlgoUpdateTests();

    int update_result = spUpdate.saveSpinalAsiaAssessmentValues(context,mPoints,pid,modid);

    PrintWriter out = new PrintWriter("response.txt");

    JSONObject json = new JSONObject();
    json.put("update_result",  Integer.toString(update_result));
    out.print(json);

  }

  public static NeurologyFormTotalsSummary LoadSpinalAlgoFormResult(JSONObject request) throws IOException, JSONException
  {
    NeurologyMusclePoints mPoints = setMusclePoints(request);
    NeurologyForm neurologyForm = new NeurologyForm();
    neurologyForm.setAnalContraction (mPoints.getVacBinary());
    neurologyForm.setAnalSensation (mPoints.getDapBinary());

    neurologyForm.UpdateLevelAt("C2", mPoints.getC2rt(), mPoints.getC2lt(), mPoints.getC2rp(), mPoints.getC2lp(), "0", "0");
    neurologyForm.UpdateLevelAt("C3", mPoints.getC3rt(), mPoints.getC3lt(), mPoints.getC3rp(), mPoints.getC3lp(), "0", "0");
    neurologyForm.UpdateLevelAt("C4", mPoints.getC4rt(), mPoints.getC4lt(), mPoints.getC4rp(), mPoints.getC4lp(), "0", "0");
    neurologyForm.UpdateLevelAt("C5", mPoints.getC5rt(), mPoints.getC5lt(), mPoints.getC5rp(), mPoints.getC5lp(), mPoints.getC5rm(), mPoints.getC5lm() );
    neurologyForm.UpdateLevelAt("C6", mPoints.getC6rt(), mPoints.getC6lt(), mPoints.getC6rp(), mPoints.getC6lp(), mPoints.getC6rm(), mPoints.getC6lm());
    neurologyForm.UpdateLevelAt("C7", mPoints.getC7rt(), mPoints.getC7lt(), mPoints.getC7rp(), mPoints.getC7lp(), mPoints.getC7rm(), mPoints.getC7lm());
    neurologyForm.UpdateLevelAt("C8", mPoints.getC8rt(), mPoints.getC8lt(), mPoints.getC8rp(), mPoints.getC8lp(), mPoints.getC8rm(), mPoints.getC8lm());
    neurologyForm.UpdateLevelAt("T1", mPoints.getT1rt(), mPoints.getT1lt(), mPoints.getT1rp(), mPoints.getT1lp(), mPoints.getT1rm(), mPoints.getT1lm());
    neurologyForm.UpdateLevelAt("T2", mPoints.getT2rt(), mPoints.getT2lt(), mPoints.getT2rp(), mPoints.getT2lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("T3", mPoints.getT3rt(), mPoints.getT3lt(), mPoints.getT3rp(), mPoints.getT3lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("T4", mPoints.getT4rt(), mPoints.getT4lt(), mPoints.getT4rp(), mPoints.getT4lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("T5", mPoints.getT5rt(), mPoints.getT5lt(), mPoints.getT5rp(), mPoints.getT5lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("T6", mPoints.getT6rt(), mPoints.getT6lt(), mPoints.getT6rp(), mPoints.getT6lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("T7", mPoints.getT7rt(), mPoints.getT7lt(), mPoints.getT7rp(), mPoints.getT7lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("T8", mPoints.getT8rt(), mPoints.getT8lt(), mPoints.getT8rp(), mPoints.getT8lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("T9", mPoints.getT9rt(), mPoints.getT9lt(), mPoints.getT9rp(), mPoints.getT9lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("T10", mPoints.getT10rt(), mPoints.getT10lt(), mPoints.getT10rp(), mPoints.getT10lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("T11", mPoints.getT11rt(), mPoints.getT11lt(), mPoints.getT11rp(), mPoints.getT11lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("T12", mPoints.getT12rt(), mPoints.getT12lt(), mPoints.getT12rp(), mPoints.getT12lp(),  "0", "0");
    neurologyForm.UpdateLevelAt("L1", mPoints.getL1rt(), mPoints.getL1lt(), mPoints.getL1rp(), mPoints.getL1lp(), "0", "0");
    neurologyForm.UpdateLevelAt("L2", mPoints.getL2rt(), mPoints.getL2lt(), mPoints.getL2rp(), mPoints.getL2lp(), mPoints.getL2rm(), mPoints.getL2lm());
    neurologyForm.UpdateLevelAt("L3", mPoints.getL3rt(), mPoints.getL3lt(), mPoints.getL3rp(), mPoints.getL3lp(), mPoints.getL3rm(), mPoints.getL3lm());
    neurologyForm.UpdateLevelAt("L4", mPoints.getL4rt(), mPoints.getL4lt(), mPoints.getL4rp(), mPoints.getL4lp(), mPoints.getL4rm(), mPoints.getL4lm());
    neurologyForm.UpdateLevelAt("L5", mPoints.getL5rt(), mPoints.getL5lt(), mPoints.getL5rp(), mPoints.getL5lp(), mPoints.getL5rm(), mPoints.getL5lm());
    neurologyForm.UpdateLevelAt("S1", mPoints.getS1rt(), mPoints.getS1lt(), mPoints.getS1rp(), mPoints.getS1lp(), mPoints.getS1rm(), mPoints.getS1lm());
    neurologyForm.UpdateLevelAt("S2", mPoints.getS2rt(), mPoints.getS2lt(), mPoints.getS2rp(), mPoints.getS2lp(), "0", "0");
    neurologyForm.UpdateLevelAt("S3", mPoints.getS3rt(), mPoints.getS3lt(), mPoints.getS3rp(), mPoints.getS3lp(), "0", "0");
    neurologyForm.UpdateLevelAt("S4_5", mPoints.getS45rt(), mPoints.getS45lt(), mPoints.getS45rp(), mPoints.getS45lp(), "0", "0");


    NeurologyFormTotalsSummary nTotalsSummary = new NeurologyFormTotalsSummary();
    nTotalsSummary = Algorithm.GetTotalsSummaryFor(neurologyForm);

    return nTotalsSummary;
  }

  public void setMusclePointValues(JSONObject request) throws JSONException
  {
    c5rm = request.get("c5rm").toString();
    c6rm = request.get("c6rm").toString();
    c7rm = request.get("c7rm").toString();
    c8rm = request.get("c8rm").toString();
    t1rm = request.get("t1rm").toString();

    l2rm = request.get("l2rm").toString();
    l3rm = request.get("l3rm").toString();
    l4rm = request.get("l4rm").toString();
    l5rm = request.get("l5rm").toString();
    s1rm = request.get("s1rm").toString();

    // Right Touch
    c2rt = request.get("c2rt").toString();
    c3rt = request.get("c3rt").toString();
    c4rt = request.get("c4rt").toString();
    c5rt = request.get("c5rt").toString();
    c6rt = request.get("c6rt").toString();
    c7rt = request.get("c7rt").toString();
    c8rt = request.get("c8rt").toString();

    t1rt = request.get("t1rt").toString();
    t2rt = request.get("t2rt").toString();
    t3rt = request.get("t3rt").toString();
    t4rt = request.get("t4rt").toString();
    t5rt = request.get("t5rt").toString();
    t6rt = request.get("t6rt").toString();
    t7rt = request.get("t7rt").toString();
    t8rt = request.get("t8rt").toString();
    t9rt = request.get("t9rt").toString();
    t10rt = request.get("t10rt").toString();
    t11rt = request.get("t11rt").toString();
    t12rt = request.get("t12rt").toString();

    l1rt = request.get("l1rt").toString();
    l2rt = request.get("l2rt").toString();
    l3rt = request.get("l3rt").toString();
    l4rt = request.get("l4rt").toString();
    l5rt = request.get("l5rt").toString();
    s1rt = request.get("s1rt").toString();
    s2rt = request.get("s2rt").toString();
    s3rt = request.get("s3rt").toString();
    s45rt = request.get("s45rt").toString();

    // Right Prick

    c2rp = request.get("c2rp").toString();
    c3rp = request.get("c3rp").toString();
    c4rp = request.get("c4rp").toString();
    c5rp = request.get("c5rp").toString();
    c6rp = request.get("c6rp").toString();
    c7rp = request.get("c7rp").toString();
    c8rp = request.get("c8rp").toString();

    t1rp = request.get("t1rp").toString();
    t2rp = request.get("t2rp").toString();
    t3rp = request.get("t3rp").toString();
    t4rp = request.get("t4rp").toString();
    t5rp = request.get("t5rp").toString();
    t6rp = request.get("t6rp").toString();
    t7rp = request.get("t7rp").toString();
    t8rp = request.get("t8rp").toString();
    t9rp = request.get("t9rp").toString();
    t10rp = request.get("t10rp").toString();
    t11rp = request.get("t11rp").toString();
    t12rp  = request.get("t12rp").toString();

    l1rp  = request.get("l1rp").toString();
    l2rp  = request.get("l2rp").toString();
    l3rp = request.get("l3rp").toString();
    l4rp = request.get("l4rp").toString();
    l5rp = request.get("l5rp").toString();
    s1rp = request.get("s1rp").toString();
    s2rp = request.get("s2rp").toString();
    s3rp  = request.get("s3rp").toString();
    s45rp  = request.get("s45rp").toString();


    // Left Motor
    c5lm  = request.get("c5lm").toString();
    c6lm  = request.get("c6lm").toString();
    c7lm  = request.get("c7lm").toString();
    c8lm  = request.get("c8lm").toString();
    t1lm  = request.get("t1lm").toString();

    l2lm  = request.get("l2lm").toString();
    l3lm  = request.get("l3lm").toString();
    l4lm  = request.get("l4lm").toString();
    l5lm  = request.get("l5lm").toString();
    s1lm  = request.get("s1lm").toString();

    // Left Touch
    c2lt  = request.get("c2lt").toString();
    c3lt  = request.get("c3lt").toString();
    c4lt  = request.get("c4lt").toString();
    c5lt  = request.get("c5lt").toString();
    c6lt  = request.get("c6lt").toString();
    c7lt  = request.get("c7lt").toString();
    c8lt  = request.get("c8lt").toString();

    t1lt  = request.get("t1lt").toString();
    t2lt  = request.get("t2lt").toString();
    t3lt  = request.get("t3lt").toString();
    t4lt  = request.get("t4lt").toString();
    t5lt  = request.get("t5lt").toString();
    t6lt  = request.get("t6lt").toString();
    t7lt  = request.get("t7lt").toString();
    t8lt  = request.get("t8lt").toString();
    t9lt  = request.get("t9lt").toString();
    t10lt  = request.get("t10lt").toString();
    t11lt  = request.get("t11lt").toString();
    t12lt  = request.get("t12lt").toString();

    l1lt  = request.get("l1lt").toString();
    l2lt  = request.get("l2lt").toString();
    l3lt  = request.get("l3lt").toString();
    l4lt  = request.get("l4lt").toString();
    l5lt  = request.get("l5lt").toString();
    s1lt  = request.get("s1lt").toString();
    s2lt  = request.get("s2lt").toString();
    s3lt  = request.get("s3lt").toString();
    s45lt  = request.get("s45lt").toString();

    // Left Prick

    c2lp  = request.get("c2lp").toString();
    c3lp  = request.get("c3lp").toString();
    c4lp  = request.get("c4lp").toString();
    c5lp  = request.get("c5lp").toString();
    c6lp  = request.get("c6lp").toString();
    c7lp  = request.get("c7lp").toString();
    c8lp  = request.get("c8lp").toString();

    t1lp  = request.get("t1lp").toString();
    t2lp  = request.get("t2lp").toString();
    t3lp  = request.get("t3lp").toString();
    t4lp  = request.get("t4lp").toString();
    t5lp  = request.get("t5lp").toString();
    t6lp  = request.get("t6lp").toString();
    t7lp  = request.get("t7lp").toString();
    t8lp = request.get("t8lp").toString();
    t9lp  = request.get("t9lp").toString();
    t10lp  = request.get("t10lp").toString();
    t11lp  = request.get("t11lp").toString();
    t12lp  = request.get("t12lp").toString();

    l1lp  = request.get("l1lp").toString();
    l2lp  = request.get("l2lp").toString();
    l3lp  = request.get("l3lp").toString();
    l4lp  = request.get("l4lp").toString();
    l5lp  = request.get("l5lp").toString();
    s1lp  = request.get("s1lp").toString();
    s2lp  = request.get("s2lp").toString();
    s3lp  = request.get("s3lp").toString();
    s45lp  = request.get("s45lp").toString();

    vac = request.get("vac").toString();
    dap = request.get("dap").toString();

    rtrm = request.get("rtrm").toString();
    rtrt = request.get("rtrt").toString();
    rtrp = request.get("rtrp").toString();
    ltlm = request.get("ltlm").toString();
    ltlt = request.get("ltlt").toString();
    ltlp = request.get("ltlp").toString();
    uer = request.get("uer").toString();
    uel = request.get("uel").toString();
    uems = request.get("uems").toString();
    ler = request.get("ler").toString();
    lel = request.get("lel").toString();
    lems = request.get("lems").toString();
    lt = request.get("lt").toString();
    pp = request.get("pp").toString();
  }


  public static NeurologyMusclePoints setMusclePoints(JSONObject request) throws JSONException
  {
    NeurologyMusclePoints musPoints = new NeurologyMusclePoints();
    musPoints.setC5rm ( request.get("c5rm").toString());
    musPoints.setC6rm ( request.get("c6rm").toString());
    musPoints.setC7rm ( request.get("c7rm").toString());
    musPoints.setC8rm ( request.get("c8rm").toString());
    musPoints.setT1rm ( request.get("t1rm").toString());

    musPoints.setL2rm ( request.get("l2rm").toString());
    musPoints.setL3rm ( request.get("l3rm").toString());
    musPoints.setL4rm ( request.get("l4rm").toString());
    musPoints.setL5rm ( request.get("l5rm").toString());
    musPoints.setS1rm ( request.get("s1rm").toString());

    // Right Touch
    musPoints.setC2rt ( request.get("c2rt").toString());
    musPoints.setC3rt ( request.get("c3rt").toString());
    musPoints.setC4rt ( request.get("c4rt").toString());
    musPoints.setC5rt ( request.get("c5rt").toString());
    musPoints.setC6rt ( request.get("c6rt").toString());
    musPoints.setC7rt ( request.get("c7rt").toString());
    musPoints.setC8rt ( request.get("c8rt").toString());

    musPoints.setT1rt ( request.get("t1rt").toString());
    musPoints.setT2rt ( request.get("t2rt").toString());
    musPoints.setT3rt ( request.get("t3rt").toString());
    musPoints.setT4rt ( request.get("t4rt").toString());
    musPoints.setT5rt ( request.get("t5rt").toString());
    musPoints.setT6rt ( request.get("t6rt").toString());
    musPoints.setT7rt ( request.get("t7rt").toString());
    musPoints.setT8rt ( request.get("t8rt").toString());
    musPoints.setT9rt ( request.get("t9rt").toString());
    musPoints.setT10rt ( request.get("t10rt").toString());
    musPoints.setT11rt ( request.get("t11rt").toString());
    musPoints.setT12rt ( request.get("t12rt").toString());

    musPoints.setL1rt ( request.get("l1rt").toString());
    musPoints.setL2rt ( request.get("l2rt").toString());
    musPoints.setL3rt ( request.get("l3rt").toString());
    musPoints.setL4rt ( request.get("l4rt").toString());
    musPoints.setL5rt ( request.get("l5rt").toString());
    musPoints.setS1rt ( request.get("s1rt").toString());
    musPoints.setS2rt ( request.get("s2rt").toString());
    musPoints.setS3rt ( request.get("s3rt").toString());
    musPoints.setS45rt ( request.get("s45rt").toString());

    // Right Prick

    musPoints.setC2rp ( request.get("c2rp").toString());
    musPoints.setC3rp ( request.get("c3rp").toString());
    musPoints.setC4rp ( request.get("c4rp").toString());
    musPoints.setC5rp ( request.get("c5rp").toString());
    musPoints.setC6rp ( request.get("c6rp").toString());
    musPoints.setC7rp ( request.get("c7rp").toString());
    musPoints.setC8rp ( request.get("c8rp").toString());

    musPoints.setT1rp ( request.get("t1rp").toString());
    musPoints.setT2rp ( request.get("t2rp").toString());
    musPoints.setT3rp ( request.get("t3rp").toString());
    musPoints.setT4rp ( request.get("t4rp").toString());
    musPoints.setT5rp ( request.get("t5rp").toString());
    musPoints.setT6rp ( request.get("t6rp").toString());
    musPoints.setT7rp ( request.get("t7rp").toString());
    musPoints.setT8rp ( request.get("t8rp").toString());
    musPoints.setT9rp ( request.get("t9rp").toString());
    musPoints.setT10rp ( request.get("t10rp").toString());
    musPoints.setT11rp ( request.get("t11rp").toString());
    musPoints.setT12rp  ( request.get("t12rp").toString());

    musPoints.setL1rp  ( request.get("l1rp").toString());
    musPoints.setL2rp  ( request.get("l2rp").toString());
    musPoints.setL3rp ( request.get("l3rp").toString());
    musPoints.setL4rp ( request.get("l4rp").toString());
    musPoints.setL5rp ( request.get("l5rp").toString());
    musPoints.setS1rp ( request.get("s1rp").toString());
    musPoints.setS2rp ( request.get("s2rp").toString());
    musPoints.setS3rp  ( request.get("s3rp").toString());
    musPoints.setS45rp  ( request.get("s45rp").toString());


    // Left Motor
    musPoints.setC5lm  ( request.get("c5lm").toString());
    musPoints.setC6lm  ( request.get("c6lm").toString());
    musPoints.setC7lm  ( request.get("c7lm").toString());
    musPoints.setC8lm  ( request.get("c8lm").toString());
    musPoints.setT1lm  ( request.get("t1lm").toString());

    musPoints.setL2lm  ( request.get("l2lm").toString());
    musPoints.setL3lm  ( request.get("l3lm").toString());
    musPoints.setL4lm  ( request.get("l4lm").toString());
    musPoints.setL5lm  ( request.get("l5lm").toString());
    musPoints.setS1lm  ( request.get("s1lm").toString());

    // Left Touch
    musPoints.setC2lt  ( request.get("c2lt").toString());
    musPoints.setC3lt  ( request.get("c3lt").toString());
    musPoints.setC4lt  ( request.get("c4lt").toString());
    musPoints.setC5lt  ( request.get("c5lt").toString());
    musPoints.setC6lt  ( request.get("c6lt").toString());
    musPoints.setC7lt  ( request.get("c7lt").toString());
    musPoints.setC8lt  ( request.get("c8lt").toString());

    musPoints.setT1lt  ( request.get("t1lt").toString());
    musPoints.setT2lt  ( request.get("t2lt").toString());
    musPoints.setT3lt  ( request.get("t3lt").toString());
    musPoints.setT4lt  ( request.get("t4lt").toString());
    musPoints.setT5lt  ( request.get("t5lt").toString());
    musPoints.setT6lt  ( request.get("t6lt").toString());
    musPoints.setT7lt  ( request.get("t7lt").toString());
    musPoints.setT8lt  ( request.get("t8lt").toString());
    musPoints.setT9lt  ( request.get("t9lt").toString());
    musPoints.setT10lt  ( request.get("t10lt").toString());
    musPoints.setT11lt  ( request.get("t11lt").toString());
    musPoints.setT12lt  ( request.get("t12lt").toString());

    musPoints.setL1lt  ( request.get("l1lt").toString());
    musPoints.setL2lt  ( request.get("l2lt").toString());
    musPoints.setL3lt  ( request.get("l3lt").toString());
    musPoints.setL4lt  ( request.get("l4lt").toString());
    musPoints.setL5lt  ( request.get("l5lt").toString());
    musPoints.setS1lt  ( request.get("s1lt").toString());
    musPoints.setS2lt  ( request.get("s2lt").toString());
    musPoints.setS3lt  ( request.get("s3lt").toString());
    musPoints.setS45lt  ( request.get("s45lt").toString());

    // Left Prick

    musPoints.setC2lp  ( request.get("c2lp").toString());
    musPoints.setC3lp  ( request.get("c3lp").toString());
    musPoints.setC4lp  ( request.get("c4lp").toString());
    musPoints.setC5lp  ( request.get("c5lp").toString());
    musPoints.setC6lp  ( request.get("c6lp").toString());
    musPoints.setC7lp  ( request.get("c7lp").toString());
    musPoints.setC8lp  ( request.get("c8lp").toString());

    musPoints.setT1lp  ( request.get("t1lp").toString());
    musPoints.setT2lp  ( request.get("t2lp").toString());
    musPoints.setT3lp  ( request.get("t3lp").toString());
    musPoints.setT4lp  ( request.get("t4lp").toString());
    musPoints.setT5lp  ( request.get("t5lp").toString());
    musPoints.setT6lp  ( request.get("t6lp").toString());
    musPoints.setT7lp  ( request.get("t7lp").toString());
    musPoints.setT8lp ( request.get("t8lp").toString());
    musPoints.setT9lp  ( request.get("t9lp").toString());
    musPoints.setT10lp  ( request.get("t10lp").toString());
    musPoints.setT11lp  ( request.get("t11lp").toString());
    musPoints.setT12lp  ( request.get("t12lp").toString());

    musPoints.setL1lp  ( request.get("l1lp").toString());
    musPoints.setL2lp  ( request.get("l2lp").toString());
    musPoints.setL3lp  ( request.get("l3lp").toString());
    musPoints.setL4lp  ( request.get("l4lp").toString());
    musPoints.setL5lp  ( request.get("l5lp").toString());
    musPoints.setS1lp  ( request.get("s1lp").toString());
    musPoints.setS2lp  ( request.get("s2lp").toString());
    musPoints.setS3lp  ( request.get("s3lp").toString());
    musPoints.setS45lp  ( request.get("s45lp").toString());

    if(request.get("vac").equals("Y"))
    {
      musPoints.setVacBinary(BinaryObservation.Yes);
    }
    else if (request.get("vac").equals("N"))
    {
      musPoints.setVacBinary ( BinaryObservation.No);
    }
    else if (request.get("vac").equals("NT"))
    {
      musPoints.setVacBinary ( BinaryObservation.NT);
    }
    else
    {
      musPoints.setVacBinary ( BinaryObservation.None);
    }

    if(request.get("dap").equals("Y"))
    {
      musPoints.setDapBinary(BinaryObservation.Yes);
    }
    else if (request.get("dap").equals("N"))
    {
      musPoints.setDapBinary ( BinaryObservation.No);
    }
    else if (request.get("dap").equals("NT"))
    {
      musPoints.setDapBinary ( BinaryObservation.NT);
    }
    else
    {
      musPoints.setDapBinary ( BinaryObservation.None);
    }

    musPoints.setVac(request.get("vac").toString());
    musPoints.setDap(request.get("dap").toString());

    musPoints.setRightSensory(request.get("sensoryRight").toString());
    musPoints.setLeftSensory(request.get("sensoryLeft").toString());
    musPoints.setRightMotor(request.get("motorRight").toString());
    musPoints.setLeftMotor(request.get("motorLeft").toString());
    musPoints.setAsiaComplete(request.get("comp").toString());
    musPoints.setNeurologicalLevelInjury(request.get("nli").toString());
    musPoints.setAsiaImparimentScale(request.get("ais").toString());
     musPoints.setRightSensoryPatial(request.get("sensoryRightPatial").toString());
     musPoints.setLeftSensoryPatial(request.get("sensoryLeftPatial").toString());
     musPoints.setRightMotorPatial(request.get("motorRightPatial").toString());
     musPoints.setLeftMotorPatial(request.get("motorLeftPatial").toString());
    // musPoints.setComment("");
    musPoints.setSingleNeurologicalLevel("");

    musPoints.setRightMotorTotal(request.get("rtrm").toString());
    musPoints.setRightTouchTotal(request.get("rtrt").toString());
    musPoints.setRightPrickTotal(request.get("rtrp").toString());
    musPoints.setLeftMotorTotal(request.get("ltlm").toString());
    musPoints.setLeftTouchTotal(request.get("ltlt").toString());
    musPoints.setLeftPrickTotal(request.get("ltlp").toString());
    musPoints.setRightUpperMotorTotal(request.get("uer").toString());
    musPoints.setLeftUpperMotorTotal(request.get("uel").toString());
    musPoints.setUpperMotorTotal(request.get("uems").toString());
    musPoints.setRightLowerMotorTotal(request.get("ler").toString());
    musPoints.setLeftLowerMotorTotal(request.get("lel").toString());
    musPoints.setLowerMotorTotal(request.get("lems").toString());
    musPoints.setTouchTotal(request.get("lt").toString());
    musPoints.setPrickTotal(request.get("pp").toString());

    musPoints.setComment(request.get("comment").toString());
    // musPoints.setAssessmentDate(request.get("assessment_date").toString());
    // musPoints.setAssessmentTimepoint(request.get("assessment_timepoint").toString());

    return musPoints;
  }

}
