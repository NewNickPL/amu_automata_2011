package pl.edu.amu.wmi.daut.base;

/**
 * Klasa odpowiedzialna za epsilon-przejscie, gdy automat
 * znajduje się na początku napisu.
 */
public class BeginningOfTextTransitionLabel extends ZeroLengthConditionalTransitionLabel {

    @Override
    protected boolean doCheckContext(String s, int position) {
        if ((position < 0) || (position > s.length())) {
            throw new PositionOutOfStringBordersException();
        } else return (position == 0);
    }

    @Override
    public boolean canAcceptCharacter(char c) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "BeginningOfText";
    }

}
