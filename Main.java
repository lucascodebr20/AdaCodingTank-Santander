package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        double[] arraySalarioBruto = new double[5];
        double[] arrayPagoInss = new double[5];
        double[] arrayPagoIR = new double[5];
        double[] arraySalarioLiquido = new double[5];

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < arraySalarioBruto.length; i++) {
            System.out.println("Digite um salario");
            double salarioDigitado = sc.nextDouble();
            arraySalarioBruto[i] = salarioDigitado;
        }

        sc.close();

        for (int i = 0; i < arraySalarioBruto.length; i++) {
            double salario = arraySalarioBruto[i];

            for (FaixaInss faixa : FaixaInss.values()) {
                if (salario >= faixa.getValorMinimoINSS() && salario <= faixa.getValorMaximoINSS()) {
                    double porcentagemINSS = faixa.getImpostoInss();
                    arrayPagoInss[i] = (porcentagemINSS / 100) * salario;
                }
            }

            for (FaixaImpostoRenda faixa : FaixaImpostoRenda.values()) {
                if (salario >= faixa.getValorMinimoIR() && salario <= faixa.getValorMaximoIR()) {
                    double porcentagemIR = faixa.getImpostoIR();
                    arrayPagoIR[i] = (porcentagemIR / 100) * salario;
                }
            }

            for (int p = 0; p < arraySalarioBruto.length; p++) {
                double somaImposto = arrayPagoInss[p] + arrayPagoIR[p];
                arraySalarioLiquido[p] = arraySalarioBruto[p] - somaImposto;
            }

        }

        for (int i = 0; i < arraySalarioBruto.length; i++) {
            System.out.println("----------------------");
            System.out.printf("O seu salario bruto é de R$ %.2f \n",arraySalarioBruto[i]);
            System.out.printf("Você pagou ao INSS o valor de R$ %.2f \n",arrayPagoInss[i]);
            System.out.printf("Você pagou de Imposto de Renda R$ %.2f \n",arrayPagoIR[i]);
            System.out.printf("Seu salario liquido é de R$ %.2f\n",arraySalarioLiquido[i]);
        }

    }


    enum FaixaImpostoRenda {
        FAIXA1IR(0.0, 1903.98, 0),
        FAIXA2IR(1903.99, 2826.65, 7.5),
        FAIXA3IR(2826.66, 3751.05, 15),
        FAIXA4IR(3751.06, 4664.68, 22.5),
        FAIXA5IR(4664.68, 99999999999999.99, 27.5);

        private final double valorMinimoIR;
        private final double valorMaximoIR;
        private final double impostoIR;

        FaixaImpostoRenda(double valorMinimoIR, double valorMaximoIR, double impostoIR) {
            this.valorMinimoIR = valorMinimoIR;
            this.valorMaximoIR = valorMaximoIR;
            this.impostoIR = impostoIR;
        }

        public double getValorMinimoIR() {
            return valorMinimoIR;
        }

        public double getValorMaximoIR() {
            return valorMaximoIR;
        }

        public double getImpostoIR() {
            return impostoIR;
        }
    }

    enum FaixaInss {
        FAIXA1INSS(0.0, 1212.00, 7.5),
        FAIXA2INSS(1212.01, 2427.35, 9),
        FAIXA3INSS(2427.36, 3641.03, 12),
        FAIXA4INSS(3641.04, 7087.22, 14),
        FAIXA5INSS(7087.22, 99999999999999.99, 14); // Corrigido o valor máximo

        private final double valorMinimoINSS;
        private final double valorMaximoINSS;
        private final double impostoInss;

        FaixaInss(double valorMinimoINSS, double valorMaximoINSS, double impostoInss) {
            this.valorMinimoINSS = valorMinimoINSS;
            this.valorMaximoINSS = valorMaximoINSS;
            this.impostoInss = impostoInss;
        }

        public double getValorMinimoINSS() {
            return valorMinimoINSS;
        }

        public double getValorMaximoINSS() {
            return valorMaximoINSS;
        }

        public double getImpostoInss() {
            return impostoInss;
        }
    }
}
