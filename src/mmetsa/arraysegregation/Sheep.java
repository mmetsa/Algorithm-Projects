package mmetsa.arraysegregation;

public class Sheep {

    enum Animal {sheep, goat}

    public static void main (String[] param) {
        // for debugging
    }

    /**
     * Reorders a list of animals, all of the goats before the sheep.
     * The algorithm idea was taken from https://javadiscover.blogspot.com/2018/04/segregate-0s-and-1s-in-array-by-looping.html
     * @param animals - the list to be reordered
     */

    public static void reorder(Animal[] animals) {

        // Loops through the array - increasing i from 0 and decreasing j from array.length until i = j.
        // This algorithm works in linear time
        for (int i = 0, j = animals.length - 1; i < j; ) {

            if (animals[i] == Animal.sheep) {

                if(animals[j] == Animal.goat) {
                    animals[i] = Animal.goat;
                    animals[j] = Animal.sheep;
                }

                j--;
            } else {
                i++;
            }
        }
    }
}
