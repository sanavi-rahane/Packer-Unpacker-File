## Overview

This project is a Java- Based File Packer and Unpacker utility that allows users to combine multiple files from a folder into a single packed file and later restore them back to their original form.

The application uses Java File Handeling and Streams and also applies basic XOR- based encryption to secure file data inside the pack file.

## Features

- Packs all .txt files from a spacified folder into a single pack file
- Stores metadata for each file : 
    - File Name
    - File Size
    - File Data
- Uses a fixed 100- byte header for each file
- Encrypts file data using XOR encryption
- Unpacks files by :
    - Reading metadata from the packed file
    - Recreating original files with correct names and content
- Unpacked files are created in the current working directory

### Technologies Used

- Java lamguage
- File Handling ( File , FileInputStream, FileOutputStream)
- Byte Streams
- Basic Encryption (XOR)

## project Structure

File-Packer-Unpacker/
|
|-PackUnpack.java
|-README.md

## Auther
Sanavi Rahane 