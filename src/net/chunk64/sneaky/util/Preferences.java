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
	private int nextID;

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
		properties.setProperty("save-dir", Screenie.SAVE_DIRECTORY.getAbsolutePath());
		properties.setProperty("next-id", String.valueOf(Screenie.NEXT_ID));

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

	private HotKey loadHotkey(Properties properties, String key)
	{
		try
		{
			return parseHotKey(properties.getProperty(key));

		} catch (Exception e)
		{
			return HotKey.createDefault();
		}

	}

	private Object loadProperty(Properties properties, String key, Object defaultValue)
	{
		try
		{
			return properties.getProperty(key);
		} catch (Exception e)
		{
			return defaultValue;
		}

	}

	private Integer loadInteger(Properties properties, String key, int defaultValue)
	{
		try
		{
			return Integer.parseInt(properties.getProperty(key));
		} catch (Exception e)
		{
			return defaultValue;
		}

	}

	public void load()
	{
		try
		{
			FileInputStream in = new FileInputStream(prefFile);
			properties.load(in);
			in.close();
		} catch (IOException e)
		{
			System.err.println("Could not load prefs");
			e.printStackTrace();
		}

		hotKey = loadHotkey(properties, "hotkey");
		saveDirectory = new File((String) loadProperty(properties, "save-dir", Screenie.DEFAULT_SAVE_DIR));
		nextID = loadInteger(properties, "next-id", 0);

		if (!saveDirectory.exists())
			saveDirectory = Screenie.DEFAULT_SAVE_DIR;

		HotKey.CURRENT = hotKey;
		Screenie.SAVE_DIRECTORY = saveDirectory;
		Screenie.NEXT_ID = nextID;

		System.out.println("loaded: " + hotKey + " | " + saveDirectory.getPath() + " | " + nextID);
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
