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

import android.app.Activity;
import android.app.ListActivity;
import android.widget.BaseAdapter;

/**
 * Periodically invalidates an Adapter via notifyDataSetChanged
 *
 * @author ligi ( aka: Marcus Bueschleb | mail: ligi at ligi dot de )
 *         <p/>
 *         License: GPLv3
 */
public class PeriodicallyInvalidateAdapter implements Runnable {

    private final Activity ctx;
    private final Invalidator myInvalidator;

    public PeriodicallyInvalidateAdapter(ListActivity ctx, BaseAdapter adapter) {
        this.ctx = ctx;
        myInvalidator = new Invalidator(adapter);
        new Thread(this).start();
    }

    class Invalidator implements Runnable {
        private BaseAdapter adapter;

        public Invalidator(BaseAdapter adapter) {
            this.adapter = adapter;
        }

        public void run() {
            adapter.notifyDataSetChanged();
        }
    }

    private boolean running = true;

    public void run() {
        while (running) try {
            ctx.runOnUiThread(myInvalidator);
            Thread.sleep(200);
        } catch (Exception e) {
        }
    }

}
 