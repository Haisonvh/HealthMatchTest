/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.units;

import java.util.Map;

/**
 *
 * @author HaiSonVH
 */
public class Steps {
    private int id;
    private int question_id;
    private int result_id;
    private Map<String, Integer> answers;

    public Steps() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public Map<String, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, Integer> answers) {
        this.answers = answers;
    }

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }
    
}
