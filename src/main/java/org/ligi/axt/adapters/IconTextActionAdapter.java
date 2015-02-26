/*                                                                                                                          
 * This software is free software; you can redistribute it and/or modify                                                     
 * it under the terms of the GNU General Public License as published by                                                     
 * the Free Software Foundation; either version 3 of the License, or                                                        
 * (at your option) any later version.                                                                                      
 *                                                                                                                          
 * This program is distributed in the hope that it will be useful, but                                                      
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY                                               
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License                                                  
 * for more details.                                                                                                        
 *                                                                                                                          
 * You should have received a copy of the GNU General Public License along                                                  
 * with this program; if not, write to the Free Software Foundation, Inc.,                                                  
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA                                                                    
 */

package org.ligi.axt.adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Vector;

/**
 * an Adapter for a action/image/label combo - use it for menus
 *
 * @author ligi ( aka: Marcus Bueschleb | mail: ligi at ligi dot de )
 */
public class IconTextActionAdapter extends BaseAdapter {

    private Context mContext;

    private Vector<Integer> actions = new Vector<>();
    private Vector<Integer> images = new Vector<>();
    private Vector<Integer> labels = new Vector<>();

    private int containerResId = -1;
    private int textResId = -1;
    private int imageResId = -1;

    public void add(int actionid, int img_resid, Integer label_resid) {
        actions.add(actionid);
        images.add(img_resid);
        labels.add(label_resid);
    }

    public void style(int containerResId, int textResId, int imageResId) {
        this.containerResId = containerResId;
        this.textResId = textResId;
        this.imageResId = imageResId;
    }

    public IconTextActionAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return images.size();
    }

    public int getAction(int pos) {
        return actions.get(pos);
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (containerResId != -1) { // if setStyle(..) was called
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = vi.inflate(containerResId, null);
            TextView label = (TextView) row.findViewById(textResId);
            label.setText(labels.get(position));

            ImageView img = (ImageView) row.findViewById(imageResId);
            img.setImageResource(images.get(position));
            return row;
        }

        // fallback - build some layout w/o xml
        LinearLayout lin = new LinearLayout(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
        //lin.setLayoutParams(lp);
        lin.setOrientation(LinearLayout.HORIZONTAL);

        ImageView i = new ImageView(mContext);
        i.setLayoutParams(lp);
        i.setImageResource(images.get(position));
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        lin.addView(i);
        TextView tv = new TextView(mContext);
        tv.setText(labels.get(position));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_MM, 20f); // 2cm
        lin.addView(tv);

        return lin;

    }
}
