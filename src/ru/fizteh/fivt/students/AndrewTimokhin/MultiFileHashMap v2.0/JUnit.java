package ru.fizteh.fivt.students.AndrewTimokhin.MultiFileHashMap;

import java.io.*;
import java.util.*;

public class JUnit {
	public static void main(String[] args) throws IOException {
		String path = System.getProperty("fizteh.db.dir").toString();
		TableWork tb = new TableWork(0, path);
		Functional fn = new Functional();

		ModeWork mw = new ModeWork();

		mw.usermode(fn, tb);
		Write wr = new Write();
		for (int i = 0; i < tb.t.length; i++) {

			wr.write(tb.t[i], tb);
		}

	};

}