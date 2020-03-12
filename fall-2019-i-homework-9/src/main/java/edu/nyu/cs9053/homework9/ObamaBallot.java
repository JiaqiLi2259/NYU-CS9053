package edu.nyu.cs9053.homework9;

public class ObamaBallot extends PresidentialBallot {

    public ObamaBallot(boolean isSupport, String presidentName) {
        super(isSupport, presidentName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (obj.getClass() != getClass())) {
            return false;
        }
        ObamaBallot that = (ObamaBallot) obj;
        return (getPresidentName() == null ? that.getPresidentName() == null : getPresidentName().equals(that.getPresidentName()))
                && (isYes() == that.isYes());
    }

    @Override
    public int hashCode() {
        int hashCode = Boolean.hashCode(isYes());
        hashCode = 11 * hashCode + (getPresidentName() == null ? 0 : getPresidentName().hashCode());
        return hashCode;
    }

}
