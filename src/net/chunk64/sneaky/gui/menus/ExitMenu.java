package net.chunk64.sneaky.gui.menus;

import net.chunk64.sneaky.gui.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitMenu extends JMenuItem
{
	public ExitMenu()
	{
		setText("Exit");
		setMnemonic('e');

		addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GUI.INSTANCE.exit();
			}
		});

	}
}
