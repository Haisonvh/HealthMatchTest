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
public class Data {
    private Questions[] questions;
    private Steps[] steps;
    private Results[] results;

    public Data() {
    }

    public Questions[] getQuestions() {
        return questions;
    }

    public void setQuestions(Questions[] questions) {
        this.questions = questions;
    }

    public Steps[] getSteps() {
        return steps;
    }

    public void setSteps(Steps[] steps) {
        this.steps = steps;
    }

    public Results[] getResults() {
        return results;
    }

    public void setResults(Results[] results) {
        this.results = results;
    }
    
    
}
