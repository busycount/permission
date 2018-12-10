package com.busycount.permission.sample;

import android.Manifest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.busycount.permission.PermissionListener;
import com.busycount.permission.PermissionUtil;
import com.busycount.rvadapter.BaseRvAdapter;
import com.busycount.rvadapter.BaseRvHolder;

/**
 * RvAdapter
 * <p>
 * 2018/12/10 | Count.C | Created
 */
public class RvAdapter extends BaseRvAdapter<Integer> {
    @Override
    public BaseRvHolder<Integer> onExtCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RvHolder(viewGroup);
    }

    public class RvHolder extends BaseRvHolder<Integer> {

        Button button;
        TextView textView;


        public RvHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
            textView = itemView.findViewById(R.id.textView);
            button = itemView.findViewById(R.id.button4);
        }

        @Override
        public void onBindData(Integer integer) {
            textView.setText("i " + integer);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    request();
                }
            });
        }

        private void request() {
            PermissionUtil.request(getContext(),
                                   new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS},
                                   new PermissionListener() {
                                       @Override
                                       public void onGranted() {
                                           Toast.makeText(getContext(), "onGranted", Toast.LENGTH_SHORT).show();
                                       }

                                       @Override
                                       public void onDenied(String[] denied) {
                                           StringBuilder builder = new StringBuilder();
                                           for (String str : denied) {
                                               builder.append(str).append(" , ");
                                           }
                                           Log.d("console", "onDenied: " + builder.toString());
                                           Toast.makeText(getContext(), "onDenied " + builder.toString(), Toast.LENGTH_SHORT).show();
                                       }
                                   });
        }
    }
}
