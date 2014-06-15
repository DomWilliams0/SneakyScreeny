package net.chunk64.sneaky.hotkey;

import net.chunk64.sneaky.util.Utils;

import java.util.Arrays;

public class HotKey
{

	public static HotKey CURRENT;
	public static final int SHIFT = 1;
	public static final int ALT = 8;
	public static final int CTRL = 2;

	/**
	 * ALT, SHIFT, CONTROL
	 */
	private boolean[] mods;
	private char character;
	private int vk;
	private int modifiers;

	public HotKey(char character, boolean[] modifiers)
	{
		this.character = character;
		this.mods = modifiers;
		this.vk = Utils.getVkFromCharacter(character);
		summariseModifiers();
	}

	public HotKey(char character)
	{
		this(character, new boolean[3]);
	}

	public char getCharacter()
	{
		return character;
	}

	public int getModifiers()
	{
		return modifiers;
	}

	public boolean[] getModifiersArray()
	{
		return mods;
	}

	public void setAlt(boolean b)
	{
		mods[0] = b;
		summariseModifiers();
	}

	public void setShift(boolean b)
	{
		mods[1] = b;
		summariseModifiers();
	}

	public void setCtrl(boolean b)
	{
		mods[2] = b;
		summariseModifiers();
	}

	public int getVk()
	{
		return vk;
	}

	public void summariseModifiers()
	{
		int result = 0;
		for (int i = 0; i < mods.length; i++)
			if (mods[i])
				result |= toInt(i);

		modifiers = result;

	}

	private int toInt(int index)
	{
		switch (index)
		{
			case 0:
				return ALT;
			case 1:
				return SHIFT;
			case 2:
				return CTRL;
		}
		return -1;
	}

	@Override
	public String toString()
	{
		return "HotKey{" +
				"mods=" + Arrays.toString(mods) +
				", character=" + character +
				", vk=" + vk +
				", modifiers=" + modifiers +
				'}';
	}


	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;

		if (!(obj instanceof HotKey))
			return false;

		HotKey hotkey = (HotKey) obj;
		return hotkey.character == character && Arrays.equals(hotkey.mods, mods);


	}

	public static HotKey createDefault()
	{
		return new HotKey('+', new boolean[]{false, true, true});
	}


}
