package br.com.rescue_bots_android.bluetooth.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.rescue_bots_android.R;
import br.com.rescue_bots_android.sqlite.entity.Path;

public class RouteListAdapter  extends BaseAdapter {
	Context context;
	List<Path> list;
	
	public RouteListAdapter(Context context, List<Path> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		if (list != null) {
			return list.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int location) {
		if (list != null) {
			return list.get(location);
		} else {
			return 0;
		}
	}

	@Override
	public long getItemId(int location) {
		if (list != null) {
			return list.get(location).getId();
		} else {
			return 0;
		}
	}
	public void add(Path bean){
		list.add(bean);
	}
	public void clearItems(){
		list.clear();;
	}
	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		try {
			Path bean = (Path) list.get(position);
			view = View.inflate(context, R.layout.activity_route_item, null);
			if (bean != null) {
				((TextView) view.findViewById(R.id.textview_lista_latitude))
						.setText((bean.getLatitude() != null) ? bean.getLatitude(): "");

				

				((TextView) view.findViewById(R.id.textview_lista_longitude))
				.setText((bean.getLongitude() != null) ? bean.getLongitude(): "");

				
				
				
				String status = bean.getFound();
				if(status!=null){
					if(status.equalsIgnoreCase("OK")){
						((ImageView)view.findViewById(R.id.imageView_lista_status))
						.setImageDrawable( context.getResources().getDrawable(R.drawable.marker_okfound));
					}else if(status.equalsIgnoreCase("DO")){
						((ImageView)view.findViewById(R.id.imageView_lista_status))
						.setImageDrawable( context.getResources().getDrawable(R.drawable.marker_dofound));
					}else if(status.equalsIgnoreCase("NO")){
						((ImageView)view.findViewById(R.id.imageView_lista_status))
						.setImageDrawable( context.getResources().getDrawable(R.drawable.marker_nofound));
					}
				}else{
					((ImageView)view.findViewById(R.id.imageView_lista_status))
					.setImageDrawable( context.getResources().getDrawable(R.drawable.marker_nofound));
				}
				
			}
		} catch (Exception e) {
			Log.e("ERROR", e.getLocalizedMessage(), e);
		}
		return view;
	}
	

}
