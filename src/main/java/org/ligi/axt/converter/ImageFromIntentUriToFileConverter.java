package org.ligi.axt.converter;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.ligi.axt.AXT;

public class ImageFromIntentUriToFileConverter {

    final Context context;

    public ImageFromIntentUriToFileConverter(final Context context) {
        this.context = context;
    }

    public File extract(Uri selectedImage) {
        final String[] filePathColumn = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME};
        final Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        // some devices (OS versions return an URI of com.android instead of com.google.android
        if (selectedImage.toString().startsWith("content://com.android.gallery3d.provider")) {
            // use the com.google provider, not the com.android provider.
            selectedImage = Uri.parse(selectedImage.toString().replace("com.android.gallery3d", "com.google.android.gallery3d"));
        }
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            // if it is a picasa image on newer devices with OS 3.0 and up
            if (selectedImage.toString().startsWith("content://com.google.android.gallery3d")) {
                columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME);
                if (columnIndex != -1) {
                    // Do this in a background thread, since we are fetching a large image from the web

                    return getBitmap("image_file_name.jpg", selectedImage);

                }
            } else { // it is a regular local image file
                String filePath = cursor.getString(columnIndex);

                // taken from http://stackoverflow.com/questions/20067508/get-real-path-from-uri-android-kitkat-new-storage-access-framework
                if (filePath == null && Build.VERSION.SDK_INT >= 19) {
                    filePath = getFilePathForKITKAT(selectedImage);
                }
                cursor.close();
                return new File(filePath);
            }
        }
        // If it is a picasa image on devices running OS prior to 3.0
        else if (selectedImage.toString().length() > 0) {
            // Do this in a background thread, since we are fetching a large image from the web
            return getBitmap("image_file_name.jpg", selectedImage);
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private String getFilePathForKITKAT(Uri selectedImage) {
        final String wholeID = DocumentsContract.getDocumentId(selectedImage);

        // Split at colon, use second item in the array
        final String id = wholeID.split(":")[1];

        final String[] column = {MediaStore.Images.Media.DATA};

        // where id is equal to
        final String sel = MediaStore.Images.Media._ID + "=?";

        final Cursor innerCursor = context.getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, new String[]{id}, null);

        final int columnIndex = innerCursor.getColumnIndex(column[0]);

        if (innerCursor.moveToFirst()) {
            return innerCursor.getString(columnIndex);
        }
        return null;
    }


    private File getBitmap(String tag, Uri url) {
        final File cacheDir = context.getCacheDir();

        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }

        final File f = new File(cacheDir, tag);

        try {
            final InputStream is = getInputStreamByURL(url);
            AXT.at(is).toFile(f);
            return f;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private InputStream getInputStreamByURL(Uri url) throws IOException {
        if (url.toString().startsWith("content://com.google.android.gallery3d")) {
            return context.getContentResolver().openInputStream(url);
        } else {
            return new URL(url.toString()).openStream();
        }
    }
}
