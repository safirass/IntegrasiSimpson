/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simpsonintegration;

import java.util.stream.IntStream;

public class SimpsonIntegration {

    // Fungsi yang akan diintegrasikan
    public static double function(double x) {
        return 4 / (1 + x * x);
    }

    // Metode Simpson 1/3
    public static double simpson13(double a, double b, int n) {
        // Pastikan jumlah segmen n adalah genap
        if (n % 2 != 0) {
            n++;
        }

        double h = (b - a) / n;
        double sum = function(a) + function(b);

        // Sum 4*f(xi) for i odd and 2*f(xi) for i even
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += (i % 2 == 0 ? 2 : 4) * function(x);
        }

        return (h / 3) * sum;
    }

    public static void main(String[] args) {
        // Batas integrasi
        double a = 0.0;
        double b = 1.0;
        // Variasi nilai N
        int[] nValues = {10, 100, 1000, 10000};
        // Nilai referensi pi
        double referencePi = 3.14159265358979323846;

        for (int n : nValues) {
            long startTime = System.nanoTime();
            double result = simpson13(a, b, n);
            long endTime = System.nanoTime();
            double error = Math.abs(result - referencePi);
            double rmsError = Math.sqrt(error * error);

            System.out.printf("N = %d, Result = %.15f, RMS Error = %.15f, Execution Time = %d ns%n",
                    n, result, rmsError, (endTime - startTime));
        }
    }
}
