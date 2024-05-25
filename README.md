# RSA Encryption in Python and JAVA

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
## Usage
1. Prime Generation:
The generate_prime function generates a random prime number within a given range.
2. Key Calculation:
Calculate n and phi(n), then choose e and compute d using the mod_inverse function.
3. Encryption:
The message is converted to ASCII and then encrypted using the public key.
4. Decryption:
The ciphertext is decrypted using the private key to retrieve the original message.

### Notes
The is_prime function is a simple and inefficient way to check for primality, and could be improved for larger ranges.
The generate_prime function may need optimization for generating larger primes efficiently.
For a real-world implementation, use well-tested libraries such as PyCrypto for cryptographic operations.
