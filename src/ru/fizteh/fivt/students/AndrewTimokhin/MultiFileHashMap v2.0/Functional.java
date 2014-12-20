package ru.fizteh.fivt.students.AndrewTimokhin.MultiFileHashMap;

import java.io.IOException;
import java.util.*;

public class Functional {

	void put(Map<String, Object>[] map, int i, String key, String value) {
		if (i == -1) {
			System.out.println("NotDefBD#");
			return;
		}

		if (map[i].containsKey(key)) {
			System.out.println("overwrite");
			System.out.println(map[i].get(key));
		} else {
			System.out.println("new");
		}
		map[i].put(key, value);
	}

	void get(Map<String, Object>[] map, int i, String key) {
		if (i == -1) {
			System.out.println("NotDefBD#");
			return;
		}
		if (map[i] != null) {
			if (map[i].containsKey(key)) {
				System.out.println("found");
				System.out.println(map[i].get(key));
			} else {
				System.out.println("not found");
			}
		}
	}

	void remove(Map<String, Object>[] map, int i, String key) {
		if (i == -1) {
			System.out.println("NotDefBD#");
			return;
		}
		if (map[i] != null) {
			if (map[i].containsKey(key)) {
				map[i].remove(key);
			} else {
				System.out.println("not found");
			}
		}
	}

	void showtables(TableDriver[] td) throws NullPointerException, IOException {
		for (TableDriver time : td) {

			System.out.println("table " + time.getName() + " " + time.size());
		}
	}
}
