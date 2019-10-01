package de.ostfalia.algo.ws19.s1;

import de.ostfalia.algo.ws19.base.IManagement;
import de.ostfalia.algo.ws19.base.IMember;
import de.ostfalia.algo.ws19.base.KindOfSport;

public class Management implements IManagement{

	
	
	public Management () {
		
	}
	
	public Management(String[] toManage) {
		
	}

	public Management(String fileName) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insert(IMember member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMember search(long key) {
		// TODO Auto-generated method stub
		return null;
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
