package net.chunk64.sneaky.util;

import net.chunk64.sneaky.hotkey.HotKey;
import net.chunk64.sneaky.screenshot.Screenie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Preferences
{
	public static Preferences INSTANCE;
	private Properties properties;
	private File prefFile;

	private HotKey hotKey;
	private File saveDirectory;

	public Preferences()
	{
		INSTANCE = this;
		properties = new Properties();
		prefFile = new File((System.getProperty("os.name").toUpperCase().contains("WIN") ? System.getenv("AppData") : System.getProperty("user.home") + "/Library/Application Support") + "/SneakyScreeny/prefs.dat");
	}


	public void save()
	{
		String hotkey = Arrays.toString(HotKey.CURRENT.getModifiersArray());

		properties.clear();
		properties.setProperty("hotkey", HotKey.CURRENT.getVk() + "," + hotkey.substring(1, hotkey.length() - 1).replace(" ", ""));
		properties.setProperty("save-dir", saveDirectory.getAbsolutePath());

		try
		{

			prefFile.delete();
			prefFile.createNewFile();

			File parent = prefFile.getParentFile();
			if (!parent.exists())
				parent.mkdirs();


			FileOutputStream out = new FileOutputStream(prefFile);
			properties.store(out, null);
			out.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public void load()
	{
		try
		{
			FileInputStream in = new FileInputStream(prefFile);
			properties.load(in);
			in.close();

			System.out.println("time to start setting");
			hotKey = parseHotKey((String) properties.get("hotkey"));
			saveDirectory = new File((String) properties.get("save-dir"));
			if (!saveDirectory.exists())
				saveDirectory = Screenie.DEFAULT_SAVE_DIR;
		} catch (Exception e)
		{
			// defaults
			System.out.println("loading defaults");
			hotKey = new HotKey('*', new boolean[]{false, true, true});
			saveDirectory = Screenie.DEFAULT_SAVE_DIR;
		}

		HotKey.CURRENT = hotKey;
		if (!saveDirectory.exists())
			saveDirectory.mkdir();
		Screenie.SAVE_DIRECTORY = saveDirectory;

		System.out.println("loaded: " + hotKey + " | " + saveDirectory.getPath());
	}

	private HotKey parseHotKey(String s)
	{
		String[] split = s.split(",");
		boolean[] mods = new boolean[3];
		for (int i = 1; i < split.length; i++)
			mods[i - 1] = Boolean.parseBoolean(split[i]);
		return new HotKey(Utils.getCharacterFromVk(Integer.parseInt(split[0])), mods);
	}

}
