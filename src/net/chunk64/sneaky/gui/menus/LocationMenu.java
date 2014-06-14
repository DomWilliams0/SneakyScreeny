package net.chunk64.sneaky.gui.menus;

import net.chunk64.sneaky.screenshot.Screenie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationMenu extends JMenuItem
{

	public LocationMenu()
	{
		setText("Output");
		setMnemonic('o');
		addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser(Screenie.SAVE_DIRECTORY.getParentFile());
				fileChooser.setDialogTitle("Choose the directory where screenshots will be saved.");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				fileChooser.showOpenDialog(null);

				System.out.println("fileChooser.getSelectedFile() = " + fileChooser.getSelectedFile());
				if (fileChooser.getSelectedFile() != null)
					Screenie.SAVE_DIRECTORY = fileChooser.getSelectedFile();
				System.out.println("set to " + Screenie.SAVE_DIRECTORY);

			}
		});
	}
}