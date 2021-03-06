import attractions.Dodgems;
import attractions.Park;
import attractions.Playground;
import attractions.RollerCoaster;
import org.junit.Before;
import org.junit.Test;
import people.Visitor;
import stalls.CandyflossStall;
import stalls.IceCreamStall;
import stalls.ParkingSpot;
import stalls.TobaccoStall;

import static org.junit.Assert.*;

public class ThemeParkTest {

    ThemePark themePark;
    RollerCoaster rollerCoaster;
    Playground playground;
    Dodgems dodgems;
    Park park;
    Visitor visitor1;
    Visitor visitor2;
    Visitor visitor3;
    Visitor visitor4;
    TobaccoStall tobaccoStall;
    IceCreamStall iceCreamStall;


    @Before
    public void setUp() throws Exception {
        tobaccoStall = new TobaccoStall("Jacks Drum", "Jack Jarvis", ParkingSpot.B1);
        iceCreamStall = new IceCreamStall("Dream Cones", "Vanilla Ice", ParkingSpot.A4);
        rollerCoaster = new RollerCoaster("Blue Ridge", 10);
        playground = new Playground("Fun Zone", 7);
        dodgems = new Dodgems("Bumper Cars", 5);
        park = new Park("Leafy Meadows", 9);
        visitor1 = new Visitor(1,1,1);
        visitor2 = new Visitor(100,100,100000);
        visitor3 = new Visitor(1000,1000,100000);
        visitor4 = new Visitor(1,1000,100000);
        themePark = new ThemePark();
    }

    @Test
    public void getAllReviewed() {
        themePark.add(rollerCoaster);
        themePark.add(playground);
        assertEquals(2,themePark.getAllReviewed().size());
    }

    @Test
    public void visit__visit_count() {
        themePark.visit(visitor1,rollerCoaster);
        themePark.visit(visitor2,rollerCoaster);
        themePark.visit(visitor1,playground);
        assertEquals(2,rollerCoaster.getVisitCount());
    }

    @Test
    public void visit__visitedAttractions() {
        themePark.visit(visitor1,rollerCoaster);
        themePark.visit(visitor1,playground);
        themePark.visit(visitor1,dodgems);
        themePark.visit(visitor1,park);
        assertEquals(4,visitor1.getVisitedAttractions().size());
    }

    @Test
    public void getReviews() {
        rollerCoaster.setRating(1);
        playground.setRating(2);
        dodgems.setRating(3);
        themePark.add(rollerCoaster);
        themePark.add(playground);
        themePark.add(dodgems);
        assertEquals("{Fun Zone=2, Bumper Cars=3, Blue Ridge=1}",themePark.getReviews().toString());
    }

    // Incomplete, need to do checks for not allowed
    @Test
    public void getAllAllowedFor() {
        themePark.add(rollerCoaster);
        themePark.add(playground);
        themePark.add(dodgems);
        themePark.add(park);
        themePark.add(tobaccoStall);
        themePark.add(iceCreamStall);
        assertEquals(6,themePark.getAllAllowedFor(visitor1).size());
    }
}