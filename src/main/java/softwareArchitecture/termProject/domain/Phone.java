package softwareArchitecture.termProject.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Phone {
    private int frontNum;
    private int middleNum;
    private int lastNum;

    public Phone() { }

    public Phone(int frontNum, int middleNum, int lastNum) {
        this.frontNum = frontNum;
        this.middleNum = middleNum;
        this.lastNum = lastNum;
    }
}
