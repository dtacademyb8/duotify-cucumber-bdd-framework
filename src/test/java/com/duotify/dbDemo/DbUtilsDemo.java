package com.duotify.dbDemo;

import com.duotify.utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class DbUtilsDemo {


    public static void main(String[] args) {


        DBUtils.createConnection();


        List<List<Object>> result = DBUtils.getQueryResultAsListOfLists("SELECT * FROM  users limit 5");

        System.out.println(result);

        for (List<Object> row : result) {
            System.out.println(row);
        }

        System.out.println(result.get(0).get(4));

        List<Map<String, Object>> result2 = DBUtils.getQueryResultListOfMaps("SELECT * FROM  users limit 5");

        System.out.println(result2);

        for (Map<String, Object> row : result2) {
            System.out.println(row);
        }

        System.out.println(result2.get(0).get("email"));



        System.out.println(DBUtils.getColumnNames("SELECT * FROM  users limit 5"));
        System.out.println(DBUtils.getRowCount());


    }
}
