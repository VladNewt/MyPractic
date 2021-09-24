package practic160821.enumtest;

public enum Colors {

    RED(255,0,0), //public static final Colors RED = new Colors(); - эта строка здесь скрыта
    GREEN(0,255,0) {
        @Override
        public String toString() {
            //return String.format("%s[%d, %d, %d]",name(),RED.r,RED.g,RED.b);
            return String.format("%s[%d, %d, %d]",name(),r(),g(),b());
        }

        public void out() {
            System.out.println(this);
        }
    },
    BLUE(0,0,255);

    private int number;
    private int r;
    private int g;
    private int b;

    public void out(){}


    Colors() {

    }

    Colors(int number) {
        this.number = number;
    }

    Colors(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int number() {
        return number;
    }


    public int r() {
        return r;
    }

    public int g() {
        return g;
    }

    public int b() {
        return b;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d, %d]",r,g,b);
    }
}
