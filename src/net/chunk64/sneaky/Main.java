package net.chunk64.sneaky;

import net.chunk64.sneaky.gui.GUI;
import net.chunk64.sneaky.hotkey.HotKeyListener;
import net.chunk64.sneaky.util.Preferences;

import java.awt.*;

public class Main
{


	public static void main(String[] args) throws AWTException
	{
				new Preferences().load();
				new GUI();
		//
		////		HotKey.CURRENT = new HotKey('p');
		////		HotKey.CURRENT.setAlt(true);
				new HotKeyListener();



	}


}
