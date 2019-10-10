package de.ostfalia.algo.ws19.base;

import java.time.LocalDate;

public class Member implements IMember {

	private long key;
	private String name;
	private String prename;
	private LocalDate birthday;
	private Gender gen;
	private KindOfSport sports;

	public Member() {

	}

	public Member(String string) {

		this(string.split(","));
		// new Member(string.split(","));
		// TODO Auto-generated constructor stub
	}

	public Member(String[] string) {
		name = string[0].replace(" ", "");
		prename = string[1].replace(" ", "");
		birthday = LocalDate.parse(string[2].replace(" ", ""));
		gen = Gender.valueOf(string[3].replace(" ", ""));
		sports = KindOfSport.valueOf(string[4].replace(" ", ""));
		key = this.createKey();

	}

	private long createKey() {
		long schluessel = 0;
		schluessel += name.charAt(0) - 'A' + 1;
		schluessel *= 100;
		schluessel += prename.charAt(0) - 'A' + 1;
		schluessel *= 100;
		schluessel += birthday.getDayOfMonth();
		schluessel *= 100;
		schluessel += birthday.getMonthValue();
		schluessel *= 100;
		schluessel += (birthday.getYear() % 100);
		return schluessel;

	}

	/*
	 * Vergleicht ob zwei Objekte die das Interface IMember implementieren der
	 * gleichen Klasse angehören.
	 */
	public int compareTo(IMember o) {
		
			if (this.getClass().getTypeName().equals(o.getClass().getTypeName())) {
				return 0;
			}
		
		return 1;

	}

	@Override
	public long getKey() {
		return key;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return prename;
	}

	@Override
	public Gender getGender() {
		// TODO Auto-generated method stub
		return gen;
	}

	@Override
	public LocalDate getDate() {
		// TODO Auto-generated method stub
		return birthday;
	}

	@Override
	public KindOfSport getKindOfSport() {
		// TODO Auto-generated method stub
		return sports;
	}
	public String toString() {
		return ""+ key + prename + name + birthday+ gen+ sports;
	}

}
