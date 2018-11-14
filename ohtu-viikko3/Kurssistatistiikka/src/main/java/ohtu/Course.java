/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

/**
 *
 * @author bisi
 */
public class Course {
    
    private String fullName;
    private String name;
    private int[] exercises;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFullname() {
        return fullName;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
    }
    
    public int nmE(){
        int k = 0;
        for (int i : exercises){
            k+=i;
        }
        return k;
    }
}
