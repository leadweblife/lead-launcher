package com.leadweblife.leadlauncher;

/**
 * Created by ankesh on 16/4/16.
 */

public class DBApp {

    String _pname;
    String _name;


    // Empty constructor
    public DBApp(){

    }
    // constructor
    public DBApp(String name, String pname){
        this._pname = pname;
        this._name = name;

    }


    public String get_pname(){
        return this._pname;
    }

    // setting id
    public void set_pname(String id){
        this._pname= id;
    }



    public String get_name(){
        return this._name;
    }

    // setting id
    public void set_name(String id){
        this._name= id;
    }

}

