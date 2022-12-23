package com.minecraftserverzone.harrypotter.setup.capabilities;

public interface IPlayerStats {

	void setUsingSkill(int battle);
    int getUsingSkill();
	
    void setBattleTick(int battle);
    int getBattleTick();
    
    void setFlying(int flying);
    int getFlying();
    
    void setClickedSkill(int clickedSkill);
    int getClickedSkill();
    
    void setSelectedHotbar(int selectedHotbar);
    int getSelectedHotbar();
    
    void setHotbarBeforeBattleStance(int setHotbarBeforeBattleStance);
    int getHotbarBeforeBattleStance();
    
    void setSpellsLevel(int[] spellLevel);
    void setSpellLevel(int spellLevel, int num);
    int[] getSpellsLevel();
    
    void setSpellsCD(int[] spellCD);
    void setSpellCD(int spellCD, int num);
    int[] getSpellsCD();
    
}