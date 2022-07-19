package tw.com.fasttrackspending;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class adapter extends BaseAdapter {
    Context c;
    ArrayList<SingleRow>  arrayList;
    adapter(Context c){
            this.c=c;
            arrayList = new ArrayList<>();
            Resources res = c.getResources();
            String[] names = res.getStringArray(R.array.country_name);
            int[] image ={R.drawable.myfood,R.drawable.myfood,R.drawable.myfood,R.drawable.myfood,R.drawable.myfood,R.drawable.myfood,R.drawable.myfood};
            for(int i =0;i<names.length;i++){
                arrayList.add(new SingleRow(names[i],image[i]));
            }
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row =inflater.inflate(R.layout.mylistview2,viewGroup,false);
        TextView t1 = row.findViewById(R.id.textView);
        ImageView i1   =row.findViewById(R.id.imageView);
        return row;
    }
}
