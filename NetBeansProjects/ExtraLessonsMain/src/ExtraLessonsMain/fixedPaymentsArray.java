/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.util.ArrayList;

/**
 *
 * @author YishaiBasserabie
 */
public class fixedPaymentsArray {
    private fixedPayment arr [];
    private int count = 0;

    public fixedPaymentsArray(ArrayList<String> newList, ArrayList<String> list, int id, String date, String time) {
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();
        paymentsArray pa = new paymentsArray ();
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < la.getLessonDataArray().size(); k++) {
                if (sa.studentIDFromName(list.get(i)) == la.getLessonDataArray().get(k).getStudentID() &&
                        la.getLessonDataArray().get(k).getLessonDate().equals(date) &&
                        la.getLessonDataArray().get(k).getLessonTime().equals(time) && newList.contains(sa.studentIDFromName(list.get(i)))) {
                    
                    arr[count] = new fixedPayment(sa.studentIDFromName(list.get(i)), pa.getIfPaidFromLessonID(la.getLessonDataArray().get(k).getLessonID()));
                    count++;
                }
            }
        }
    }

    public fixedPayment[] getArr() {
        return arr;
    }

    public int getCount() {
        return count;
    }
    
}
