package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }
        
        String url3 = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String url4 = "https://studies.cs.helsinki.fi/courses/rails2018/stats";
        
        String statsResponse1= Request.Get(url3).execute().returnContent().asString();
        String statsResponse2 = Request.Get(url4).execute().returnContent().asString();
        
        JsonParser parser = new JsonParser();
        
        JsonObject parsittuData1 = parser.parse(statsResponse1).getAsJsonObject();
        JsonObject parsittuData2 = parser.parse(statsResponse2).getAsJsonObject();
        
        System.out.println("\nparsittudata1:");  
        System.out.println(parsittuData1);
        
        System.out.println("\nparsittudata2:");  
        System.out.println(parsittuData2);
        
        System.out.println("\n\n" + jsonToString(parsittuData1));
        

        String url1 = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String url2 = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String bodyText1 = Request.Get(url1).execute().returnContent().asString();
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();

        System.out.println("\n\njson-muotoinen data 1:");
        System.out.println(bodyText1);

        System.out.println("json-muotoinen data 2:");
        System.out.println(bodyText2);

        

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText1, Submission[].class);
        Course[] cours = mapper.fromJson(bodyText2, Course[].class);

        System.out.println("opiskelijanumero " + studentNr + "\n");

        for (Course cour : cours) {
            System.out.println(cour.getFullname() + "\n");
            int k = 0;
            int t = 0;
            for (Submission submission : subs) {

                if (cour.getName().equals(submission.getCourse())) {
                    k += submission.getExercises().length;
                    t += submission.getHours();
                    System.out.println("viikko " + submission.getWeek() + ":");
                    System.out.println(" tehtyjä tehtäviä " + submission.getExercises().length
                            + "/" + cour.getExercises()[submission.getWeek()] + " aikaa kului "
                            + submission.getHours() + " tehdyt tehtävät: " + Arrays.toString(submission.getExercises()));

                }               
            }
             System.out.println("\n yhteensä: " + k + "/" + cour.nmE() + " " + t + " tuntia");
             
             if(cour.getName().equals("rails2018")){
                 System.out.println(jsonToString(parsittuData2));
             }
             if(cour.getName().equals("ohtu2018")){
                 System.out.println(jsonToString(parsittuData1));
             }
        }

    }
    
    
    public static String jsonToString(JsonObject course){
        int completedExercises = 0, hours = 0, exercises = 0;
        
        for(String key : course.keySet()){
            completedExercises += course.get(key).getAsJsonObject().get("students").getAsInt();
            hours += course.get(key).getAsJsonObject().get("hour_total").getAsInt();
            exercises += course.get(key).getAsJsonObject().get("exercise_total").getAsInt();
        }
        String s = "kurssilla yhteensä " + completedExercises + " palautusta, palautettuja tehtäviä " +
                exercises + " kpl, aikaa käytetty yhteensä " + hours + " tuntia";
        return s;
    }
}
