package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;

public class DealHouseListener implements ActionListener {

    private GameEngine gameEngine;
    private GameEngineCallbackGUI callBack;

    public DealHouseListener(GameEngine gameEngine, GameEngineCallbackGUI callBack) {
        this.gameEngine = gameEngine;
        this.callBack = callBack;
    }

    //initalizes dealing cards for house
    @Override
    public void actionPerformed(ActionEvent e) {        
        new Thread() {
            @Override
            public void run() {
                gameEngine.dealHouse(100);
            }
        }.start();

        callBack.DealCards();
    }

}
