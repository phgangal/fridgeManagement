package com.fridgemanagement.service;

import com.fridgemanagement.exception.FridgeManagementException;
import com.fridgemanagement.utils.FridgeManagementConstant;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

    /**
     * Util class to encrypt/decrypt string value and matches them
     */
    @Service
    public final class EncryptorDecrytorService {

        private int noOfInternation = FridgeManagementConstant.NO_OF_INTERNATION;
        private String hashingAlgo = FridgeManagementConstant.HASHING_ALGO;
        private String stringEncoding = FridgeManagementConstant.STRING_ENCODING;
        private String salt = FridgeManagementConstant.SALT;

        /**
         *
         */
        public EncryptorDecrytorService() {
        }

        /**
         *
         */
        public void initialize() {
            this.hashingAlgo = FridgeManagementConstant.HASHING_ALGO;
            this.stringEncoding = FridgeManagementConstant.STRING_ENCODING;
            this.noOfInternation = FridgeManagementConstant.NO_OF_INTERNATION;
        }

        /**
         * encrypt given string
         *
         * @param inputString String The base64 representation
         * @return String
         * @throws com.fridgemanagement.exception.FridgeManagementException
         */
        public String encrypt(final String inputString) throws FridgeManagementException {
            byte[] generatedSalt = generateSalt();
            byte[] hashedInputString = getHash(inputString, generatedSalt);
            return byteToBase64(hashedInputString);
        }

        /**
         * decrypt given string
         *
         * @param inputString String The base64 representation
         * @return byte[]
         * @throws FridgeManagementException
         */
        public byte[] decrypt(final String inputString) throws FridgeManagementException {
            String salt = byteToBase64(generateSalt());
            byte[] digestedSalt = base64ToByte(salt);
            return getHash(inputString, digestedSalt);

        }

        /**
         * match encrypted and decrypted value to return true/false
         *
         * @param encryptedValue String The base64 representation
         * @param decryptedValue byte[] The base64 representation
         * @return boolean
         * @throws FridgeManagementException
         */
        public boolean match(final String encryptedValue, final byte[] decryptedValue) throws FridgeManagementException {
            byte[] digestedEncryptedValue = base64ToByte(encryptedValue);
            return Arrays.equals(decryptedValue, digestedEncryptedValue);
        }

        /**
         * From a password, a number of iterations and a salt,
         * returns the corresponding digest
         *
         * @param inputString String The password to encrypt
         * @param salt        byte[] The salt
         * @return byte[] The digested password
         * @throws FridgeManagementException If the algorithm doesn't exist
         */
        public byte[] getHash(final String inputString, final byte[] salt) throws FridgeManagementException {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance(hashingAlgo);
            } catch (NoSuchAlgorithmException noalgoe) {
                throw new FridgeManagementException(noalgoe.getMessage());
            }
            digest.reset();
            digest.update(salt);
            byte[] encryptedInput = null;
            try {
                encryptedInput = digest.digest(inputString.getBytes(stringEncoding));
            } catch (UnsupportedEncodingException nse) {
                throw new FridgeManagementException(nse.getMessage());
            }
            for (int i = 0; i < noOfInternation; i++) {
                digest.reset();
                encryptedInput = digest.digest(encryptedInput);
            }
            return encryptedInput;
        }

        /**
         * From a base 64 representation, returns the corresponding byte[]
         *
         * @param data String The base64 representation
         * @return byte[]
         * @throws FridgeManagementException
         */
        public byte[] base64ToByte(final String data) throws FridgeManagementException {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                return decoder.decodeBuffer(data);
            } catch (IOException ioe) {
                throw new FridgeManagementException(ioe.getMessage());
            }
        }

        /**
         * From a byte[] returns a base 64 representation
         *
         * @param data byte[]
         * @return String
         */
        public String byteToBase64(final byte[] data) {
            BASE64Encoder endecoder = new BASE64Encoder();
            return endecoder.encode(data);
        }

        /**
         * Generate a random byte[] as 64 bits salt
         *
         * @return
         * @throws FridgeManagementException
         */
        public byte[] generateSalt() throws FridgeManagementException {
            String saltFromProperty = this.salt;
            return saltFromProperty.getBytes();
        }

        public void main(String[] args){
            EncryptorDecrytorService encryptorDecryptor = new EncryptorDecrytorService();
            try {
                System.out.println(encryptorDecryptor.encrypt("administrator"));
            } catch (FridgeManagementException e) {
                e.printStackTrace();
            }
        }
    }

