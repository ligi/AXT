package org.ligi.axt.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.widget.SimpleCursorAdapter;

public class LinkAndDescriptionAdapter extends SimpleCursorAdapter {


    public static SimpleCursorAdapter createByArray(Context ctx, LinkWithDescription[] links) {
        return createByArray(ctx, links, android.R.layout.two_line_list_item);
    }

    public static SimpleCursorAdapter createByArray(Context ctx, LinkWithDescription[] links, int resid) {
        final String[] matrix = {"_id", "name", "value"};
        final String[] columns = {"name", "value"};
        final int[] layouts = {android.R.id.text1, android.R.id.text2};
        MatrixCursor cursor = new MatrixCursor(matrix);

        int i = 0;
        for (LinkWithDescription link : links) {
            if (link instanceof LinkWithDescriptionAndTitle)
                cursor.addRow(new Object[]{i++, ((LinkWithDescriptionAndTitle) link).getTitle(), ((LinkWithDescriptionAndTitle) link).getDescription()});
            else cursor.addRow(new Object[]{i++, link.getDescription(), link.getURL()});

        }

        return new SimpleCursorAdapter(ctx, resid, cursor, columns, layouts);
    }

    public LinkAndDescriptionAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
    }


}
