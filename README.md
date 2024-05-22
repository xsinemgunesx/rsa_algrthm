# RSA Encryption in Python

This project demonstrates a simple implementation of the RSA encryption algorithm in Python. RSA (Rivest-Shamir-Adleman) is a widely used public-key cryptosystem for secure data transmission.

## How It Works
### Prime Number Generation:
Two distinct prime numbers p and q are generated within a specified range.
### Key Generation:
Compute n = p * q, which is used as the modulus for both the public and private keys.
Compute the totient function phi(n) = (p - 1) * (q - 1).
Choose an integer e such that 1 < e < phi(n) and gcd(e, phi(n)) = 1. This e becomes the public exponent.
Compute the private exponent d such that (d * e) % phi(n) = 1.
### Encryption:
Convert the message to a list of ASCII values.
For each ASCII value m, compute the ciphertext c = (m^e) % n.
### Decryption:
For each ciphertext c, compute the original message m = (c^d) % n.
Convert the ASCII values back to characters to retrieve the original message.
