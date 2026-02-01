import java.io.*;
import java.util.*;

class Pack 
{
    public 
        int iRet = 0;
        byte Buffer[] = new byte[1024];         // Buffer to read data
        byte bheader[] = new byte[100];         // Buffer to read header of pack file
        byte key = 0x11;                        // cryptograpy key
       

        void Packing(String FolderName,String PackFileName)   throws Exception
        {
            File folderobj = new File(FolderName);
            File packobj = new File(PackFileName);

            if(folderobj.exists() && folderobj.isDirectory())
            {
                if(packobj.exists())
                {
                    System.out.println("file with this name allready exist");
                    return;
                }
                packobj.createNewFile();
                FileOutputStream fwobj = new FileOutputStream(packobj);
                File Arr[] = folderobj.listFiles();
                    
                for(int i = 0; i < Arr.length; i++)
                {
                    if(Arr[i].getName().endsWith(".txt"))
                    {
                        String Header = Arr[i].getName() + " "+ Arr[i].length();
                    
                        // padding for 100 byte header
                        while (Header.length() != 100) {
                            Header = Header + " ";
                        }
                        bheader = Header.getBytes();
                        //header write
                        fwobj.write(bheader);

                        //read data
                        FileInputStream frobj = new FileInputStream(Arr[i]);
                        while ((iRet = frobj.read(Buffer))!= -1)
                        {
                            // Encryption 
                            for(int j = 0; j < Buffer.length; j++)
                            {
                                Buffer[j] = (byte)(Buffer[j] ^ key);
                            }
                            // data write
                            fwobj.write(Buffer,0,iRet);
                        }
                        frobj.close();
                    }
                }
                fwobj.close();  
                System.out.println("File Packing Succesfully Done! ");
            }
            else
            {
                System.out.println("folder not exist");
            }

        }  
        
        void UnPacking(String PackFileName) throws Exception
        {
            File packobj = new File(PackFileName);
            String Tokens[] = null;
            int FileSize = 0;

            if(packobj.exists())
            {
                FileInputStream frobj = new FileInputStream(packobj);

                while ((iRet = frobj.read(bheader,0,100)) != -1) 
                {
                    String Header = new String(bheader);
                    // remove padding
                    Header.trim();
                    
                    Tokens = Header.split(" ");
                    File fobj = new File(Tokens[0]);
                    fobj.createNewFile();
                    FileOutputStream fwobj = new FileOutputStream(fobj);
                    FileSize = Integer.parseInt(Tokens[1]);

                    Buffer = new byte[FileSize];
                    frobj.read(Buffer, 0, FileSize);
                    
                    // Decryption Logic
                    for(int i = 0; i < Buffer.length; i++)
                    {
                        Buffer[i] = (byte)(Buffer[i] ^ key);
                    }
                        
                    fwobj.write(Buffer,0,FileSize);
                    fwobj.close();


                }
                frobj.close();
                System.out.println("File Unpacking succesfully Done!");
            }
            else
            {
                System.out.println("PackFile not exist");
            }
        }

                 
}

public class PackUnpack 
{
    public static void main(String[] args) throws Exception
    {
        int Choice = 0;
        Scanner sobj = new Scanner(System.in);
        System.out.println("1 : Packer\t2 : Unpacker");
        Choice = sobj.nextInt();
        sobj.nextLine();
        Pack pobj = new Pack();
        switch (Choice) {
            case 1:
                System.out.println("File Packing started");

                System.out.println("Enter folder name");
                String foldername = sobj.nextLine();
                
                System.out.println("Enter pack file name");
                String packFilename = sobj.nextLine();

                
                pobj.Packing(foldername, packFilename);
               
                break;
            case 2:
                System.out.println("File UnPacking started");
                System.out.println("Enter pack file name");
                String packedFilename = sobj.nextLine();

                pobj.UnPacking(packedFilename);
                break;
        
            default:
                System.out.println("Enter valid choice");
                break;
        }
        sobj.close();
    }
}