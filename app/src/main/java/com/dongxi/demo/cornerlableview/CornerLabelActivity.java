package com.dongxi.demo.cornerlableview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dongxi.demo.R;
import com.dongxi.demo.cornerlableview.drawbale.Adapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CornerLabelActivity extends AppCompatActivity {
    private static final String TAG = "CornerLabelActivity";

    @BindView(R.id.text_corner_label_change)
    TextView mTextCornerLabelChange;
    @BindView(R.id.grid_view)
    GridView mGridView;
    @BindView(R.id.corner_label_view)
    CornerLabelView mCornerLabelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corner_label);
        ButterKnife.bind(this);

        mGridView.setNumColumns(2);
        mGridView.setAdapter(new Adapter(this));
    }

    @OnClick(R.id.text_corner_label_change)
    public void onViewClicked() {
        // 顺时针旋转
        int position = mCornerLabelView.getPosition();  // 0：右上角、1：右下角、2：左下角、3：左上角
        RelativeLayout.LayoutParams relativeLayout = (RelativeLayout.LayoutParams) mCornerLabelView.getLayoutParams();
        Log.e(TAG, "onViewClicked: position=="+position);
        if (position == 0){
            // 右上角--->右下角
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            mCornerLabelView.setLayoutParams(relativeLayout);
            mCornerLabelView.setPosition(1);
//            mCornerLabelView.invalidate();
        }else if (position == 1){
            // 右下角--->左下角

            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            mCornerLabelView.setLayoutParams(relativeLayout);
            mCornerLabelView.setPosition(2);
//            mCornerLabelView.invalidate();
        }else if (position == 2){
            // 左下角--->左上角
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            mCornerLabelView.setLayoutParams(relativeLayout);
            mCornerLabelView.setPosition(3);
//            mCornerLabelView.invalidate();
        }else if (position == 3){
            // 左上角--->右上角
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            mCornerLabelView.setLayoutParams(relativeLayout);
            mCornerLabelView.setPosition(0);
//            mCornerLabelView.invalidate();
        }
    }
}
