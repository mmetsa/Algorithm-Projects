package mmetsa.graphtask;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphTaskTest {

    @Test (timeout=20000)
    public void test1ResetVerticesInfo() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        g.createRandomSimpleGraph(6, 9);

        GraphTask.Vertex v1 = g.first;
        while (v1 != null) {
            System.out.println(v1.info);
            v1 = v1.next;
        }

        g.resetVerticesInfo();
        v1 = g.first;
        while (v1 != null) {
            assertEquals(0, v1.info);
            v1 = v1.next;
        }
    }

    @Test(timeout = 20000)
    public void test2checkIfGraphIsSimple() {
        GraphTask task = new GraphTask();
        for (int i = 0; i < 1000; i++) {
            GraphTask.Graph g = new GraphTask.Graph("G");
            g.createRandomSimpleGraph(6, 9);
            if (!g.checkIfGraphIsSimple()) {
                throw new RuntimeException("Error: Graph is not simple: " + g);
            }
        }
    }

    @Test(timeout = 20000)
    public void test3checkIfGraphIsSimple() {
        GraphTask task = new GraphTask();
        for (int i = 0; i < 1000; i++) {
            GraphTask.Graph g = new GraphTask.Graph("G");
            g.createRandomSimpleGraph(10, 13);
            if (!g.checkIfGraphIsSimple()) {
                throw new RuntimeException("Error: Graph is not simple: " + g);
            }
        }
    }

    @Test(timeout = 20000)
    public void test4checkIfGraphIsSimple() {
        GraphTask task = new GraphTask();
        for (int i = 0; i < 1000; i++) {
            GraphTask.Graph g = new GraphTask.Graph("G");
            g.createRandomSimpleGraph(60, 128);
            if (!g.checkIfGraphIsSimple()) {
                throw new RuntimeException("Error: Graph is not simple: " + g);
            }
        }
    }

    @Test(timeout = 20000)
    public void test5checkIfGraphIsSimple() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        g.createRandomSimpleGraph(6, 9);
        GraphTask.Arc a1 = new GraphTask.Arc("v1->v1");
        a1.setTarget(g.first);
        g.first.setFirst(a1);
        assertFalse(g.checkIfGraphIsSimple());
    }

    @Test(timeout = 20000)
    public void test6checkIfGraphIsSimple() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        g.createRandomSimpleGraph(6, 9);
        GraphTask.Arc a1 = new GraphTask.Arc("v1->v2");
        GraphTask.Arc a2 = new GraphTask.Arc("v1->v2");
        a1.setTarget(g.first.next);
        a2.setTarget(g.first.next);
        a1.setNext(a2);
        g.first.setFirst(a1);

        assertFalse(g.checkIfGraphIsSimple());
    }

    @Test(timeout = 20000, expected = RuntimeException.class)
    public void test7checkBasicErrors() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");

        g.checkBasicErrors(new GraphTask.Vertex(""), new GraphTask.Vertex(""));
    }

    @Test(timeout = 20000, expected = IllegalArgumentException.class)
    public void test8checkBasicErrors() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        GraphTask.Vertex v1 = new GraphTask.Vertex("");
        g.setFirst(v1);

        g.checkBasicErrors(new GraphTask.Vertex(""), new GraphTask.Vertex(""));
    }

    @Test(timeout = 20000, expected = IllegalArgumentException.class)
    public void test9checkBasicErrors() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        GraphTask.Vertex v1 = new GraphTask.Vertex("");
        g.setFirst(v1);

        g.checkBasicErrors(v1, new GraphTask.Vertex(""));
    }

    @Test(timeout = 20000)
    public void test10checkBasicErrors() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        GraphTask.Vertex v1 = new GraphTask.Vertex("");
        GraphTask.Vertex v2 = new GraphTask.Vertex("");
        g.setFirst(v1);
        g.first.setNext(v2);

        g.checkBasicErrors(v1, v2);
    }

    @Test(timeout = 20000)
    public void test11canTravelFromStartToEndVertex() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        GraphTask.Vertex v1 = new GraphTask.Vertex("v1");
        GraphTask.Vertex v2 = new GraphTask.Vertex("v2");
        GraphTask.Arc a1 = new GraphTask.Arc("v2->v1");
        a1.setTarget(v1);
        v2.setFirst(a1);
        g.setFirst(v1);
        g.first.setNext(v2);

        assertFalse(g.canTravelFromStartToEndVertex(v1, v2));
    }

    @Test(timeout = 20000)
    public void test12canTravelFromStartToEndVertex() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        GraphTask.Vertex v1 = new GraphTask.Vertex("");
        GraphTask.Vertex v2 = new GraphTask.Vertex("");
        GraphTask.Arc a1 = new GraphTask.Arc("v1->v2");
        a1.setTarget(v2);
        v1.setFirst(a1);
        g.setFirst(v1);
        g.first.setNext(v2);

        assertTrue(g.canTravelFromStartToEndVertex(v1, v2));
    }

    @Test(timeout = 20000)
    public void test13canTravelFromStartToEndVertex() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        g.createRandomSimpleGraph(6, 9);

        assertTrue(g.canTravelFromStartToEndVertex(g.first, g.first));
        assertTrue(g.canTravelFromStartToEndVertex(g.first, g.first.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first, g.first.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first, g.first.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first, g.first.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first, g.first.next.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next, g.first));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next, g.first.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next, g.first.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next, g.first.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next, g.first.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next, g.first.next.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next, g.first));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next, g.first.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next, g.first.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next, g.first.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next, g.first.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next, g.first.next.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next, g.first));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next, g.first.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next, g.first.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next, g.first.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next, g.first.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next, g.first.next.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next, g.first));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next, g.first.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next, g.first.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next, g.first.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next, g.first.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next, g.first.next.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next.next, g.first));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next.next, g.first.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next.next, g.first.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next.next, g.first.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next.next, g.first.next.next.next.next));
        assertTrue(g.canTravelFromStartToEndVertex(g.first.next.next.next.next.next, g.first.next.next.next.next.next));

    }

    @Test (timeout = 20000)
    public void test1BellmanFord() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        GraphTask.Vertex v1 = new GraphTask.Vertex("A");
        GraphTask.Vertex v2 = new GraphTask.Vertex("B");
        GraphTask.Vertex v3 = new GraphTask.Vertex("C");

        GraphTask.Arc a1 = new GraphTask.Arc("A->B");
        GraphTask.Arc a2 = new GraphTask.Arc("A->C");
        GraphTask.Arc a3 = new GraphTask.Arc("C->B");

        a1.setInfo(3);
        a2.setInfo(2);
        a3.setInfo(0);

        a1.setNext(a2);
        a1.setTarget(v2);
        a2.setTarget(v3);
        a3.setTarget(v2);

        v1.setNext(v2);
        v2.setNext(v3);
        v1.setFirst(a1);
        v3.setFirst(a3);

        g.setFirst(v1);

        List<GraphTask.Arc> expected = new ArrayList<>() {};
        expected.add(a2);
        expected.add(a3);
        assertEquals("Shortest route between A and B should be " + expected, expected, g.BellmanFord(v1, v2, false));
        expected.remove(a3);
        assertEquals("Shortest route between A and C should be " + expected, expected, g.BellmanFord(v1, v3, false));
        expected.remove(a2);
        expected.add(a3);
        assertEquals("Shortest route between B and C should be " + expected, expected, g.BellmanFord(v3, v2, false));

        a3.setInfo(-1);

        expected.clear();
        expected.add(a2);
        expected.add(a3);
        assertEquals("Shortest route between A and B should be " + expected, expected, g.BellmanFord(v1, v2, false));
        expected.remove(a3);
        assertEquals("Shortest route between A and C should be " + expected, expected, g.BellmanFord(v1, v3, false));
        expected.remove(a2);
        expected.add(a3);
        assertEquals("Shortest route between B and C should be " + expected, expected, g.BellmanFord(v3, v2, false));

    }

    @Test(timeout = 20000, expected = RuntimeException.class)
    public void test1BellmanFordError() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");
        GraphTask.Vertex v1 = new GraphTask.Vertex("A");
        GraphTask.Vertex v2 = new GraphTask.Vertex("B");
        GraphTask.Vertex v3 = new GraphTask.Vertex("C");

        GraphTask.Arc a1 = new GraphTask.Arc("A->B");
        GraphTask.Arc a2 = new GraphTask.Arc("A->C");
        GraphTask.Arc a3 = new GraphTask.Arc("C->B");
        GraphTask.Arc a4 = new GraphTask.Arc("B->C");

        a1.setInfo(3);
        a2.setInfo(-2);
        a3.setInfo(-2);
        a4.setInfo(3);

        a1.setNext(a2);
        a1.setTarget(v2);
        a2.setTarget(v3);
        a3.setTarget(v2);
        a4.setTarget(v1);

        v1.setNext(v2);
        v2.setNext(v3);
        v1.setFirst(a1);
        v2.setFirst(a4);
        v3.setFirst(a3);

        g.setFirst(v1);
        g.BellmanFord(v1, v2, false);
    }

    @Test(timeout = 20000)
    public void test2BellManFord() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");

        GraphTask.Vertex v1 = new GraphTask.Vertex("A");
        GraphTask.Vertex v2 = new GraphTask.Vertex("B");
        GraphTask.Vertex v3 = new GraphTask.Vertex("C");
        GraphTask.Vertex v4 = new GraphTask.Vertex("D");

        v1.setNext(v2);
        v2.setNext(v3);
        v3.setNext(v4);

        GraphTask.Arc a1 = new GraphTask.Arc("A->B");
        GraphTask.Arc a2 = new GraphTask.Arc("A->C");
        GraphTask.Arc a3 = new GraphTask.Arc("B->C");
        GraphTask.Arc a4 = new GraphTask.Arc("C->A");
        GraphTask.Arc a5 = new GraphTask.Arc("C->D");
        GraphTask.Arc a6 = new GraphTask.Arc("D->B");
        GraphTask.Arc a7 = new GraphTask.Arc("D->C");

        a1.setInfo(3);
        a2.setInfo(10);
        a3.setInfo(5);
        a4.setInfo(2);
        a5.setInfo(1);
        a6.setInfo(4);
        a7.setInfo(4);

        a1.setTarget(v2);
        a2.setTarget(v3);
        a3.setTarget(v3);
        a4.setTarget(v1);
        a5.setTarget(v4);
        a6.setTarget(v2);
        a7.setTarget(v3);

        v1.setFirst(a1);
        a1.setNext(a2);
        v2.setFirst(a3);
        v3.setFirst(a4);
        a4.setNext(a5);
        v4.setFirst(a6);
        a6.setNext(a7);

        g.setFirst(v1);

        List<GraphTask.Arc> expected = new ArrayList<>() {};
        expected.add(a1);
        assertEquals("Fastest route between A and B should be ", expected, g.BellmanFord(v1, v2, false));
        expected.add(a3);
        assertEquals("Fastest route between A and C should be ", expected, g.BellmanFord(v1, v3, false));
        expected.add(a5);
        assertEquals("Fastest route between A and D should be ", expected, g.BellmanFord(v1, v4, false));
        expected.clear();
        expected.add(a3);
        expected.add(a4);
        assertEquals("Fastest route between B and A should be ", expected, g.BellmanFord(v2, v1, false));
        expected.remove(a4);
        assertEquals("Fastest route between B and C should be ", expected, g.BellmanFord(v2, v3, false));
        expected.add(a5);
        assertEquals("Fastest route between B and D should be ", expected, g.BellmanFord(v2, v4, false));
        expected.clear();
        expected.add(a4);
        assertEquals("Fastest route between C and A should be ", expected, g.BellmanFord(v3, v1, false));
        expected.clear();
        expected.add(a5);
        expected.add(a6);
        assertEquals("Fastest route between C and B should be ", expected, g.BellmanFord(v3, v2, false));
        expected.remove(a6);
        assertEquals("Fastest route between C and D should be ", expected, g.BellmanFord(v3, v4, false));
        expected.clear();
        expected.add(a7);
        expected.add(a4);
        assertEquals("Fastest route between D and A should be ", expected, g.BellmanFord(v4, v1, false));
        expected.clear();
        expected.add(a6);
        assertEquals("Fastest route between D and B should be ", expected, g.BellmanFord(v4, v2, false));
        expected.clear();
        expected.add(a7);
        assertEquals("Fastest route between D and C should be ", expected, g.BellmanFord(v4, v3, false));

        a6.setInfo(-2);
        a2.setInfo(-1);

        expected.clear();
        expected.add(a2);
        expected.add(a5);
        expected.add(a6);
        assertEquals("Fastest route between A and B should be ", expected, g.BellmanFord(v1, v2, false));
        expected.clear();
        expected.add(a2);
        assertEquals("Fastest route between A and C should be ", expected, g.BellmanFord(v1, v3, false));
        expected.add(a5);
        assertEquals("Fastest route between A and D should be ", expected, g.BellmanFord(v1, v4, false));
        expected.clear();
        expected.add(a3);
        expected.add(a4);
        assertEquals("Fastest route between B and A should be ", expected, g.BellmanFord(v2, v1, false));
        expected.remove(a4);
        assertEquals("Fastest route between B and C should be ", expected, g.BellmanFord(v2, v3, false));
        expected.add(a5);
        assertEquals("Fastest route between B and D should be ", expected, g.BellmanFord(v2, v4, false));
        expected.clear();
        expected.add(a4);
        assertEquals("Fastest route between C and A should be ", expected, g.BellmanFord(v3, v1, false));
        expected.clear();
        expected.add(a5);
        expected.add(a6);
        assertEquals("Fastest route between C and B should be ", expected, g.BellmanFord(v3, v2, false));
        expected.remove(a6);
        assertEquals("Fastest route between C and D should be ", expected, g.BellmanFord(v3, v4, false));
        expected.clear();
        expected.add(a6);
        expected.add(a3);
        expected.add(a4);
        assertEquals("Fastest route between D and A should be ", expected, g.BellmanFord(v4, v1, false));
        expected.clear();
        expected.add(a6);
        assertEquals("Fastest route between D and B should be ", expected, g.BellmanFord(v4, v2, false));
        expected.add(a3);
        assertEquals("Fastest route between D and C should be ", expected, g.BellmanFord(v4, v3, false));

    }

    @Test(timeout = 20000, expected = RuntimeException.class)
    public void test2BellmanFordError() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");

        GraphTask.Vertex v1 = new GraphTask.Vertex("A");
        GraphTask.Vertex v2 = new GraphTask.Vertex("B");
        GraphTask.Vertex v3 = new GraphTask.Vertex("C");
        GraphTask.Vertex v4 = new GraphTask.Vertex("D");

        v1.setNext(v2);
        v2.setNext(v3);
        v3.setNext(v4);

        GraphTask.Arc a1 = new GraphTask.Arc("A->B");
        GraphTask.Arc a2 = new GraphTask.Arc("A->C");
        GraphTask.Arc a3 = new GraphTask.Arc("B->C");
        GraphTask.Arc a4 = new GraphTask.Arc("C->A");
        GraphTask.Arc a5 = new GraphTask.Arc("C->D");
        GraphTask.Arc a6 = new GraphTask.Arc("D->B");
        GraphTask.Arc a7 = new GraphTask.Arc("D->C");

        a1.setInfo(3);
        a2.setInfo(-1);
        a3.setInfo(0);
        a4.setInfo(2);
        a5.setInfo(1);
        a6.setInfo(-2);
        a7.setInfo(4);

        a1.setTarget(v2);
        a2.setTarget(v3);
        a3.setTarget(v3);
        a4.setTarget(v1);
        a5.setTarget(v4);
        a6.setTarget(v2);
        a7.setTarget(v3);

        v1.setFirst(a1);
        a1.setNext(a2);
        v2.setFirst(a3);
        v3.setFirst(a4);
        a4.setNext(a5);
        v4.setFirst(a6);
        a6.setNext(a7);

        g.setFirst(v1);

        g.BellmanFord(v1, v2, false);
    }

    @Test(timeout = 20000)
    public void test3BellmanFord() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");

        GraphTask.Vertex v1 = new GraphTask.Vertex("A");
        GraphTask.Vertex v2 = new GraphTask.Vertex("B");
        GraphTask.Vertex v3 = new GraphTask.Vertex("C");
        GraphTask.Vertex v4 = new GraphTask.Vertex("D");
        GraphTask.Vertex v5 = new GraphTask.Vertex("E");

        v1.setNext(v2);
        v2.setNext(v3);
        v3.setNext(v4);
        v4.setNext(v5);

        GraphTask.Arc a1 = new GraphTask.Arc("A->B");
        GraphTask.Arc a2 = new GraphTask.Arc("A->D");
        GraphTask.Arc a3 = new GraphTask.Arc("B->C");
        GraphTask.Arc a4 = new GraphTask.Arc("B->D");
        GraphTask.Arc a5 = new GraphTask.Arc("C->A");
        GraphTask.Arc a6 = new GraphTask.Arc("C->D");
        GraphTask.Arc a7 = new GraphTask.Arc("D->C");
        GraphTask.Arc a8 = new GraphTask.Arc("D->E");
        GraphTask.Arc a9 = new GraphTask.Arc("E->C");
        GraphTask.Arc a10 = new GraphTask.Arc("E->A");

        a1.setInfo(3);
        a2.setInfo(2);
        a3.setInfo(4);
        a4.setInfo(7);
        a5.setInfo(5);
        a6.setInfo(1);
        a7.setInfo(20);
        a8.setInfo(7);
        a9.setInfo(11);
        a10.setInfo(5);

        a1.setTarget(v2);
        a2.setTarget(v4);
        a3.setTarget(v3);
        a4.setTarget(v4);
        a5.setTarget(v1);
        a6.setTarget(v4);
        a7.setTarget(v3);
        a8.setTarget(v5);
        a9.setTarget(v3);
        a10.setTarget(v1);

        a1.setNext(a2);
        a3.setNext(a4);
        a5.setNext(a6);
        a7.setNext(a8);
        a9.setNext(a10);
        v1.setFirst(a1);
        v2.setFirst(a3);
        v3.setFirst(a5);
        v4.setFirst(a7);
        v5.setFirst(a9);

        g.setFirst(v1);

        List<GraphTask.Arc> expected = new ArrayList<>() {};
        expected.add(a1);
        assertEquals("Fastest route between A and B should be " + expected, expected, g.BellmanFord(v1, v2, false));
        expected.add(a3);
        assertEquals("Fastest route between A and C should be " + expected, expected, g.BellmanFord(v1, v3, false));
        expected.clear();
        expected.add(a2);
        assertEquals("Fastest route between A and D should be " + expected, expected, g.BellmanFord(v1, v4, false));
        expected.add(a8);
        assertEquals("Fastest route between A and E should be " + expected, expected, g.BellmanFord(v1, v5, false));
        expected.clear();
        expected.add(a3);
        expected.add(a5);
        assertEquals("Fastest route between B and A should be " + expected, expected, g.BellmanFord(v2, v1, false));
        expected.remove(a5);
        assertEquals("Fastest route between B and C should be " + expected, expected, g.BellmanFord(v2, v3, false));
        expected.add(a6);
        assertEquals("Fastest route between B and D should be " + expected, expected, g.BellmanFord(v2, v4, false));
        expected.add(a8);
        assertEquals("Fastest route between B and E should be " + expected, expected, g.BellmanFord(v2, v5, false));
        expected.clear();
        expected.add(a5);
        assertEquals("Fastest route between C and A should be " + expected, expected, g.BellmanFord(v3, v1, false));
        expected.add(a1);
        assertEquals("Fastest route between C and B should be 8 " + expected, expected, g.BellmanFord(v3, v2, false));
        expected.clear();
        expected.add(a6);
        assertEquals("Fastest route between C and D should be " + expected, expected, g.BellmanFord(v3, v4, false));
        expected.add(a8);
        assertEquals("Fastest route between C and E should be " + expected, expected, g.BellmanFord(v3, v5, false));
        expected.clear();
        expected.add(a8);
        expected.add(a10);
        assertEquals("Fastest route between D and A should be " + expected, expected, g.BellmanFord(v4, v1, false));
        expected.add(a1);
        assertEquals("Fastest route between D and B should be " + expected, expected, g.BellmanFord(v4, v2, false));
        expected.clear();
        expected.add(a8);
        expected.add(a9);
        assertEquals("Fastest route between D and C should be " + expected, expected, g.BellmanFord(v4, v3, false));
        expected.remove(a9);
        assertEquals("Fastest route between D and E should be " + expected, expected, g.BellmanFord(v4, v5, false));
        expected.clear();
        expected.add(a10);
        assertEquals("Fastest route between E and A should be " + expected, expected, g.BellmanFord(v5, v1, false));
        expected.add(a1);
        assertEquals("Fastest route between E and B should be " + expected, expected, g.BellmanFord(v5, v2, false));
        expected.clear();
        expected.add(a9);
        assertEquals("Fastest route between E and C should be " + expected, expected, g.BellmanFord(v5, v3, false));
        expected.clear();
        expected.add(a10);
        expected.add(a2);
        assertEquals("Fastest route between E and D should be " + expected, expected, g.BellmanFord(v5, v4, false));

        a5.setInfo(-3);
        a4.setInfo(-1);
        a9.setInfo(-2);

        expected.clear();
        expected.add(a1);
        assertEquals("Fastest route between A and B should be " + expected, expected, g.BellmanFord(v1, v2, false));
        expected.add(a3);
        assertEquals("Fastest route between A and C should be " + expected, expected, g.BellmanFord(v1, v3, false));
        expected.clear();
        expected.add(a2);
        assertEquals("Fastest route between A and D should be " + expected, expected, g.BellmanFord(v1, v4, false));
        expected.add(a8);
        assertEquals("Fastest route between A and E should be " + expected, expected, g.BellmanFord(v1, v5, false));
        expected.clear();
        expected.add(a3);
        expected.add(a5);
        assertEquals("Fastest route between B and A should be " + expected, expected, g.BellmanFord(v2, v1, false));
        expected.remove(a5);
        assertEquals("Fastest route between B and C should be " + expected, expected, g.BellmanFord(v2, v3, false));
        expected.clear();
        expected.add(a4);
        assertEquals("Fastest route between B and D should be " + expected, expected, g.BellmanFord(v2, v4, false));
        expected.add(a8);
        assertEquals("Fastest route between B and E should be " + expected, expected, g.BellmanFord(v2, v5, false));
        expected.clear();
        expected.add(a5);
        assertEquals("Fastest route between C and A should be " + expected, expected, g.BellmanFord(v3, v1, false));
        expected.add(a1);
        assertEquals("Fastest route between C and B should be " + expected, expected, g.BellmanFord(v3, v2, false));
        expected.remove(a1);
        expected.add(a2);
        assertEquals("Fastest route between C and D should be " + expected, expected, g.BellmanFord(v3, v4, false));
        expected.add(a8);
        assertEquals("Fastest route between C and E should be " + expected, expected, g.BellmanFord(v3, v5, false));
        expected.clear();
        expected.add(a8);
        expected.add(a9);
        expected.add(a5);
        assertEquals("Fastest route between D and A should be " + expected, expected, g.BellmanFord(v4, v1, false));
        expected.add(a1);
        assertEquals("Fastest route between D and B should be " + expected, expected, g.BellmanFord(v4, v2, false));
        expected.remove(a1);
        expected.remove(a5);
        assertEquals("Fastest route between D and C should be " + expected, expected, g.BellmanFord(v4, v3, false));
        expected.remove(a9);
        assertEquals("Fastest route between D and E should be " + expected, expected, g.BellmanFord(v4, v5, false));
        expected.clear();
        expected.add(a9);
        expected.add(a5);
        assertEquals("Fastest route between E and A should be " + expected, expected, g.BellmanFord(v5, v1, false));
        expected.add(a1);
        assertEquals("Fastest route between E and B should be " + expected, expected, g.BellmanFord(v5, v2, false));
        expected.clear();
        expected.add(a9);
        assertEquals("Fastest route between E and C should be " + expected, expected, g.BellmanFord(v5, v3, false));
        expected.add(a5);
        expected.add(a2);
        assertEquals("Fastest route between E and D should be " + expected, expected, g.BellmanFord(v5, v4, false));
    }

    @Test(timeout = 20000, expected = RuntimeException.class)
    public void test3BellmanFordError() {
        GraphTask task = new GraphTask();
        GraphTask.Graph g = new GraphTask.Graph("G");

        GraphTask.Vertex v1 = new GraphTask.Vertex("A");
        GraphTask.Vertex v2 = new GraphTask.Vertex("B");
        GraphTask.Vertex v3 = new GraphTask.Vertex("C");
        GraphTask.Vertex v4 = new GraphTask.Vertex("D");
        GraphTask.Vertex v5 = new GraphTask.Vertex("E");

        v1.setNext(v2);
        v2.setNext(v3);
        v3.setNext(v4);
        v4.setNext(v5);

        GraphTask.Arc a1 = new GraphTask.Arc("A->B");
        GraphTask.Arc a2 = new GraphTask.Arc("A->D");
        GraphTask.Arc a3 = new GraphTask.Arc("B->C");
        GraphTask.Arc a4 = new GraphTask.Arc("B->D");
        GraphTask.Arc a5 = new GraphTask.Arc("C->A");
        GraphTask.Arc a6 = new GraphTask.Arc("C->D");
        GraphTask.Arc a7 = new GraphTask.Arc("D->C");
        GraphTask.Arc a8 = new GraphTask.Arc("D->E");
        GraphTask.Arc a9 = new GraphTask.Arc("E->C");
        GraphTask.Arc a10 = new GraphTask.Arc("E->A");

        a1.setInfo(3);
        a2.setInfo(2);
        a3.setInfo(4);
        a4.setInfo(-1);
        a5.setInfo(-3);
        a6.setInfo(1);
        a7.setInfo(-4);
        a8.setInfo(7);
        a9.setInfo(-2);
        a10.setInfo(5);

        a1.setTarget(v2);
        a2.setTarget(v4);
        a3.setTarget(v3);
        a4.setTarget(v4);
        a5.setTarget(v1);
        a6.setTarget(v4);
        a7.setTarget(v3);
        a8.setTarget(v5);
        a9.setTarget(v3);
        a10.setTarget(v1);

        a1.setNext(a2);
        a3.setNext(a4);
        a5.setNext(a6);
        a7.setNext(a8);
        a9.setNext(a10);
        v1.setFirst(a1);
        v2.setFirst(a3);
        v3.setFirst(a5);
        v4.setFirst(a7);
        v5.setFirst(a9);

        g.setFirst(v1);

        g.BellmanFord(v2, v4, false);

    }
}

