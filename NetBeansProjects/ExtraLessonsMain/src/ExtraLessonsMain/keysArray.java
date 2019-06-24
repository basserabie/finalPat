/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class keysArray {
    ConnectDB  db = new ConnectDB();
    private ArrayList<fetchKeys> keyArray = new ArrayList<>();

    public keysArray() {
        ResultSet r = db.getResults("SELECT * FROM lessonKeys");
        try {
            while(r.next()) {
                fetchKeys fks = new fetchKeys(r.getInt("lessonID"), r.getString("lessonKey"));
                keyArray.add(fks);
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
//        this.sortArray();
    }

    public ArrayList<fetchKeys> getKeyArray() {
        return keyArray;
    }

    
    
    public void setKeyArray(ArrayList<fetchKeys> keyArray) {
        this.keyArray = keyArray;
    }
    
    public String getKeyFromLessonID(int id) {
        String key = "";
        for (int i = 0; i < this.keyArray.size(); i++) {
            if (this.keyArray.get(i).getLessonID() == id) {
                key = this.keyArray.get(i).getLessonKey();
            }
        }
        return key;
    }
    
    public int getFirstLessonIDFromKey(String key) {
        int id = 0;
        for (int i = 0; i < this.keyArray.size(); i++) {
            if (this.keyArray.get(i).getLessonKey().equals(key)) {
                id = this.keyArray.get(i).getLessonID();
            }
        }
        return id;
    }
    
    public String getStartTimeFromKey(String key) {
        lessonDataArray la = new lessonDataArray();
        String startTime = "";
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (this.getKeyArray().get(i).getLessonKey().equals(key)) {
                startTime = la.getLessonDataArray().get(i).getLessonTime();
            }
        }
        return startTime;
    }
    
    public int getLessonIDFromKey(String key) {
        int id = 0;
        for (int i = 0; i < this.keyArray.size(); i++) {
            if (this.keyArray.get(i).getLessonKey().equals(key)) {
                id = this.keyArray.get(i).getLessonID();
            }
        }
        return id;
    }
    
    public String getDateFromKey(String key) {
        lessonDataArray la = new lessonDataArray();
        String date = "";
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (this.getKeyArray().get(i).getLessonKey().equals(key)) {
                date = la.getLessonDataArray().get(i).getLessonDate();  //FIX HERE IF DOESNT WORK
            }
        }
        return date;
    }
    
    public String getEndTimeFromKey(String key) {
        lessonDataArray la = new lessonDataArray();
        String startTime = "";
        String endTime = "";
        
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (this.getKeyArray().get(i).getLessonKey().equals(key)) {
                startTime = la.getStartTimeFromIndex(i);
                endTime = la.getEndTime(startTime, la.getLessonDataArray().get(i).getLessonDuration());
            }
        }
        return endTime;
    }
    
    public String getKeyFromDateAndTime(String date, String time) {
        lessonDataArray la = new lessonDataArray();
        String key = "";
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (la.getLessonDataArray().get(i).getLessonDate().equals(date) &&
                    la.getLessonDataArray().get(i).getLessonTime().equals(time)) {
                key = this.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID());
            }
        }
        return key;
    }
    
    public void sortArray() {
        Calendar p1 = Calendar.getInstance();
        Calendar p2 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
        for (int i = 0; i < this.keyArray.size(); i++) {
            try {
                if (i < this.keyArray.size()-1) {
                    p1.setTime(sdf.parse(this.getDateFromKey(this.keyArray.get(i).getLessonKey()) + " " + this.getStartTimeFromKey(this.keyArray.get(i).getLessonKey())));
                    p2.setTime(sdf.parse(this.getDateFromKey(this.keyArray.get(i+1).getLessonKey()) + " " + this.getStartTimeFromKey(this.keyArray.get(i+1).getLessonKey())));
                }
                fetchKeys temp;
                if (p1.after(p2)) {
                    temp = this.keyArray.get(i);
                    this.keyArray.set(i, this.keyArray.get(i+1));
                    this.keyArray.set(i+1, temp);
                }
            } catch (ParseException ex) {
                Logger.getLogger(keysArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int [] getLessonIDSFromKey(String key) {
        ArrayList<Integer> idList = new ArrayList<>();
        for (int i  = 0; i < this.keyArray.size(); i++) {
            if (this.keyArray.get(i).getLessonKey().equals(key)) {
                idList.add(this.keyArray.get(i).getLessonID());
            }
        }
        int idArray [] = idList.stream().mapToInt(i -> i).toArray();
        return idArray;
    }
    public void deleteKeyFromDateAndTime(String date, String time) {
        ConnectDB db = new ConnectDB();
        String keyToDelete = this.getKeyFromDateAndTime(date, time);
        for (int i = 0; i < this.keyArray.size(); i++) {
            if (this.keyArray.get(i).getLessonKey().equals(keyToDelete)) {
                String deleteKey = "DELETE * FROM lessonKeys WHERE lessonKey = '" + keyToDelete + "'";
                try {
                    db.UpdateDatabase(deleteKey);
                } catch (SQLException ex) {
                    Logger.getLogger(keysArray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
