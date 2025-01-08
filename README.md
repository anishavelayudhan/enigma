# Enigma Machine
![demo](https://i.imgur.com/kRge8M8.png)

This project implements the Enigma Machine algorithm in Java, including the rotor and reflector functionality. It simulates the workings of the historical Enigma Machine used during World War II for encryption and decryption of messages. The code allows encoding and decoding of text using configurable rotors and a reflector, all within the console.

### Description

The Enigma Machine is a cipher machine used for encrypting and decrypting messages. It uses a series of rotors and a reflector to scramble plain text into ciphertext and vice versa. Each rotor provides a substitution cipher, and the reflector ensures that the encryption and decryption processes are reversible. This implementation is a Java-based representation of the Enigma Machine based on Mike Koss' "Paper Enigma" concept, which simplifies the rotors and reflector mechanism for easy understanding.

This implementation simulates the operation of the Enigma Machine with:
1. Configurable rotors.
2. Reflector functionality.
3. A basic console interface to encode and decode text.

### Features

- **Rotor Mechanism**: The machine uses rotors to substitute each character in the input.
- **Reflector**: After the rotors have scrambled the input, the reflector sends it back through the rotors in reverse to complete the encryption/decryption.
- **Console Input/Output**: The program takes user input from the console, encrypts/decrypts the text, and prints the result to the console.
