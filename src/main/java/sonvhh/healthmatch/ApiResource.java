/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.healthmatch;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import sonvhh.units.Answer;
import sonvhh.units.Data;
import sonvhh.units.Questions;
import sonvhh.units.Results;
import sonvhh.units.Steps;

/**
 * REST Web Service
 *
 * @author HaiSonVH
 */
@Path("api")
public class ApiResource {

    @Context
    private ServletContext context;
    
    private Data data;
    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
        
    }

    /**
     * @return the question of the first step
     */
    @GET
    @Path("begin")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getJson() {        
        getData();
        return getResponseForNextStep(1);        
    }

    /**
     * 
     * @param content Json string of Answer.
     * @return the next question or the result
     */
    @POST
    @Path("answer")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String postJson(Answer content) {
        getData();        
        //find the step data
        Steps step = Arrays.stream(data.getSteps())
                .filter(x -> x.getId() == content.getStep_id())
                .findFirst()
                .orElse(null);
        //no step id in database
        if(step == null){
            JsonObject element = new JsonObject();
            element.addProperty("error", "There is no step "+ content.getStep_id());
            return element.toString();
        } else {
            //no question for current step or no match answer
            if (step.getAnswers() == null || !step.getAnswers().containsKey(content.getAnswer())){
                JsonObject element = new JsonObject();
                element.addProperty("error", "This step does not contain the answer: "+ content.getAnswer());
                return element.toString();
            } else {
                int nextStep = step.getAnswers().get(content.getAnswer());
                return getResponseForNextStep(nextStep);
            }
        }        
    }
    
    /**
     * Read and parser json file into Object
     */
    private void getData(){
        String dataPath = context.getRealPath("/WEB-INF/questions.json");
        Gson gson = new Gson();
        try (Reader reader = new FileReader(dataPath)) {
            // Convert JSON File to Java Object
            data = gson.fromJson(reader, Data.class);
	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Based on the step id, get the response string
     * @param nextStepId
     * @return json String of question or result
     */
    private String getResponseForNextStep(int nextStepId){
        JsonObject response = new JsonObject();
        //find the step data
        Steps step = Arrays.stream(data.getSteps())
                .filter(x -> x.getId() == nextStepId)
                .findFirst()
                .orElse(null);
        //response question
        if(step.getQuestion_id() != 0){
            Questions question = Arrays.stream(data.getQuestions())
                .filter(x -> x.getId() == step.getQuestion_id())
                .findFirst()
                .orElse(null);
        
            //wrapping to match the required
            
            JsonObject element = new JsonObject();
            element.addProperty("step_id", step.getId());
            element.addProperty("question", question.getQuestion());
            JsonArray answers = new JsonArray();
            for (String temp:question.getValidation()){
                answers.add(temp);
            }
            element.add("answers",answers);
            response.add("question",element);
        } else { //response result
            Results result = Arrays.stream(data.getResults())
                .filter(x -> x.getId() == step.getResult_id())
                .findFirst()
                .orElse(null);
        
            //wrapping to match the required
            
            JsonObject element = new JsonObject();
            element.addProperty("name", result.getName());
            element.addProperty("description", result.getDescription());
            response.add("match",element);
        } 
        return response.toString();
    }
}
