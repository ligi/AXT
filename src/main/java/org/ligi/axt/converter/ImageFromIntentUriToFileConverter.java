package org.ligi.axt.converter;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
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

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param uri     The Uri to query.
     * @author paulburke
     */
    public String extract(final Uri uri) {

        String result = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            result = handleKitKat(context, uri);
        }

        if (result == null && "content".equalsIgnoreCase(uri.getScheme())) {
            result = getDataColumn(context, uri, null, null);
        }

        if (result == null) {
            final File extract = extractFile(uri);
            if (extract!=null) {
                result = extract.getAbsolutePath();
            }
        }

        if (result == null && "file".equalsIgnoreCase(uri.getScheme())) {
            result = uri.getPath();
        }

        return result;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String handleKitKat(final Context context, final Uri uri) {
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    private File extractFile(Uri selectedImage) {
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

        if (new File(wholeID).exists()) {
            return wholeID;
        }
        final String id;
        if (wholeID.contains(":")) id = wholeID.split(":")[1];
        else id = wholeID;

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
