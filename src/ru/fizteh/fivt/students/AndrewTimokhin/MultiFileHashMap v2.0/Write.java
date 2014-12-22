package ru;

import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Write {
	private String filepath;

	public Write() {

	}

	public void write(TableDriver tm, TableWork tb) throws IOException {
		if (tm != null) {
			filepath = tb.homeDir + "\\" + tm.getName();
			File time2 = new File(filepath);
			time2.mkdir();

			for (Integer i = 0; i < 16; i++) {
				if (tm.map[i] != null) {

					for (Integer j = 0; j < 16; j++) {
						String local = j.toString() + ".dat";

						File tmp0 = new File(filepath + "\\" + i.toString());
						tmp0.mkdir();

						File tmp = new File(filepath + "\\" + i.toString()
								+ "\\" + local);
						tmp.createNewFile();
						try (DataOutputStream out = new DataOutputStream(
								new FileOutputStream(filepath + "\\"
										+ i.toString() + "\\" + local))) {
							Set<String> st = tm.map[i].keySet();
							for (String time : st) {

								String tr = local.replaceAll(".dat", "");
								if ((time.hashCode() / 16 % 16) == Integer
										.parseInt(tr)) {

									out.writeInt(time.length());
									out.writeChars(time);
									out.writeInt(((String) tm.map[i].get(time))
											.length());
									out.writeChars((String) tm.map[i].get(time));
								}

							}

						} catch (FileNotFoundException e) {
							System.err.print("Not Found " + e.toString());
						} catch (IOException e) {
							System.err.print("IOException " + e.toString());
						}
						if (tmp.length() == 0) {
							tmp.delete();
						}
					}
					File tmp0 = new File(filepath + "\\" + i.toString());
					if (tmp0.list().length == 0)
						tmp0.delete();
				}
				if (tm.map[i] == null) {
					File delTable = new File(filepath + "\\" + i.toString());

					delTable.delete();
				}
			}
		}
	}
}
