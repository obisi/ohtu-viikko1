package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url1 = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String url2 = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String bodyText1 = Request.Get(url1).execute().returnContent().asString();
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();

        System.out.println("json-muotoinen data 1:");
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
        }

    }
}
