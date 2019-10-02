package de.ostfalia.algo.ws19.base;

import java.time.LocalDate;

public class Member implements IMember{

	private long key;
	private String name;
	private String prename;
	private LocalDate birthday;
	private Gender gen;	
	private KindOfSport sports;
	private Member next;
	private static Member last = null;
	
	public Member(String string) {
		
		this(string.split(","));
		//new Member(string.split(","));
		// TODO Auto-generated constructor stub
	}

	public Member (String[] string) {
		name=string[0].replace(" ", "");
		prename=string[1].replace(" ", "");
		birthday= LocalDate.parse(string[2].replace(" ", ""));
		gen = Gender.valueOf(string[3].replace(" ", ""));
		sports= KindOfSport.valueOf(string[4].replace(" ", ""));
		key=this.createKey();
		next=last;
		last=this;
		
	}
	@Override
	
	private  long createKey() {
		String schluessel = "";
		Parse.Longschluessel;
	}
	public int compareTo(IMember o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getKey() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gender getGender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KindOfSport getKindOfSport() {
		// TODO Auto-generated method stub
		return null;
	}

}
