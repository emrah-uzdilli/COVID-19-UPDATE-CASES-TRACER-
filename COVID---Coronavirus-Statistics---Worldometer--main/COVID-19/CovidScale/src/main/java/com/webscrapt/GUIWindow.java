package com.webscrapt;


import javax.swing.*;

import com.webscrapt.WebScraper.*;

import java.awt.*;
import java.awt.event.*;

public class GUIWindow {

    private JFrame windowFrame;
    private JPanel windowPanel;
    private JLabel numCoronaCase;
    private JLabel numDeath;
    private JLabel numRecovered;
    private JLabel update;
    private JButton refresh;
    private WebScraper web;

    public GUIWindow() {
        web = new WebScraper();

        try {
            web.getData();
        } catch (Exception IOException) {
            System.out.println("IOException found in GUIWindow calling .getData()");
        }

        windowFrame = new JFrame("CoVid 19 - Tracker");
        windowPanel = new JPanel();
        numCoronaCase = new JLabel(web.getTotalCoronaCases());
        numDeath = new JLabel(web.getTotalDeaths());
        numRecovered = new JLabel(web.getTotalRecoveries());
        update = new JLabel(web.getLastUpdate());
        refresh = new JButton("Refresh");
    }

    public void setUpGUI() {
        Container cp = windowFrame.getContentPane();
        cp.setLayout(new BorderLayout());

        JLabel text = new JLabel("  The Current CoronaVirus Situation", SwingConstants.CENTER);
        text.setFont(new Font("SansSerif", Font.BOLD, 50));
        cp.add(text, BorderLayout.NORTH);

        cp.add(windowPanel, BorderLayout.CENTER);
        windowPanel.setLayout(new GridLayout(4, 2));
        windowPanel.add(new JLabel(" Number of Corona Cases: "));
        windowPanel.add(numCoronaCase);
        System.out.println(" Number of Corona Cases: " + web.getTotalCoronaCases());
        windowPanel.add(new JLabel(" Number of Corona Deaths: "));
        windowPanel.add(numDeath);
        System.out.println(" Number of Corona Deaths: " + web.getTotalDeaths());
        windowPanel.add(new JLabel(" Number of Recovered: "));
        windowPanel.add(numRecovered);
        System.out.println(" Number of Corona Recovered: " + web.getTotalRecoveries());
        windowPanel.add(new JLabel(" Last Updated: "));
        windowPanel.add(update);
        System.out.println(" Number of Corona Updated: " + web.getLastUpdate());

        cp.add(refresh, BorderLayout.SOUTH);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.pack();
        windowFrame.setLocationRelativeTo(null);
        windowFrame.setVisible(true);
    }

    public void setUpButtonListener() {
        buttonListener bl = new buttonListener();
        refresh.addActionListener(bl);
    }

    private class buttonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                web.getData();
            } catch (Exception IOException) {
                System.out.println("IOException found in buttonListener calling .getData()");
            }

            numCoronaCase.setText(web.getTotalCoronaCases());
            numDeath.setText(web.getTotalDeaths());
            numRecovered.setText(web.getTotalRecoveries());
            update.setText(web.getLastUpdate());

        }
    }

}
