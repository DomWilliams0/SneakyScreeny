package net.chunk64.sneaky.screenshot;

import net.chunk64.sneaky.gui.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tray
{
	public static Tray INSTANCE;
	private static ExecutorService executorService;
	private static BufferedImage[] states;

	private TrayIcon icon;

	static
	{
		Color[] colours = new Color[]{Color.CYAN, Color.RED};
		states = new BufferedImage[colours.length];
		for (int i = 0; i < states.length; i++)
		{
			BufferedImage state = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = state.createGraphics();
			g.setColor(colours[i]);
			g.fillRect(0, 0, state.getWidth(), state.getHeight());
			states[i] = state;
		}
	}

	public Tray()
	{
		if (!SystemTray.isSupported())
		{
			System.err.println("Could not create system tray icon");
			return;
		}

		SystemTray tray = SystemTray.getSystemTray();

		icon = new TrayIcon(states[0], "SneakyScreeny");
		if (INSTANCE != null)
			tray.remove(icon);
		else
			executorService = Executors.newSingleThreadExecutor();
		INSTANCE = this;

		PopupMenu menu = new PopupMenu();
		MenuItem menuItem = new MenuItem("Exit");
		menu.add(menuItem);
		icon.setPopupMenu(menu);
		menuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GUI.INSTANCE.exit();
			}
		});

		try
		{
			tray.add(icon);
			icon.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if (e.getClickCount() == 2)
						GUI.INSTANCE.unminimize();
				}
			});
		} catch (AWTException e)
		{
			System.err.println("Could not add system tray icon");
			e.printStackTrace();
		}
	}

	public void displayMessage(String caption, String text, TrayIcon.MessageType messageType)
	{
		icon.displayMessage(caption, text, messageType);
	}


	public void flash()
	{
		icon.displayMessage("", null, TrayIcon.MessageType.INFO);
		executorService.submit(new Flash());
	}

	private class Flash implements Runnable
	{

		@Override
		public void run()
		{
			int state = 0;

			for (int i = 0; i < 5; i++)
			{
				Tray.INSTANCE.icon.setImage(states[1]);
				try
				{
					Thread.sleep(200);
				} catch (InterruptedException e)
				{
					System.err.println("failed on " + i);
					return;
				}
				state = state == 0 ? 1 : 0;
			}

		}
	}

}
