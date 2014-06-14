package net.chunk64.sneaky.gui;

import net.chunk64.sneaky.gui.menus.ExitMenu;
import net.chunk64.sneaky.gui.menus.HotkeyMenu;
import net.chunk64.sneaky.gui.menus.LocationMenu;
import net.chunk64.sneaky.gui.menus.OpenExplorerMenu;
import net.chunk64.sneaky.hotkey.HotKeyListener;
import net.chunk64.sneaky.screenshot.Tray;
import net.chunk64.sneaky.util.Preferences;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI
{

	public static GUI INSTANCE;
	private JFrame frame;

	public GUI()
	{
		INSTANCE = this;

		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
		}

		frame = new JFrame("SneakyScreeny");
		frame.setSize(320, 180);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter()
		{
			boolean shown = false;

			@Override
			public void windowClosing(WindowEvent e)
			{
				frame.setState(Frame.ICONIFIED);
				frame.setVisible(false);

				if (!shown)
				{
					Tray.INSTANCE.displayMessage("Sent to tray", "The icon will flash red when you take a screenshot.", TrayIcon.MessageType.INFO);
					shown = true;
				}
			}
		});


		JMenuBar bar = new JMenuBar();
		bar.add(new HotkeyMenu());
		bar.add(new LocationMenu());
		bar.add(new OpenExplorerMenu());
		bar.add(new ExitMenu());

		frame.setJMenuBar(bar);
		frame.pack();
		frame.setVisible(true);

		new Tray();

	}

	public void exit()
	{
		Preferences.INSTANCE.save();
		HotKeyListener.INSTANCE.stop();
		System.exit(0);
	}

	public void unminimize()
	{
		frame.setState(Frame.NORMAL);
		frame.setVisible(true);
	}


}
