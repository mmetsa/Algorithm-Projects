package mmetsa.quaternions;
import java.util.Objects;
import java.util.StringTokenizer;

public class Quaternion {

    public static void main(String[] args) {
        Quaternion a = Quaternion.valueOf("");
        System.out.println(a);
    }

    private static final double EPSILON = 0.0000000001D;

    private double a, b, c, d;

    public Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public Quaternion clone() throws CloneNotSupportedException {
        return new Quaternion(a, b, c, d);
    }

    public Quaternion conjugate() {
        return new Quaternion(a, -1 * b, -1 * c, -1 * d);
    }

    public Quaternion divideByLeft(Quaternion q) {
        if (q.isZero()) {
            throw new ArithmeticException("Error: Can't divide by 0");
        }
        return q.inverse().times(this);

    }

    public Quaternion divideByRight(Quaternion q) {
        if (q.isZero()) {
            throw new ArithmeticException("Error: Can't divide by 0");
        }
        return this.times(q.inverse());

    }

    public Quaternion dotMult(Quaternion q) {
        return (this.times(q.conjugate())).plus(q.times(this.conjugate())).divideByLeft(new Quaternion(2, 0, 0, 0));
    }

    public boolean equals(Object qo) {
        if (!(qo instanceof Quaternion)) {
            return false;
        }
        return Math.abs(this.a - ((Quaternion) qo).a) < EPSILON
                && Math.abs(this.b - ((Quaternion) qo).b) < EPSILON
                && Math.abs(this.c - ((Quaternion) qo).c) < EPSILON
                && Math.abs(this.d - ((Quaternion) qo).d) < EPSILON;
    }

    public double getIpart() {
        return this.b;
    }

    public double getJpart() {
        return this.c;
    }

    public double getKpart() {
        return this.d;
    }

    public double getRpart() {
        return this.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }

    public Quaternion inverse() {
        double[] finalQ = new double[] {0, 0, 0, 0};
        double divideBy = a * a + b * b + c * c + d * d;
        if (Math.abs(divideBy) < EPSILON) {
            throw new ArithmeticException("Error: can't divide by 0");
        }
        finalQ[0] = a / divideBy;
        finalQ[1] = -1 * b / divideBy;
        finalQ[2] = -1 * c / divideBy;
        finalQ[3] = -1 * d / divideBy;
        return new Quaternion(finalQ[0], finalQ[1], finalQ[2], finalQ[3]);

    }

    public boolean isZero() {
        return Math.abs(a) < EPSILON && Math.abs(b) < EPSILON && Math.abs(c) < EPSILON && Math.abs(d) < EPSILON;
    }

    public Quaternion minus(Quaternion q) {
        return new Quaternion(a - q.a, b - q.b, c - q.c, d - q.d);
    }

    public double norm() {
        return Math.sqrt(a * a + b * b + c * c + d * d);
    }

    public Quaternion opposite() {
        return new Quaternion(-1 * a, -1 * b, -1 * c, -1 * d);
    }

    public  Quaternion plus(Quaternion q) {
        return new Quaternion(a + q.a, b + q.b, c + q.c, d + q.d);
    }

    public Quaternion times(double r) {
        return new Quaternion(a * r, b * r, c * r, d * r);
    }

    public Quaternion times(Quaternion q) {
        double[] finalQ = new double[] {0, 0, 0, 0};
        finalQ[0] = a * q.a - b * q.b - c * q.c - d * q.d;
        finalQ[1] = a * q.b + b * q.a + c * q.d - d * q.c;
        finalQ[2] = a * q.c - b * q.d + c * q.a + d * q.b;
        finalQ[3] = a * q.d + b * q.c - c * q.b + d * q.a;

        return new Quaternion(finalQ[0], finalQ[1], finalQ[2], finalQ[3]);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (a != 0) {
            sb.append(a);
        }
        if (b != 0) {
            sb.append(b >= 0 ? "+" : "").append(b).append("i");
        }
        if (c != 0) {
            sb.append(c < 0 ? "" : "+").append(c).append("j");
        }
        if (d != 0) {
            sb.append(d < 0 ? "" : "+").append(d).append("k");
        }
        return sb.toString();
        //return a + (b >= 0 ? "+" : "") + b + (c < 0 ? "i" : "i+") + c + (d < 0 ? "j" : "j+") + d + "k";

    }

    public static Quaternion valueOf(String s) {
        double a = 0, b = 0, c = 0, d = 0;
        int counter = 0;
        String prevToken = "";
        StringTokenizer st = new StringTokenizer(s, "+-", true);
        while (st.hasMoreTokens()) {
            String tok = st.nextToken();
            if (tok.equals("-")) {
                if (counter != 0) {
                    counter++;
                }
                prevToken = "-";
                continue;
            }
            if (counter == 0) {
                try {
                    a = Double.parseDouble(prevToken + tok);
                    prevToken = "";
                } catch (Exception e) {
                    throw new IllegalArgumentException("String does not represent a Quaternion.");
                }
                counter ++;
            } else {
                counter++;
                if (tok.equals("+")) {
                    continue;
                }
                if (tok.endsWith("i")) {
                    try {
                        b = Double.parseDouble(prevToken + tok.substring(0, tok.length() - 1));
                        prevToken = "";
                    } catch (Exception e) {
                        throw new IllegalArgumentException("String does not represent a Quaternion.");
                    }
                } else if (tok.endsWith("j")) {
                    try {
                        c = Double.parseDouble(prevToken + tok.substring(0, tok.length() - 1));
                        prevToken = "";
                    } catch (Exception e) {
                        throw new IllegalArgumentException("String does not represent a Quaternion.");
                    }
                } else if (tok.endsWith("k")) {
                    try {
                        d = Double.parseDouble(prevToken + tok.substring(0, tok.length() - 1));
                        prevToken = "";
                    } catch (Exception e) {
                        throw new IllegalArgumentException("String does not represent a Quaternion.");
                    }
                } else {
                    throw new IllegalArgumentException("String does not represent a Quaternion.");
                }
            }
        }
        return new Quaternion(a, b, c, d);
    }

}
