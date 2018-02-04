package com.tony.engine.util;

import java.util.HashMap;

import com.tony.engine.gui.Sequence;
import com.tony.engine.gui.SpriteSheet;

import java.util.ArrayList;

public abstract class Resources{
	public static final SpriteSheet SHEET_SAMUS = new SpriteSheet("spritesheets/samus.png","xml/samus.xml");
	
	public static final Sequence SEQ_SAMUS_WALK_LEFT = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_154.png");
		add("samus_155.png");
		add("samus_156.png");
		add("samus_157.png");
		add("samus_158.png");
		add("samus_159.png");
		add("samus_160.png");
		add("samus_161.png");
		add("samus_162.png");
		add("samus_163.png");
	}});

	public static final Sequence SEQ_SAMUS_WALK_RIGHT = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_165.png");
		add("samus_166.png");
		add("samus_167.png");
		add("samus_168.png");
		add("samus_169.png");
		add("samus_170.png");
		add("samus_171.png");
		add("samus_172.png");
		add("samus_173.png");
		add("samus_174.png");
	}});

	public static final Sequence SEQ_SAMUS_IDLE = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_01.png");
	}});

	public static final Sequence SEQ_SAMUS_PROJECTILE = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_09.png");
		add("samus_10.png");
		add("samus_11.png");
		add("samus_12.png");
		add("samus_13.png");
		add("samus_14.png");
	}});

	public static final Sequence SEQ_SAMUS_PROJECTILE2 = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_22.png");
		add("samus_23.png");
		add("samus_24.png");
		add("samus_25.png");
		add("samus_26.png");
		add("samus_27.png");
	}});

	public static final Sequence SEQ_SAMUS_IDLE_LEFT = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_70.png");
	}});

	public static final Sequence SEQ_SAMUS_IDLE_RIGHT = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_02.png");
	}});

	public static final Sequence SEQ_SAMUS_ROLL_RIGHT = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_279.png");
		add("samus_280.png");
	}});
	public static final Sequence SEQ_SAMUS_ROLL_LEFT = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_290.png");
		add("samus_291.png");
	}});
	
	public static final Sequence SEQ_SAMUS_ROLL_IDLE = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_281.png");
	}});
	public static final Sequence SEQ_SAMUS_ROLL = new Sequence(SHEET_SAMUS,new ArrayList<String>(){{
		add("samus_281.png");
		add("samus_282.png");
		add("samus_283.png");
		add("samus_284.png");
		add("samus_285.png");
		add("samus_286.png");
		add("samus_287.png");
		add("samus_288.png");
	}});
}