public class Main {

    public static void main(String[] args) {
        if (args.length == 1 && "--demo".equals(args[0])) {
            runDemo();
        } else {
            System.out.println("Usage: java -cp out Main --demo");
        }
    }

    private static void runDemo() {
        int passed = 0;

        Circle circleVector =
                new Circle("C1", 2, new VectorRenderer());

        passed += check(
                "T1",
                "Circle + VectorRenderer",
                circleVector.execute(),
                "VECTOR circle radius=2"
        );

        Circle circleRaster =
                new Circle("C2", 2, new RasterRenderer());

        passed += check(
                "T2",
                "Circle + RasterRenderer",
                circleRaster.execute(),
                "RASTER circle radius=2"
        );

        Square squareVector =
                new Square("S1", 3, new VectorRenderer());

        passed += check(
                "T3",
                "Square + VectorRenderer",
                squareVector.execute(),
                "VECTOR square side=3"
        );

        Square squareRaster =
                new Square("S2", 3, new RasterRenderer());

        passed += check(
                "T4",
                "Square + RasterRenderer",
                squareRaster.execute(),
                "RASTER square side=3"
        );

        passed += checkRuntimeSwitch();

        Circle circleAscii =
                new Circle("C3", 2, new AsciiRenderer());

        passed += check(
                "T6",
                "Circle + AsciiRenderer",
                circleAscii.execute(),
                "ASCII circle radius=2"
        );

        Square squareAscii =
                new Square("S3", 3, new AsciiRenderer());

        passed += check(
                "T7",
                "Square + AsciiRenderer",
                squareAscii.execute(),
                "ASCII square side=3"
        );

        System.out.println("SUMMARY: " + passed + "/7 PASS");
    }

    private static int checkRuntimeSwitch() {
        Circle circle =
                new Circle("C-SWITCH", 2, new VectorRenderer());

        Shape originalReference = circle;

        String idBefore = circle.getId();
        int radiusBefore = circle.getRadius();

        String before = circle.execute();

        circle.setImplementation(new RasterRenderer());

        Shape afterReference = circle;

        String after = circle.execute();

        boolean sameObject =
                originalReference == afterReference;

        boolean stateUnchanged =
                idBefore.equals(circle.getId())
                        && radiusBefore == circle.getRadius();

        boolean resultsCorrect =
                "VECTOR circle radius=2".equals(before)
                        && "RASTER circle radius=2".equals(after);

        boolean pass =
                sameObject
                        && stateUnchanged
                        && resultsCorrect;

        System.out.println(
                "T5 " + status(pass)
                        + " | Circle + VectorRenderer -> RasterRenderer"
                        + " | sameObject=" + sameObject
                        + " | stateUnchanged=" + stateUnchanged
        );

        System.out.println(
                "   before=" + before
                        + " | after=" + after
        );

        if (!pass) {
            System.out.println(
                    "   expected before=VECTOR circle radius=2"
                            + " | after=RASTER circle radius=2"
            );
        }

        return pass ? 1 : 0;
    }

    private static int check(
            String testId,
            String classes,
            String actual,
            String expected
    ) {
        boolean pass = expected.equals(actual);

        System.out.println(
                testId + " " + status(pass)
                        + " | " + classes
                        + " | result=" + actual
        );

        if (!pass) {
            System.out.println("   expected=" + expected);
        }

        return pass ? 1 : 0;
    }

    private static String status(boolean pass) {
        return pass ? "PASS" : "FAIL";
    }
}