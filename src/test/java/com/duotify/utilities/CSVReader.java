package com.duotify.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    /**
     *   Utility method that reads the content of a csv file and returns it as Object[][] to be used in @DataProvider
     *
     * @param str - path to a file
     * @return - returns 2D array of Objects that represents the content of the csv file
     */

    public static  List<List<String>>  readFromCSV(String str) throws IOException {

        List<List<String>> result = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader(str));

            String strConv;

            while((strConv=br.readLine()) !=null){
                String[] eachrow = strConv.split(",");
                List<String> strings = Arrays.asList(eachrow);
                result.add(strings);
            }






//

        return result;


    }



}
