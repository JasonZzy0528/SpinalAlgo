/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jason_-zZ
 */
public class SpinalAlgoUpdateTests {
    private Connection conn;
    
    public void setConnection(String dbName, String driverName, String serverName, String port, String username, String password){
        
        String connectionURL = "jdbc:mysql://" + serverName + ":" + port + "/" + dbName;
        try {
            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection(connectionURL,username,password);
        } catch (Exception ex) {
            Logger.getLogger(SpinalAlgoUpdateTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int saveSpinalAsiaAssessmentValues(JSONObject context, NeurologyMusclePoints musPoints, String pid) throws SQLException, JSONException
    {
        
        String outputSql = "INSERT INTO ASIA_Impairment_Assessment VALUES (";
        String sqlStr = "";
        String today_str = "";
        
        /*System.out.println("Into saveSpinalAsiaAssessmentValues...");
        System.out.println("assessment_date: " + musPoints.getAssessmentDate());
        System.out.println("assessment_timepoint: " + musPoints.getAssessmentTimepoint());
        System.out.println("comment: " + musPoints.getComment());*/
        
        
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        today_str = sdf.format(now);*/
        
        for (int i=0; i<164; i++)
        {
            sqlStr = sqlStr + "?,";
        }
        outputSql = outputSql + sqlStr + "?)";
        
        String sci_id = getLastAsiaImairmentId(pid,context);
                
        setConnection(context.get("dbName").toString(), context.get("driverName").toString(), context.get("serverName").toString(), context.get("port").toString(), context.get("userName").toString(), context.get("password").toString());
        conn.setAutoCommit(false);
      
        PreparedStatement ps = conn.prepareStatement(outputSql);
        ps.setInt(1, Integer.parseInt(sci_id));
        ps.setInt(2, Integer.parseInt(pid));
        ps.setString(3,musPoints.getAssessmentDate());
        ps.setString(4,musPoints.getAssessmentTimepoint());
        //ps.setString(3, today_str);
        //ps.setString(4, "");
        ps.setString(5, musPoints.getC5rm());
        ps.setString(6, musPoints.getC6rm());
        ps.setString(7, musPoints.getC7rm());
        ps.setString(8, musPoints.getC8rm());
        ps.setString(9, musPoints.getT1rm());
        ps.setString(10, musPoints.getL2rm());
        ps.setString(11, musPoints.getL3rm());
        ps.setString(12, musPoints.getL4rm());
        ps.setString(13, musPoints.getL5rm());
        ps.setString(14, musPoints.getS1rm());
        ps.setString(15, musPoints.getC5lm());
        ps.setString(16, musPoints.getC6lm());
        ps.setString(17, musPoints.getC7lm());
        ps.setString(18, musPoints.getC8lm());
        ps.setString(19, musPoints.getT1lm());
        ps.setString(20, musPoints.getL2lm());
        ps.setString(21, musPoints.getL3lm());
        ps.setString(22, musPoints.getL4lm());
        ps.setString(23, musPoints.getL5lm());
        ps.setString(24, musPoints.getS1lm());
        
        ps.setString(25, musPoints.getC2rt());
        ps.setString(26, musPoints.getC3rt());
        ps.setString(27, musPoints.getC4rt());
        ps.setString(28, musPoints.getC5rt());
        ps.setString(29, musPoints.getC6rt());
        ps.setString(30, musPoints.getC7rt());
        ps.setString(31, musPoints.getC8rt());
        ps.setString(32, musPoints.getT1rt());
        ps.setString(33, musPoints.getT2rt());
        ps.setString(34, musPoints.getT3rt());
        ps.setString(35, musPoints.getT4rt());
        ps.setString(36, musPoints.getT5rt());
        ps.setString(37, musPoints.getT6rt());
        ps.setString(38, musPoints.getT7rt());
        ps.setString(39, musPoints.getT8rt());
        ps.setString(40, musPoints.getT9rt());
        ps.setString(41, musPoints.getT10rt());
        ps.setString(42, musPoints.getT11rt());
        ps.setString(43, musPoints.getT12rt());
        ps.setString(44, musPoints.getL1rt());
        ps.setString(45, musPoints.getL2rt());
        ps.setString(46, musPoints.getL3rt());
        ps.setString(47, musPoints.getL4rt());
        ps.setString(48, musPoints.getL5rt());
        ps.setString(49, musPoints.getS1rt());
        ps.setString(50, musPoints.getS2rt());
        ps.setString(51, musPoints.getS3rt());
        ps.setString(52, musPoints.getS45rt());
        
        ps.setString(53, musPoints.getC2rp());
        ps.setString(54, musPoints.getC3rp());
        ps.setString(55, musPoints.getC4rp());
        ps.setString(56, musPoints.getC5rp());
        ps.setString(57, musPoints.getC6rp());
        ps.setString(58, musPoints.getC7rp());
        ps.setString(59, musPoints.getC8rp());
        ps.setString(60, musPoints.getT1rp());
        ps.setString(61, musPoints.getT2rp());
        ps.setString(62, musPoints.getT3rp());
        ps.setString(63, musPoints.getT4rp());
        ps.setString(64, musPoints.getT5rp());
        ps.setString(65, musPoints.getT6rp());
        ps.setString(66, musPoints.getT7rp());
        ps.setString(67, musPoints.getT8rp());
        ps.setString(68, musPoints.getT9rp());
        ps.setString(69, musPoints.getT10rp());
        ps.setString(70, musPoints.getT11rp());
        ps.setString(71, musPoints.getT12rp());
        ps.setString(72, musPoints.getL1rp());
        ps.setString(73, musPoints.getL2rp());
        ps.setString(74, musPoints.getL3rp());
        ps.setString(75, musPoints.getL4rp());
        ps.setString(76, musPoints.getL5rp());
        ps.setString(77, musPoints.getS1rp());
        ps.setString(78, musPoints.getS2rp());
        ps.setString(79, musPoints.getS3rp());
        ps.setString(80, musPoints.getS45rp());
        
        ps.setString(81, musPoints.getC2lt());
        ps.setString(82, musPoints.getC3lt());
        ps.setString(83, musPoints.getC4lt());
        ps.setString(84, musPoints.getC5lt());
        ps.setString(85, musPoints.getC6lt());
        ps.setString(86, musPoints.getC7lt());
        ps.setString(87, musPoints.getC8lt());
        ps.setString(88, musPoints.getT1lt());
        ps.setString(89, musPoints.getT2lt());
        ps.setString(90, musPoints.getT3lt());
        ps.setString(91, musPoints.getT4lt());
        ps.setString(92, musPoints.getT5lt());
        ps.setString(93, musPoints.getT6lt());
        ps.setString(94, musPoints.getT7lt());
        ps.setString(95, musPoints.getT8lt());
        ps.setString(96, musPoints.getT9lt());
        ps.setString(97, musPoints.getT10lt());
        ps.setString(98, musPoints.getT11lt());
        ps.setString(99, musPoints.getT12lt());
        ps.setString(100, musPoints.getL1lt());
        ps.setString(101, musPoints.getL2lt());
        ps.setString(102, musPoints.getL3lt());
        ps.setString(103, musPoints.getL4lt());
        ps.setString(104, musPoints.getL5lt());
        ps.setString(105, musPoints.getS1lt());
        ps.setString(106, musPoints.getS2lt());
        ps.setString(107, musPoints.getS3lt());
        ps.setString(108, musPoints.getS45lt());
        
        ps.setString(109, musPoints.getC2lp());
        ps.setString(110, musPoints.getC3lp());
        ps.setString(111, musPoints.getC4lp());
        ps.setString(112, musPoints.getC5lp());
        ps.setString(113, musPoints.getC6lp());
        ps.setString(114, musPoints.getC7lp());
        ps.setString(115, musPoints.getC8lp());
        ps.setString(116, musPoints.getT1lp());
        ps.setString(117, musPoints.getT2lp());
        ps.setString(118, musPoints.getT3lp());
        ps.setString(119, musPoints.getT4lp());
        ps.setString(120, musPoints.getT5lp());
        ps.setString(121, musPoints.getT6lp());
        ps.setString(122, musPoints.getT7lp());
        ps.setString(123, musPoints.getT8lp());
        ps.setString(124, musPoints.getT9lp());
        ps.setString(125, musPoints.getT10lp());
        ps.setString(126, musPoints.getT11lp());
        ps.setString(127, musPoints.getT12lp());
        ps.setString(128, musPoints.getL1lp());
        ps.setString(129, musPoints.getL2lp());
        ps.setString(130, musPoints.getL3lp());
        ps.setString(131, musPoints.getL4lp());
        ps.setString(132, musPoints.getL5lp());
        ps.setString(133, musPoints.getS1lp());
        ps.setString(134, musPoints.getS2lp());
        ps.setString(135, musPoints.getS3lp());
        ps.setString(136, musPoints.getS45lp());
        
        ps.setString(137, musPoints.getRightSensory());
        ps.setString(138, musPoints.getLeftSensory());
        ps.setString(139, musPoints.getRightMotor());
        ps.setString(140, musPoints.getLeftMotor());
        ps.setString(141, musPoints.getAsiaComplete());
        ps.setString(142, musPoints.getAsiaImparimentScale());
        ps.setString(143, musPoints.getRightSensoryPatial());
        ps.setString(144, musPoints.getLeftSensoryPatial());
        ps.setString(145, musPoints.getRightMotorPatial());
        ps.setString(146, musPoints.getLeftMotorPatial());
        ps.setString(147, musPoints.getNeurologicalLevelInjury());
        ps.setString(148, musPoints.getVac());
        ps.setString(149, musPoints.getDap());
        ps.setString(150, musPoints.getSingleNeurologicalLevel());
        ps.setString(151, musPoints.getComment());
        
        ps.setString(152, musPoints.getRightMotorTotal());
        ps.setString(153, musPoints.getRightTouchTotal());
        ps.setString(154, musPoints.getRightPrickTotal());
        ps.setString(155, musPoints.getLeftMotorTotal());
        ps.setString(156, musPoints.getLeftTouchTotal());
        ps.setString(157, musPoints.getLeftPrickTotal());
        ps.setString(158, musPoints.getRightUpperMotorTotal());
        ps.setString(159, musPoints.getLeftUpperMotorTotal());
        ps.setString(160, musPoints.getUpperMotorTotal());
        ps.setString(161, musPoints.getRightLowerMotorTotal());
        ps.setString(162, musPoints.getLeftLowerMotorTotal());
        ps.setString(163, musPoints.getLowerMotorTotal());
        ps.setString(164, musPoints.getTouchTotal());
        ps.setString(165, musPoints.getPrickTotal());
        
        int updateTable = ps.executeUpdate();
        conn.commit();
       
        /*Statement stmt = null;
        int update = 0;
        
        try {
            System.out.println("outputSql: " + outputSql);
            stmt = connectToDb(context);
            update = stmt.executeUpdate(outputSql);
        } catch (Exception e) {
            logger.debug("Error: " + e.getMessage());
        }*/
        return updateTable;
    }
    
    public int saveSpinalAsiaAssessmentValues(JSONObject context, NeurologyMusclePoints musPoints, String pid, String modid) throws SQLException, JSONException
    {
        
        String outputSql = "UPDATE ASIA_Impairment_Assessment SET ";
        String sqlStr = "";
        String today_str = "";
        
        /*System.out.println("Into saveSpinalAsiaAssessmentValues...");
        System.out.println("assessment_date: " + musPoints.getAssessmentDate());
        System.out.println("assessment_timepoint: " + musPoints.getAssessmentTimepoint());
        System.out.println("comment: " + musPoints.getComment());*/
        
        
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        today_str = sdf.format(now);*/
        
        /*for (int i=0; i<164; i++)
        {
            sqlStr = sqlStr + "?,";
        }
        outputSql = outputSql + sqlStr + "?)";*/
        
        //String sci_id = getLastAsiaImairmentId(pid,context);
        
        outputSql += this.getAsiaTableParamNames(context);
        outputSql += " WHERE sci_id=? AND asia_impairment_id=?";
        
        System.out.println(outputSql);
        
        String sci_id = modid;
                
        setConnection(context.get("dbName").toString(), context.get("driverName").toString(), context.get("serverName").toString(), context.get("port").toString(), context.get("userName").toString(), context.get("password").toString());
        conn.setAutoCommit(false);
      
        PreparedStatement ps = conn.prepareStatement(outputSql);
        ps.setInt(1, Integer.parseInt(sci_id));
        ps.setInt(2, Integer.parseInt(pid));
        ps.setString(3,musPoints.getAssessmentDate());
        ps.setString(4,musPoints.getAssessmentTimepoint());
        //ps.setString(3, today_str);
        //ps.setString(4, "");
        ps.setString(5, musPoints.getC5rm());
        ps.setString(6, musPoints.getC6rm());
        ps.setString(7, musPoints.getC7rm());
        ps.setString(8, musPoints.getC8rm());
        ps.setString(9, musPoints.getT1rm());
        ps.setString(10, musPoints.getL2rm());
        ps.setString(11, musPoints.getL3rm());
        ps.setString(12, musPoints.getL4rm());
        ps.setString(13, musPoints.getL5rm());
        ps.setString(14, musPoints.getS1rm());
        ps.setString(15, musPoints.getC5lm());
        ps.setString(16, musPoints.getC6lm());
        ps.setString(17, musPoints.getC7lm());
        ps.setString(18, musPoints.getC8lm());
        ps.setString(19, musPoints.getT1lm());
        ps.setString(20, musPoints.getL2lm());
        ps.setString(21, musPoints.getL3lm());
        ps.setString(22, musPoints.getL4lm());
        ps.setString(23, musPoints.getL5lm());
        ps.setString(24, musPoints.getS1lm());
        
        ps.setString(25, musPoints.getC2rt());
        ps.setString(26, musPoints.getC3rt());
        ps.setString(27, musPoints.getC4rt());
        ps.setString(28, musPoints.getC5rt());
        ps.setString(29, musPoints.getC6rt());
        ps.setString(30, musPoints.getC7rt());
        ps.setString(31, musPoints.getC8rt());
        ps.setString(32, musPoints.getT1rt());
        ps.setString(33, musPoints.getT2rt());
        ps.setString(34, musPoints.getT3rt());
        ps.setString(35, musPoints.getT4rt());
        ps.setString(36, musPoints.getT5rt());
        ps.setString(37, musPoints.getT6rt());
        ps.setString(38, musPoints.getT7rt());
        ps.setString(39, musPoints.getT8rt());
        ps.setString(40, musPoints.getT9rt());
        ps.setString(41, musPoints.getT10rt());
        ps.setString(42, musPoints.getT11rt());
        ps.setString(43, musPoints.getT12rt());
        ps.setString(44, musPoints.getL1rt());
        ps.setString(45, musPoints.getL2rt());
        ps.setString(46, musPoints.getL3rt());
        ps.setString(47, musPoints.getL4rt());
        ps.setString(48, musPoints.getL5rt());
        ps.setString(49, musPoints.getS1rt());
        ps.setString(50, musPoints.getS2rt());
        ps.setString(51, musPoints.getS3rt());
        ps.setString(52, musPoints.getS45rt());
        
        ps.setString(53, musPoints.getC2rp());
        ps.setString(54, musPoints.getC3rp());
        ps.setString(55, musPoints.getC4rp());
        ps.setString(56, musPoints.getC5rp());
        ps.setString(57, musPoints.getC6rp());
        ps.setString(58, musPoints.getC7rp());
        ps.setString(59, musPoints.getC8rp());
        ps.setString(60, musPoints.getT1rp());
        ps.setString(61, musPoints.getT2rp());
        ps.setString(62, musPoints.getT3rp());
        ps.setString(63, musPoints.getT4rp());
        ps.setString(64, musPoints.getT5rp());
        ps.setString(65, musPoints.getT6rp());
        ps.setString(66, musPoints.getT7rp());
        ps.setString(67, musPoints.getT8rp());
        ps.setString(68, musPoints.getT9rp());
        ps.setString(69, musPoints.getT10rp());
        ps.setString(70, musPoints.getT11rp());
        ps.setString(71, musPoints.getT12rp());
        ps.setString(72, musPoints.getL1rp());
        ps.setString(73, musPoints.getL2rp());
        ps.setString(74, musPoints.getL3rp());
        ps.setString(75, musPoints.getL4rp());
        ps.setString(76, musPoints.getL5rp());
        ps.setString(77, musPoints.getS1rp());
        ps.setString(78, musPoints.getS2rp());
        ps.setString(79, musPoints.getS3rp());
        ps.setString(80, musPoints.getS45rp());
        
        ps.setString(81, musPoints.getC2lt());
        ps.setString(82, musPoints.getC3lt());
        ps.setString(83, musPoints.getC4lt());
        ps.setString(84, musPoints.getC5lt());
        ps.setString(85, musPoints.getC6lt());
        ps.setString(86, musPoints.getC7lt());
        ps.setString(87, musPoints.getC8lt());
        ps.setString(88, musPoints.getT1lt());
        ps.setString(89, musPoints.getT2lt());
        ps.setString(90, musPoints.getT3lt());
        ps.setString(91, musPoints.getT4lt());
        ps.setString(92, musPoints.getT5lt());
        ps.setString(93, musPoints.getT6lt());
        ps.setString(94, musPoints.getT7lt());
        ps.setString(95, musPoints.getT8lt());
        ps.setString(96, musPoints.getT9lt());
        ps.setString(97, musPoints.getT10lt());
        ps.setString(98, musPoints.getT11lt());
        ps.setString(99, musPoints.getT12lt());
        ps.setString(100, musPoints.getL1lt());
        ps.setString(101, musPoints.getL2lt());
        ps.setString(102, musPoints.getL3lt());
        ps.setString(103, musPoints.getL4lt());
        ps.setString(104, musPoints.getL5lt());
        ps.setString(105, musPoints.getS1lt());
        ps.setString(106, musPoints.getS2lt());
        ps.setString(107, musPoints.getS3lt());
        ps.setString(108, musPoints.getS45lt());
        
        ps.setString(109, musPoints.getC2lp());
        ps.setString(110, musPoints.getC3lp());
        ps.setString(111, musPoints.getC4lp());
        ps.setString(112, musPoints.getC5lp());
        ps.setString(113, musPoints.getC6lp());
        ps.setString(114, musPoints.getC7lp());
        ps.setString(115, musPoints.getC8lp());
        ps.setString(116, musPoints.getT1lp());
        ps.setString(117, musPoints.getT2lp());
        ps.setString(118, musPoints.getT3lp());
        ps.setString(119, musPoints.getT4lp());
        ps.setString(120, musPoints.getT5lp());
        ps.setString(121, musPoints.getT6lp());
        ps.setString(122, musPoints.getT7lp());
        ps.setString(123, musPoints.getT8lp());
        ps.setString(124, musPoints.getT9lp());
        ps.setString(125, musPoints.getT10lp());
        ps.setString(126, musPoints.getT11lp());
        ps.setString(127, musPoints.getT12lp());
        ps.setString(128, musPoints.getL1lp());
        ps.setString(129, musPoints.getL2lp());
        ps.setString(130, musPoints.getL3lp());
        ps.setString(131, musPoints.getL4lp());
        ps.setString(132, musPoints.getL5lp());
        ps.setString(133, musPoints.getS1lp());
        ps.setString(134, musPoints.getS2lp());
        ps.setString(135, musPoints.getS3lp());
        ps.setString(136, musPoints.getS45lp());
        
        ps.setString(137, musPoints.getRightSensory());
        ps.setString(138, musPoints.getLeftSensory());
        ps.setString(139, musPoints.getRightMotor());
        ps.setString(140, musPoints.getLeftMotor());
        ps.setString(141, musPoints.getAsiaComplete());
        ps.setString(142, musPoints.getAsiaImparimentScale());
        ps.setString(143, musPoints.getRightSensoryPatial());
        ps.setString(144, musPoints.getLeftSensoryPatial());
        ps.setString(145, musPoints.getRightMotorPatial());
        ps.setString(146, musPoints.getLeftMotorPatial());
        ps.setString(147, musPoints.getNeurologicalLevelInjury());
        ps.setString(148, musPoints.getVac());
        ps.setString(149, musPoints.getDap());
        ps.setString(150, musPoints.getSingleNeurologicalLevel());
        ps.setString(151, musPoints.getComment());
        
        ps.setString(152, musPoints.getRightMotorTotal());
        ps.setString(153, musPoints.getRightTouchTotal());
        ps.setString(154, musPoints.getRightPrickTotal());
        ps.setString(155, musPoints.getLeftMotorTotal());
        ps.setString(156, musPoints.getLeftTouchTotal());
        ps.setString(157, musPoints.getLeftPrickTotal());
        ps.setString(158, musPoints.getRightUpperMotorTotal());
        ps.setString(159, musPoints.getLeftUpperMotorTotal());
        ps.setString(160, musPoints.getUpperMotorTotal());
        ps.setString(161, musPoints.getRightLowerMotorTotal());
        ps.setString(162, musPoints.getLeftLowerMotorTotal());
        ps.setString(163, musPoints.getLowerMotorTotal());
        ps.setString(164, musPoints.getTouchTotal());
        ps.setString(165, musPoints.getPrickTotal());
        
        ps.setString(166,pid);
        ps.setString(167,modid);
        
        int updateTable = ps.executeUpdate();
        conn.commit();
       
        /*Statement stmt = null;
        int update = 0;
        
        try {
            System.out.println("outputSql: " + outputSql);
            stmt = connectToDb(context);
            update = stmt.executeUpdate(outputSql);
        } catch (Exception e) {
            logger.debug("Error: " + e.getMessage());
        }*/
        return updateTable;
    }
    
    private String getAsiaTableParamNames(JSONObject context){
        
         String paramNamesOut = "";
        String sql = "DESCRIBE ASIA_Impairment_Assessment;";        
        
        Statement statement = null;        
        try{
            statement = connectToDb(context);
            ResultSet rs = statement.executeQuery(sql);
            //ResultSetMetaData rsmd = rs.getMetaData();
            //int colNum = rsmd.getColumnCount();            
            
            while(rs.next()){
                //for(int i=0; i<colNum; i++){
                    paramNamesOut += rs.getString(1) + "=?,";
                //}
            }
            paramNamesOut = paramNamesOut.substring(0,paramNamesOut.length()-1);
        } catch (Exception ex) {      
            Logger.getLogger(SpinalAlgoUpdateTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paramNamesOut;        
    }
    
    private Statement connectToDb(JSONObject context){
        
        Statement stmt = null;
        
        try {
            setConnection(context.get("dbName").toString(), context.get("driverName").toString(), context.get("serverName").toString(), context.get("port").toString(), context.get("userName").toString(), context.get("password").toString());
            stmt = conn.createStatement();
        } catch (Exception ex) {        
            Logger.getLogger(SpinalAlgoUpdateTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stmt;
    }
    
    private String getLastAsiaImairmentId(String p_id , JSONObject context){
        
        String sql = "SELECT asia_impairment_id from ASIA_Impairment_Assessment WHERE SCI_ID = " +  p_id + " ORDER BY asia_impairment_id DESC";        
        String lastId = "1";
        Statement statement = null;
        
        try{
            statement = connectToDb(context);
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                lastId = rs.getString(1);
            }
            int lastIdInt = Integer.parseInt(lastId);
            lastIdInt++;
            lastId = "" + lastIdInt;            
        } catch (Exception ex) {      
            Logger.getLogger(SpinalAlgoUpdateTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastId;        
    }
}

