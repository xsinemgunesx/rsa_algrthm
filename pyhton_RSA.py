import random
import math


def is_prime(number):
    if number < 2:
        return False
    for i in range(2, number // 2 + 1):
        if number % i == 0:
            return False
    return True


def generate_prime(min_value, max_value):
    prime = random.randint(min_value, max_value)
    while not is_prime(prime):
        prime = random.randint(min_value, max_value)
        return prime


def mod_inverse(e, phi):
    for d in range(3, phi):
        if (d * e) % phi == 1:
            return d
    raise ValueError("mod_inverse does not exist")


try:
    p = int(generate_prime(2, 298))
    q = int(generate_prime(2, 298))
except ValueError as e:
    print(e)

while p == q:
    q = generate_prime(2, 298)

n = p * q
phi_n = (p - 1) * (q - 1)

e = random.randint(3, phi_n - 1)
while math.gcd(e, phi_n) != 1:
    e = random.randint(3, phi_n - 1)

d = mod_inverse(e, phi_n)

print("Public Key : ", e)
print("Private Key : ", d)
print("n : ", n)
print("Phi(n) : ", phi_n)
print("p:", p)
print("q:", q)

message = "Hello World"

# mesajı ascii hale çevirme
message_encoded = [ord(ch) for ch in message]

# (m^e) mod n = c
ciphertext = [pow(ch, e, n) for ch in message_encoded]

#şifreli mesajı yazdır
print("chiper text : ", ciphertext)

#şifreli mesajı çözme
message_decoded = [pow(ch, d, n) for ch in ciphertext]
message = "".join(chr(ch) for ch in message_decoded)

print("original text : ", message)
