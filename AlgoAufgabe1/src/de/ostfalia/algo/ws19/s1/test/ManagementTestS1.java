package de.ostfalia.algo.ws19.s1.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import de.ostfalia.algo.ws19.base.IManagement;
import de.ostfalia.algo.ws19.base.IMember;
import de.ostfalia.algo.ws19.base.KindOfSport;
import de.ostfalia.algo.ws19.base.Member;
import de.ostfalia.algo.ws19.s1.Management;
import de.ostfalia.junit.annotations.AfterMethod;
import de.ostfalia.junit.annotations.TestDescription;
import de.ostfalia.junit.base.IMessengerRules;
import de.ostfalia.junit.base.ITraceRules;
import de.ostfalia.junit.common.Enumeration;
import de.ostfalia.junit.common.Version;
import de.ostfalia.junit.conditional.PassTrace;
import de.ostfalia.junit.rules.MessengerRule;
import de.ostfalia.junit.rules.RuleControl;
import de.ostfalia.junit.rules.TraceRule;
import de.ostfalia.junit.runner.TopologicalSortRunner;

/**
 * 
 * @author M. Gruendel
 *
 */
@RunWith(TopologicalSortRunner.class)
public class ManagementTestS1 {
	
	public RuleControl opt = RuleControl.NONE;
	public IMessengerRules messenger = MessengerRule.newInstance(opt);	
	public ITraceRules trace = TraceRule.newInstance(opt);
		

	@Rule
	public TestRule chain = RuleChain
							.outerRule(trace)	
							.around(messenger);
	
	@Rule
    public TestRule timeout = new DisableOnDebug(
                              new Timeout(1000, TimeUnit.MILLISECONDS));
	
	/**
	 * Datensatz mit 10 Eintraegen als Testdaten fuer die JUnit-Tests.
	 */
	public String[] data10 = {"Hueber, Uta, 1922-10-15, F, HANDBALL",		//[0]
					   		  "Muller, Uta, 1964-01-28, F, HANDBALL",		//[1]
					   		  "Fried, Heike, 1997-12-14, F, RUDERN",		//[2]
					   		  "Meyer, Tanja, 1946-04-16, F, HANDBALL",		//[3]
					   		  "Brauer, Mandy, 1933-07-21, F, FUSSBALL",		//[4]
					   		  "Weiss, Ulrich, 1987-06-09, M, FUSSBALL",		//[5]
					   		  "Bohm, Stephanie, 1931-10-22, F, HANDBALL",	//[6]
					   		  "Hueber, Annett, 1936-11-19, F, RUDERN",		//[7]
					   		  "Hertz, Thomas, 1946-10-01, M, HANDBALL",		//[8]
					   		  "Scholz, Anja, 1933-01-12, F, RUDERN"};		//[9]
	
	/**
	 * Schluesselwerte fuer den Datensatz data (10 Eintraege).
	 */
	public long[] keys10 = {82115101922L, 132128011964L, 60814121997L, 132016041946L, 
				     	    21321071933L, 232109061987L, 21922101931L, 80119111936L,
				     	    82001101946L, 190112011933L};
	
	int[] all = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
	int[] ofs = {-1, +1};

	@Before
	public void setUp() throws Exception {
		assertTrue(Version.INCOMPATIBLE, Version.request("4.5.2"));
		PassTrace.preProcessorDefaults();		
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Standard-Konstruktoraufruf Management().<br> 
	 * 		Nach dem Aufruf des Standard-Konstruktors duerfen sich keine Datensaetze
	 *      in der Mitgliederverwaltung befinden.</li>
	 *	<li>Erwartet: Anzahl der Datensaetze = 0.</li>
	 * </ul>
	 */
	@Test
	@TestDescription("Testen des Kontruktors().")
	public void testKonstruktorOhneParameter() {
		trace.add("Konstruktoraufruf Management().");
		IManagement mgnt = new Management(); 		
		evaluate(mgnt);
	}
	
	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit einem Datensatz.<br> 
	 * 		Nach dem Aufruf des Konstruktors muss sich genau ein Datensatz (data10[0])
	 * 		in der Mitgliederverwaltung befinden.</li>
	 *	<li>Erwartet: data10[0] in der Mitgliederverwaltung.</li>
	 * </ul>
	 */
	@Test
	@AfterMethod("testKonstruktorOhneParameter")
	@TestDescription("Testen des Kontruktors(String[]) mit einem Datensatz.")
	public void testKonstruktorEinDatensatz() {
		trace.add("Konstruktoraufruf Management(String[]).");
		IManagement mgnt = new Management(Arrays.copyOf(data10, 1));
		evaluate(mgnt, 0);		
	}
	
	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen.<br> 
	 * 		Nach dem Aufruf des Konstruktors mussen sich alle 10 Datensaetze in der 
	 * 		Mitgliederverwaltung befinden.</li>
	 *	<li>Erwartet: data10[0] bis data10[9] in der Mitgliederverwaltung.</li>
	 * </ul>
	 */
	@Test
	@AfterMethod("testKonstruktorEinDatensatz")
	@TestDescription("Testen des Kontruktors(String[]) mit 10 Datensaetzen.")
	public void testKonstruktorZehnDatensaetze() {
		trace.add("Konstruktoraufruf Management(String[]).");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);		
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Standard-Konstruktoraufruf Management() und anschliessendes
	 * 		einfuegen von 10 Datensaetzen.<br> 
	 * 		Alle Datensaetze muessen in die Mitgliederverwaltung eingefuegt 
	 * 		werden koennen.</li>
	 *	<li>Erwartet: data10[0] bis data10[9] in der Mitgliederverwaltung.</li>
	 * </ul>
	 */
	@Test
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Testen der insert(IMember)-Methode mit 10 Datensaetzen.")
	public void testInsert() {
		trace.add("Konstruktoraufruf Management().");
		IManagement mgnt = new Management(); 
		evaluate(mgnt);
		
		ITraceRules subTrace = trace.newSubtrace(opt);
		trace.add("Datensaetze in die Mitgliederverwaltung einfuegen.");
		for (int i = 0; i < data10.length; i++) {
			subTrace.add("Konstruktoraufruf Member(\"%s\").", data10[i]);
			IMember member = new Member(data10[i]);
			subTrace.addInfo("Aufruf von insert(%s).", member);
			mgnt.insert(member);
		}
		trace.add(subTrace, subTrace.hasOccurrences());
		evaluate(mgnt, all);
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes Suchen nach den Schuesselwerten in der 
	 *		Mitgliederverwaltung.</li>
	 *	<li>Erwartet: Alle Schuesselwerte muessen in der Mitgliederverwaltung
	 *		gefunden werden. Die Methode search(long) muss den zugehoerigen
	 *		Datensatz zurueckliefern.</li>
	 * </ul>
	 */
	@Test
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Testen der search(long)-Methode mit 10 Datensaetzen.")
	public void testSearchLong() {
		trace.add("Konstruktoraufruf Management(String[]).");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);
		
		ITraceRules subTrace = trace.newSubtrace(opt);
		trace.add("Suchen nach Schluesseln in der Mitgliederverwaltung.");
		String msg = "Erhaltener Datensatz bei der Suche nach %d.";
		for (int i = 0; i < data10.length; i++) {
			subTrace.add("Aufruf von search(%d).", keys10[i]);
			String exp  = concat(i);
			IMember got = mgnt.search(keys10[i]);
			subTrace.addInfo(PassTrace.ifEquals(msg, exp, got, keys10[i]));			
		}
		trace.add(subTrace, subTrace.hasOccurrences());
		assertFalse("Methode search(long) liefert falschen Datensatz.",
				trace.hasOccurrences());
	}
	
	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes Suchen nach Schuesselwerten, die nicht in der
	 * 		Mitgliederverwaltung existieren.</li>
	 *	<li>Erwartet: Kein Schuesselwert darf in der Mitgliederverwaltung
	 *		gefunden werden. Methode search(long) muss null liefern.</li>
	 * </ul>
	 */
	@Test
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Methode search(long) muss bei nicht vorhandenen Schluesseln null liefern.")
	public void testSearchNotExist() {
		trace.add("Konstruktoraufruf Management(String[]).");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);
		
		ITraceRules subTrace = trace.newSubtrace(opt);
		trace.add("Suchen nach nicht vorhandenen Schluesseln in der Mitgliederverwaltung.");
		String msg = "Erhaltener Datensatz bei der Suche nach Schluessel %d.";
		for (int i = 0; i < data10.length; i++) {
			for (int j = 0; j < ofs.length; j++) {
				long key = keys10[i] + ofs[j];
				subTrace.add("Aufruf von search(%d)", key);			
				IMember got = mgnt.search(key);
				subTrace.addInfo(PassTrace.ifEquals(msg, null, got, key));	
			}
		}
		trace.add(subTrace, subTrace.hasOccurrences());
		assertFalse("Methode search(long) liefert falschen Datensatz.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes Suchen nach dem Nach- und Vornamen in der 
	 *		Mitgliederverwaltung.</li>
	 *	<li>Erwartet: Alle Namen muessen in der Mitgliederverwaltung
	 *		gefunden. Die Methode search(String, String) muss den zugehoerigen
	 *		Datensatz zurueckliefern.</li>
	 * </ul>
	 */
	@Test
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Testen der search(String, String)-Methode mit 10 Datensaetzen.")
	public void testSearchName() {
		trace.add("Konstruktoraufruf Management(String[]).");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);
		
		ITraceRules subTrace = trace.newSubtrace(opt);
		trace.add("Suchen nach Nach- und Vornamen in der Mitgliederverwaltung.");
		String msg  = "Erhaltener Datensatz bei der Suche nach \"%s, %s\".";
		for (int i = 0; i < data10.length; i++) {
			String[] parts = data10[i].split("\\s*,\\s*");
			subTrace.add("Aufruf von search(\"%s, %s\")", parts[0], parts[1]);
			String exp  = concat(i);
			IMember got = mgnt.search(parts[0], parts[1]);			
			subTrace.addInfo(PassTrace.ifEquals(msg, exp, got, parts[0], parts[1]));				
		}
		trace.add(subTrace, subTrace.hasOccurrences());
		assertFalse("Methode search(String, String) liefert falschen Datensatz.",
				trace.hasOccurrences());
	}
	
	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes Suchen nach dem Nach- und Vornamen in der 
	 *		Mitgliederverwaltung.</li>
	 *	<li>Erwartet: Kein Namen darf in der Mitgliederverwaltung
	 *		gefunden werden. Die Methode search(String, String) muss null liefern.</li>
	 * </ul>
	 */
	@Test
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Methode search(String, String) muss bei unbekannten Namen null liefern.")
	public void testSearchUnknown() {
		trace.add("Konstruktoraufruf Management(String[]).");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);
		
		ITraceRules subTrace = trace.newSubtrace(opt);
		trace.add("Suchen nach nicht vorhandenen Nachnamen in der Mitgliederverwaltung.");
		String msg  = "Erhaltener Datensatz bei der Suche nach \"%s, %s\".";		
		for (int i = 0; i < data10.length; i++) {			
			String[] parts = data10[i].split("\\s*,\\s*");
			String unknown = parts[0] + "ix";
			subTrace.add("Aufruf von search(\"%s, %s\")", unknown, parts[1]);			
			IMember got = mgnt.search(unknown, parts[1]);			
			subTrace.addInfo(PassTrace.ifEquals(msg, null, got, unknown, parts[1]));				
		}
		trace.add(subTrace, subTrace.hasOccurrences());
		
		//----------------------------------------------------------------------
		subTrace.clear();
		trace.add("Suchen nach nicht vorhandenen Vornamen in der Mitgliederverwaltung.");
		for (int i = 0; i < data10.length; i++) {			
			String[] parts = data10[i].split("\\s*,\\s*");
			String unknown = parts[1] + "ix";
			subTrace.add("Aufruf von search(\"%s, %s\")", parts[0], unknown);			
			IMember got = mgnt.search(parts[0], unknown);			
			subTrace.addInfo(PassTrace.ifEquals(msg, null, got, parts[0], unknown));				
		}
		trace.add(subTrace, subTrace.hasOccurrences());
		
		assertFalse("Methode search(String, String) liefert falschen Datensatz.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes ermitteln der Anzahl der Eintraege fuer die 
	 * 		Sportarten HANDBALL, RUDERN, FUSSBALL und REITEN in der 
	 * 		Mitgliederverwaltung.</li>
	 *	<li>Erwartet: Die Methode size(KindOfSport) muss folgende Werte liefern:
	 *		<ul>
	 *			<li>HANDBALL: 5</li>
	 *			<li>RUDERN: 3</li>
	 *			<li>FUSSBALL: 2</li>
	 *			<li>REITEN: 0</li>
	 *		</ul> 
	 *	</li>
	 * </ul>
	 */
	@Test
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Testen der size(KindOfSport)-Methode mit 10 Datensaetzen.")
	public void testSizeKindOfSport() {
		trace.add("Konstruktoraufruf Management(String[]).");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);
		
		String msg = "Erhaltene Anzahl der Mitglieder fuer die Sportarten %s.";
		
		KindOfSport sport = KindOfSport.HANDBALL;
		trace.add("Aufruf von size(%s).", sport);
		trace.addInfo(PassTrace.ifEquals(msg, 5, mgnt.size(sport), sport));
		
		sport = KindOfSport.RUDERN;
		trace.add("Aufruf von size(%s).", sport);
		trace.addInfo(PassTrace.ifEquals(msg, 3, mgnt.size(sport), sport));
		
		sport = KindOfSport.FUSSBALL;
		trace.add("Aufruf von size(%s).", sport);
		trace.addInfo(PassTrace.ifEquals(msg, 2, mgnt.size(sport), sport));
		
		sport = KindOfSport.REITEN;
		trace.add("Aufruf von size(%s).", sport);
		trace.addInfo(PassTrace.ifEquals(msg, 0, mgnt.size(sport), sport));
		
		assertFalse("Methode size(KindOfSport) liefert falsches Ergebnis.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes ermitteln der Eintraege fuer die Sportarten 
	 * 		HANDBALL, RUDERN, FUSSBALL und REITEN in der Mitgliederverwaltung.</li>
	 *	<li>Erwartet:<br>
	 * 		Die Methode discipline(KindOfSport) muss folgende Datensaetze
	 *		liefern (angegeben ist jeweils der Index im Datensatz data10):
	 *		<ul>
	 *			<li>HANDBALL: [8], [6], [3], [1], [0]</li>
	 *			<li>RUDERN: [9], [7], [2]</li>
	 *			<li>FUSSBALL: [5], [4]</li>
	 *			<li>REITEN: nicht im Datensatz vorhanden (also ein leeres Array)</li>
	 *		</ul> 
	 *	</li>
	 * </ul>
	 */
	@Test
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Testen der discipline(KindOfSport)-Methode mit 10 Datensaetzen.")
	public void testDiscipline() {
		trace.add("Konstruktoraufruf Management(String[]).");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);

		KindOfSport sport = KindOfSport.HANDBALL;
		trace.add("Aufruf von discipline(%s).", sport);
		evaluate(mgnt.discipline(sport), 8, 6, 3, 1, 0);

		sport = KindOfSport.RUDERN;
		trace.add("Aufruf von discipline(%s).", sport);
		evaluate(mgnt.discipline(sport), 9, 7, 2);
		
		sport = KindOfSport.FUSSBALL;
		trace.add("Aufruf von discipline(%s).", sport);
		evaluate(mgnt.discipline(sport), 5, 4);
		
		sport = KindOfSport.REITEN;
		trace.add("Aufruf von discipline(%s).", sport);
		evaluate(mgnt.discipline(sport));
		
		assertFalse("Methode discipline(KindOfSport) liefert falsches Ergebnis.",
				trace.hasOccurrences());		
	}
	
	//-----------------------------------------------------------------
	
	/**
	 * Ueberpruefung der durch die Methode toArray() der Mitgliederverwaltung
	 * zurueckgelieferten Datensaetze.
	 * @param mgnt - Mitgliederverwaltung: IManagement.
	 * @param elements - Indizes der erwarteten Testdatensaetze: int...
	 */
	private void evaluate(IManagement mgnt, int... elements) {
		trace.add("Aufruf der toArray() - Methode und Ueberpruefung der Datensaetze.");
		evaluate(mgnt.toArray(), elements);
	}
	
	/**
	 * Ueberpruefung der uebergebenen Datensaetze anhand der Indizes der 
	 * erwarteten Testdatensaetze.
	 * @param members - Array mit Datensaetze: IMember[].
	 * @param elements - Indizes der erwarteten Testdatensaetze: int...
	 */
	private void evaluate(IMember[] members, int... elements) {
		ITraceRules subtrace = trace.newSubtrace(opt);
		if (members != null) {
			subtrace.add(PassTrace.ifEquals("Anzahl der Mitgliederdatensaetze muss %d sein.", 
					elements.length, members.length, elements.length));
			int length = Integer.max(elements.length, members.length);
			if (length > 0) {
				subtrace.add("Ueberpruefung der Datensaetze.");
				ITraceRules sub2trace = subtrace.newSubtrace(opt);
				sub2trace.enumeration(new Enumeration(0, Enumeration.array));
				for (int i = 0; i < length; i++) {
					String exp  = (i < elements.length) ? concat(elements[i]) 
							: "<keinen weiteren Datensaetze>";
					IMember got = (i < members.length) ? members[i] : null;
					sub2trace.add(PassTrace.ifEquals("Datensatz an Position %d im Array.",
							exp, got, i));
				}
				subtrace.add(sub2trace, sub2trace.hasOccurrences());
			}
		} else { 
			subtrace.add(PassTrace.ifNotNull("IMember[] darf nicht null sein.", members));
		}
		trace.add(subtrace, subtrace.hasOccurrences());
		assertFalse("Fehlerhafte(r) Datensaetze/-satz in der Mitgliederverwaltung.",
				trace.hasOccurrences());
	}
	
	/**
	 * Zusammenfuegen eines Schluesselwerts mit dem zugehoerigen Datensatz
	 * entsprechend ihrem Index in den Testdatensaetzen.
	 * @param element - Index im Datensatz: int.
	 * @return Schluesselwert mit dem zugehoerigen Datensatz: String. 
	 */
	private String concat(int element) {		
		return keys10[element] + ", " + data10[element];						
	}

}
