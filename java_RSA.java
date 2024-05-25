import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

    public class RSA {

        public static boolean isPrime(BigInteger number) {
            if (number.compareTo(BigInteger.TWO) < 0) {
                return false;
            }
            for (BigInteger i = BigInteger.TWO; i.compareTo(number.divide(BigInteger.TWO)) <= 0; i = i.add(BigInteger.ONE)) {
                if (number.mod(i).equals(BigInteger.ZERO)) {
                    return false;
                }
            }
            return true;
        }

        public static BigInteger generatePrime(BigInteger min, BigInteger max) {
            Random random = new SecureRandom();
            BigInteger prime = new BigInteger(max.bitLength(), random);
            while (!isPrime(prime)) {
                prime = new BigInteger(max.bitLength(), random);
            }
            return prime;
        }

        public static BigInteger modInverse(BigInteger e, BigInteger phi) {
            for (BigInteger d = BigInteger.valueOf(3); d.compareTo(phi) < 0; d = d.add(BigInteger.ONE)) {
                if (e.multiply(d).mod(phi).equals(BigInteger.ONE)) {
                    return d;
                }
            }
            throw new IllegalArgumentException("Modular inverse does not exist");
        }

        public static void main(String[] args) {
            try {
                BigInteger p = generatePrime(BigInteger.TWO, BigInteger.valueOf(298));
                BigInteger q = generatePrime(BigInteger.TWO, BigInteger.valueOf(298));

                while (p.equals(q)) {
                    q = generatePrime(BigInteger.TWO, BigInteger.valueOf(298));
                }

                BigInteger n = p.multiply(q);
                BigInteger phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

                BigInteger e = new BigInteger(phi_n.bitLength(), new Random()).add(BigInteger.TWO);
                while (e.gcd(phi_n).compareTo(BigInteger.ONE) != 0) {
                    e = new BigInteger(phi_n.bitLength(), new Random()).add(BigInteger.TWO);
                }

                BigInteger d = modInverse(e, phi_n);

                System.out.println("Public Key: " + e);
                System.out.println("Private Key: " + d);
                System.out.println("n: " + n);
                System.out.println("Phi(n): " + phi_n);
                System.out.println("p: " + p);
                System.out.println("q: " + q);

                String message = "Sinem Gunes";

                //Mesajı bayt dizisine dönüştürürüz.
                byte[] messageBytes = message.getBytes();

                // (m^e) mod n = c
                BigInteger[] ciphertext = new BigInteger[messageBytes.length];
                for (int i = 0; i < messageBytes.length; i++) {
                    ciphertext[i] = BigInteger.valueOf(messageBytes[i]).modPow(e, n);
                }

                // (c^d) mod n = m
                BigInteger[] messageDecoded = new BigInteger[ciphertext.length];
                for (int i = 0; i < ciphertext.length; i++) {
                    messageDecoded[i] = ciphertext[i].modPow(d, n);
                }


                byte[] decryptedBytes = new byte[messageDecoded.length];
                for (int i = 0; i < messageDecoded.length; i++) {
                    decryptedBytes[i] = messageDecoded[i].byteValue();
                }
                String decryptedMessage = new String(decryptedBytes);

                System.out.println("Ciphertext: " + java.util.Arrays.toString(ciphertext));
                System.out.println("Original Text: " + decryptedMessage);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


