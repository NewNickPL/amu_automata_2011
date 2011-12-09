package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.HashSet;
import java.util.Set;

/**
 * Klasa testujaca klasę CharRangeTransitionLabel.
 */
public class TestCharSetTransitionLabel extends TestCase {

    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Puste przecięcie.
     */
    public final void testEmptyIntersection() {

        AutomatonSpecification aut = new NaiveAutomatonSpecification();

        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();

        aut.markAsInitial(s0);
        aut.markAsFinal(s2);
        
        HashSet<Character> set = new HashSet();
        set.add('a');
        set.add('b');
        set.add('c');
        
        HashSet<Character> set2 = new HashSet();
        set.add('d');
        set.add('e');
        set.add('f');
        
        TransitionLabel trans = new CharSetTransitionLabel(set);
        TransitionLabel trans2 = new CharSetTransitionLabel(set2);

        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s0, s1, trans.intersectWith(trans2));

        assertTrue(trans.canAcceptCharacter('a'));
        assertTrue(trans.canAcceptCharacter('b'));
        assertTrue(trans.canAcceptCharacter('c'));
        assertFalse(trans.canAcceptCharacter('h'));

        assertFalse(trans.canBeEpsilon());

        assertSame(trans.intersectWith(trans2), trans2.intersectWith(trans));

        assertSame(trans.intersectWith(trans2), new EmptyTransitionLabel());
    }

    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Niepuste przecięcie.
     */
    public final void testNotEmptyIntersection() {

        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();

        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        
        HashSet<Character> set = new HashSet();
        set.add('a');
        set.add('b');
        set.add('c');
        
        HashSet<Character> set2 = new HashSet();
        set.add('b');
        set.add('c');
        set.add('d');
        
        TransitionLabel trans = new CharSetTransitionLabel(set);
        TransitionLabel trans2 = new CharSetTransitionLabel(set2);
        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s2, s3, trans2.intersectWith(trans));

        assertFalse(trans.canBeEpsilon());
        assertTrue(trans.canAcceptCharacter('a'));
        assertTrue(trans.canAcceptCharacter('b'));
        assertTrue(trans.canAcceptCharacter('c'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('b'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('c'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('a'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('d'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('b'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('c'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('a'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('d'));
    }

    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Przedziały zawierające się.
     */
    public final void testContainedIntersection() {
        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();
        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        
        HashSet<Character> set = new HashSet();
        set.add('a');
        set.add('b');
        set.add('c');
        set.add('d');
        set.add('e');
        
        HashSet<Character> set2 = new HashSet();
        set.add('b');
        set.add('c');
        set.add('d');
        
        TransitionLabel trans = new CharSetTransitionLabel(set);
        TransitionLabel trans2 = new CharSetTransitionLabel(set2);
        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s2, s3, trans2.intersectWith(trans));

        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('a'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('b'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('c'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('d'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('e'));

        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('a'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('b'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('c'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('d'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('e'));
       
    }
    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Przedziały równe.
     */
    public final void testEqualIntersection() {
        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();
        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        
        HashSet<Character> set = new HashSet();
        set.add('a');
        set.add('b');
        set.add('c');
        
        TransitionLabel trans = new CharSetTransitionLabel(set);
        TransitionLabel trans2 = new CharSetTransitionLabel(set);
        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s2, s3, trans2.intersectWith(trans));

        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('a'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('b'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('c'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('d'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('a'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('b'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('c'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('d'));
    }

    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Końce przedziałów równe.
     */
    public final void testEndsEqualIntersection() {
        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();
        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        
        HashSet<Character> set = new HashSet();
        set.add('a');
        set.add('b');
        set.add('c');
        set.add('d');
        
        HashSet<Character> set2 = new HashSet();
        set.add('b');
        set.add('c');
        set.add('d');
        
        TransitionLabel trans = new CharSetTransitionLabel(set);
        TransitionLabel trans2 = new CharSetTransitionLabel(set2);
        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s2, s3, trans2.intersectWith(trans));

        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('a'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('b'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('c'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('d'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('a'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('b'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('c'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('d'));
    }
    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Przedziały zawierające się, jednoznakowe.
     */
    public final void testOneCharIntersection() {
        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();
        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        
        HashSet<Character> set = new HashSet();
        set.add('a');
        
        TransitionLabel trans = new CharSetTransitionLabel(set);
        TransitionLabel trans2 = new CharSetTransitionLabel(set);
        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s2, s3, trans2.intersectWith(trans));

        assertTrue(trans.canAcceptCharacter('a'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('a'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('b'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('a'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('b'));
    }
};
