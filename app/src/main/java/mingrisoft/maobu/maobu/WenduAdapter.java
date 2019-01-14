package mingrisoft.maobu.maobu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by maobu on 2017/3/29.
 */

public class WenduAdapter extends ArrayAdapter<Wendu> {

    private int resourceId;

    public WenduAdapter(Context context, int textViewResourceId,
                        List<Wendu> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertview, ViewGroup parent){
       Wendu wendu=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertview==null){
            view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.wenduName=(TextView)view.findViewById(R.id.wendu_name);
            viewHolder.wenduImage=(ImageView)view.findViewById(R.id.wendu_image);
            view.setTag(viewHolder);//存储viewholder
        }else{
            view=convertview;
            viewHolder=(ViewHolder)view.getTag();//重新获取viewholder
        }
        viewHolder.wenduImage.setImageResource(wendu.getImageid());
        viewHolder.wenduName.setText(wendu.getName());
        return view;

    }
    class ViewHolder{
        TextView wenduName;
        ImageView wenduImage;
    }


}
