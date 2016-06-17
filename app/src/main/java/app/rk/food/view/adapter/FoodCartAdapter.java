package app.rk.food.view.adapter;

import android.app.Activity;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.rk.food.R;
import app.rk.food.model.FoodData;
import app.rk.food.utils.Log;
import app.rk.food.utils.Utility;
import app.rk.food.view.activity.MainActivity;

/**
 *
 */
public class FoodCartAdapter extends RecyclerView.Adapter<FoodCartAdapter.FoodCartViewHolder> {
    Activity mActivity;
    List<FoodData> mFoodDataList;
    OnDataChangeListener mOnDataChangeListener;
    public FoodCartAdapter(Activity mActivity, List<FoodData> foodDataList) {
        this.mActivity = mActivity;
        this.mFoodDataList = foodDataList;
    }

    @Override
    public FoodCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View flatItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_food_cart_item, parent, false);
        return new FoodCartViewHolder(flatItem);
    }

    @Override
    public void onBindViewHolder(final FoodCartViewHolder holder, int position) {
        try {
            final FoodData mFoodData = mFoodDataList.get(position);
            String mFoodName = mFoodData.getFoodName();
            String mFoodImage = mFoodData.getFoodImgURL();
//            String mFoodDescription = mFoodData.getFoodDescription();
            Double mFoodPrice = mFoodData.getFoodPrice();

            holder.textViewCartFoodName.setText(mFoodName);
            holder.textViewFoodPrice.setText(String.valueOf(mFoodPrice));
            holder.cardViewCartFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Utility(mActivity).toast(mActivity.getString(R.string.working_on_it));
                }
            });
            holder.imageButtonClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFoodDataList.remove(mFoodData);
                    notifyDataSetChanged();
                    doUpadteActions();
                }
            });
            Glide.with(mActivity)
                    .load(mFoodImage)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .into(holder.imageViewCartFood);

            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            int _04 = (int) mActivity.getResources().getDimension(R.dimen._04);
            if (mFoodDataList.size() == (position + 1)) {
                mlp.setMargins(_04, _04, _04, (int) mActivity.getResources().getDimension(R.dimen._20));
            } else {
                mlp.setMargins(_04, _04, _04, 0);
            }
        } catch (Exception e) {
            Log.printStackTrace(e);
        }
    }

    @Override
    public int getItemCount() {
        return (null != mFoodDataList ? mFoodDataList.size() : 0);
    }

    public class FoodCartViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewCartFood;
        TextView textViewCartFoodName;
        ImageView imageViewCartFood;
        TextView textViewFoodPrice;
        TextView textViewQuantity;
        ImageButton imageButtonClose;

        public FoodCartViewHolder(View itemView) {
            super(itemView);
            cardViewCartFood = (CardView) itemView.findViewById(R.id.cardViewCartFood);
            textViewCartFoodName = (TextView) itemView.findViewById(R.id.textViewCartFoodName);
            imageViewCartFood = (ImageView) itemView.findViewById(R.id.imageViewCartFood);
            imageButtonClose = (ImageButton) itemView.findViewById(R.id.imageButtonClose);
            textViewQuantity = (TextView) itemView.findViewById(R.id.textViewQuantity);
            textViewFoodPrice = (TextView) itemView.findViewById(R.id.textViewFoodPrice);
        }
    }
    private void doUpadteActions() {
        if(mOnDataChangeListener != null){
            mOnDataChangeListener.onDataChanged(mFoodDataList.size());
        }
    }

    public void setOnDataChangeListener(OnDataChangeListener onDataChangeListener){
        mOnDataChangeListener = onDataChangeListener;
    }
    public interface OnDataChangeListener {
         void onDataChanged(int size);
    }
}