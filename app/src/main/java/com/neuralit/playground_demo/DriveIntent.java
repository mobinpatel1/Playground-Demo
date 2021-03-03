package com.neuralit.playground_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class DriveIntent extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_intent);

        imageView = findViewById(R.id.imageView);

        onSharedIntent();

    }


    private void onSharedIntent() {
        Intent receiverdIntent = getIntent();
        String receivedAction = receiverdIntent.getAction();
        String receivedType = receiverdIntent.getType();


        if (Intent.ACTION_SEND.equals(receivedAction) && receivedType != null) {
            if ("text/plain".equals(receivedType)) {
                handleSendText(receiverdIntent); // Handle text being sent
            } else if (receivedType.startsWith("image/")) {
                handleSendImage(receiverdIntent); // Handle single image being sent
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(receivedAction) && receivedType != null) {
            if (receivedType.startsWith("image/")) {
                handleSendMultipleImages(receiverdIntent); // Handle multiple images being sent
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            Log.d("TAG","Text  : " + sharedText );
            Glide.with(getApplicationContext()).load(sharedText).into(imageView);
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            // Update UI to reflect image being shared
            Glide.with(getApplicationContext()).load(imageUri).into(imageView);
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            // Update UI to reflect multiple images being shared
        }
    }


//    Reading excel
//public void readExcelFileFromAssets() {
//    try {
//        InputStream myInput;
//        // initialize asset manager
//        AssetManager assetManager = getAssets();
//        //  open excel sheet
//        myInput = assetManager.open("myexcelsheet.xls");
//        // Create a POI File System object
//        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
//        // Create a workbook using the File System
//        HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
//        // Get the first sheet from workbook
//        HSSFSheet mySheet = myWorkBook.getSheetAt(0);
//        // We now need something to iterate through the cells.
//        Iterator<Keyboard.Row> rowIter = mySheet.rowIterator();
//        int rowno =0;
//
//        while (rowIter.hasNext()) {
//            Log.e("TAG", " row no "+ rowno );
//            HSSFRow myRow = (HSSFRow) rowIter.next();
//            if(rowno !=0) {
//                Iterator<Cell> cellIter = myRow.cellIterator();
//                int colno =0;
//                String sno="", date="", det="";
//                while (cellIter.hasNext()) {
//                    HSSFCell myCell = (HSSFCell) cellIter.next();
//                    if (colno==0){
//                        sno = myCell.toString();
//                    }else if (colno==1){
//                        date = myCell.toString();
//                    }else if (colno==2){
//                        det = myCell.toString();
//                    }
//                    colno++;
//                    Log.e("TAG", " Index :" + myCell.getColumnIndex() + " -- " + myCell.toString());
//                }
//
//            }
//            rowno++;
//        }
//    } catch (Exception e) {
//        Log.e("TAG", "error "+ e.toString());
//    }
//}

}