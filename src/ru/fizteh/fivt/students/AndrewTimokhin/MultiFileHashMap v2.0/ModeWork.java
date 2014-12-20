package ru.fizteh.fivt.students.AndrewTimokhin.MultiFileHashMap;

import java.io.IOException;
import java.util.*;

class ModeWork {
	Functional fn;

	ModeWork() {
		fn = new Functional();
	}

	int stepMode(String[] array, TableWork f, int index)
			throws NullPointerException, IOException {

		switch (array[0 + index]) {
		case "put":
			if (f.indicator == -1)
				System.out.println("UNDEF");
			else
				fn.put(f.t[f.indicator].map, array[1 + index].hashCode() % 16,
						array[1 + index], array[2 + index]);
			return 2;
		case "get":
			if (f.indicator == -1)
				System.out.println("UNDEF");
			else
				fn.get(f.t[f.indicator].map, array[1 + index].hashCode() % 16,
						array[1 + index]);
			return 1;
		case "remove":
			if (f.indicator == -1)
				System.out.println("UNDEF");
			else
				fn.remove(f.t[f.indicator].map,
						array[1 + index].hashCode() % 16, array[1 + index]);
			return 1;
		case "create":
			f.create(array[1 + index]);

			return 1;
		case "drop":
			f.drop(array[1 + index]);

			return 1;
		case "use":
			if (f.userUse(array[1 + index]) == 0) {

			} else {

			}

			return 1;

		case "showtables":
			fn.showtables(f.t);
			return 1;
		case "exit":
			return -1;
		default:
			System.out.println("Unknow operation. Fail.");
			return -1;
		}
	}

	void usermode(Functional func, TableWork t) throws NullPointerException,
			IOException {

		String str = new String();
		String[] array;
		Scanner rd = new Scanner(System.in);
		while (true) {
			System.out.print("$ ");
			str = rd.nextLine().toString();
			array = str.trim().split(" ");

			if (stepMode(array, t, 0) == -1) {
				break;
			}
		}
		rd.close();

	}

	/*
	 * void interactive( Map<String, Object>[] time , String[] mass) { int
	 * offset = 0; Functional func = new Functional(time); int i = 0; while
	 * (true) { if (i < mass.length) { offset = stepMode(time,mass, func, i); i
	 * += offset; if (offset == -1) { break; } i++; } else { break; } }
	 * 
	 * }
	 */

}
