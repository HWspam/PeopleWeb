package com.company;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {


    public static ArrayList<Person> nameAbrev = new ArrayList<>();

    public static void main(String[] args) throws Exception{


        File data = new File("people.csv");
        Scanner scanner = new Scanner(data);

        while(scanner.hasNext()) {
            String yep = scanner.nextLine();
            String [] a = yep.split("\\,");
            Person eachSpot = new Person(a[0] ,a[1], a[2], a[3], a[4],a[5]);
            nameAbrev.add(eachSpot);
        }
        Spark.staticFileLocation("/public");

        Spark.init();
        Spark.get(
                "/",
                (((request, response) -> {
                   String name = request.queryParams("offset");
                    int b = 0;
                    if (name != null && !name.isEmpty()) {
                        b = Integer.parseInt(name);
                    }

                    ArrayList<Person> z = new ArrayList<Person>();
                    for (int i = 0; i<20; i++) {
                        z.add(nameAbrev.get(b+1));
                    }
                    HashMap hm = new HashMap();
                    hm.put("people", z);
                    if (b-20 >= 0){
                        hm.put("previous", b -20);
                    }
                    if (b+20 < nameAbrev.size()) {
                        hm.put("next", b +20);
                    }
                    return new ModelAndView(hm, "home.html");
                })),
                new MustacheTemplateEngine()
        );

        Spark.get(
                "/person",
                (((request, response) -> {

                    HashMap what = new HashMap();
                    int id = Integer.valueOf(request.queryParams("id"));
                    Person ok = nameAbrev.get(id -1);
                    what.put("person", ok);
                    return new ModelAndView(what, "person.html");

                })),
                new MustacheTemplateEngine()

        );



    }

}
