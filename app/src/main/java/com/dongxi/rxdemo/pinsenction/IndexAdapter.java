package com.dongxi.rxdemo.pinsenction;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.dongxi.rxdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luhao on 2016/6/3.
 */
public class IndexAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter, SectionIndexer {
    private static final String TAG = "IndexAdapter";
    private static final int[] COLORS = new int[]{
            R.color.green_light, R.color.orange_light,
            R.color.blue_light, R.color.red_light};
    private List<CityBean> data;
    private List<Item> items;//这个才是真正显示的list
    private Context context;
    private Item[] sections;//头标记数组


    private onSectionClickListener onSectionClickListener;
    public interface onSectionClickListener{
        void sectionClick(int type,int position) ;
    }
    public void setOnSectionClickListener(onSectionClickListener listener){
        this.onSectionClickListener = listener ;
    }



    public IndexAdapter(Context context, List<CityBean> data) {
        this.data = data;
        this.context = context;
        initSection();
    }

    //初始化头信息
    private void initSection() {
        items = new ArrayList<>();
        prepareSections(data.size());
        for (int i = 0; i < data.size(); i++) {
            //添加头信息
            Item section = new Item(Item.SECTION, data.get(i));
            section.sectionPosition = i;
            section.listPosition = data.get(i).getCityId();
            onSectionAdded(section, section.sectionPosition);
            items.add(section);
            //当前城市的下级城市
            for (int j = 0; j < data.get(i).getSubordinateList().size(); j++) {
                Item item = new Item(Item.ITEM, data.get(i).getSubordinateList().get(j));
                item.sectionPosition = i;
                item.listPosition = data.get(i).getSubordinateList().get(j).getCityId();
                items.add(item);
            }
        }
    }

    //当前view是否属于固定的item
    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == Item.SECTION;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).type;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        View v;
        ViewHolder vh;
        GroupViewHolder gvh;
        Item item = items.get(position);// 从集合中获取当前行的数据

        // 从ViewHolder缓存的控件中改变控件的值
        // 这里主要是避免多次强制转化目标对象而造成的资源浪费

        if (item.type == Item.SECTION) {
            // 分组
            if (view == null) {
                // 加载行布局文件
                v = View.inflate(context, R.layout.group_item, null);
                gvh = new GroupViewHolder();

                gvh.tvGroupName = (TextView) v.findViewById(R.id.tv);

                v.setTag(gvh);
            } else {
                v = view;
                gvh = (GroupViewHolder) view.getTag();
            }
            // 数据填充
            v.setBackgroundColor(parent.getResources().getColor(COLORS[item.sectionPosition % COLORS.length]));

            Log.e(TAG, "getView: position=="+items.get(position).sectionPosition);
            if (items.get(position).sectionPosition == 0){
                // 精彩
                gvh.tvGroupName.setText("精彩");
            }else if (items.get(position).sectionPosition == 1){
                // 全部
                gvh.tvGroupName.setText("全部");
            }
//            gvh.tvGroupName.setText("你是好人");
            gvh.tvGroupName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onSectionClickListener != null){
                        // 0 表示全部，1 表示精彩
                        onSectionClickListener.sectionClick(items.get(position).sectionPosition,position);
                    }
                }
            });

        }else {
            // 条目
            if (view == null) {
                // 说明当前这一行不是重用的
                // 加载行布局文件，产生具体的一行
                v = View.inflate(context, R.layout.item, null);
                // 创建存储一行控件的对象
                vh = new ViewHolder();
                // 将该行的控件全部存储到vh中
                vh.tvName = (TextView) v.findViewById(R.id.text_text);
                v.setTag(vh);// 将vh存储到行的Tag中
            } else {
                v = view;
                // 取出隐藏在行中的Tag--取出隐藏在这一行中的vh控件缓存对象
                vh = (ViewHolder) view.getTag();
            }

            vh.tvName.setText(item.getCityBean().getCityName());

            vh.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "item", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return v;
    }

    protected void prepareSections(int sectionsNumber) {
        sections = new Item[sectionsNumber];
    }

    protected void onSectionAdded(Item section, int sectionPosition) {
        sections[sectionPosition] = section;
    }


    @Override
    public Item[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if (sectionIndex >= sections.length) {
            sectionIndex = sections.length - 1;
        }
        return sections[sectionIndex].listPosition;
    }

    @Override
    public int getSectionForPosition(int position) {
        if (position >= getCount()) {
            position = getCount() - 1;
        }
        return getItem(position).sectionPosition;
    }

    // 存储一行中的控件（缓存作用）---避免多次强转每行的控件
    class ViewHolder {
        TextView tvName;
    }
    class GroupViewHolder {
        TextView tvGroupName;
    }
}
