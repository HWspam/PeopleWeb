package com.company;

import spark.Spark;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


    public static ArrayList<Person> nameAbrev = new ArrayList<>();

    public static void main(String[] args) throws Exception{


        File data = new File("people.csv");
        Scanner scanner = new Scanner(data);

        while(scanner.hasNext()) {
            String yep = scanner.nextLine();
            String [] a = yep.split(",");
            Person eachSpot = new Person(Integer.parseInt(a[0]), a[1], a[2], a[3], a[4],a[5]);
            nameAbrev.add(eachSpot);
        }
        Spark.staticFileLocation("/public");

        Spark.init();
        Spark.get(
                "/",
                (((request, response) -> {
                   String name =



                    return "";

                }))

        );
        Spark.get(
                "/person",
                (((request, response) -> {

                    HashMap what = new HashMap();
                    Person ok = Person.setId();
                    Person singlePerson = nameAbrev.get(ok);

                    what.put(ok, singlePerson);


                    return "";
                }))
        );



    }

}
