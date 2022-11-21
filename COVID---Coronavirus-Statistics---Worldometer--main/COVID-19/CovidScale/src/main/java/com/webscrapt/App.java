package com.webscrapt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class App
{

    public static void main( String[] args ) throws Exception
    {


        GetTable table=new GetTable();
        table.getTable();
        GUIWindow gui = new GUIWindow();
        gui.setUpGUI();
        gui.setUpButtonListener();

    }
}
