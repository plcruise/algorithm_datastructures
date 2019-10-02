package de.ostfalia.algo.ws19.base;


public enum BuchstabenString {
	a("01"),
	b("02"),
	c("03"),
	d("04"),
	e("05"),
	f("06"),
	g("07"),
	h("08"),
	i("09"),
	j("10"),
	k("11"),
	l("12"),
	m("13"),
	n("14"),
	o("15"),
	p("16"),
	q("17"),
	r("18"),
	s("19"),
	t("20"),
	u("21"),
	v("22"),
	w("23"),
	x("24"),
	y("25"),
	z("26");
	
private String letterNo;
	
	BuchstabenString(String letter){
		this.letterNo=letter;
	}
	
	public String getLetter () {
		return letterNo;
	}
}
