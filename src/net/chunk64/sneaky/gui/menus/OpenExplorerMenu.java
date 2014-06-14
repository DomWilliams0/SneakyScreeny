package net.chunk64.sneaky.gui.menus;

import net.chunk64.sneaky.screenshot.Screenie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OpenExplorerMenu extends JMenuItem
{

	public OpenExplorerMenu()
	{
		setText("Explorer");
		setMnemonic('O');

		addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Desktop.getDesktop().open(Screenie.SAVE_DIRECTORY == null ? Screenie.DEFAULT_SAVE_DIR : Screenie.SAVE_DIRECTORY);
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}


			}
		});


	}
}
