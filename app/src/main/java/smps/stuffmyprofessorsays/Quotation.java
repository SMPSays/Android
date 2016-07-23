package smps.stuffmyprofessorsays;

/**
 * Created by chris on 6/26/16.
 */

class Quotation {
    private String quote;
    private String professor;
    private String subject;
    private String school;

    Quotation(String quote, String professor, String subject, String school){
        this.quote = quote;
        this.professor = professor;
        this.subject = subject;
        this.school = school;
    }

    String getQuote() {
        return quote;
    }

    String getProfessor() {
        return professor;
    }

    String getSubject() {
        return subject;
    }

    String getSchool() {
        return school;
    }
}
