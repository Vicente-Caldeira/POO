public class Interface {
    
    public static void main(String[] args) {
        if (args.length == 2)
            withFile(args);
        else if (args.length == 12)
            withInput(args);
        else
            System.out.println("Incorrect user input.");
    }

    public static void withFile(String[] args) {
        if (!args[0].equals("-f"))
            System.out.println("Incorrect indicator, should be -f.");
        if (!args[1].endsWith(".txt")) {
            System.out.println("The file is an incorrect format, should be a .txt file.");
        }

        System.out.println("Arguments are " + args[0] + " " + args[1]);
    }

    public static void withInput(String[] args) {
        if (!args[0].equals("-r"))
            System.out.println("Incorrect indicator, should be -r.");
        System.out.println("Arguments are " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " +
                args[4] + " " + args[5] + " " + args[6] + " " + args[7] + " " + args[8] + " " +
                args[9] + " " + args[10] + " " + args[11]);
    }
}


/*class ArgumentPrinter {
    public void printArguments(String flag, int n, String a, int n1, String alpha, String beta, String delta, String eta, String rho) {
        System.out.println("Flag: " + flag);
        System.out.println("n: " + n);
        System.out.println("a: " + a);
        System.out.println("n1: " + n1);
        System.out.println("Alpha: " + alpha);
        System.out.println("Beta: " + beta);
        System.out.println("Delta: " + delta);
        System.out.println("Eta: " + eta);
        System.out.println("Rho: " + rho);
    }

    public static void boogie(String[] args) {
        System.out.println("Good Morning USA!!!");
        System.out.println(args.length);
        if (args.length < 9) {
            System.out.println("Insufficient arguments provided.");
            return;
        }
        
        String flag = args[0];
        int n = Integer.parseInt(args[1]);
        String a = args[2];
        int n1 = Integer.parseInt(args[3]);
        String alpha = args[4];
        String beta = args[5];
        String delta = args[6];
        String eta = args[7];
        String rho = args[8];
        
        ArgumentPrinter printer = new ArgumentPrinter();
        printer.printArguments(flag, n, a, n1, alpha, beta, delta, eta, rho);
        System.out.println("Hello World");
    }
}*/







