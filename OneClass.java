
public class OneClass {
	private String nameS;
	private String numA;
	private String comeS;
	private String notC;
	private String lateS;

	public OneClass(String nameSubject, String numAllStudents, String comeStudents, String notComeStudents,
			String lateStudents) {
		nameS = nameSubject;
		numA = numAllStudents;
		comeS = comeStudents;
		notC = notComeStudents;
		lateS = lateStudents;
	}

	public String getNameSubject() {
		return nameS;
	}

	public String getNumAllStudents() {
		return numA;
	}

	public String getComeStudents() {
		return comeS;
	}

	public String getNotComeStudents() {
		return notC;
	}

	public String getLateStudents() {
		return lateS;
	}
}
