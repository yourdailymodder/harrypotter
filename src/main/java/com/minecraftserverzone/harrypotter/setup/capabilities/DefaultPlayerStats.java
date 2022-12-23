package com.minecraftserverzone.harrypotter.setup.capabilities;


public class DefaultPlayerStats implements IPlayerStats {

    private int battleStance, flying, selectedHotbar, setHotbarBeforeBattleStance, clickedSkill, usingSkill;
    private int[] spellsLevel;
    private int[] spellsCD;
    
    @Override
    public void setBattleTick(int battleTick) {
        this.battleStance = battleTick;
    }

    @Override
    public int getBattleTick() {
        return battleStance;
    }

	@Override
	public void setFlying(int flying) {
		this.flying = flying;
	}

	@Override
	public int getFlying() {
		return flying;
	}

	@Override
	public void setSelectedHotbar(int selectedHotbar) {
		this.selectedHotbar = selectedHotbar;
	}

	@Override
	public int getSelectedHotbar() {
		return selectedHotbar;
	}

	@Override
	public void setHotbarBeforeBattleStance(int setHotbarBeforeBattleStance) {
		this.setHotbarBeforeBattleStance = setHotbarBeforeBattleStance;
	}

	@Override
	public int getHotbarBeforeBattleStance() {
		return setHotbarBeforeBattleStance;
	}

	@Override
	public void setClickedSkill(int clickedSkill) {
		this.clickedSkill = clickedSkill;
	}

	@Override
	public int getClickedSkill() {
		return this.clickedSkill;
	}

	@Override
	public void setUsingSkill(int usingSkill) {
		this.usingSkill = usingSkill;
	}

	@Override
	public int getUsingSkill() {
		return this.usingSkill;
	}

	@Override
	public void setSpellsLevel(int[] spellLevel) {
		this.spellsLevel = spellLevel;
	}

	@Override
	public int[] getSpellsLevel() {
		return spellsLevel;
	}

	@Override
	public void setSpellLevel(int spellLevel, int num) {
		this.spellsLevel[num] = spellLevel;
	}

	@Override
	public void setSpellsCD(int[] spellCD) {
		this.spellsCD = spellCD;
	}

	@Override
	public int[] getSpellsCD() {
		return spellsCD;
	}

	@Override
	public void setSpellCD(int spellCD, int num) {
		this.spellsCD[num] = spellCD;
	}

}