/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;
import javax.servlet.*;
//import javax.websocket.Session;
import org.json.JSONException;
import org.json.JSONObject;  

/**
 *
 * @author purat
 */
public class CalculateClassification extends HttpServlet {
        
    	public int RightUpperMotorTotal = 0;
        public boolean RightUpperMotorTotalHasImpairmentNotDueToSci = false;
	public boolean RightUpperMotorContainsNt = false;
	public int LeftUpperMotorTotal = 0;
	public boolean LeftUpperMotorTotalHasImpairmentNotDueToSci = false;
	public boolean LeftUpperMotorContainsNt = false;
	public int RightLowerMotorTotal = 0;
	public boolean RightLowerMotorTotalHasImpairmentNotDueToSci = false;
	public boolean RightLowerMotorContainsNt = false;
	public int LeftLowerMotorTotal = 0;
	public boolean LeftLowerMotorTotalHasImpairmentNotDueToSci = false;
	public boolean LeftLowerMotorContainsNt = false;
	public int RightTouchTotal = 0;
	public boolean RightTouchTotalHasImpairmentNotDueToSci = false;
	public boolean RightTouchContainsNt = false;
	public int LeftTouchTotal = 0;
	public boolean LeftTouchTotalHasImpairmentNotDueToSci = false;
	public boolean LeftTouchContainsNt = false;
	public int RightPrickTotal = 0;
	public boolean RightPrickTotalHasImpairmentNotDueToSci = false;
	public boolean RightPrickContainsNt = false;
	public int LeftPrickTotal = 0;
	public boolean LeftPrickTotalHasImpairmentNotDueToSci = false;
	public boolean LeftPrickContainsNt = false;

@Override

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
 {
    try {
        Algorithm algo = new Algorithm();
        String action = request.getParameter("action");
        String modality = request.getParameter("modality");
        String pid = request.getParameter("pid");
        
        NeurologyMusclePoints musPoints = new NeurologyMusclePoints();
        NeurologyFormTotals neuTotals = new NeurologyFormTotals();

        if (request.getParameter("btnCalculate") != null) {
            /*
             String c5rm = request.getParameter("c5rm");
             String c6rm = request.getParameter("c6rm");
             String c7rm = request.getParameter("c7rm");
             String c8rm = request.getParameter("c8rm");
             String t1rm = request.getParameter("t1rm");
            
             String l2rm = request.getParameter("l2rm");
             String l3rm = request.getParameter("l3rm");
             String l4rm = request.getParameter("l4rm");
             String l5rm = request.getParameter("l5rm");
             String s1rm = request.getParameter("s1rm");

            // Right Touch 
             String c2rt = request.getParameter("c2rt");
             String c3rt = request.getParameter("c3rt");
             String c4rt = request.getParameter("c4rt");
             String c5rt = request.getParameter("c5rt");
             String c6rt = request.getParameter("c6rt");
             String c7rt = request.getParameter("c7rt");
             String c8rt = request.getParameter("c8rt");

             String t1rt = request.getParameter("t1rt");
             String t2rt = request.getParameter("t2rt");
             String t3rt = request.getParameter("t3rt");
             String t4rt = request.getParameter("t4rt");
             String t5rt = request.getParameter("t5rt");
             String t6rt = request.getParameter("t6rt");
             String t7rt = request.getParameter("t7rt");
             String t8rt = request.getParameter("t8rt");
             String t9rt = request.getParameter("t9rt");
             String t10rt = request.getParameter("t10rt");
             String t11rt = request.getParameter("t11rt");
             String t12rt = request.getParameter("t12rt");
            
             String  l1rt = request.getParameter("l1rt");
             String l2rt = request.getParameter("l2rt");
             String l3rt = request.getParameter("l3rt");
             String l4rt = request.getParameter("l4rt");
             String l5rt = request.getParameter("l5rt");
             String s1rt = request.getParameter("s1rt");
             String s2rt = request.getParameter("s2rt");
             String s3rt = request.getParameter("s3rt");
             String s45rt = request.getParameter("s45rt");

            // Right Prick
            
             String c2rp = request.getParameter("c2rp");
             String c3rp = request.getParameter("c3rp");
             String c4rp = request.getParameter("c4rp");
             String c5rp = request.getParameter("c5rp");
             String c6rp = request.getParameter("c6rp");
             String c7rp = request.getParameter("c7rp");
             String c8rp = request.getParameter("c8rp");

             String t1rp = request.getParameter("t1rp");
             String t2rp = request.getParameter("t2rp");
             String t3rp = request.getParameter("t3rp");
             String t4rp = request.getParameter("t4rp");
             String t5rp = request.getParameter("t5rp");
             String t6rp = request.getParameter("t6rp");
             String t7rp = request.getParameter("t7rp");
             String t8rp = request.getParameter("t8rp");
             String t9rp = request.getParameter("t9rp");
             String t10rp = request.getParameter("t10rp");
             String t11rp = request.getParameter("t11rp");
             String t12rp  = request.getParameter("t12rp");
           
             String l1rp  = request.getParameter("l1rp");
             String l2rp  = request.getParameter("l2rp");
             String l3rp = request.getParameter("l3rp");
             String l4rp = request.getParameter("l4rp");
             String l5rp = request.getParameter("l5rp");
             String s1rp = request.getParameter("s1rp");
             String s2rp = request.getParameter("s2rp");
             String s3rp  = request.getParameter("s3rp");
             String s45rp  = request.getParameter("s45rp");
            
            
            // Left Motor 
             String c5lm  = request.getParameter("c5lm");
             String c6lm  = request.getParameter("c6lm");
             String c7lm  = request.getParameter("c7lm");
             String c8lm  = request.getParameter("c8lm");
             String t1lm  = request.getParameter("t1lm");
            
             String l2lm  = request.getParameter("l2lm");
             String l3lm  = request.getParameter("l3lm");
             String l4lm  = request.getParameter("l4lm");
             String l5lm  = request.getParameter("l5lm");
             String s1lm  = request.getParameter("s1lm");

            // Left Touch 
             String c2lt  = request.getParameter("c2lt");
             String c3lt  = request.getParameter("c3lt");
             String c4lt  = request.getParameter("c4lt");
             String c5lt  = request.getParameter("c5lt");
             String c6lt  = request.getParameter("c6lt");
             String c7lt  = request.getParameter("c7lt");
             String c8lt  = request.getParameter("c8lt");

             String t1lt  = request.getParameter("t1lt");
             String t2lt  = request.getParameter("t2lt");
             String t3lt  = request.getParameter("t3lt");
             String t4lt  = request.getParameter("t4lt");
             String t5lt  = request.getParameter("t5lt");
             String t6lt  = request.getParameter("t6lt");
             String t7lt  = request.getParameter("t7lt");
             String t8lt  = request.getParameter("t8lt");
             String t9lt  = request.getParameter("t9lt");
             String t10lt  = request.getParameter("t10lt");
             String t11lt  = request.getParameter("t11lt");
             String t12lt  = request.getParameter("t12lt");
            
             String l1lt  = request.getParameter("l1lt");
             String l2lt  = request.getParameter("l2lt");
             String l3lt  = request.getParameter("l3lt");
             String l4lt  = request.getParameter("l4lt");
             String l5lt  = request.getParameter("l5lt");
             String s1lt  = request.getParameter("s1lt");
             String s2lt  = request.getParameter("s2lt");
             String s3lt  = request.getParameter("s3lt");
             String s45lt  = request.getParameter("s45lt");

            // Left Prick
            
             String c2lp  = request.getParameter("c2lp");
             String c3lp  = request.getParameter("c3lp");
             String c4lp  = request.getParameter("c4lp");
             String c5lp  = request.getParameter("c5lp");
             String c6lp  = request.getParameter("c6lp");
             String c7lp  = request.getParameter("c7lp");
             String c8lp  = request.getParameter("c8lp");

             String t1lp  = request.getParameter("t1lp");
             String t2lp  = request.getParameter("t2lp");
             String t3lp  = request.getParameter("t3lp");
             String t4lp  = request.getParameter("t4lp");
             String t5lp  = request.getParameter("t5lp");
             String t6lp  = request.getParameter("t6lp");
             String t7lp  = request.getParameter("t7lp");
             String t8lp = request.getParameter("t8lp");
             String t9lp  = request.getParameter("t9lp");
             String t10lp  = request.getParameter("t10lp");
             String t11lp  = request.getParameter("t11lp");
             String t12lp  = request.getParameter("t12lp");
           
             String l1lp  = request.getParameter("l1lp");
             String l2lp  = request.getParameter("l2lp");
             String l3lp  = request.getParameter("l3lp");
             String l4lp  = request.getParameter("l4lp");
             String l5lp  = request.getParameter("l5lp");
             String s1lp  = request.getParameter("s1lp");
             String s2lp  = request.getParameter("s2lp");
             String s3lp  = request.getParameter("s3lp");
             String s45lp  = request.getParameter("s45lp");
            
            
            String[] rum_values = {request.getParameter("c5rm"),request.getParameter("c6rm"),request.getParameter("c7rm"),request.getParameter("c8rm"),request.getParameter("t1rm")};
            if(containsNT(rum_values)) this.RightUpperMotorContainsNt = true;
            if(hasImpairmentNotDueToSCI(rum_values)) this.RightUpperMotorTotalHasImpairmentNotDueToSci = true;
           
            String[] rul_values = {request.getParameter("l2rm"),request.getParameter("l3rm"),request.getParameter("l4rm"),request.getParameter("l5rm"),request.getParameter("s1rm")};
            if(containsNT(rul_values)) this.RightLowerMotorContainsNt = true;
            if(hasImpairmentNotDueToSCI(rul_values)) this.RightLowerMotorTotalHasImpairmentNotDueToSci = true;
            
            String[] rt_values = {request.getParameter("c2rt"),request.getParameter("c3rt"),request.getParameter("c4rt"),request.getParameter("c5rt"),request.getParameter("c6rt"),request.getParameter("c7rt"),request.getParameter("c8rt"),request.getParameter("t1rt"),request.getParameter("t1rt"),request.getParameter("t1rt"),request.getParameter("t1rt"),request.getParameter("t1rt"),request.getParameter("t1rt"),request.getParameter("t1rt"),request.getParameter("t1rt")};
            if(containsNT(rul_values)) this.RightLowerMotorContainsNt = true;
            if(hasImpairmentNotDueToSCI(rul_values)) this.RightLowerMotorTotalHasImpairmentNotDueToSci = true;
           */
            
            musPoints.setC5rm(checkMuscleValues("rmu", request.getParameter("c5rm"))); 
            musPoints.setC6rm(checkMuscleValues("rmu", request.getParameter("c6rm")));
            musPoints.setC7rm(checkMuscleValues("rmu", request.getParameter("c7rm")));
            musPoints.setC8rm(checkMuscleValues("rmu", request.getParameter("c8rm")));
            musPoints.setT1rm(checkMuscleValues("rmu", request.getParameter("t1rm")));
 
            musPoints.setL2rm(checkMuscleValues("rml", request.getParameter("l2rm")));
            musPoints.setL3rm(checkMuscleValues("rml", request.getParameter("l3rm")));
            musPoints.setL4rm(checkMuscleValues("rml", request.getParameter("l4rm")));
            musPoints.setL5rm(checkMuscleValues("rml", request.getParameter("l5rm")));
            musPoints.setS1rm(checkMuscleValues("rml", request.getParameter("s1rm")));
            
            musPoints.setC2rt(checkMuscleValues("rt", request.getParameter("c2rt")));
            musPoints.setC3rt(checkMuscleValues("rt", request.getParameter("c3rt")));
            musPoints.setC4rt(checkMuscleValues("rt", request.getParameter("c4rt")));
            musPoints.setC5rt(checkMuscleValues("rt", request.getParameter("c5rt")));
            musPoints.setC6rt(checkMuscleValues("rt", request.getParameter("c6rt")));
            musPoints.setC7rt(checkMuscleValues("rt", request.getParameter("c7rt")));
            musPoints.setC8rt(checkMuscleValues("rt", request.getParameter("c8rt")));
            musPoints.setT1rt(checkMuscleValues("rt", request.getParameter("t1rt")));
            musPoints.setT2rt(checkMuscleValues("rt", request.getParameter("t2rt")));
            musPoints.setT3rt(checkMuscleValues("rt", request.getParameter("t3rt")));
            musPoints.setT4rt(checkMuscleValues("rt", request.getParameter("t4rt")));
            musPoints.setT5rt(checkMuscleValues("rt", request.getParameter("t5rt")));
            musPoints.setT6rt(checkMuscleValues("rt", request.getParameter("t6rt")));
            musPoints.setT7rt(checkMuscleValues("rt", request.getParameter("t7rt")));
            musPoints.setT8rt(checkMuscleValues("rt", request.getParameter("t8rt")));
            musPoints.setT9rt(checkMuscleValues("rt", request.getParameter("t9rt")));
            musPoints.setT10rt(checkMuscleValues("rt", request.getParameter("t10rt")));
            musPoints.setT11rt(checkMuscleValues("rt", request.getParameter("t11rt")));
            musPoints.setT12rt(checkMuscleValues("rt", request.getParameter("t12rt")));
            musPoints.setL1rt(checkMuscleValues("rt", request.getParameter("l1rt")));
            musPoints.setL2rt(checkMuscleValues("rt", request.getParameter("l2rt")));
            musPoints.setL3rt(checkMuscleValues("rt", request.getParameter("l3rt")));
            musPoints.setL4rt(checkMuscleValues("rt", request.getParameter("l4rt")));
            musPoints.setL5rt(checkMuscleValues("rt", request.getParameter("l5rt")));
            musPoints.setS1rt(checkMuscleValues("rt", request.getParameter("s1rt")));
            musPoints.setS2rt(checkMuscleValues("rt", request.getParameter("s2rt")));
            musPoints.setS3rt(checkMuscleValues("rt", request.getParameter("s3rt")));
            musPoints.setS45rt(checkMuscleValues("rt", request.getParameter("s45rt")));
            
            musPoints.setC2rp(checkMuscleValues("rp", request.getParameter("c2rp")));
            musPoints.setC3rp(checkMuscleValues("rp", request.getParameter("c3rp")));
            musPoints.setC4rp(checkMuscleValues("rp", request.getParameter("c4rp")));
            musPoints.setC5rp(checkMuscleValues("rp", request.getParameter("c5rp")));
            musPoints.setC6rp(checkMuscleValues("rp", request.getParameter("c6rp")));
            musPoints.setC7rp(checkMuscleValues("rp", request.getParameter("c7rp")));
            musPoints.setC8rp(checkMuscleValues("rp", request.getParameter("c8rp")));
            musPoints.setT1rp(checkMuscleValues("rp", request.getParameter("t1rp")));
            musPoints.setT2rp(checkMuscleValues("rp", request.getParameter("t2rp")));
            musPoints.setT3rp(checkMuscleValues("rp", request.getParameter("t3rp")));
            musPoints.setT4rp(checkMuscleValues("rp", request.getParameter("t4rp")));
            musPoints.setT5rp(checkMuscleValues("rp", request.getParameter("t5rp")));
            musPoints.setT6rp(checkMuscleValues("rp", request.getParameter("t6rp")));
            musPoints.setT7rp(checkMuscleValues("rp", request.getParameter("t7rp")));
            musPoints.setT8rp(checkMuscleValues("rp", request.getParameter("t8rp")));
            musPoints.setT9rp(checkMuscleValues("rp", request.getParameter("t9rp")));
            musPoints.setT10rp(checkMuscleValues("rp", request.getParameter("t10rp")));
            musPoints.setT11rp(checkMuscleValues("rp", request.getParameter("t11rp")));
            musPoints.setT12rp(checkMuscleValues("rp", request.getParameter("t12rp")));
            musPoints.setL1rp(checkMuscleValues("rp", request.getParameter("l1rp")));
            musPoints.setL2rp(checkMuscleValues("rp", request.getParameter("l2rp")));
            musPoints.setL3rp(checkMuscleValues("rp", request.getParameter("l3rp")));
            musPoints.setL4rp(checkMuscleValues("rp", request.getParameter("l4rp")));
            musPoints.setL5rp(checkMuscleValues("rp", request.getParameter("l5rp")));
            musPoints.setS1rp(checkMuscleValues("rp", request.getParameter("s1rp")));
            musPoints.setS2rp(checkMuscleValues("rp", request.getParameter("s2rp")));
            musPoints.setS3rp(checkMuscleValues("rp", request.getParameter("s3rp")));
            musPoints.setS45rp(checkMuscleValues("rp", request.getParameter("s45rp")));

            // Left
            
            musPoints.setC5lm(checkMuscleValues("lmu", request.getParameter("c5lm")));
            musPoints.setC6lm(checkMuscleValues("lmu", request.getParameter("c6lm")));
            musPoints.setC7lm(checkMuscleValues("lmu", request.getParameter("c7lm")));
            musPoints.setC8lm(checkMuscleValues("lmu", request.getParameter("c8lm")));
            musPoints.setT1lm(checkMuscleValues("lmu", request.getParameter("t1lm")));

            
            musPoints.setL2lm(checkMuscleValues("lml", request.getParameter("l2lm")));
            musPoints.setL3lm(checkMuscleValues("lml", request.getParameter("l3lm")));
            musPoints.setL4lm(checkMuscleValues("lml", request.getParameter("l4lm")));
            musPoints.setL5lm(checkMuscleValues("lml", request.getParameter("l5lm")));
            musPoints.setS1lm(checkMuscleValues("lml", request.getParameter("s1lm")));
            
             
            musPoints.setC2lt(checkMuscleValues("lt", request.getParameter("c2lt")));
            musPoints.setC3lt(checkMuscleValues("lt", request.getParameter("c3lt")));
            musPoints.setC4lt(checkMuscleValues("lt", request.getParameter("c4lt")));
            musPoints.setC5lt(checkMuscleValues("lt", request.getParameter("c5lt")));
            musPoints.setC6lt(checkMuscleValues("lt", request.getParameter("c6lt")));
            musPoints.setC7lt(checkMuscleValues("lt", request.getParameter("c7lt")));
            musPoints.setC8lt(checkMuscleValues("lt", request.getParameter("c8lt")));
            musPoints.setT1lt(checkMuscleValues("lt", request.getParameter("t1lt")));
            musPoints.setT2lt(checkMuscleValues("lt", request.getParameter("t2lt")));
            musPoints.setT3lt(checkMuscleValues("lt", request.getParameter("t3lt")));
            musPoints.setT4lt(checkMuscleValues("lt", request.getParameter("t4lt")));
            musPoints.setT5lt(checkMuscleValues("lt", request.getParameter("t5lt")));
            musPoints.setT6lt(checkMuscleValues("lt", request.getParameter("t6lt")));
            musPoints.setT7lt(checkMuscleValues("lt", request.getParameter("t7lt")));
            musPoints.setT8lt(checkMuscleValues("lt", request.getParameter("t8lt")));
            musPoints.setT9lt(checkMuscleValues("lt", request.getParameter("t9lt")));
            musPoints.setT10lt(checkMuscleValues("lt", request.getParameter("t10lt")));
            musPoints.setT11lt(checkMuscleValues("lt", request.getParameter("t11lt")));
            musPoints.setT12lt(checkMuscleValues("lt", request.getParameter("t12lt")));
            musPoints.setL1lt(checkMuscleValues("lt", request.getParameter("l1lt")));
            musPoints.setL2lt(checkMuscleValues("lt", request.getParameter("l2lt")));
            musPoints.setL3lt(checkMuscleValues("lt", request.getParameter("l3lt")));
            musPoints.setL4lt(checkMuscleValues("lt", request.getParameter("l4lt")));
            musPoints.setL5lt(checkMuscleValues("lt", request.getParameter("l5lt")));
            musPoints.setS1lt(checkMuscleValues("lt", request.getParameter("s1lt")));
            musPoints.setS2lt(checkMuscleValues("lt", request.getParameter("s2lt")));
            musPoints.setS3lt(checkMuscleValues("lt", request.getParameter("s3lt")));
            musPoints.setS45lt(checkMuscleValues("lt", request.getParameter("s45lt")));
            
            musPoints.setC2lp(checkMuscleValues("lp", request.getParameter("c2lp")));
            musPoints.setC3lp(checkMuscleValues("lp", request.getParameter("c3lp")));
            musPoints.setC4lp(checkMuscleValues("lp", request.getParameter("c4lp")));
            musPoints.setC5lp(checkMuscleValues("lp", request.getParameter("c5lp")));
            musPoints.setC6lp(checkMuscleValues("lp", request.getParameter("c6lp")));
            musPoints.setC7lp(checkMuscleValues("lp", request.getParameter("c7lp")));
            musPoints.setC8lp(checkMuscleValues("lp", request.getParameter("c8lp")));
            musPoints.setT1lp(checkMuscleValues("lp", request.getParameter("t1lp")));
            musPoints.setT2lp(checkMuscleValues("lp", request.getParameter("t2lp")));
            musPoints.setT3lp(checkMuscleValues("lp", request.getParameter("t3lp")));
            musPoints.setT4lp(checkMuscleValues("lp", request.getParameter("t4lp")));
            musPoints.setT5lp(checkMuscleValues("lp", request.getParameter("t5lp")));
            musPoints.setT6lp(checkMuscleValues("lp", request.getParameter("t6lp")));
            musPoints.setT7lp(checkMuscleValues("lp", request.getParameter("t7lp")));
            musPoints.setT8lp(checkMuscleValues("lp", request.getParameter("t8lp")));
            musPoints.setT9lp(checkMuscleValues("lp", request.getParameter("t9lp")));
            musPoints.setT10lp(checkMuscleValues("lp", request.getParameter("t10lp")));
            musPoints.setT11lp(checkMuscleValues("lp", request.getParameter("t11lp")));
            musPoints.setT12lp(checkMuscleValues("lp", request.getParameter("t12lp")));
            musPoints.setL1lp(checkMuscleValues("lp", request.getParameter("l1lp")));
            musPoints.setL2lp(checkMuscleValues("lp", request.getParameter("l2lp")));
            musPoints.setL3lp(checkMuscleValues("lp", request.getParameter("l3lp")));
            musPoints.setL4lp(checkMuscleValues("lp", request.getParameter("l4lp")));
            musPoints.setL5lp(checkMuscleValues("lp", request.getParameter("l5lp")));
            musPoints.setS1lp(checkMuscleValues("lp", request.getParameter("s1lp")));
            musPoints.setS2lp(checkMuscleValues("lp", request.getParameter("s2lp")));
            musPoints.setS3lp(checkMuscleValues("lp", request.getParameter("s3lp")));
            musPoints.setS45lp(checkMuscleValues("lp", request.getParameter("s45lp")));
        }
        
        String url = "./jsp/spinalalgo.jsp?modality=" + modality + "&action=" + action + "&pid=" + pid;
        // response.sendRedirect(url);
        
        this.RightUpperMotorTotal = Integer.parseInt(musPoints.getC5rm()) + Integer.parseInt(musPoints.getC6rm()) + Integer.parseInt(musPoints.getC7rm()) + Integer.parseInt(musPoints.getC8rm()) + Integer.parseInt(musPoints.getT1rm()) ;
        this.RightLowerMotorTotal = Integer.parseInt(musPoints.getL2rm()) + Integer.parseInt(musPoints.getL3rm()) + Integer.parseInt(musPoints.getL4rm()) + Integer.parseInt(musPoints.getL5rm()) + Integer.parseInt(musPoints.getS1rm()) ;
        int rtrm_total = this.RightUpperMotorTotal + this.RightLowerMotorTotal;
        
        int crt_total = Integer.parseInt(musPoints.getC2rt()) + Integer.parseInt(musPoints.getC3rt()) + Integer.parseInt(musPoints.getC4rt()) + Integer.parseInt(musPoints.getC5rt()) + Integer.parseInt(musPoints.getC6rt()) + Integer.parseInt(musPoints.getC7rt()) + Integer.parseInt(musPoints.getC8rt());
        int trt_total = Integer.parseInt(musPoints.getT1rt()) + Integer.parseInt(musPoints.getT2rt()) + Integer.parseInt(musPoints.getT3rt()) + Integer.parseInt(musPoints.getT4rt()) + Integer.parseInt(musPoints.getT5rt()) + Integer.parseInt(musPoints.getT6rt()) + Integer.parseInt(musPoints.getT7rt()) + Integer.parseInt(musPoints.getT8rt()) + Integer.parseInt(musPoints.getT9rt()) + Integer.parseInt(musPoints.getT10rt()) + Integer.parseInt(musPoints.getT11rt()) + Integer.parseInt(musPoints.getT12rt()) ;
        int lrt_total = Integer.parseInt(musPoints.getL1rt()) + Integer.parseInt(musPoints.getL2rt()) + Integer.parseInt(musPoints.getL3rt()) + Integer.parseInt(musPoints.getL4rt()) + Integer.parseInt(musPoints.getL5rt()) ;
        int srt_total = Integer.parseInt(musPoints.getS1rt()) + Integer.parseInt(musPoints.getS2rt()) +  Integer.parseInt(musPoints.getS3rt()) + Integer.parseInt(musPoints.getS45rt()) ;
        this.RightTouchTotal = crt_total + trt_total + lrt_total + srt_total;
        
        int crp_total = Integer.parseInt(musPoints.getC2rp()) + Integer.parseInt(musPoints.getC3rp()) + Integer.parseInt(musPoints.getC4rp()) + Integer.parseInt(musPoints.getC5rp()) + Integer.parseInt(musPoints.getC6rp()) + Integer.parseInt(musPoints.getC7rp()) + Integer.parseInt(musPoints.getC8rp());
        int trp_total = Integer.parseInt(musPoints.getT1rp()) + Integer.parseInt(musPoints.getT2rp()) + Integer.parseInt(musPoints.getT3rp()) + Integer.parseInt(musPoints.getT4rp()) + Integer.parseInt(musPoints.getT5rp()) + Integer.parseInt(musPoints.getT6rp()) + Integer.parseInt(musPoints.getT7rp()) + Integer.parseInt(musPoints.getT8rp()) + Integer.parseInt(musPoints.getT9rp()) + Integer.parseInt(musPoints.getT10rp()) + Integer.parseInt(musPoints.getT11rp()) + Integer.parseInt(musPoints.getT12rp()) ;
        int lrp_total = Integer.parseInt(musPoints.getL1rp()) + Integer.parseInt(musPoints.getL2rp()) + Integer.parseInt(musPoints.getL3rp()) + Integer.parseInt(musPoints.getL4rp()) + Integer.parseInt(musPoints.getL5rp()) ;
        int srp_total = Integer.parseInt(musPoints.getS1rp()) + Integer.parseInt(musPoints.getS2rp()) + Integer.parseInt(musPoints.getS3rp()) + Integer.parseInt(musPoints.getS45rp()) ;
        this.RightPrickTotal = crp_total + trp_total + lrp_total + srp_total;
        
        // Left
        
        this.LeftUpperMotorTotal = Integer.parseInt(musPoints.getC5lm()) + Integer.parseInt(musPoints.getC6lm()) + Integer.parseInt(musPoints.getC7lm()) + Integer.parseInt(musPoints.getC8lm()) + Integer.parseInt(musPoints.getT1lm()) ;
        this.LeftLowerMotorTotal = Integer.parseInt(musPoints.getL2lm()) + Integer.parseInt(musPoints.getL3lm()) + Integer.parseInt(musPoints.getL4lm()) + Integer.parseInt(musPoints.getL5lm()) + Integer.parseInt(musPoints.getS1lm()) ;
        int ltlm_total = this.LeftUpperMotorTotal + this.LeftLowerMotorTotal;
        
        int clt_total = Integer.parseInt(musPoints.getC2lt()) + Integer.parseInt(musPoints.getC3lt()) + Integer.parseInt(musPoints.getC4lt()) + Integer.parseInt(musPoints.getC5lt()) + Integer.parseInt(musPoints.getC6lt()) + Integer.parseInt(musPoints.getC7lt()) + Integer.parseInt(musPoints.getC8lt());
        int tlt_total = Integer.parseInt(musPoints.getT1lt()) + Integer.parseInt(musPoints.getT2lt()) + Integer.parseInt(musPoints.getT3lt()) + Integer.parseInt(musPoints.getT4lt()) + Integer.parseInt(musPoints.getT5lt()) + Integer.parseInt(musPoints.getT6lt()) + Integer.parseInt(musPoints.getT7lt()) + Integer.parseInt(musPoints.getT8lt()) + Integer.parseInt(musPoints.getT9lt()) + Integer.parseInt(musPoints.getT10lt()) + Integer.parseInt(musPoints.getT11lt()) + Integer.parseInt(musPoints.getT12lt()) ;
        int llt_total = Integer.parseInt(musPoints.getL1lt()) + Integer.parseInt(musPoints.getL2lt()) + Integer.parseInt(musPoints.getL3lt()) + Integer.parseInt(musPoints.getL4lt()) + Integer.parseInt(musPoints.getL5lt()) ;
        int slt_total = Integer.parseInt(musPoints.getS1lt()) + Integer.parseInt(musPoints.getS2lt()) +  Integer.parseInt(musPoints.getS3lt()) + Integer.parseInt(musPoints.getS45lt()) ;
        this.LeftTouchTotal = clt_total + tlt_total + llt_total + slt_total;
        
        int clp_total = Integer.parseInt(musPoints.getC2lp()) + Integer.parseInt(musPoints.getC3lp()) + Integer.parseInt(musPoints.getC4lp()) + Integer.parseInt(musPoints.getC5lp()) + Integer.parseInt(musPoints.getC6lp()) + Integer.parseInt(musPoints.getC7lp()) + Integer.parseInt(musPoints.getC8lp());
        int tlp_total = Integer.parseInt(musPoints.getT1lp()) + Integer.parseInt(musPoints.getT2lp()) + Integer.parseInt(musPoints.getT3lp()) + Integer.parseInt(musPoints.getT4lp()) + Integer.parseInt(musPoints.getT5lp()) + Integer.parseInt(musPoints.getT6lp()) + Integer.parseInt(musPoints.getT7lp()) + Integer.parseInt(musPoints.getT8lp()) + Integer.parseInt(musPoints.getT9lp()) + Integer.parseInt(musPoints.getT10lp()) + Integer.parseInt(musPoints.getT11lp()) + Integer.parseInt(musPoints.getT12lp()) ;
        int llp_total = Integer.parseInt(musPoints.getL1lp()) + Integer.parseInt(musPoints.getL2lp()) + Integer.parseInt(musPoints.getL3lp()) + Integer.parseInt(musPoints.getL4lp()) + Integer.parseInt(musPoints.getL5lp()) ;
        int slp_total = Integer.parseInt(musPoints.getS1lp()) + Integer.parseInt(musPoints.getS2lp()) + Integer.parseInt(musPoints.getS3lp()) + Integer.parseInt(musPoints.getS45lp()) ;
        this.LeftPrickTotal = clp_total + tlp_total + llp_total + slp_total;
        
        NeurologyFormTotals nTotals = new NeurologyFormTotals();
        nTotals.RightUpperMotorTotal = this.RightUpperMotorTotal;
        nTotals.RightUpperMotorContainsNt = this.RightUpperMotorContainsNt;
        nTotals.RightUpperMotorTotalHasImpairmentNotDueToSci = this.RightUpperMotorTotalHasImpairmentNotDueToSci;
        
        nTotals.RightLowerMotorTotal = this.RightLowerMotorTotal;
        nTotals.RightLowerMotorContainsNt = this.RightLowerMotorContainsNt;
        nTotals.RightLowerMotorTotalHasImpairmentNotDueToSci = this.RightLowerMotorTotalHasImpairmentNotDueToSci;
        
        nTotals.RightPrickTotal = this.RightPrickTotal;
        nTotals.RightTouchTotal = this.RightTouchTotal;
        
        nTotals.LeftUpperMotorTotal = this.LeftUpperMotorTotal;
        nTotals.LeftUpperMotorContainsNt = this.LeftUpperMotorContainsNt;
        nTotals.LeftUpperMotorTotalHasImpairmentNotDueToSci = this.LeftUpperMotorTotalHasImpairmentNotDueToSci;
        
        nTotals.LeftLowerMotorTotal = this.LeftLowerMotorTotal;
        nTotals.LeftLowerMotorContainsNt = this.LeftLowerMotorContainsNt;
        nTotals.LeftLowerMotorTotalHasImpairmentNotDueToSci = this.LeftLowerMotorTotalHasImpairmentNotDueToSci;
        
        nTotals.LeftPrickTotal = this.LeftPrickTotal;
        nTotals.RightPrickContainsNt = this.RightPrickContainsNt;
        nTotals.RightPrickTotalHasImpairmentNotDueToSci = this.RightPrickTotalHasImpairmentNotDueToSci;
        
        nTotals.LeftTouchTotal = this.LeftTouchTotal; 
        nTotals.RightTouchContainsNt = this.RightTouchContainsNt;
        nTotals.RightTouchTotalHasImpairmentNotDueToSci = this.RightTouchTotalHasImpairmentNotDueToSci;
        
        nTotals.UpperMotorTotal = this.RightUpperMotorTotal + this.LeftUpperMotorTotal;
        nTotals.LowerMotorTotal = this.RightLowerMotorTotal + this.LeftLowerMotorTotal;
        nTotals.TouchTotal = this.RightTouchTotal + this.LeftTouchTotal;
        nTotals.PrickTotal = this.RightPrickTotal + this.LeftPrickTotal;
                
        NeurologyFormTotalsSummary nTotalsSummary = new NeurologyFormTotalsSummary();
        nTotalsSummary = Algorithm.GetTotalsSummaryFor(nTotals);
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        JSONObject json = new JSONObject();
        json.put("rtrm_total", rtrm_total);
        json.put("rtrt_total", this.RightTouchTotal);
        json.put("rtrp_total", this.RightPrickTotal);
        
        json.put("ltlm_total", ltlm_total);
        json.put("ltlt_total", this.LeftTouchTotal);
        json.put("ltlp_total", this.LeftPrickTotal);
        
        json.put("uer", this.RightUpperMotorTotal);
        json.put("uel", this.LeftUpperMotorTotal);
        json.put("uems", this.RightUpperMotorTotal + this.LeftUpperMotorTotal);

        json.put("ler", this.RightLowerMotorTotal);
        json.put("lel", this.LeftLowerMotorTotal);
        json.put("lems", this.RightLowerMotorTotal + this.LeftLowerMotorTotal);

        json.put("ltr", this.RightTouchTotal);
        json.put("ltl", this.LeftTouchTotal);
        json.put("lt", this.RightTouchTotal + this.LeftTouchTotal);

        json.put("ppr", this.RightPrickTotal);
        json.put("ppl", this.LeftPrickTotal);
        json.put("pp", this.RightPrickTotal + this.LeftPrickTotal);
        
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
        
        //RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        //dispatcher.forward(request, response);
        
    } catch (JSONException ex) {
        Logger.getLogger(CalculateClassification.class.getName()).log(Level.SEVERE, null, ex);
    }
 }
 
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
  }
  
  private boolean containsNT(String[] values)
  {
      boolean isContain = false;
      for (int i=0; i<values.length; i++)
      {
          if(values[i].contains("NT"))
          {
              isContain = true;
              break;
          }
      }
      return isContain;
  }
  
  private boolean hasImpairmentNotDueToSCI(String[] values)
  {
      boolean isContain = false;
      for (int i=0; i<values.length; i++)
      {
          if(values[i].contains("*") || values[i].contains("!"))
          {
              isContain = true;
              break;
          }
      }
      return isContain;
  }
  
  private String checkMuscleValues(String point_id, String value)
  {
      String numValue = value;
      
      if(value.contains("NT"))
      {
          if(point_id.contains("rmu"))
          {
              RightUpperMotorContainsNt = true; 
          }
          else if (point_id.contains("rml"))
          {
              RightLowerMotorContainsNt = true;
          }
          else if (point_id.contains("rt"))
          {
              RightTouchContainsNt = true;
          }
          else if (point_id.contains("rm"))
          {
              RightPrickContainsNt = true;
          }
          else if(point_id.contains("lmu"))
          {
              LeftUpperMotorContainsNt = true;
          }
          else if(point_id.contains("lml"))
          {
              LeftLowerMotorContainsNt = true;
          }
          else if (point_id.contains("lt"))
          {
              LeftTouchContainsNt = true;
          }
          else if (point_id.contains("lp"))
          {
              LeftPrickContainsNt = true;
          }
          numValue = "0";
      }
      if (value.contains("*") || value.contains("!"))
      {
          if(point_id.contains("rmu"))
          {
              RightUpperMotorTotalHasImpairmentNotDueToSci = true;
          }
          else if (point_id.contains("rml"))
          {
              RightLowerMotorTotalHasImpairmentNotDueToSci = true;
          }
          else if (point_id.contains("rt"))
          {
              RightTouchTotalHasImpairmentNotDueToSci = true;
          }
          else if (point_id.contains("rm"))
          {
              RightPrickTotalHasImpairmentNotDueToSci = true;
          }
          else if(point_id.contains("lmu"))
          {
              LeftUpperMotorTotalHasImpairmentNotDueToSci = true;
          }
          else if(point_id.contains("lml"))
          {
              LeftLowerMotorTotalHasImpairmentNotDueToSci = true;
          }
          else if (point_id.contains("lt"))
          {
              LeftTouchTotalHasImpairmentNotDueToSci = true;
          }
          else if (point_id.contains("lp"))
          {
              LeftPrickTotalHasImpairmentNotDueToSci = true;
          }
          
          if(value.contains("NT"))
          {
              numValue = "0";
          }
          else
          {
              numValue = value.substring(0,1);
          }
      }
      return numValue;
  }

  
}
