/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.units;


/**
 *
 * @author HaiSonVH
 */
public class Questions {
    private int id;
    private String question;
    private String[] validation;

    public Questions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getValidation() {
        return validation;
    }

    public void setValidation(String[] validation) {
        this.validation = validation;
    }
    
    
}
