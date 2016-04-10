package com.springinaction.knights;

/**
 * Created by tianjiyuan on 16/3/14.
 */
public class BraveKnight implements Knight {
    
    private Quest quest;
    public BraveKnight(Quest quest){
        this.quest = quest;
    }
    public void embarkOnRequest() throws QuestException {
        quest.embark();
    }
}
