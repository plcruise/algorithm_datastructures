package de.ostfalia.algo.ws19.s1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import de.ostfalia.algo.ws19.base.ChainedList;
import de.ostfalia.algo.ws19.base.IManagement;
import de.ostfalia.algo.ws19.base.IMember;
import de.ostfalia.algo.ws19.base.KindOfSport;
import de.ostfalia.algo.ws19.base.Member;

public class Management implements IManagement {

	private ChainedList memberList;

	public Management() {
		memberList = new ChainedList();
	}

	public Management(String[] copyOf) {
		memberList = new ChainedList();
		for (String str : copyOf) {

			insert(new Member(str));
		}

	}

	public Management(String fileName) throws IOException {

		FileReader flr = new FileReader(fileName);
		BufferedReader bfr = new BufferedReader(flr);
		String line;
		do {
			line = bfr.readLine();
			if (line != null)
				insert(new Member(line));

		} while (line != null);

	}

	@Override
	public int size() {
	
		return memberList.getSize();
		
	}

	@Override
	public void insert(IMember member) {
		memberList.insert(member);
		// TODO Auto-generated method stub

	}

	@Override
	public IMember search(long key) {
		// TODO Auto-generated method stub
		return memberList.search(key);

	}

	@Override
	public IMember search(String name, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size(KindOfSport kindOfSport) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IMember[] discipline(KindOfSport kindOfSport) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMember[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfOperations() {
		// TODO Auto-generated method stub
		return 0;
	}
}
