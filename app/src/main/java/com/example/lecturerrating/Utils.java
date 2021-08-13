package com.example.lecturerrating;

import java.util.ArrayList;

public class Utils {

    private static Utils utils;
    private static ArrayList<PersonalProfile> allProfiles;

    private Utils(){
        allProfiles = new ArrayList<>();
    }

    public static Utils getInstance(){
        if(utils == null){
            utils = new Utils();
        }
        return utils;
    }

    public void addNewProfile(PersonalProfile profile){
        allProfiles.add(profile);
    }
}
