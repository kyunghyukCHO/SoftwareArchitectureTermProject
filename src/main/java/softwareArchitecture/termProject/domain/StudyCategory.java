package softwareArchitecture.termProject.domain;

public enum StudyCategory {
    SPRING("스프링"),
    NETWORK("컴퓨터 네트워크"),
    DISTRIBUTEDSYSTEM("분산 시스템"),
    DATABASE("데이터 베이스"),
    SOFTWAREARCHITECTURE("소프트웨어 아키텍처"),
    JAVA("자바");

    private String krName;

    StudyCategory(String krName) { this.krName = krName; }

    public String getKrName() { return krName; }
}
