package com.example.proxemotab;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.view.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.URI;

public class ExternalStorageHelper {



    /**
     * creates file if not already existing and uses BufferedWriter to add a new line of content to it
     * @param fileName
     * @param entry
     */
    public void appendEntryToFile(String fileName, String entry) {
        //FileOutputStream outputStream;
        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        if (!f.exists()){
            try {
                f.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }


        try {
            //outputStream = new FileOutputStream(f, true);
            //OutputStreamWriter osWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bWriter = new BufferedWriter (new FileWriter(f, true));

            bWriter.append(entry);
            bWriter.newLine();
            bWriter.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * deletes logfile
     * @param fileName
     */
    public void deleteFile(String fileName) {
        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
        f.delete();
    }

    /**
     * deletes last entry of logfile using Buffered Writer
     * [to be more exact it deletes bytes until it encounters the ASCII code entry for "new line"]
     * Code taken from James Holderness's answer on https://stackoverflow.com/questions/17732417/delete-last-line-in-text-file/17732702
     * @param fileName
     */
    public void deleteLastEntryFromFile(String fileName) throws IOException {
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName;

        RandomAccessFile f = new RandomAccessFile(filePath, "rw");
        long length = f.length() - 2;
        byte b;
        do {
            length -= 1;
            f.seek(length);
            b = f.readByte();
        } while(b != 10 && length > 0);
        if (length == 0){
            f.setLength(length);
        }else {
            f.setLength(length+1);
        }
        f.close();
    }

    public void export (Context context){

        try{
            //Context context = getApplicationContext();
            File fileLocation = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ProxemoTabLog.csv");
            Uri path = FileProvider.getUriForFile(context, "com.example.proxemotab.fileprovider", fileLocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "ProxemoData");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
            context.startActivity(Intent.createChooser(fileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), "Send ProxemoData"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
