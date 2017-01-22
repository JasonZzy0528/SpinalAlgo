package Core;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
//import javax.websocket.Session;
import org.json.JSONException;
import org.json.JSONObject;  
import update_main.SpinalAlgoUpdate;

/**
 *
 * @author purat
 */
//@WebServlet("/CalculateAlgorithm")
public class CalculateAlgorithm extends HttpServlet {
             
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
            
    
@Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
 {
        Algorithm algo = new Algorithm();
        String action = request.getParameter("action");
        String modality = request.getParameter("modality");
        String pid = request.getParameter("pid");
        String modid = request.getParameter("modid");
        if(modid == null){
            modid = "";
        }
        
        //System.out.println("Into CalculateAlgorithm doPost...");
        //System.out.println("modid: " + modid);
        
        NeurologyFormTotals neuTotals = new NeurologyFormTotals();
        ServletContext context = request.getServletContext();
       
        if (request.getParameter("btnCalculate") != null) {
            
            try {
                LoadSpinalAlgoFormResult(request, response);
                //RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                //dispatcher.forward(request, response);
            } catch (JSONException ex) {
                Logger.getLogger(CalculateAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         if (request.getParameter("btnSave") != null) {
             NeurologyMusclePoints mPoints = setMusclePoints(request);
             //System.out.println("setMusclePoints complete (in doPost of CalculateAlgorithm)...");
            try {
                if(modid.equals("")){
                    saveSpinalAlgoResult(request, response, mPoints, pid);
                }else{
                    saveSpinalAlgoResult(request, response, mPoints, pid, modid);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CalculateAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(CalculateAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String url = "./jsp/spinalalgo.jsp?modality=" + modality + "&action=" + action + "&pid=" + pid;
        // response.sendRedirect(url);

 }
 
 public void saveSpinalAlgoResult(HttpServletRequest request, HttpServletResponse response, NeurologyMusclePoints  mPoints, String pid ) throws SQLException, JSONException, IOException
 {
     SpinalAlgoUpdate spUpdate = new SpinalAlgoUpdate();
     int update_result = spUpdate.saveSpinalAsiaAssessmentValues(request.getServletContext(),mPoints,pid);
     
     response.setContentType("application/json");
     PrintWriter out = response.getWriter();
            
     JSONObject json = new JSONObject();
     json.put("update_result",  Integer.toString(update_result));
     out.print(json);

 }
 
 public void saveSpinalAlgoResult(HttpServletRequest request, HttpServletResponse response, NeurologyMusclePoints  mPoints, String pid, String modid) throws SQLException, JSONException, IOException
 {
     SpinalAlgoUpdate spUpdate = new SpinalAlgoUpdate();
     int update_result = spUpdate.saveSpinalAsiaAssessmentValues(request.getServletContext(),mPoints,pid,modid);
     
     response.setContentType("application/json");
     PrintWriter out = response.getWriter();
            
     JSONObject json = new JSONObject();
     json.put("update_result",  Integer.toString(update_result));
     out.print(json);

 }
 
 public void LoadSpinalAlgoFormResult(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException
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
           
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            
            JSONObject json = new JSONObject();
            json.put("rtrm_total", nTotalsSummary.RightMotorTotal);
            json.put("rtrt_total", nTotalsSummary.RightTouchTotal);
            json.put("rtrp_total", nTotalsSummary.RightPrickTotal);

            json.put("ltlm_total", nTotalsSummary.LeftMotorTotal);
            json.put("ltlt_total", nTotalsSummary.LeftTouchTotal);
            json.put("ltlp_total", nTotalsSummary.LeftPrickTotal);

            json.put("uer", nTotalsSummary.RightUpperMotorTotal);
            json.put("uel", nTotalsSummary.LeftUpperMotorTotal);
            json.put("uems", nTotalsSummary.UpperMotorTotal); 

            json.put("ler", nTotalsSummary.RightLowerMotorTotal);
            json.put("lel", nTotalsSummary.LeftLowerMotorTotal);
            json.put("lems", nTotalsSummary.LowerMotorTotal); 

            json.put("ltr", nTotalsSummary.RightTouchTotal);
            json.put("ltl", nTotalsSummary.LeftTouchTotal);
            json.put("lt", nTotalsSummary.TouchTotal );

            json.put("ppr", nTotalsSummary.RightPrickTotal);
            json.put("ppl", nTotalsSummary.LeftPrickTotal);
            json.put("pp", nTotalsSummary.PrickTotal);

            json.put("sensoryRight", nTotalsSummary.RightSensory);
            json.put("sensoryLeft", nTotalsSummary.LeftSensory);
            json.put("motorRight", nTotalsSummary.RightMotor);
            json.put("motorLeft", nTotalsSummary.LeftMotor);
            json.put("nli", nTotalsSummary.NeurologicalLevelOfInjury);
            json.put("comp", nTotalsSummary.Completeness);
            json.put("ais", nTotalsSummary.AsiaImpairmentScale);
            json.put("sensoryRightPatial", nTotalsSummary.RightSensoryZpp);
            json.put("sensoryLeftPatial", nTotalsSummary.LeftSensoryZpp);
            json.put("motorRightPatial", nTotalsSummary.RightMotorZpp);
            json.put("motorLeftPatial", nTotalsSummary.LeftMotorZpp);
           
            out.print(json);
 }
 
 @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String action = request.getParameter("action");
      String modality = request.getParameter("modality");
      String pid = request.getParameter("pid");
      String modid = request.getParameter("modid");
      
      if (modid != null || !modid.equals("0") || !modid.equals(""))
      {
        NeurologyMusclePoints musPoints = new NeurologyMusclePoints();
        SpinalAlgoUpdate spUpdate = new SpinalAlgoUpdate();
        musPoints = spUpdate.getSpinalAsiaAssessmentValuesByModId(request.getServletContext(),pid, modid);

        request.setAttribute("musPoints", musPoints);

        String url = "./jsp/spinalalgo.jsp?modality=" + modality + "&action=" + action + "&pid=" + pid + "&modid=" + modid;

        request.getRequestDispatcher(url).forward(request,response);
      }
      
  }
  
  public void setMusclePointValues(HttpServletRequest request)
  {
              c5rm = request.getParameter("c5rm");
              c6rm = request.getParameter("c6rm");
              c7rm = request.getParameter("c7rm");
              c8rm = request.getParameter("c8rm");
              t1rm = request.getParameter("t1rm");
            
              l2rm = request.getParameter("l2rm");
              l3rm = request.getParameter("l3rm");
              l4rm = request.getParameter("l4rm");
              l5rm = request.getParameter("l5rm");
              s1rm = request.getParameter("s1rm");

            // Right Touch 
              c2rt = request.getParameter("c2rt");
              c3rt = request.getParameter("c3rt");
              c4rt = request.getParameter("c4rt");
              c5rt = request.getParameter("c5rt");
              c6rt = request.getParameter("c6rt");
              c7rt = request.getParameter("c7rt");
              c8rt = request.getParameter("c8rt");

              t1rt = request.getParameter("t1rt");
              t2rt = request.getParameter("t2rt");
              t3rt = request.getParameter("t3rt");
              t4rt = request.getParameter("t4rt");
              t5rt = request.getParameter("t5rt");
              t6rt = request.getParameter("t6rt");
              t7rt = request.getParameter("t7rt");
              t8rt = request.getParameter("t8rt");
              t9rt = request.getParameter("t9rt");
              t10rt = request.getParameter("t10rt");
              t11rt = request.getParameter("t11rt");
              t12rt = request.getParameter("t12rt");
            
              l1rt = request.getParameter("l1rt");
              l2rt = request.getParameter("l2rt");
              l3rt = request.getParameter("l3rt");
              l4rt = request.getParameter("l4rt");
              l5rt = request.getParameter("l5rt");
              s1rt = request.getParameter("s1rt");
              s2rt = request.getParameter("s2rt");
              s3rt = request.getParameter("s3rt");
              s45rt = request.getParameter("s45rt");

            // Right Prick
            
              c2rp = request.getParameter("c2rp");
              c3rp = request.getParameter("c3rp");
              c4rp = request.getParameter("c4rp");
              c5rp = request.getParameter("c5rp");
              c6rp = request.getParameter("c6rp");
              c7rp = request.getParameter("c7rp");
              c8rp = request.getParameter("c8rp");

              t1rp = request.getParameter("t1rp");
              t2rp = request.getParameter("t2rp");
              t3rp = request.getParameter("t3rp");
              t4rp = request.getParameter("t4rp");
              t5rp = request.getParameter("t5rp");
              t6rp = request.getParameter("t6rp");
              t7rp = request.getParameter("t7rp");
              t8rp = request.getParameter("t8rp");
              t9rp = request.getParameter("t9rp");
              t10rp = request.getParameter("t10rp");
              t11rp = request.getParameter("t11rp");
              t12rp  = request.getParameter("t12rp");
           
              l1rp  = request.getParameter("l1rp");
              l2rp  = request.getParameter("l2rp");
              l3rp = request.getParameter("l3rp");
              l4rp = request.getParameter("l4rp");
              l5rp = request.getParameter("l5rp");
              s1rp = request.getParameter("s1rp");
              s2rp = request.getParameter("s2rp");
              s3rp  = request.getParameter("s3rp");
              s45rp  = request.getParameter("s45rp");
            
            
            // Left Motor 
              c5lm  = request.getParameter("c5lm");
              c6lm  = request.getParameter("c6lm");
              c7lm  = request.getParameter("c7lm");
              c8lm  = request.getParameter("c8lm");
              t1lm  = request.getParameter("t1lm");
            
              l2lm  = request.getParameter("l2lm");
              l3lm  = request.getParameter("l3lm");
              l4lm  = request.getParameter("l4lm");
              l5lm  = request.getParameter("l5lm");
              s1lm  = request.getParameter("s1lm");

            // Left Touch 
              c2lt  = request.getParameter("c2lt");
              c3lt  = request.getParameter("c3lt");
              c4lt  = request.getParameter("c4lt");
              c5lt  = request.getParameter("c5lt");
              c6lt  = request.getParameter("c6lt");
              c7lt  = request.getParameter("c7lt");
              c8lt  = request.getParameter("c8lt");

              t1lt  = request.getParameter("t1lt");
              t2lt  = request.getParameter("t2lt");
              t3lt  = request.getParameter("t3lt");
              t4lt  = request.getParameter("t4lt");
              t5lt  = request.getParameter("t5lt");
              t6lt  = request.getParameter("t6lt");
              t7lt  = request.getParameter("t7lt");
              t8lt  = request.getParameter("t8lt");
              t9lt  = request.getParameter("t9lt");
              t10lt  = request.getParameter("t10lt");
              t11lt  = request.getParameter("t11lt");
              t12lt  = request.getParameter("t12lt");
            
              l1lt  = request.getParameter("l1lt");
              l2lt  = request.getParameter("l2lt");
              l3lt  = request.getParameter("l3lt");
              l4lt  = request.getParameter("l4lt");
              l5lt  = request.getParameter("l5lt");
              s1lt  = request.getParameter("s1lt");
              s2lt  = request.getParameter("s2lt");
              s3lt  = request.getParameter("s3lt");
              s45lt  = request.getParameter("s45lt");

            // Left Prick
            
              c2lp  = request.getParameter("c2lp");
              c3lp  = request.getParameter("c3lp");
              c4lp  = request.getParameter("c4lp");
              c5lp  = request.getParameter("c5lp");
              c6lp  = request.getParameter("c6lp");
              c7lp  = request.getParameter("c7lp");
              c8lp  = request.getParameter("c8lp");

              t1lp  = request.getParameter("t1lp");
              t2lp  = request.getParameter("t2lp");
              t3lp  = request.getParameter("t3lp");
              t4lp  = request.getParameter("t4lp");
              t5lp  = request.getParameter("t5lp");
              t6lp  = request.getParameter("t6lp");
              t7lp  = request.getParameter("t7lp");
              t8lp = request.getParameter("t8lp");
              t9lp  = request.getParameter("t9lp");
              t10lp  = request.getParameter("t10lp");
              t11lp  = request.getParameter("t11lp");
              t12lp  = request.getParameter("t12lp");
           
              l1lp  = request.getParameter("l1lp");
              l2lp  = request.getParameter("l2lp");
              l3lp  = request.getParameter("l3lp");
              l4lp  = request.getParameter("l4lp");
              l5lp  = request.getParameter("l5lp");
              s1lp  = request.getParameter("s1lp");
              s2lp  = request.getParameter("s2lp");
              s3lp  = request.getParameter("s3lp");
              s45lp  = request.getParameter("s45lp");   
              
              vac = request.getParameter("vac");
              dap = request.getParameter("dap");
              
              rtrm = request.getParameter("rtrm");
              rtrt = request.getParameter("rtrt");
              rtrp = request.getParameter("rtrp");
              ltlm = request.getParameter("ltlm");
              ltlt = request.getParameter("ltlt");
              ltlp = request.getParameter("ltlp");
              uer = request.getParameter("uer");
              uel = request.getParameter("uel");
              uems = request.getParameter("uems");
              ler = request.getParameter("ler");
              lel = request.getParameter("lel");
              lems = request.getParameter("lems");
              lt = request.getParameter("lt");
              pp = request.getParameter("pp");
  }
  
  
  public NeurologyMusclePoints setMusclePoints(HttpServletRequest request)
  {
              NeurologyMusclePoints musPoints = new NeurologyMusclePoints();
              musPoints.setC5rm ( request.getParameter("c5rm"));
              musPoints.setC6rm ( request.getParameter("c6rm"));
              musPoints.setC7rm ( request.getParameter("c7rm"));
              musPoints.setC8rm ( request.getParameter("c8rm"));
              musPoints.setT1rm ( request.getParameter("t1rm"));
            
              musPoints.setL2rm ( request.getParameter("l2rm"));
              musPoints.setL3rm ( request.getParameter("l3rm"));
              musPoints.setL4rm ( request.getParameter("l4rm"));
              musPoints.setL5rm ( request.getParameter("l5rm"));
              musPoints.setS1rm ( request.getParameter("s1rm"));

            // Right Touch 
              musPoints.setC2rt ( request.getParameter("c2rt"));
              musPoints.setC3rt ( request.getParameter("c3rt"));
              musPoints.setC4rt ( request.getParameter("c4rt"));
              musPoints.setC5rt ( request.getParameter("c5rt"));
              musPoints.setC6rt ( request.getParameter("c6rt"));
              musPoints.setC7rt ( request.getParameter("c7rt"));
              musPoints.setC8rt ( request.getParameter("c8rt"));

              musPoints.setT1rt ( request.getParameter("t1rt"));
              musPoints.setT2rt ( request.getParameter("t2rt"));
              musPoints.setT3rt ( request.getParameter("t3rt"));
              musPoints.setT4rt ( request.getParameter("t4rt"));
              musPoints.setT5rt ( request.getParameter("t5rt"));
              musPoints.setT6rt ( request.getParameter("t6rt"));
              musPoints.setT7rt ( request.getParameter("t7rt"));
              musPoints.setT8rt ( request.getParameter("t8rt"));
              musPoints.setT9rt ( request.getParameter("t9rt"));
              musPoints.setT10rt ( request.getParameter("t10rt"));
              musPoints.setT11rt ( request.getParameter("t11rt"));
              musPoints.setT12rt ( request.getParameter("t12rt"));
            
              musPoints.setL1rt ( request.getParameter("l1rt"));
              musPoints.setL2rt ( request.getParameter("l2rt"));
              musPoints.setL3rt ( request.getParameter("l3rt"));
              musPoints.setL4rt ( request.getParameter("l4rt"));
              musPoints.setL5rt ( request.getParameter("l5rt"));
              musPoints.setS1rt ( request.getParameter("s1rt"));
              musPoints.setS2rt ( request.getParameter("s2rt"));
              musPoints.setS3rt ( request.getParameter("s3rt"));
              musPoints.setS45rt ( request.getParameter("s45rt"));

            // Right Prick
            
              musPoints.setC2rp ( request.getParameter("c2rp"));
              musPoints.setC3rp ( request.getParameter("c3rp"));
              musPoints.setC4rp ( request.getParameter("c4rp"));
              musPoints.setC5rp ( request.getParameter("c5rp"));
              musPoints.setC6rp ( request.getParameter("c6rp"));
              musPoints.setC7rp ( request.getParameter("c7rp"));
              musPoints.setC8rp ( request.getParameter("c8rp"));

              musPoints.setT1rp ( request.getParameter("t1rp"));
              musPoints.setT2rp ( request.getParameter("t2rp"));
              musPoints.setT3rp ( request.getParameter("t3rp"));
              musPoints.setT4rp ( request.getParameter("t4rp"));
              musPoints.setT5rp ( request.getParameter("t5rp"));
              musPoints.setT6rp ( request.getParameter("t6rp"));
              musPoints.setT7rp ( request.getParameter("t7rp"));
              musPoints.setT8rp ( request.getParameter("t8rp"));
              musPoints.setT9rp ( request.getParameter("t9rp"));
              musPoints.setT10rp ( request.getParameter("t10rp"));
              musPoints.setT11rp ( request.getParameter("t11rp"));
              musPoints.setT12rp  ( request.getParameter("t12rp"));
           
              musPoints.setL1rp  ( request.getParameter("l1rp"));
              musPoints.setL2rp  ( request.getParameter("l2rp"));
              musPoints.setL3rp ( request.getParameter("l3rp"));
              musPoints.setL4rp ( request.getParameter("l4rp"));
              musPoints.setL5rp ( request.getParameter("l5rp"));
              musPoints.setS1rp ( request.getParameter("s1rp"));
              musPoints.setS2rp ( request.getParameter("s2rp"));
              musPoints.setS3rp  ( request.getParameter("s3rp"));
              musPoints.setS45rp  ( request.getParameter("s45rp"));
            
            
            // Left Motor 
              musPoints.setC5lm  ( request.getParameter("c5lm"));
              musPoints.setC6lm  ( request.getParameter("c6lm"));
              musPoints.setC7lm  ( request.getParameter("c7lm"));
              musPoints.setC8lm  ( request.getParameter("c8lm"));
              musPoints.setT1lm  ( request.getParameter("t1lm"));
            
              musPoints.setL2lm  ( request.getParameter("l2lm"));
              musPoints.setL3lm  ( request.getParameter("l3lm"));
              musPoints.setL4lm  ( request.getParameter("l4lm"));
              musPoints.setL5lm  ( request.getParameter("l5lm"));
              musPoints.setS1lm  ( request.getParameter("s1lm"));

            // Left Touch 
              musPoints.setC2lt  ( request.getParameter("c2lt"));
              musPoints.setC3lt  ( request.getParameter("c3lt"));
              musPoints.setC4lt  ( request.getParameter("c4lt"));
              musPoints.setC5lt  ( request.getParameter("c5lt"));
              musPoints.setC6lt  ( request.getParameter("c6lt"));
              musPoints.setC7lt  ( request.getParameter("c7lt"));
              musPoints.setC8lt  ( request.getParameter("c8lt"));

              musPoints.setT1lt  ( request.getParameter("t1lt"));
              musPoints.setT2lt  ( request.getParameter("t2lt"));
              musPoints.setT3lt  ( request.getParameter("t3lt"));
              musPoints.setT4lt  ( request.getParameter("t4lt"));
              musPoints.setT5lt  ( request.getParameter("t5lt"));
              musPoints.setT6lt  ( request.getParameter("t6lt"));
              musPoints.setT7lt  ( request.getParameter("t7lt"));
              musPoints.setT8lt  ( request.getParameter("t8lt"));
              musPoints.setT9lt  ( request.getParameter("t9lt"));
              musPoints.setT10lt  ( request.getParameter("t10lt"));
              musPoints.setT11lt  ( request.getParameter("t11lt"));
              musPoints.setT12lt  ( request.getParameter("t12lt"));
            
              musPoints.setL1lt  ( request.getParameter("l1lt"));
              musPoints.setL2lt  ( request.getParameter("l2lt"));
              musPoints.setL3lt  ( request.getParameter("l3lt"));
              musPoints.setL4lt  ( request.getParameter("l4lt"));
              musPoints.setL5lt  ( request.getParameter("l5lt"));
              musPoints.setS1lt  ( request.getParameter("s1lt"));
              musPoints.setS2lt  ( request.getParameter("s2lt"));
              musPoints.setS3lt  ( request.getParameter("s3lt"));
              musPoints.setS45lt  ( request.getParameter("s45lt"));

            // Left Prick
            
              musPoints.setC2lp  ( request.getParameter("c2lp"));
              musPoints.setC3lp  ( request.getParameter("c3lp"));
              musPoints.setC4lp  ( request.getParameter("c4lp"));
              musPoints.setC5lp  ( request.getParameter("c5lp"));
              musPoints.setC6lp  ( request.getParameter("c6lp"));
              musPoints.setC7lp  ( request.getParameter("c7lp"));
              musPoints.setC8lp  ( request.getParameter("c8lp"));

              musPoints.setT1lp  ( request.getParameter("t1lp"));
              musPoints.setT2lp  ( request.getParameter("t2lp"));
              musPoints.setT3lp  ( request.getParameter("t3lp"));
              musPoints.setT4lp  ( request.getParameter("t4lp"));
              musPoints.setT5lp  ( request.getParameter("t5lp"));
              musPoints.setT6lp  ( request.getParameter("t6lp"));
              musPoints.setT7lp  ( request.getParameter("t7lp"));
              musPoints.setT8lp ( request.getParameter("t8lp"));
              musPoints.setT9lp  ( request.getParameter("t9lp"));
              musPoints.setT10lp  ( request.getParameter("t10lp"));
              musPoints.setT11lp  ( request.getParameter("t11lp"));
              musPoints.setT12lp  ( request.getParameter("t12lp"));
           
              musPoints.setL1lp  ( request.getParameter("l1lp"));
              musPoints.setL2lp  ( request.getParameter("l2lp"));
              musPoints.setL3lp  ( request.getParameter("l3lp"));
              musPoints.setL4lp  ( request.getParameter("l4lp"));
              musPoints.setL5lp  ( request.getParameter("l5lp"));
              musPoints.setS1lp  ( request.getParameter("s1lp"));
              musPoints.setS2lp  ( request.getParameter("s2lp"));
              musPoints.setS3lp  ( request.getParameter("s3lp"));
              musPoints.setS45lp  ( request.getParameter("s45lp")); 
              
              if(request.getParameter("vac").equals("Y"))
              {
                   musPoints.setVacBinary(BinaryObservation.Yes);
              }
              else if (request.getParameter("vac").equals("N"))
              {
                  musPoints.setVacBinary ( BinaryObservation.No);
              }
              else if (request.getParameter("vac").equals("NT"))
              {
                  musPoints.setVacBinary ( BinaryObservation.NT);
              }
              else
              {
                  musPoints.setVacBinary ( BinaryObservation.None);
              }
             
              if(request.getParameter("dap").equals("Y"))
              {
                   musPoints.setDapBinary(BinaryObservation.Yes);
              }
              else if (request.getParameter("dap").equals("N"))
              {
                  musPoints.setDapBinary ( BinaryObservation.No);
              }
              else if (request.getParameter("dap").equals("NT"))
              {
                  musPoints.setDapBinary ( BinaryObservation.NT);
              }
              else
              {
                  musPoints.setDapBinary ( BinaryObservation.None);
              }

              musPoints.setVac(request.getParameter("vac"));
              musPoints.setDap(request.getParameter("dap"));
              
              musPoints.setRightSensory(request.getParameter("sensoryRight"));
              musPoints.setLeftSensory(request.getParameter("sensoryLeft"));
              musPoints.setRightMotor(request.getParameter("motorRight"));
              musPoints.setLeftMotor(request.getParameter("motorLeft"));
              musPoints.setAsiaComplete(request.getParameter("comp"));
              musPoints.setNeurologicalLevelInjury(request.getParameter("nli"));
              musPoints.setAsiaImparimentScale(request.getParameter("ais"));
              musPoints.setRightSensoryPatial(request.getParameter("sensoryRightPatial"));
              musPoints.setLeftSensoryPatial(request.getParameter("sensoryLeftPatial"));
              musPoints.setRightMotorPatial(request.getParameter("motorRightPatial"));
              musPoints.setLeftMotorPatial(request.getParameter("motorLeftPatial"));
              //musPoints.setComment("");
              musPoints.setSingleNeurologicalLevel("");
              
              musPoints.setRightMotorTotal(request.getParameter("rtrm"));
              musPoints.setRightTouchTotal(request.getParameter("rtrt"));
              musPoints.setRightPrickTotal(request.getParameter("rtrp"));
              musPoints.setLeftMotorTotal(request.getParameter("ltlm"));
              musPoints.setLeftTouchTotal(request.getParameter("ltlt"));
              musPoints.setLeftPrickTotal(request.getParameter("ltlp"));
              musPoints.setRightUpperMotorTotal(request.getParameter("uer"));
              musPoints.setLeftUpperMotorTotal(request.getParameter("uel"));
              musPoints.setUpperMotorTotal(request.getParameter("uems"));
              musPoints.setRightLowerMotorTotal(request.getParameter("ler"));
              musPoints.setLeftLowerMotorTotal(request.getParameter("lel"));
              musPoints.setLowerMotorTotal(request.getParameter("lems"));
              musPoints.setTouchTotal(request.getParameter("lt"));
              musPoints.setPrickTotal(request.getParameter("pp"));
              
              musPoints.setComment(request.getParameter("comment"));
              musPoints.setAssessmentDate(request.getParameter("assessment_date"));
              musPoints.setAssessmentTimepoint(request.getParameter("assessment_timepoint"));
   
              return musPoints;
  }
  
}
