package app.rk.food.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.rk.food.R;
import app.rk.food.model.FoodData;
import app.rk.food.utils.Log;
import app.rk.food.utils.Utility;
import app.rk.food.view.activity.MainActivity;
import app.rk.food.view.widget.QuantityPicker;

/**
 *
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{
    private Activity mActivity;
    private List<FoodData> mFoodDataList;
    private TextView textViewCartItem;
    private String TAG=getClass().getSimpleName();

    public FoodAdapter( Activity mActivity, List<FoodData> foodDataList) {
        this.mActivity = mActivity;
        this.mFoodDataList = foodDataList;
        textViewCartItem=(TextView) mActivity.findViewById(R.id.textViewCartItem);
        textViewCartItem.setText(String.valueOf(MainActivity.mCartItemList.size()));
    }
    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View flatItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_food_item, parent, false);
        return new FoodViewHolder(flatItem);
    }

    @Override
    public void onBindViewHolder(final FoodViewHolder holder, int position) {
        try {
            final FoodData mFoodData=mFoodDataList.get(position);
//            Log.e(TAG, "onBindViewHolder: "+MainActivity.mCartItemList.size()+":"+MainActivity.mCartItemList.contains(mFoodData));
//            Log.e(TAG, "onBindViewHolder: "+MainActivity.mCartItemList.contains(mFoodData));
//            if(!MainActivity.mCartItemList.contains(mFoodData)) {
//                holder.linearLayoutAddToCart.setBackgroundResource(R.drawable.rounded_button_added_to_cart);
//            } else {
//                holder.linearLayoutAddToCart.setBackgroundResource(R.drawable.rounded_button_cart);
//            }
            final String mFoodName = mFoodData.getFoodName();
            String mFoodImage = mFoodData.getFoodImgURL();
            String mFoodDescription = mFoodData.getFoodDescription();
            Double mFoodPrice = mFoodData.getFoodPrice();

            holder.textViewFoodName.setText(mFoodName);
            holder.textViewFoodDescription.setText(mFoodDescription);
            holder.textViewFoodPrice.setText(String.valueOf(mFoodPrice));
            holder.cardViewFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        new Utility(mActivity).toast(mActivity.getString(R.string.working_on_it));

                }
            });
            holder.linearLayoutAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!MainActivity.mCartItemList.contains(mFoodData)) {
                        FoodData _foodDate=new FoodData();
                        _foodDate.setFoodId(mFoodData.getFoodId());
                        _foodDate.setFoodName(mFoodData.getFoodName());
                        _foodDate.setFoodType(mFoodData.getFoodType());
                        _foodDate.setFoodImgURL(mFoodData.getFoodImgURL());
                        _foodDate.setFoodDescription(mFoodData.getFoodDescription());
                        _foodDate.setFoodPrice(mFoodData.getFoodPrice());
                        _foodDate.setFoodQuantity(holder.quantityPicker.getQuantity());
                        Log.d(TAG, "onClick: "+_foodDate.getFoodQuantity());
                        MainActivity.mCartItemList.add(_foodDate);
                        holder.linearLayoutAddToCart.setBackgroundResource(R.drawable.rounded_button_added_to_cart);
                    }else {
                        MainActivity.mCartItemList.remove(mFoodData);
                        holder.linearLayoutAddToCart.setBackgroundResource(R.drawable.rounded_button_cart);
                    }
                    textViewCartItem.setText(String.valueOf(MainActivity.mCartItemList.size()));
                }
            });
            Glide.with(mActivity)
                    .load(mFoodImage)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .into(holder.imageViewFood);

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

    public  class FoodViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewFood;
        TextView textViewFoodName;
        ImageView imageViewFood;
        TextView textViewFoodDescription;
        TextView textViewFoodPrice;
        LinearLayout linearLayoutAddToCart;
        QuantityPicker quantityPicker;
        public FoodViewHolder(View itemView) {
            super(itemView);
            cardViewFood=(CardView)itemView.findViewById(R.id.cardViewFood);
            textViewFoodName = (TextView) itemView.findViewById(R.id.textViewFoodName);
            imageViewFood = (ImageView) itemView.findViewById(R.id.imageViewFood);
            textViewFoodDescription = (TextView) itemView.findViewById(R.id.textViewFoodDescription);
            textViewFoodPrice = (TextView) itemView.findViewById(R.id.textViewFoodPrice);
            linearLayoutAddToCart = (LinearLayout) itemView.findViewById(R.id.linearLayoutAddToCart);
            quantityPicker=(QuantityPicker)itemView.findViewById(R.id.quantityPicker);
        }
    }
}
