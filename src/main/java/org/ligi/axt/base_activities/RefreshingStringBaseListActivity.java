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
package org.ligi.axt.base_activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * a ListActivity with a Thread to change the items
 * in a given interval ( default 10Hz )
 * useful for debugging purposes
 */
public abstract class RefreshingStringBaseListActivity extends ListActivity implements Runnable {

    private myArrayAdapter adapter;
    private boolean running = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new myArrayAdapter(this);
        this.setListAdapter(adapter);

        new Thread(this).start();
    }


    public abstract String getStringByPosition(int pos);

    class myArrayAdapter extends BaseAdapter {

        private Activity context;
        private int count = 0;

        public myArrayAdapter(Activity context) {
            super();

            this.context = context;
            while (getStringByPosition(count++) != null) ; // check how many items there are - the last one is null - indicates the end
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = vi.inflate(android.R.layout.simple_list_item_1, null);
            TextView label = (TextView) row.findViewById(android.R.id.text1);
            label.setText(getStringByPosition(position));
            return (row);
        }

        public int getCount() {
            return count;
        }

        /**
         * do not use - not implemented!
         */
        public Object getItem(int arg0) {
            return null;
        }

        /**
         * do not use - not implemented!
         */
        public long getItemId(int position) {
            return 0;
        }

    }

    @Override
    protected void onDestroy() {
        running = false;
        super.onDestroy();
    }

    final Handler mHandler = new Handler();
    // Create runnable for posting
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            adapter.notifyDataSetChanged();
        }
    };

    public int getRefreshSleep() {
        return 100;
    }

    public void run() {
        while (running) {
            mHandler.post(mUpdateResults);
            try {
                Thread.sleep(getRefreshSleep());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
